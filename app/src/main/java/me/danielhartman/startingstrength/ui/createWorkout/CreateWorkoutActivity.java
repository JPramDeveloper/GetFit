package me.danielhartman.startingstrength.ui.createWorkout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Choreographer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.HashMap;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.dagger.DaggerHolder;

public class CreateWorkoutActivity extends AppCompatActivity {

    private static final String TAG = CreateWorkoutActivity.class.getSimpleName();
    @BindView(R.id.exerciseFrame)FrameLayout mExerciseFrame;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Inject
    CreateWorkoutPresenter presenter;

    @BindView(R.id.toolbar)Toolbar toolbar;

    CreateWorkoutVPAdapter VPAdapter;

    CreateWorkoutDay currentDay;
    CreateWorkoutDay previousDay;
    CreateWorkoutDay nextDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_workout_activity);
        ButterKnife.bind(this);
        DaggerHolder.getInstance().component().inject(this);
//        getSupportFragmentManager().beginTransaction().add(R.id.container,new CreateWorkoutName()).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.exerciseFrame, new CreateExerciseFragment()).commit();
        setSupportActionBar(toolbar);
        mExerciseFrame.setVisibility(View.GONE);
        VPAdapter = new CreateWorkoutVPAdapter(getSupportFragmentManager());
        viewPager.setAdapter(VPAdapter);
        setViewPagerEvents();
        presenter.setCurrentDay(0);
        getSupportActionBar().setTitle("Create Workout");
        currentDay = VPAdapter.fragMap.get(0);
    }
    public void setViewPagerEvents(){
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int index) {
                presenter.setCurrentDay(index);
                currentDay = VPAdapter.fragMap.get(index);
                presenter.setCurrentDayAdapter(currentDay.getAdapter());
                currentDay.getAdapter().setData(presenter.getSetsForGivenDay(presenter.getCurrentDay()));

                Log.d(TAG, "onPageSelected: " + String.valueOf(index));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public FrameLayout getExerciseFrame() {
        return mExerciseFrame;
    }

    @Override
    public void onBackPressed() {
        if (mExerciseFrame.getVisibility()==View.GONE) {
            super.onBackPressed();
        }else{
            ((CreateWorkoutDay)getSupportFragmentManager().findFragmentById(R.id.container)).setCreateExerciseInvisible();
        }
    }
    protected class CreateWorkoutVPAdapter extends FragmentStatePagerAdapter {
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
            return 7;
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
