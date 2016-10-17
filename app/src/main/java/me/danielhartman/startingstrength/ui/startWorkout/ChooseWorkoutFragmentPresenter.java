package me.danielhartman.startingstrength.ui.startWorkout;

import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import me.danielhartman.startingstrength.databinding.ChooseWorkoutFragmentBinding;
import me.danielhartman.startingstrength.model.Workout;
import me.danielhartman.startingstrength.network.DataGetterCallback;
import me.danielhartman.startingstrength.ui.base.Presenter;

public class ChooseWorkoutFragmentPresenter extends Presenter implements DataGetterCallback{

    private ChooseWorkoutFragmentBinding binding;
    private ChooseWorkoutAdapter adapter;
    private Fragment fragment;

    @Override
    public void onResume() {
        if (adapter == null) {
            setupRecycler();
            testData();
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

    public void testData() {
        List<Workout> test = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Workout w = new Workout();
            w.setName("test" + String.valueOf(i));
            w.setDescription("String");
            test.add(w);
        }
        updateData(test);
    }


    @Override
    public void returnWorkoutList(List<Workout> list) {

    }
}
