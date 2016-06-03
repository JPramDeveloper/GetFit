package me.danielhartman.startingstrength.Ui.ViewWorkout;


import com.parse.ParseObject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import me.danielhartman.startingstrength.Network.ViewWorkoutNetworkCalls;
import me.danielhartman.startingstrength.Interfaces.ViewWorkoutCallback;
import me.danielhartman.startingstrength.Interfaces.WorkoutSelector;
import me.danielhartman.startingstrength.Model.Plan;
import me.danielhartman.startingstrength.Model.Workout;
import butterknife.ButterKnife;
import me.danielhartman.startingstrength.Ui.MyApplication;
import me.danielhartman.startingstrength.adapter.ViewWorkoutAdapter;
import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.adapter.ViewPlanAdapter;
import me.danielhartman.startingstrength.adapter.util.SimpleSectionedRecyclerViewAdapter;

public class ViewWorkout_Fragment extends Fragment implements ViewWorkoutCallback, WorkoutSelector {

    public ViewWorkoutAdapter mViewWorkoutAdapter;
    public static String SELECTED_WORKOUT = "selectedworkout";
    private RecyclerView recyclerView;
    private View rootView;
    private Fragment mFragment;
    @Inject
    ViewWorkoutNetworkCalls mViewWorkoutNetworkCalls;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.recyclerview, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        ButterKnife.bind(this, rootView);

        ((MyApplication)(getActivity().getApplication())).getViewWorkoutComponent().inject(this);
        mFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.container);

        mViewWorkoutNetworkCalls.fetchWorkouts((ViewWorkoutCallback)mFragment);
        return rootView;
    }

    @Override
    public void requestResult(ArrayList<ParseObject> workouts) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        mViewWorkoutAdapter = new ViewWorkoutAdapter(getActivity().getApplicationContext(), workouts,(WorkoutSelector)mFragment );
        recyclerView.setAdapter(mViewWorkoutAdapter);
    }

    @Override
    public void failedToRetreiveData() {

    }

    @Override
    public void onItemClick(ParseObject workout) {
        ViewPlan_Fragment fragment = new ViewPlan_Fragment();
        Bundle b = new Bundle();
        b.putSerializable(SELECTED_WORKOUT,(Workout)workout);
        fragment.setArguments(b);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,fragment)
                .addToBackStack(null)
                .commit();
    }

}

