package me.danielhartman.startingstrength.ui.viewWorkout;


import android.app.Activity;
import android.content.Intent;
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
import me.danielhartman.startingstrength.ui.accountManagement.AccountActivity;
import me.danielhartman.startingstrength.ui.accountManagement.LoginPresenter;
import me.danielhartman.startingstrength.util.Schema;

public class ViewWorkoutPresenter {
    private static final String TAG = ViewWorkoutPresenter.class.getSimpleName();
    DatabaseReference mDatabase;
    List<Workout> workoutResults = new ArrayList<>();
    LoginPresenter loginPresenter;
    ViewWorkoutAdapter adapter;
    ChildEventListener listener;

    public ViewWorkoutPresenter(LoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        adapter = new ViewWorkoutAdapter();
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

    public void attachListner(Activity activity) {
        if (loginPresenter.getUser() != null) {
            workoutResults = new ArrayList<>();
            createListener();
            mDatabase.child(Schema.USERS).child(loginPresenter.getUser().getUid())
                    .child(Schema.WORKOUT).addChildEventListener(listener);
        } else {
            Intent i = new Intent(activity.getApplicationContext(), AccountActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            activity.startActivity(i);
        }
    }

    public void detatchListener() {
        mDatabase.removeEventListener(listener);
    }


    public ChildEventListener createListener() {
        listener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG, "onChildAdded: " + dataSnapshot.getKey());
                getWorkouts(dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        return listener;
    }

    public void getWorkouts(String key) {
        mDatabase.child(Schema.WORKOUT_TOP_LEVEL).child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Workout w = dataSnapshot.getValue(Workout.class);
                workoutResults.add(w);
                Log.d(TAG, "onDataChange: Workout Size = " + String.valueOf(workoutResults.size()));
                adapter.setData(workoutResults);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: ");
            }
        });
    }
}
