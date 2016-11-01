package me.danielhartman.startingstrength.dagger;

import me.danielhartman.startingstrength.dagger.component.ApplicationComponent;

public class DaggerHolder {
    private static DaggerHolder sInstance = new DaggerHolder();
    private ApplicationComponent mComponent;

    private DaggerHolder() {
    }

    public static DaggerHolder getInstance() {
        return sInstance;
    }

    public void setComponent(ApplicationComponent component) {
        mComponent = component;
    }

    public ApplicationComponent component() {
        return mComponent;
    }


}