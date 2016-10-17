package me.danielhartman.startingstrength.dagger.module;

import android.content.Context;
import android.provider.ContactsContract;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.danielhartman.startingstrength.network.DataGetter;
import me.danielhartman.startingstrength.ui.accountManagement.LoginPresenter;
import me.danielhartman.startingstrength.ui.createWorkout.CreateWorkoutPresenter;
import me.danielhartman.startingstrength.ui.startWorkout.ChooseWorkoutFragmentPresenter;
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
    ChooseWorkoutFragmentPresenter getChooseWorkoutPresenter(DataGetter dataGetter, LoginPresenter loginPresenter){
        return new ChooseWorkoutFragmentPresenter(dataGetter, loginPresenter);}


    @Provides
    @Singleton
    LoginPresenter getLoginPresenter(AlertUtil alertUtil) {
        return new LoginPresenter(alertUtil);
    }

    @Provides
    @Singleton
    ViewWorkoutPresenter getViewWorkoutPresenter(LoginPresenter loginPresenter, DataGetter dataGetter, Context context) {
        return new ViewWorkoutPresenter(loginPresenter, dataGetter, context);
    }

    @Provides
    @Singleton
    AlertUtil getAlertUtil() {
        return new AlertUtil();
    }

    @Provides
    @Singleton
    DataGetter getDataGetter(){
        return new DataGetter();
    }

    @Provides
    @Singleton
    ViewWorkoutDetailsPresenter getViewWorkoutDetailsPresenter(){
        return new ViewWorkoutDetailsPresenter();
    }

}
