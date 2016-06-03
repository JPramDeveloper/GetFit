package me.danielhartman.startingstrength.Ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.dagger.Component.CreateWorkoutComponent;
import me.danielhartman.startingstrength.dagger.Component.DaggerCreateWorkoutComponent;
import me.danielhartman.startingstrength.dagger.Component.DaggerViewWorkoutComponent;

public class MainActivity extends AppCompatActivity {

    CreateWorkoutComponent mCreateWorkoutComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new MainMenu_Fragment())
                .addToBackStack(null)
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
