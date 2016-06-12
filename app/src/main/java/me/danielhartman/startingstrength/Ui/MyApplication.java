package me.danielhartman.startingstrength.ui;

import com.parse.Parse;
import com.parse.ParseObject;

import android.app.Application;

import me.danielhartman.startingstrength.Model.Exercise;
import me.danielhartman.startingstrength.Model.Plan;
import me.danielhartman.startingstrength.Model.Session;
import me.danielhartman.startingstrength.Model.Workout;
import me.danielhartman.startingstrength.dagger.Component.DaggerViewWorkoutComponent;
import me.danielhartman.startingstrength.util.NonPublic;
import me.danielhartman.startingstrength.dagger.Component.ViewWorkoutComponent;



    public class MyApplication extends Application {
    private ViewWorkoutComponent mViewWorkoutComponent;
        public void onCreate(){
            super.onCreate();

            ParseObject.registerSubclass(Workout.class);
            ParseObject.registerSubclass(Plan.class);
            ParseObject.registerSubclass(Session.class);
            ParseObject.registerSubclass(Exercise.class);
            Parse.initialize(this, NonPublic.PARESE_KEY1, NonPublic.PARESE_KEY2);

            mViewWorkoutComponent = DaggerViewWorkoutComponent.builder().build();
        }

    public ViewWorkoutComponent getViewWorkoutComponent() {
        return mViewWorkoutComponent;
    }
}





