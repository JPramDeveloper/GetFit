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

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.dagger.DaggerHolder;

public class CreateWorkoutDay extends Fragment{
    private static final String TAG = CreateWorkoutDay.class.getSimpleName();

    private View rootView;

    @BindView(R.id.addExercise)
    Button addExerciseButton;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.workoutTitle)
    TextView workoutTitle;

    FrameLayout mExerciseFrame;

    @Inject
    CreateWorkoutPresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.create_workout_day, container, false);
        ButterKnife.bind(this, rootView);
        DaggerHolder.getInstance().component().inject(this);
        mExerciseFrame = ((CreateWorkoutActivity)getActivity()).getExerciseFrame();
        mExerciseFrame.setVisibility(View.GONE);
        mPresenter.setAddFrameDisplayed(false);
        populateRecycler();
        updateDay();
        setWorkoutTitle();
        GestureDetectorCompat detector = new GestureDetectorCompat(getActivity().getApplicationContext(),new MyGestureListener());
        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                detector.onTouchEvent(motionEvent);
                return true;
            }
        });
        return rootView;
    }

    public void populateRecycler(){
        mPresenter.setAdapter(new CreateDayAdapter(mPresenter.getSetsForGivenDay(mPresenter.getCurrentDay())));
        mRecyclerView.setAdapter(mPresenter.getAdapter());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplication()));
    }

    @OnClick(R.id.addExercise)
    public void addExerciseOnClick() {
        if (mPresenter.getAddFrameDisplayed()){
           setCreateExerciseInvisible();
        }else {
            setCreateExerciseVisible();
        }
    }
    public void updateDay(){
        title.setText(mPresenter.getDayTitle());
        mPresenter.viewExercisesForToday();
    }

    public void setWorkoutTitle(){
        workoutTitle.setText(mPresenter.getWorkout().getName());
    }

    @OnClick(R.id.createDayButton)
    public void createDayOnClick(){
        mPresenter.goToNextDay();
        updateDay();
    }

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

    @OnClick(R.id.previousDayButton)
    public void onPreviousDayClicked(){
        mPresenter.goToPreviousDay();
        updateDay();
    }

    @OnClick(R.id.nextDayButton)
    public void nextDayButton(){
        Intent i = new Intent(getActivity().getApplicationContext(),AddImageActivity.class);
        startActivity(i);
    }

    public void onLeftSwipe(){
        Log.d(TAG, "onLeftSwipe: ");
        createDayOnClick();
    }

    public void onRightSwipe() {
        Log.d(TAG, "onRightSwipe: ");
        onPreviousDayClicked();
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";
        private static final int SWIPE_MIN_DISTANCE = 120;
        private static final int SWIPE_MAX_OFF_PATH = 250;
        private static final int SWIPE_THRESHOLD_VELOCITY = 200;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2,
                               float velocityX, float velocityY) {
            try {
                if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH){
                    return false;
                }
                // right to left swipe
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    onLeftSwipe();
                }
                // left to right swipe
                else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    onRightSwipe();
                }
            } catch (Exception e) {
                Log.d(TAG, "onFling: exception " + e.toString());
            }
            return false;
        }
    }




}


