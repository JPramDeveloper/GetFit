package me.danielhartman.startingstrength.ui.createWorkout;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.zip.Inflater;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.dagger.DaggerHolder;

public class CreateWorkoutName extends Activity{
    private static final String TAG = CreateWorkoutName.class.getSimpleName();
    private View rootView;

    @BindView(R.id.workoutNameEditText)
    EditText workoutName;

    @Inject
    public CreateWorkoutPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_workout_name);
        DaggerHolder.getInstance().component().inject(this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.createWorkoutButton)
    public void onButtonClick() {
        mPresenter.setWorkout(null);
        mPresenter.getWorkout().setName(workoutName.getText().toString());
        Log.d(TAG, "onButtonClick: ");
        Intent i = new Intent(this, CreateWorkoutActivity.class);
        startActivity(i);

    }
}
