package me.danielhartman.startingstrength.Ui.CreateWorkout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.dagger.DaggerHolder;

public class CreateWorkoutName extends Fragment{
    private View rootView;
    private Fragment mFragment;

    @Inject
    public CreateWorkoutPresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.create_workout_name, container, false);
        ButterKnife.bind(this, rootView);

        mFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.container);
        DaggerHolder.getInstance().component().inject(this);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
