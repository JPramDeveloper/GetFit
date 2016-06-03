package me.danielhartman.startingstrength.Ui.ViewWorkout;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import me.danielhartman.startingstrength.R;

public class ViewWorkout_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabactivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if(fab!=null) {
            fab.setOnClickListener((v) -> {
                String message = "";
                Fragment currentFrag = getSupportFragmentManager().findFragmentById(R.id.container);
                if (currentFrag instanceof ViewPlan_Fragment){
                    message = "View Plan Frag";
                }else if (currentFrag instanceof ViewWorkout_Fragment){
                    message = "View Workout";
                }
                Snackbar.make(v, message, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            });
        }
        getSupportFragmentManager().beginTransaction().add(R.id.container,new ViewWorkout_Fragment()).commit();
    }

}
