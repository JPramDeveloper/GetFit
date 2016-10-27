package me.danielhartman.startingstrength.ui.viewWorkout;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import me.danielhartman.startingstrength.model.Workout;
import me.danielhartman.startingstrength.network.WorkoutDataStore;
import me.danielhartman.startingstrength.network.DataGetterCallback;
import me.danielhartman.startingstrength.ui.accountManagement.AccountActivity;
import me.danielhartman.startingstrength.ui.accountManagement.LoginPresenter;

public class ViewWorkoutPresenter implements DataGetterCallback, ViewWorkoutAdapter.Callback {
    private static final String TAG = ViewWorkoutPresenter.class.getSimpleName();
    private DatabaseReference mDatabase;
    private LoginPresenter loginPresenter;
    private ViewWorkoutAdapter adapter;
    private WorkoutDataStore workoutDataStore;

    public ViewWorkoutPresenter(LoginPresenter loginPresenter, WorkoutDataStore workoutDataStore, Context context) {
        this.loginPresenter = loginPresenter;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        adapter = new ViewWorkoutAdapter(this, context);
        this.workoutDataStore = workoutDataStore;
    }

    public void onResume(Activity activity) {
        attachListener(activity);
        getAdapter().setContext(activity.getApplicationContext());
    }


    ViewWorkoutAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(ViewWorkoutAdapter adapter) {
        this.adapter = adapter;
    }

    void attachListener(Activity activity) {
        if (loginPresenter.getUser() != null) {
            adapter.setData(new ArrayList<>());
            workoutDataStore.getUserWorkouts(this, loginPresenter.getUser().getUid());

        } else {
            Intent i = new Intent(activity.getApplicationContext(), AccountActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            activity.startActivity(i);
        }
    }

    @Override
    public void detatchListener() {
        workoutDataStore.detachListener();
    }

    @Override
    public void returnWorkoutList(List<Workout> list) {
        adapter.setData(list);
    }

    @Override
    public void onClick(Workout workout) {

    }
}
