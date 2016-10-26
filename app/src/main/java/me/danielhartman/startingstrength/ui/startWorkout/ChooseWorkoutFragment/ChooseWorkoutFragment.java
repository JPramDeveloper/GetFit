package me.danielhartman.startingstrength.ui.startWorkout.ChooseWorkoutFragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.tool.util.L;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.dagger.DaggerHolder;
import me.danielhartman.startingstrength.databinding.ChooseWorkoutFragmentBinding;
import me.danielhartman.startingstrength.model.Workout;
import me.danielhartman.startingstrength.ui.accountManagement.AccountActivity;
import me.danielhartman.startingstrength.ui.base.BaseFragment;
import me.danielhartman.startingstrength.ui.base.BasePresenter;
import me.danielhartman.startingstrength.ui.base.Presenter;
import me.danielhartman.startingstrength.ui.startWorkout.ChooseWorkoutAdapter;
import me.danielhartman.startingstrength.ui.viewWorkout.ViewWorkoutAdapter;


public class ChooseWorkoutFragment extends BaseFragment<ChooseWorkoutContract.View> implements ChooseWorkoutContract.View, ViewWorkoutAdapter.Callback{

    @Inject
    ChooseWorkoutFragmentPresenter presenter;

    ChooseWorkoutFragmentBinding binding;

    ViewWorkoutAdapter adapter;
    

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.choose_workout_fragment,container,false);
        DaggerHolder.getInstance().component().inject(this);
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            //noinspection ConstantConditions
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Choose a Workout");
        }
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
        Intent i = new Intent(getContext(), AccountActivity.class);
        getActivity().startActivity(i);
    }

    @Override
    public void onClick(Workout workout) {
        Toast.makeText(getContext(),"Test", Toast.LENGTH_LONG).show();
    }
}
