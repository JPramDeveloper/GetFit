package me.danielhartman.startingstrength.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.dagger.Component.CreateWorkoutComponent;
import me.danielhartman.startingstrength.dagger.Component.DaggerCreateWorkoutComponent;

public class MainActivity extends AppCompatActivity {

    CreateWorkoutComponent mCreateWorkoutComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new MainMenu_Fragment())
                .commit();
    }

    public void createCreateWorkoutComponent(){
        mCreateWorkoutComponent = DaggerCreateWorkoutComponent.builder().build();
    }
    public CreateWorkoutComponent getCreateWorkoutComponent(){
        if (mCreateWorkoutComponent==null){
            createCreateWorkoutComponent();
        }
        return mCreateWorkoutComponent;
    }
}
