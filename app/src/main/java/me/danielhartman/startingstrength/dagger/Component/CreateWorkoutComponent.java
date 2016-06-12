package me.danielhartman.startingstrength.dagger.Component;

import javax.inject.Singleton;

import dagger.Component;
import me.danielhartman.startingstrength.ui.createWorkout.CreateWorkoutFragment;
import me.danielhartman.startingstrength.ui.createWorkout.CreateWorkoutName;
import me.danielhartman.startingstrength.dagger.Module.CreateWorkoutModule;

@Singleton
@Component(modules = {CreateWorkoutModule.class})
public interface CreateWorkoutComponent {

    void inject(CreateWorkoutName fragment);
    void inject(CreateWorkoutFragment fragment);

}
