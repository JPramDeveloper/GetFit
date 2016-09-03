package me.danielhartman.startingstrength.ui;

import android.app.Application;

import me.danielhartman.startingstrength.dagger.DaggerHolder;
import me.danielhartman.startingstrength.dagger.component.ApplicationComponent;
import me.danielhartman.startingstrength.dagger.component.DaggerApplicationComponent;

public class MyApplication extends Application {
    private ApplicationComponent mApplicationComponent;

    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder().build();
        DaggerHolder.getInstance().setComponent(mApplicationComponent);
    }

    public ApplicationComponent getViewWorkoutComponent() {
        return mApplicationComponent;
    }
}





