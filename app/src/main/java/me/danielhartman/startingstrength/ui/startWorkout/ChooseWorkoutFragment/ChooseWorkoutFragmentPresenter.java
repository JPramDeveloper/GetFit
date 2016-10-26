package me.danielhartman.startingstrength.ui.startWorkout.ChooseWorkoutFragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import me.danielhartman.startingstrength.databinding.ChooseWorkoutFragmentBinding;
import me.danielhartman.startingstrength.model.Workout;
import me.danielhartman.startingstrength.network.DataGetter;
import me.danielhartman.startingstrength.network.DataGetterCallback;
import me.danielhartman.startingstrength.ui.accountManagement.AccountActivity;
import me.danielhartman.startingstrength.ui.accountManagement.LoginPresenter;
import me.danielhartman.startingstrength.ui.base.Presenter;
import me.danielhartman.startingstrength.ui.viewWorkout.ViewWorkoutAdapter;

public class ChooseWorkoutFragmentPresenter implements ChooseWorkoutContract.Presenter, DataGetterCallback, ViewWorkoutAdapter.Callback{

    private ChooseWorkoutFragmentBinding binding;
    private DataGetter dataGetter;
    private LoginPresenter loginPresenter;
    ChooseWorkoutContract.View view;

    public ChooseWorkoutFragmentPresenter(DataGetter dataGetter, LoginPresenter loginPresenter) {
        this.dataGetter = dataGetter;
        this.loginPresenter = loginPresenter;
    }


    @Override
    public void present(ChooseWorkoutContract.View view) {
        this.view = view;
    }

    @Override
    public void remove() {

    }

    @Override
    public void onResume() {
        String uid = loginPresenter.getUserUID();
        if (uid!=null) {
            dataGetter.getUserWorkouts(this,uid);
        }else{
            view.noUserIdFound();
        }
    }

    @Override
    public void onPause() {

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
        dataGetter.detachListener();
    }

    @Override
    public void onClick(Workout workout) {

    }

    @Override
    public void loadWorkouts() {

    }

    @Override
    public void workoutSelected() {

    }
}
