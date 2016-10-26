package me.danielhartman.startingstrength.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import me.danielhartman.startingstrength.dagger.module.ApplicationModule;
import me.danielhartman.startingstrength.ui.MainMenu_Fragment;
import me.danielhartman.startingstrength.ui.accountManagement.AccountActivity;
import me.danielhartman.startingstrength.ui.accountManagement.CreateAccountFragment;
import me.danielhartman.startingstrength.ui.accountManagement.LoginFragment;
import me.danielhartman.startingstrength.ui.createWorkout.CreateExerciseFragment;
import me.danielhartman.startingstrength.ui.createWorkout.CreateWorkoutActivity;
import me.danielhartman.startingstrength.ui.createWorkout.CreateWorkoutDay;
import me.danielhartman.startingstrength.ui.createWorkout.CreateWorkoutName;
import me.danielhartman.startingstrength.ui.startWorkout.ChooseWorkoutFragment.ChooseWorkoutFragment;
import me.danielhartman.startingstrength.ui.startWorkout.StartWorkoutActivity;
import me.danielhartman.startingstrength.ui.viewWorkout.ViewWorkoutDetailsFragment;
import me.danielhartman.startingstrength.ui.viewWorkout.ViewWorkoutFragment;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(LoginFragment fragment);

    void inject(CreateAccountFragment fragment);

    void inject(AccountActivity activity);

    void inject(MainMenu_Fragment fragment);

    void inject(CreateWorkoutDay fragment);

    void inject(CreateExerciseFragment fragment);

    void inject(ViewWorkoutFragment fragment);

    void inject(CreateWorkoutName fragment);

    void inject(CreateWorkoutActivity activity);

    void inject(ChooseWorkoutFragment fragment);

    void inject(StartWorkoutActivity activity);

    void inject(ViewWorkoutDetailsFragment fragment);

}
