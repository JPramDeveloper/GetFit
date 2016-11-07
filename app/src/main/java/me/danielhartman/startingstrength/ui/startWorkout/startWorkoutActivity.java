package me.danielhartman.startingstrength.ui.startWorkout;

import android.os.Bundle;

import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.ui.base.BaseActivity;
import me.danielhartman.startingstrength.ui.startWorkout.ChooseWorkoutFragment.ChooseWorkoutFragment;

public class StartWorkoutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new ChooseWorkoutFragment())
                .commit();
    }
}
