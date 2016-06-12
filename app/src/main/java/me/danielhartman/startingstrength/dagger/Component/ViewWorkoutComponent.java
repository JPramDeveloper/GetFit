package me.danielhartman.startingstrength.dagger.Component;

import javax.inject.Singleton;

import dagger.Component;
import me.danielhartman.startingstrength.ui.accountManagement.CreateAccountFragment;
import me.danielhartman.startingstrength.ui.accountManagement.LoginFragment;
import me.danielhartman.startingstrength.ui.viewWorkout.ViewPlan_Fragment;
import me.danielhartman.startingstrength.ui.viewWorkout.ViewWorkout_Fragment;
import me.danielhartman.startingstrength.dagger.Module.ViewWorkoutModule;

@Singleton
@Component(modules = {ViewWorkoutModule.class})
public interface ViewWorkoutComponent {

    void inject(ViewWorkout_Fragment fragment);
    void inject(ViewPlan_Fragment fragment);
    void inject(LoginFragment fragment);
    void inject(CreateAccountFragment fragment);

}
