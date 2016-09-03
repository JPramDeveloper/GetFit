package me.danielhartman.startingstrength.ui.createWorkout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.dagger.DaggerHolder;

public class CreateWorkoutDay extends Fragment {
    private static final String TAG = CreateWorkoutDay.class.getSimpleName();
    public static String BUNDLED_DAY = "currentDay";
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    FrameLayout mExerciseFrame;

    @Inject
    CreateWorkoutPresenter mPresenter;

    CreateDayAdapter adapter;

    public static CreateWorkoutDay newInstance(int i) {
        CreateWorkoutDay c = new CreateWorkoutDay();
        Bundle b = new Bundle();
        b.putInt(BUNDLED_DAY, i);
        c.setArguments(b);
        return c;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.create_workout_day, container, false);
        ButterKnife.bind(this, rootView);
        DaggerHolder.getInstance().component().inject(this);
        setupAdapter();
        populateRecycler();
        Log.d(TAG, "onCreateView: Adapter is created");
        return rootView;
    }

    public void setupAdapter() {
        adapter = new CreateDayAdapter(new ArrayList<>());
        Bundle b = getArguments();
        if (b != null) {
            adapter.setData(mPresenter.getSetsForGivenDay(b.getInt(BUNDLED_DAY)));
            if (b.getInt(BUNDLED_DAY) == 0 && mPresenter.isFirstRun()) {
                //sets adapter in presenter since onScroll in activity hasn't been hit
                mPresenter.setCurrentDayAdapter(getAdapter());
                mPresenter.setFirstRun(false);
            }
        }
    }

    public void populateRecycler() {
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplication()));
    }

    public CreateDayAdapter getAdapter() {
        return adapter;
    }

}







