package me.danielhartman.startingstrength.util;


import me.danielhartman.startingstrength.dagger.Component.ViewWorkoutComponent;
import me.danielhartman.startingstrength.dagger.Module.ViewWorkoutModule;

public class DaggerHolder {
    private static DaggerHolder sInstance = new DaggerHolder();
    private ViewWorkoutComponent mComponent;
    public static DaggerHolder getInstance() {
        return sInstance;
    }
    private DaggerHolder() {
    }
    public void setComponent(ViewWorkoutComponent component) {
        mComponent = component;
    }
    public ViewWorkoutComponent getComponent() {
        return mComponent;
    }
}