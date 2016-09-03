package me.danielhartman.startingstrength.ui.viewWorkout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.dagger.DaggerHolder;

public class ViewWorkoutFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Inject
    ViewWorkoutPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.view_workout_fragment, container, false);
        ButterKnife.bind(this, v);
        DaggerHolder.getInstance().component().inject(this);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attachListner(getActivity());
        recyclerViewSetup();
    }

    public void recyclerViewSetup() {
        presenter.getAdapter().setContext(getActivity().getApplicationContext());
        mRecyclerView.setAdapter(presenter.getAdapter());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
    }

    @Override
    public void onPause() {
        presenter.detatchListener();
        super.onPause();
    }
}
