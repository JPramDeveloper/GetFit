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

public class CreateExerciseFragment extends Fragment {

    private static final String TAG = CreateExerciseFragment.class.getSimpleName();
    @Inject
    public CreateWorkoutPresenter mPresenter;
    @BindView(R.id.addExerciseName)
    EditText exerciseName;
    @BindView(R.id.addExerciseWeight)
    EditText exerciseWeight;
    @BindView(R.id.addExerciseReps)
    EditText exerciseReps;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.add_exercise_layout, container, false);
        ButterKnife.bind(this, rootView);
        DaggerHolder.getInstance().component().inject(this);
        return rootView;
    }

    @OnClick(R.id.addSetButton)
    public void addSetButtonClick() {
        mPresenter.addExerciseToDay(exerciseName.getText().toString(), exerciseWeight.getText().toString(),
                exerciseReps.getText().toString());
    }

    @OnClick(R.id.finishExerciseButton)
    public void finishOnClick() {
        exerciseName.setText("");
        exerciseReps.setText("");
        exerciseWeight.setText("");
        View current = getActivity().getCurrentFocus();
        if (current != null) current.clearFocus();
    }
}
