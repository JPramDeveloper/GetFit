package me.danielhartman.startingstrength.ui.startWorkout;

import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import me.danielhartman.startingstrength.databinding.ChooseWorkoutFragmentBinding;
import me.danielhartman.startingstrength.model.Workout;
import me.danielhartman.startingstrength.network.DataGetter;
import me.danielhartman.startingstrength.network.DataGetterCallback;
import me.danielhartman.startingstrength.ui.accountManagement.LoginPresenter;
import me.danielhartman.startingstrength.ui.base.Presenter;

public class ChooseWorkoutFragmentPresenter extends Presenter implements DataGetterCallback{

    private ChooseWorkoutFragmentBinding binding;
    private ChooseWorkoutAdapter adapter;
    private Fragment fragment;
    private DataGetter dataGetter;
    private LoginPresenter loginPresenter;

    public ChooseWorkoutFragmentPresenter(DataGetter dataGetter, LoginPresenter loginPresenter) {
        this.dataGetter = dataGetter;
        this.loginPresenter = loginPresenter;
    }

    @Override
    public void onResume() {
        if (adapter == null) {
            setupRecycler();
        }
        String uid = loginPresenter.getUserUID(fragment.getActivity());
        if (uid!=null) {
            dataGetter.getUserWorkouts(this,uid);
        }
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        binding = null;
        adapter = null;
        fragment = null;
    }

    void present(ChooseWorkoutFragmentBinding binding, Fragment fragment) {
        this.binding = binding;
        this.fragment = fragment;
    }

    void setupRecycler() {
        adapter = new ChooseWorkoutAdapter();
        binding.recyclerView.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(fragment.getActivity().getApplicationContext(), 1);
        binding.recyclerView.setLayoutManager(layoutManager);
    }

    void updateData(List<Workout> list) {
        adapter.setData(list);
    }


    @Override
    public void returnWorkoutList(List<Workout> list) {
        adapter.setData(list);
    }

    @Override
    public void detatchListener() {
        dataGetter.detachListener();
    }
}
