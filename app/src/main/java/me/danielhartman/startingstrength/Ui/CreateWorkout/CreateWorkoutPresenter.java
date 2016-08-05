package me.danielhartman.startingstrength.ui.createWorkout;


import android.util.Log;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import me.danielhartman.startingstrength.model.Day;
import me.danielhartman.startingstrength.model.Exercise;
import me.danielhartman.startingstrength.model.Set;
import me.danielhartman.startingstrength.model.Workout;

public class CreateWorkoutPresenter {
    private static final String TAG = CreateWorkoutPresenter.class.getSimpleName() ;
    private Workout workout;
    private int currentDay;

    public Workout getWorkout(){
        if (workout==null){
            initializeWorkout();
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
    public int getCurrentDay(){
        return currentDay;
    }
    public int goToNextDay(){
        currentDay += 1;
        return currentDay;
    }
    public List<Exercise> getExercisesForDay(int day){
        return getWorkout().getDays().get(day).getExercises();
    }
    public int addDay(){
        workout.getDays().add(new Day());
        return workout.getDays().size();
    }
    public Workout initializeWorkout(){
        workout = new Workout();
        workout.setDays(new ArrayList<>());
        addDay();
        workout.getDays().get(0).setExercises(new ArrayList<>());
        return workout;
    }
    public void addExerciseToDay(String name){
        List<Exercise> list = getWorkout().getDays().get(currentDay).getExercises();
        Exercise e = new Exercise();
        e.setName(name);
        e.setSets(new ArrayList<>());
        e.getSets().add(new Set());
        list.add(e);
    }



}
