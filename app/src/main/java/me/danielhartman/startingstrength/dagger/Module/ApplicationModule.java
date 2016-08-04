package me.danielhartman.startingstrength.dagger.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.danielhartman.startingstrength.ui.accountManagement.LoginPresenter;
import me.danielhartman.startingstrength.ui.createWorkout.CreateWorkoutPresenter;

@Module
public class ApplicationModule {

    @Provides
    @Singleton
    CreateWorkoutPresenter getWorkoutPresenter(){
        return new CreateWorkoutPresenter();
    }
    @Provides
    @Singleton
    LoginPresenter getLoginNetworkCalls(){
        return new LoginPresenter();
    }

}
