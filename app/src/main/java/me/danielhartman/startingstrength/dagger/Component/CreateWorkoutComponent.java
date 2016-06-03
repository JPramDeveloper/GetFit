package me.danielhartman.startingstrength.dagger.Component;

import javax.inject.Singleton;

import dagger.Component;
import me.danielhartman.startingstrength.Ui.AccountManagement.CreateAccount_Fragment;
import me.danielhartman.startingstrength.Ui.AccountManagement.Login_Fragment;
import me.danielhartman.startingstrength.Ui.CreateWorkout.CreateWorkoutName;
import me.danielhartman.startingstrength.Ui.ViewWorkout.ViewPlan_Fragment;
import me.danielhartman.startingstrength.Ui.ViewWorkout.ViewWorkout_Fragment;
import me.danielhartman.startingstrength.dagger.Module.CreateWorkoutModule;
import me.danielhartman.startingstrength.dagger.Module.ViewWorkoutModule;

@Singleton
@Component(modules = {CreateWorkoutModule.class})
public interface CreateWorkoutComponent {

    void inject(CreateWorkoutName fragment);

}
