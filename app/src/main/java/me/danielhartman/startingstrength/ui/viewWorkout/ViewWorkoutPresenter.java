package me.danielhartman.startingstrength.ui.viewWorkout;


import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import me.danielhartman.startingstrength.model.Key;
import me.danielhartman.startingstrength.model.Workout;
import me.danielhartman.startingstrength.ui.accountManagement.LoginPresenter;
import me.danielhartman.startingstrength.util.Schema;

public class ViewWorkoutPresenter {
    private static final String TAG = ViewWorkoutPresenter.class.getSimpleName() ;
    DatabaseReference mDatabase;
    List<Workout> workoutResults = new ArrayList<>();
    LoginPresenter loginPresenter;

    public ViewWorkoutPresenter(LoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void getWorkoutIds() {
        mDatabase.child(Schema.USERS).child(loginPresenter.getUser().getUid())
                .child(Schema.WORKOUT).addChildEventListener(new ChildEventListener() {
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
        });
    }
    public void getWorkouts(String key){
        mDatabase.child(Schema.WORKOUT_TOP_LEVEL).child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Workout w = dataSnapshot.getValue(Workout.class);
                workoutResults.add(w);
                Log.d(TAG, "onDataChange: Workout Size = " + String.valueOf(workoutResults.size()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
