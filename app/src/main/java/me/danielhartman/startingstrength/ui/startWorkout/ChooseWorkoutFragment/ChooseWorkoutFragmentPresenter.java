package me.danielhartman.startingstrength.ui.startWorkout.ChooseWorkoutFragment;

import java.util.List;

import me.danielhartman.startingstrength.databinding.ChooseWorkoutFragmentBinding;
import me.danielhartman.startingstrength.model.Workout;
import me.danielhartman.startingstrength.network.WorkoutDataStore;
import me.danielhartman.startingstrength.network.DataGetterCallback;
import me.danielhartman.startingstrength.ui.accountManagement.LoginPresenter;
import me.danielhartman.startingstrength.ui.viewWorkout.ViewWorkoutAdapter;

public class ChooseWorkoutFragmentPresenter implements ChooseWorkoutContract.Presenter, DataGetterCallback, ViewWorkoutAdapter.Callback{

    private WorkoutDataStore workoutDataStore;
    private LoginPresenter loginPresenter;
    private ChooseWorkoutContract.View view;

    public ChooseWorkoutFragmentPresenter(WorkoutDataStore workoutDataStore, LoginPresenter loginPresenter) {
        this.workoutDataStore = workoutDataStore;
        this.loginPresenter = loginPresenter;
    }


    @Override
    public void present(ChooseWorkoutContract.View view) {
        this.view = view;
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void returnWorkoutList(List<Workout> list) {
        view.updateData(list);
    }

    @Override
    public void detatchListener() {
        workoutDataStore.detachListener();
    }

    @Override
    public void onClick(Workout workout) {

    }

    @Override
    public void loadWorkouts() {
        String uid = loginPresenter.getUserUID();
        if (uid!=null) {
            workoutDataStore.getUserWorkouts(this,uid);
        }else{
            view.noUserIdFound();
        }
    }

    @Override
    public void workoutSelected() {

    }
}
