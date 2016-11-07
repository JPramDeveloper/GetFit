package me.danielhartman.startingstrength.ui.createWorkout;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.util.HashMap;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.danielhartman.startingstrength.Interfaces.CreateWorkoutCallback;
import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.dagger.DaggerHolder;
import me.danielhartman.startingstrength.ui.MainActivity;

public class CreateWorkoutActivity extends AppCompatActivity implements CreateWorkoutCallback {

    final int FIRST_DAY = 0;

    private static final String TAG = CreateWorkoutActivity.class.getSimpleName();
    @BindView(R.id.exerciseFrame)
    FrameLayout mExerciseFrame;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @Inject
    CreateWorkoutPresenter presenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    CreateWorkoutVPAdapter VPAdapter;
    CreateWorkoutDay currentDay;
    private boolean isRequestPending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_workout_activity);
        ButterKnife.bind(this);
        DaggerHolder.getInstance().component().inject(this);
        getSupportFragmentManager().beginTransaction().add(R.id.exerciseFrame, new CreateExerciseFragment()).commit();
        setSupportActionBar(toolbar);
        setupViewpager();
        presenter.setCurrentDay(FIRST_DAY);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(presenter.getWorkout().getName());
        }
        presenter.setFirstRun(true);
        presenter.setExerciseFrameAndButton(mExerciseFrame, fab);
    }

    public void setupViewpager() {
        VPAdapter = new CreateWorkoutVPAdapter(getSupportFragmentManager());
        viewPager.setAdapter(VPAdapter);
        setViewPagerEvents();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.material_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (isRequestPending) return true;

        if (item.getItemId() == R.id.action_finish) {
            progressBar.setVisibility(View.VISIBLE);
            try {
                presenter.commitToFirebase(this, this);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            isRequestPending = true;
        }
        return true;
    }

    public void setViewPagerEvents() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }

            @Override
            public void onPageSelected(int index) {
                currentDay = VPAdapter.fragMap.get(index);
                presenter.onPageSelected(currentDay, index);
                Log.d(TAG, "onPageSelected: " + String.valueOf(index));
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (presenter.getExerciseFrameVisiblity() == View.GONE) {
            super.onBackPressed();
        } else {
            presenter.hideExerciseFrame();
        }
    }

    @Override
    public void onUploadComplete() {
        isRequestPending = false;
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, "Workout Created Successfully", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    @Override
    public void onImageUploadError(String message) {
        isRequestPending = false;
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onWorkoutUploadError(String message) {
        isRequestPending = false;
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.fab)
    public void onFABClick() {
        presenter.onFabClick();
    }

    protected class CreateWorkoutVPAdapter extends FragmentStatePagerAdapter {

        private final int NUMBER_OF_DAY = 7;

        public HashMap<Integer, CreateWorkoutDay> fragMap = new HashMap<>();

        public CreateWorkoutVPAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return CreateWorkoutDay.newInstance(position);
        }

        @Override
        public int getCount() {
            return NUMBER_OF_DAY;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Day " + String.valueOf(position + 1);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            CreateWorkoutDay fragment = (CreateWorkoutDay) super.instantiateItem(container, position);
            fragMap.put(position, fragment);
            Log.d(TAG, "instantiateItem: put in map id : " + String.valueOf(position));
            return fragment;
        }
    }
}
