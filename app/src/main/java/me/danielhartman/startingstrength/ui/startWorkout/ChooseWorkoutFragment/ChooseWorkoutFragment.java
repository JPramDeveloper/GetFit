package me.danielhartman.startingstrength.ui.startWorkout.ChooseWorkoutFragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.dagger.DaggerHolder;
import me.danielhartman.startingstrength.databinding.ChooseWorkoutFragmentBinding;
import me.danielhartman.startingstrength.model.Workout;
import me.danielhartman.startingstrength.ui.accountManagement.LoginActivity;
import me.danielhartman.startingstrength.ui.base.BaseFragment;
import me.danielhartman.startingstrength.ui.base.BasePresenter;
import me.danielhartman.startingstrength.ui.viewWorkout.ViewWorkoutAdapter;


public class ChooseWorkoutFragment extends BaseFragment<ChooseWorkoutContract.View> implements ChooseWorkoutContract.View, ViewWorkoutAdapter.Callback{

    ChooseWorkoutFragmentPresenter presenter;

    ChooseWorkoutFragmentBinding binding;

    ViewWorkoutAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.choose_workout_fragment,container,false);
        presenter = DaggerHolder.getInstance().component().getChooseWorkoutFragmentPresenter();
        setupRecyclerView();
        return binding.getRoot();
    }

    public void setupRecyclerView() {
        adapter = new ViewWorkoutAdapter(this, getContext());
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
    }

    @Override
    public void initDagger() {
        DaggerHolder.getInstance().component().inject(this);
    }

    @Override
    public BasePresenter<ChooseWorkoutContract.View> getPresenter() {
        return presenter;
    }

    @Override
    public ChooseWorkoutContract.View getContractView() {
        return this;
    }

    @Override
    public void updateData(List<Workout> list) {
        adapter.setData(list);
    }

    @Override
    public void noUserIdFound() {
        Intent i = new Intent(getContext(), LoginActivity.class);
        getActivity().startActivity(i);
    }

    @Override
    public void onClick(Workout workout) {
        Toast.makeText(getContext(),"Test", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.loadWorkouts();
    }
}
