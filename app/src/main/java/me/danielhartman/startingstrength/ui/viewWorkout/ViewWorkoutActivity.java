package me.danielhartman.startingstrength.ui.viewWorkout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.danielhartman.startingstrength.R;

public class ViewWorkoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(R.id.container, new ViewWorkoutFragment()).commit();
    }
}
