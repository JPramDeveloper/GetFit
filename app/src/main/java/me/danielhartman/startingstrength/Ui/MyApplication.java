package me.danielhartman.startingstrength.ui;

import android.app.Application;

import me.danielhartman.startingstrength.dagger.DaggerHolder;
import me.danielhartman.startingstrength.dagger.component.ApplicationComponent;
import me.danielhartman.startingstrength.dagger.component.DaggerApplicationComponent;
import me.danielhartman.startingstrength.dagger.module.ApplicationModule;

public class MyApplication extends Application {
    private ApplicationComponent mApplicationComponent;

    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        DaggerHolder.getInstance().setComponent(mApplicationComponent);
    }

    public ApplicationComponent getViewWorkoutComponent() {
        return mApplicationComponent;
    }
}





