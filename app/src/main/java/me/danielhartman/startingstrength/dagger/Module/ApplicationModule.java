package me.danielhartman.startingstrength.dagger.module;

import android.provider.ContactsContract;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.danielhartman.startingstrength.network.DataGetter;
import me.danielhartman.startingstrength.ui.accountManagement.LoginPresenter;
import me.danielhartman.startingstrength.ui.createWorkout.CreateWorkoutPresenter;
import me.danielhartman.startingstrength.ui.startWorkout.ChooseWorkoutFragmentPresenter;
import me.danielhartman.startingstrength.ui.viewWorkout.ViewWorkoutPresenter;
import me.danielhartman.startingstrength.util.AlertUtil;

@Module
public class ApplicationModule {

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
    ViewWorkoutPresenter getViewWorkoutPresenter(LoginPresenter loginPresenter, DataGetter dataGetter) {
        return new ViewWorkoutPresenter(loginPresenter, dataGetter);
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

}
