package me.danielhartman.startingstrength.ui.viewWorkout;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.dagger.DaggerHolder;
import me.danielhartman.startingstrength.databinding.ViewWorkoutDetailsFragmentBinding;
import me.danielhartman.startingstrength.ui.base.BaseFragment;
import me.danielhartman.startingstrength.ui.base.BasePresenter;
import me.danielhartman.startingstrength.ui.base.Presenter;

public class ViewWorkoutDetailsFragment extends Fragment {

//    @Inject
//    ViewWorkoutDetailsPresenter presenter;

    ViewWorkoutDetailsFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.view_workout_details_fragment,container,false);
        return binding.getRoot();

    }

}
