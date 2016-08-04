package me.danielhartman.startingstrength.dagger;

import me.danielhartman.startingstrength.dagger.Component.ApplicationComponent;

public class DaggerHolder {
    private static DaggerHolder sInstance = new DaggerHolder();
    private ApplicationComponent mComponent;
    public static DaggerHolder getInstance() {
        return sInstance;
    }
    private DaggerHolder() {
    }
    public void setComponent(ApplicationComponent component) {
        mComponent = component;
    }
    public ApplicationComponent component() {
        return mComponent;
    }
}