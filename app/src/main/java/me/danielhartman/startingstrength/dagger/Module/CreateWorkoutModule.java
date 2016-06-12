package me.danielhartman.startingstrength.dagger.Module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.danielhartman.startingstrength.ui.createWorkout.CreateWorkoutPresenter;

@Module
public class CreateWorkoutModule {

    @Provides
    @Singleton
    CreateWorkoutPresenter getWorkoutPresenter(){
        return new CreateWorkoutPresenter();
    }

}
