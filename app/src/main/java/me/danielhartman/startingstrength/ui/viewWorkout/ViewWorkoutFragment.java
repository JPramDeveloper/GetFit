package me.danielhartman.startingstrength.ui.viewWorkout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.dagger.DaggerHolder;

public class ViewWorkoutFragment extends Fragment {

    ViewWorkoutAdapter adapter;

    View v;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Inject
    ViewWorkoutPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.view_workout_fragment, container, false);
        ButterKnife.bind(this, v);
        DaggerHolder.getInstance().component().inject(this);
        presenter.getWorkoutIds();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerViewSetup();
    }

    public void recyclerViewSetup(){
        adapter = new ViewWorkoutAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
    }




}
