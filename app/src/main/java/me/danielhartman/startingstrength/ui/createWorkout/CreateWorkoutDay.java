package me.danielhartman.startingstrength.ui.createWorkout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.dagger.DaggerHolder;

public class CreateWorkoutDay extends Fragment{
    public static String BUNDLED_DAY = "currentDay";

    private static final String TAG = CreateWorkoutDay.class.getSimpleName();

    private View rootView;

    @BindView(R.id.addExercise)
    Button addExerciseButton;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;


    FrameLayout mExerciseFrame;

    @Inject
    CreateWorkoutPresenter mPresenter;

    CreateDayAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.create_workout_day, container, false);
        ButterKnife.bind(this, rootView);
        DaggerHolder.getInstance().component().inject(this);
        mExerciseFrame = ((CreateWorkoutActivity)getActivity()).getExerciseFrame();
        mExerciseFrame.setVisibility(View.GONE);
        mPresenter.setAddFrameDisplayed(false);
        adapter = new CreateDayAdapter(new ArrayList<>());
        Bundle b = getArguments();
        if (b!=null){
            adapter.setData(mPresenter.getSetsForGivenDay(b.getInt(BUNDLED_DAY)));
        }

        populateRecycler();
        Log.d(TAG, "onCreateView: Adapter is created");
        return rootView;
    }

    public static CreateWorkoutDay newInstance(int i){
        CreateWorkoutDay c = new CreateWorkoutDay();
        Bundle b = new Bundle();
        b.putInt(BUNDLED_DAY,i);
        c.setArguments(b);
        return c;
    }

    public void populateRecycler(){
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplication()));
    }

    public CreateDayAdapter getAdapter(){
        return adapter;
    }

    @OnClick(R.id.addExercise)
    public void addExerciseOnClick() {
        if (mPresenter.getAddFrameDisplayed()){
           setCreateExerciseInvisible();
        }else {
            setCreateExerciseVisible();
        }
    }
//    public void updateDay(){
////        title.setText(mPresenter.getDayTitle());
//        mPresenter.viewExercisesForToday();
//    }

    public void setCreateExerciseVisible(){
        mExerciseFrame.setVisibility(View.VISIBLE);
        addExerciseButton.setText("Hide");
        mPresenter.setAddFrameDisplayed(true);
    }

    public void setCreateExerciseInvisible(){
        mExerciseFrame.setVisibility(View.GONE);
        addExerciseButton.setText("Add Exercise");
        mPresenter.setAddFrameDisplayed(false);
    }

    @OnClick(R.id.nextDayButton)
    public void nextDayButton(){
        Intent i = new Intent(getActivity().getApplicationContext(),AddImageActivity.class);
        startActivity(i);
    }
}







