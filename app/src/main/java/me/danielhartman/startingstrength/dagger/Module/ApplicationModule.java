package me.danielhartman.startingstrength.dagger.module;

import javax.annotation.security.PermitAll;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.danielhartman.startingstrength.ui.accountManagement.LoginPresenter;
import me.danielhartman.startingstrength.ui.createWorkout.CreateWorkoutPresenter;
import me.danielhartman.startingstrength.ui.viewWorkout.ViewWorkoutPresenter;

@Module
public class ApplicationModule {

    @Provides
    @Singleton
    CreateWorkoutPresenter getWorkoutPresenter(LoginPresenter loginPresenter){
        return new CreateWorkoutPresenter(loginPresenter);
    }
    @Provides
    @Singleton
    LoginPresenter getLoginPresenter(){
        return new LoginPresenter();
    }
    @Provides
    @Singleton
    ViewWorkoutPresenter getViewWorkoutPresenter(LoginPresenter loginPresenter){
        return new ViewWorkoutPresenter(loginPresenter);
    }

}
