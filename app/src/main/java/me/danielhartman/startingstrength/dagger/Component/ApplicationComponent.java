package me.danielhartman.startingstrength.dagger.Component;

import javax.inject.Singleton;

import dagger.Component;
import me.danielhartman.startingstrength.Ui.AccountManagement.AccountActivity;
import me.danielhartman.startingstrength.Ui.AccountManagement.CreateAccount_Fragment;
import me.danielhartman.startingstrength.Ui.AccountManagement.Login_Fragment;
import me.danielhartman.startingstrength.Ui.CreateWorkout.CreateWorkoutName;
import me.danielhartman.startingstrength.Ui.MainMenu_Fragment;
import me.danielhartman.startingstrength.Ui.ViewWorkout.ViewPlan_Fragment;
import me.danielhartman.startingstrength.Ui.ViewWorkout.ViewWorkout_Fragment;
import me.danielhartman.startingstrength.dagger.Module.ApplicationModule;

@Singleton
@Component(modules = { ApplicationModule.class})
public interface ApplicationComponent {

    void inject(ViewWorkout_Fragment fragment);
    void inject(ViewPlan_Fragment fragment);
    void inject(Login_Fragment fragment);
    void inject(CreateAccount_Fragment fragment);
    void inject(AccountActivity activity);
    void inject(CreateWorkoutName fragment);
    void inject(MainMenu_Fragment fragment);

}