package me.danielhartman.startingstrength.ui.createWorkout;


import android.util.Log;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import me.danielhartman.startingstrength.model.Workout;

public class CreateWorkoutPresenter {
    private static final String TAG = CreateWorkoutPresenter.class.getSimpleName() ;
    private Workout workout;

    public Workout getWorkout(){
        if (workout==null){
            workout =  new Workout();
        }
        return workout;
    }

    public void commitWorkoutToFirebase(){
        if (workout!=null){
            DatabaseReference database = FirebaseDatabase.getInstance().getReference();
            database.setValue(workout, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError!=null){
                    Log.d(TAG, "onComplete: " + databaseError.getMessage());}
                    else {
                        Log.d(TAG, "onComplete: no error") ;
                    }
                }
            });

        }
    }

}
