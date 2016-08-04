package me.danielhartman.startingstrength.ui.createWorkout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.dagger.DaggerHolder;

public class CreateWorkoutName extends Fragment{
    private View rootView;

    @BindView(R.id.workoutNameEditText)
    EditText workoutName;

    @Inject
    public CreateWorkoutPresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.create_workout_name, container, false);
        ButterKnife.bind(this, rootView);
        DaggerHolder.getInstance().component().inject(this);
        return rootView;
    }

    @OnClick(R.id.createWorkoutButton)
    public void onButtonClick() {
        mPresenter.getWorkout().setName(workoutName.getText().toString());
    }
}
