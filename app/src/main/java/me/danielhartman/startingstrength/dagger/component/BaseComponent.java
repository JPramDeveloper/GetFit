package me.danielhartman.startingstrength.dagger.component;

import me.danielhartman.startingstrength.network.LoginManager.Login;
import me.danielhartman.startingstrength.network.WorkoutSaver.Saver;
import me.danielhartman.startingstrength.ui.createWorkout.CreateWorkoutName.CWNPresenter;


public interface BaseComponent {
    CWNPresenter getCWNPresenter();

    Login getLoginManager();

    Saver getWorkoutSaver();
}
