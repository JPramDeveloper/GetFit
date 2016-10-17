package me.danielhartman.startingstrength.ui.startWorkout;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.dagger.DaggerHolder;
import me.danielhartman.startingstrength.databinding.ChooseWorkoutFragmentBinding;
import me.danielhartman.startingstrength.ui.base.BaseFragment;
import me.danielhartman.startingstrength.ui.base.Presenter;


public class ChooseWorkoutFragment extends BaseFragment {

    @Inject
    ChooseWorkoutFragmentPresenter presenter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    ChooseWorkoutFragmentBinding binding;
    

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.choose_workout_fragment,container,false);
        DaggerHolder.getInstance().component().inject(this);
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            //noinspection ConstantConditions
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Choose a Workout");
        }
        presentViews();
        return binding.getRoot();
    }

    public void setupRecyclerView() {
        binding.recyclerView.setAdapter(new ChooseWorkoutAdapter());
    }

    @Override
    public void initDagger() {
        DaggerHolder.getInstance().component().inject(this);
    }

    @Override
    public void presentViews() {
        presenter.present(binding, this);
    }

    @Override
    public Presenter getPresenter() {
        return presenter;
    }
}
