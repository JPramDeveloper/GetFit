package me.danielhartman.startingstrength.dagger.Module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.danielhartman.startingstrength.Network.LoginNetworkCalls;
import me.danielhartman.startingstrength.Network.ViewWorkoutNetworkCalls;
import me.danielhartman.startingstrength.Ui.CreateWorkout.CreateWorkoutPresenter;

@Module
public class CreateWorkoutModule {

    @Provides
    @Singleton
    CreateWorkoutPresenter getWorkoutPresenter(){
        return new CreateWorkoutPresenter();
    }

}
