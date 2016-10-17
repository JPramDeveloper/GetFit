package me.danielhartman.startingstrength.ui.viewWorkout;


import android.app.Activity;
import android.content.Intent;
import android.provider.ContactsContract;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import me.danielhartman.startingstrength.model.Workout;
import me.danielhartman.startingstrength.network.DataGetter;
import me.danielhartman.startingstrength.network.DataGetterCallback;
import me.danielhartman.startingstrength.ui.accountManagement.AccountActivity;
import me.danielhartman.startingstrength.ui.accountManagement.LoginPresenter;
import me.danielhartman.startingstrength.util.Schema;

public class ViewWorkoutPresenter implements DataGetterCallback {
    private static final String TAG = ViewWorkoutPresenter.class.getSimpleName();
    DatabaseReference mDatabase;
    List<Workout> workoutResults = new ArrayList<>();
    LoginPresenter loginPresenter;
    ViewWorkoutAdapter adapter;
    ChildEventListener listener;
    DataGetter dataGetter;

    public ViewWorkoutPresenter(LoginPresenter loginPresenter, DataGetter dataGetter) {
        this.loginPresenter = loginPresenter;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        adapter = new ViewWorkoutAdapter();
        this.dataGetter = dataGetter;
    }

    public void onResume(Activity activity) {
        workoutResults = new ArrayList<>();
        attachListener(activity);
        getAdapter().setContext(activity.getApplicationContext());
    }

    public void clear() {
        mDatabase.removeEventListener(listener);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        workoutResults = new ArrayList<>();
        adapter = null;
    }

    public ViewWorkoutAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(ViewWorkoutAdapter adapter) {
        this.adapter = adapter;
    }

    public void attachListener(Activity activity) {
        if (loginPresenter.getUser() != null) {
            workoutResults = new ArrayList<>();
            adapter.setData(new ArrayList<>());
            dataGetter.getUserWorkouts(this, loginPresenter.getUser().getUid());

        } else {
            Intent i = new Intent(activity.getApplicationContext(), AccountActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            activity.startActivity(i);
        }
    }

    @Override
    public void detatchListener() {
        dataGetter.detachListener();
    }

    @Override
    public void returnWorkoutList(List<Workout> list) {
        adapter.setData(list);
    }
}
