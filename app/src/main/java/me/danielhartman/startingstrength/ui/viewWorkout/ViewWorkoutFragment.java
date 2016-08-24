package me.danielhartman.startingstrength.ui.viewWorkout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.dagger.DaggerHolder;

public class ViewWorkoutFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ButterKnife.bind(getActivity());
        DaggerHolder.getInstance().component().inject(this);
        return inflater.inflate(R.layout.view_workout_fragment, container, false);

    }

}
