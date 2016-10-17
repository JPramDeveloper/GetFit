package me.danielhartman.startingstrength.network;

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
import me.danielhartman.startingstrength.util.Schema;

public class DataGetter implements WorkoutData {

    private ChildEventListener listener;
    private DatabaseReference mDatabase;
    private String TAG = DataGetter.class.getSimpleName();
    private DataGetterCallback getUserWorkoutsCallback;
    private List<Workout> workoutList;

    @Override
    public void getUserWorkouts(DataGetterCallback callback, String uid) {
        initFirebaseDB();
        attachListener(createListener(),uid);
        this.getUserWorkoutsCallback = callback;
    }

     private ChildEventListener createListener() {
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

     private void getWorkouts(String key) {
        mDatabase.child(Schema.WORKOUT_TOP_LEVEL).child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Workout w = dataSnapshot.getValue(Workout.class);
                workoutList.add(w);
                getUserWorkoutsCallback.returnWorkoutList(workoutList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: ");
            }
        });
    }

     private void attachListener(ChildEventListener listener, String uid) {
        mDatabase.child(Schema.USERS).child(uid)
                .child(Schema.WORKOUT).addChildEventListener(listener);
    }

     private void initFirebaseDB() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        workoutList = new ArrayList<>();
    }

    public void detachListener(){
        mDatabase.removeEventListener(listener);
        getUserWorkoutsCallback =null;
        listener = null;
    }

}
