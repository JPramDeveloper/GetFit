package me.danielhartman.startingstrength.ui.viewWorkout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.danielhartman.startingstrength.R;

public class ViewWorkoutActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        toolbar.setTitle("Test");
        getSupportFragmentManager().beginTransaction().add(R.id.container, new ViewWorkoutFragment()).commit();
    }
}
