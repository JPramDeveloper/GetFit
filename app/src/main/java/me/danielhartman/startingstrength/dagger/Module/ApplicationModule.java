package me.danielhartman.startingstrength.dagger.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.danielhartman.startingstrength.network.WorkoutDataStore;
import me.danielhartman.startingstrength.ui.accountManagement.LoginPresenter;
import me.danielhartman.startingstrength.ui.createWorkout.CreateWorkoutName.CWNPresenter;
import me.danielhartman.startingstrength.ui.createWorkout.CreateWorkoutPresenter;
import me.danielhartman.startingstrength.ui.startWorkout.ChooseWorkoutFragment.ChooseWorkoutFragmentPresenter;
import me.danielhartman.startingstrength.ui.viewWorkout.ViewWorkoutDetailsPresenter;
import me.danielhartman.startingstrength.ui.viewWorkout.ViewWorkoutPresenter;
import me.danielhartman.startingstrength.util.AlertUtil;

@Module
public class ApplicationModule {

    Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    Context providesContext(){
        return context;
    }

    @Provides
    @Singleton
    CreateWorkoutPresenter getWorkoutPresenter(LoginPresenter loginPresenter) {
        return new CreateWorkoutPresenter(loginPresenter);
    }

    @Provides
    @Singleton
    ChooseWorkoutFragmentPresenter getChooseWorkoutPresenter(WorkoutDataStore workoutDataStore, LoginPresenter loginPresenter){
        return new ChooseWorkoutFragmentPresenter(workoutDataStore, loginPresenter);}


    @Provides
    @Singleton
    LoginPresenter getLoginPresenter(AlertUtil alertUtil) {
        return new LoginPresenter(alertUtil);
    }

    @Provides
    @Singleton
    ViewWorkoutPresenter getViewWorkoutPresenter(LoginPresenter loginPresenter, WorkoutDataStore workoutDataStore, Context context) {
        return new ViewWorkoutPresenter(loginPresenter, workoutDataStore, context);
    }

    @Provides
    @Singleton
    AlertUtil getAlertUtil() {
        return new AlertUtil();
    }

    @Provides
    @Singleton
    WorkoutDataStore getDataGetter(){
        return new WorkoutDataStore();
    }

    @Provides
    @Singleton
    ViewWorkoutDetailsPresenter getViewWorkoutDetailsPresenter(){
        return new ViewWorkoutDetailsPresenter();
    }

    @Provides
    @Singleton
    CWNPresenter getCWNPresenter(){
        return new CWNPresenter();
    }
}
