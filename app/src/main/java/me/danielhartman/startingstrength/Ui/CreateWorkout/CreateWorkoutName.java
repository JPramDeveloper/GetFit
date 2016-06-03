package me.danielhartman.startingstrength.Ui.CreateWorkout;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.danielhartman.startingstrength.Interfaces.ViewWorkoutCallback;
import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.Ui.MainActivity;
import me.danielhartman.startingstrength.Ui.MyApplication;

public class CreateWorkoutName extends Fragment{
    private View rootView;
    private Fragment mFragment;

    @Bind(R.id.testText)
    TextView mTest;

    @Inject
    public CreateWorkoutPresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.create_workout_name, container, false);
        ButterKnife.bind(this, rootView);

        mFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.container);
        ((MainActivity)getActivity()).getCreateWorkoutComponent().inject(this);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getActivity().getApplicationContext(), mPresenter.getTestText(), Toast.LENGTH_SHORT).show();
    }
}
