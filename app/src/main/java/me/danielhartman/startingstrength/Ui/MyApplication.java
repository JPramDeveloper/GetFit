package me.danielhartman.startingstrength.Ui;
import android.app.Application;

import me.danielhartman.startingstrength.dagger.Component.ApplicationComponent;
import me.danielhartman.startingstrength.dagger.Component.DaggerApplicationComponent;
import me.danielhartman.startingstrength.dagger.DaggerHolder;

public class MyApplication extends Application {
    private ApplicationComponent mApplicationComponent;
        public void onCreate(){
            super.onCreate();

            mApplicationComponent = DaggerApplicationComponent.builder().build();
            DaggerHolder.getInstance().setComponent(mApplicationComponent);
        }

    public ApplicationComponent getViewWorkoutComponent() {
        return mApplicationComponent;
    }
}





