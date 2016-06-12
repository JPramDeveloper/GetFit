package me.danielhartman.startingstrength.dagger.Module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.danielhartman.startingstrength.Network.LoginNetworkCalls;
import me.danielhartman.startingstrength.Network.ViewWorkoutNetworkCalls;


@Module
public class ViewWorkoutModule {

    @Provides
    @Singleton
    ViewWorkoutNetworkCalls mViewWorkoutController(){
        return new ViewWorkoutNetworkCalls();
    }
    @Provides
    @Singleton
    LoginNetworkCalls getLoginNetworkCalls(){
        return new LoginNetworkCalls();
    }

}
