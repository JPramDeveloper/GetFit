package me.danielhartman.startingstrength.dagger.Module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.danielhartman.startingstrength.Ui.AccountManagement.LoginPresenter;
import me.danielhartman.startingstrength.Ui.CreateWorkout.CreateWorkoutPresenter;

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
