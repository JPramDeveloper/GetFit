package me.danielhartman.startingstrength.ui.startWorkout.ChooseWorkoutFragment;

import java.util.List;

import me.danielhartman.startingstrength.model.Workout;
import me.danielhartman.startingstrength.ui.base.BasePresenter;

public class ChooseWorkoutContract {

    interface View  {
        void updateData(List<Workout> list);
        void noUserIdFound();
    }

    interface Presenter extends BasePresenter<View> {
        void loadWorkouts();

        void workoutSelected();

    }


}
