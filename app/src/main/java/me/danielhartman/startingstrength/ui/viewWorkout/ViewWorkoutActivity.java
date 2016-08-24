package me.danielhartman.startingstrength.ui.viewWorkout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.danielhartman.startingstrength.R;

public class ViewWorkoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(R.id.container, new ViewWorkoutFragment()).commit();
    }
}
