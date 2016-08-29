package me.danielhartman.startingstrength.ui.createWorkout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Choreographer;
import android.view.View;
import android.widget.FrameLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.dagger.DaggerHolder;

public class CreateWorkoutActivity extends AppCompatActivity {

    @BindView(R.id.exerciseFrame)FrameLayout mExerciseFrame;

    @Inject
    CreateWorkoutPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_workout_activity);
        ButterKnife.bind(this);
        DaggerHolder.getInstance().component().inject(this);
        getSupportFragmentManager().beginTransaction().add(R.id.container,new CreateWorkoutName()).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.exerciseFrame, new CreateExerciseFragment()).commit();
        mExerciseFrame.setVisibility(View.GONE);
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
}
