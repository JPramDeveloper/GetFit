package me.danielhartman.startingstrength.ui.createWorkout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.ui.MainActivity;

public class CreateWorkoutName extends Fragment{
    private View rootView;
    private Fragment mFragment;

    @BindView(R.id.testText)
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
    }
    @OnClick(R.id.create_workout_name_continue)
    public void onClickContinue(){
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new CreateWorkoutFragment()).commit();
    }
}
