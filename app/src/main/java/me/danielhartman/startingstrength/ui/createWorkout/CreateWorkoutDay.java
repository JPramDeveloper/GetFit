package me.danielhartman.startingstrength.ui.createWorkout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.dagger.DaggerHolder;
import me.danielhartman.startingstrength.model.Exercise;
import me.danielhartman.startingstrength.model.Set;

public class CreateWorkoutDay extends Fragment{
    private static final String TAG = CreateWorkoutDay.class.getSimpleName();
    private View rootView;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    FrameLayout mExerciseFrame;

    @Inject
    public CreateWorkoutPresenter mPresenter;

    private CreateDayAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.create_workout_day, container, false);
        ButterKnife.bind(this, rootView);
        DaggerHolder.getInstance().component().inject(this);
        mExerciseFrame = ((CreateWorkoutActivity)getActivity()).getmExerciseFrame();
        mExerciseFrame.setVisibility(View.GONE);
        populateRecycler();
        return rootView;
    }

    public void populateRecycler(){
        mPresenter.setAdapter(new CreateDayAdapter(mPresenter.getSetsForGivenDay(mPresenter.getCurrentDay())));
        mRecyclerView.setAdapter(mPresenter.getAdapter());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplication()));
    }

    @OnClick(R.id.addExercise)
    public void addExerciseOnClick() {
        mExerciseFrame.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.finishDaysButton)
    public void finishDaysButtonClick(){
    }
    public void setNewData(List<Set> list){
        adapter.setData(list);
    }





}


