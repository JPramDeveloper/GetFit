package me.danielhartman.startingstrength.ui.createWorkout.CreateWorkoutName;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.dagger.DaggerHolder;
import me.danielhartman.startingstrength.databinding.CreateWorkoutNameConstraintLayoutBinding;
import me.danielhartman.startingstrength.ui.base.BaseFragment;
import me.danielhartman.startingstrength.ui.base.BasePresenter;
import me.danielhartman.startingstrength.ui.createWorkout.CreateWorkoutNameActivity;


public class CWNFragment extends BaseFragment<CWNContract.View> implements CWNContract.View {

   CWNPresenter presenter;
    CreateWorkoutNameConstraintLayoutBinding binding;

    @Override
    public void initDagger() {
        presenter = DaggerHolder.getInstance().component().getCWNPresenter();
    }

    @Override
    public BasePresenter<CWNContract.View> getPresenter() {
        return presenter;
    }

    @Override
    public CWNContract.View getContractView() {
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.create_workout_name_constraint_layout,container,false);
        return binding.getRoot();
    }
}
