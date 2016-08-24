package me.danielhartman.startingstrength.ui.createWorkout;


import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import me.danielhartman.startingstrength.model.Day;
import me.danielhartman.startingstrength.model.Exercise;
import me.danielhartman.startingstrength.model.Set;
import me.danielhartman.startingstrength.model.Workout;
import me.danielhartman.startingstrength.util.Schema;

public class CreateWorkoutPresenter {
    private static final String TAG = CreateWorkoutPresenter.class.getSimpleName();
    private Workout workout;
    private int currentDay = 0;
    private CreateDayAdapter adapter;
    private Boolean isAddFrameDisplayed;

    public Boolean getAddFrameDisplayed() {
        return isAddFrameDisplayed;
    }

    public void setAddFrameDisplayed(Boolean addFrameDisplayed) {
        isAddFrameDisplayed = addFrameDisplayed;
    }

    public Workout getWorkout() {
        if (workout == null) {
            initializeWorkout();
        }
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public void commitWorkoutToFirebase(String userId) {
        if (workout != null) {
            DatabaseReference database = FirebaseDatabase.getInstance().getReference();
            String key = database.child(Schema.WORKOUT_TOP_LEVEL).push().getKey();
            database.child(Schema.WORKOUT_TOP_LEVEL).child(key).setValue(workout);
            database.child(Schema.USERS).child(userId).child(Schema.WORKOUT).child(key).setValue(key, (databaseError, databaseReference) -> {
                if (databaseError != null) {
                    Log.d(TAG, "onComplete: " + databaseError.getMessage());
                } else {
                    Log.d(TAG, "onComplete: no error");
                }
            });
        }
    }

    public int getCurrentDay() {
        return currentDay;
    }

    public List<Exercise> getExercisesForDay(int day) {
        return getWorkout().getDays().get(day).getExercises();
    }

    public int addDay() {
        workout.getDays().add(new Day());
        return workout.getDays().size();
    }

    public void initNextDay() {
        if (workout.getDays().size() < currentDay + 1) {
            workout.getDays().add(new Day());
            workout.getDays().get(getCurrentDay()).setExercises(new ArrayList<>());
        }
    }

    public Workout initializeWorkout() {
        workout = new Workout();
        workout.setDays(new ArrayList<>());
        addDay();
        workout.getDays().get(0).setExercises(new ArrayList<>());
        return workout;
    }

    public CreateDayAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(CreateDayAdapter adapter) {
        this.adapter = adapter;
    }

    public void updateData() {
        if (adapter != null) {
            adapter.setData(getSetsForGivenDay(getCurrentDay()));
        } else {
            Log.e(TAG, "updateData: NO ADAPTER SET");
        }
    }

    public List<Object> getSetsForGivenDay(int day) {
        List<Object> list = new ArrayList<>();
        for (Exercise e : getExercisesForDay(day)) {
            list.add(e);
            for (Set s : e.getSets()) {
                list.add(s);
            }
        }
        return list;
    }

    public Exercise createExercise(String name, String weight, String reps) {
        Exercise exercise = makeExercise(name);

        Set set = new Set();
        set.setExerciseName(name);
        set.setReps(reps);
        set.setWeight(weight);
        exercise.getSets().add(set);

        return exercise;
    }

    public void addExerciseToDay(String name, String weight, String reps) {
        createExercise(name, weight, reps);
        updateData();
    }

    public Exercise makeExercise(String exerciseName) {
        return findExistingExercise(exerciseName)!=null ? findExistingExercise(exerciseName) : createNewExercise(exerciseName);
    }

    public Exercise findExistingExercise(String exerciseName){
        for (Exercise e : getExercisesForDay(getCurrentDay())) {
            if (e.getName().equalsIgnoreCase(exerciseName)) {
                Log.d(TAG, "makeExercise: Returning Exercise");
                return e;
            }
        }
        return null;
    }

    public Exercise createNewExercise(String exerciseName){
        Exercise newExercise = new Exercise();
        newExercise.setName(exerciseName);
        newExercise.setSets(new ArrayList<>());
        Log.d(TAG, "makeExercise: returning new exercise");
        getExercisesForDay(getCurrentDay()).add(newExercise);
        return newExercise;
    }

    public int goToPreviousDay() {
        if (currentDay > 0) {
            currentDay -= 1;
        }
        return currentDay;
    }

    public int goToNextDay() {
        currentDay += 1;
        initNextDay();
        return currentDay;
    }

    public void viewExercisesForToday() {
        Log.d(TAG, "Displaying Exercises for index: " + String.valueOf(currentDay));
        adapter.setData(getSetsForGivenDay(getCurrentDay()));
    }
}
