package me.danielhartman.startingstrength.ui.createWorkout;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import me.danielhartman.startingstrength.Interfaces.CreateWorkoutCallback;
import me.danielhartman.startingstrength.model.Day;
import me.danielhartman.startingstrength.model.Exercise;
import me.danielhartman.startingstrength.model.Set;
import me.danielhartman.startingstrength.model.Workout;
import me.danielhartman.startingstrength.ui.accountManagement.LoginPresenter;
import me.danielhartman.startingstrength.util.Schema;

import static android.support.v4.app.ActivityCompat.startActivityForResult;

public class CreateWorkoutPresenter {
    private static final String TAG = CreateWorkoutPresenter.class.getSimpleName();
    private Workout workout;
    private int currentDay = 0;
    private CreateDayAdapter adapter;
    private Boolean isAddFrameDisplayed;
    private String key;
    private LoginPresenter loginPresenter;

    public CreateWorkoutPresenter(LoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
    }

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

    public void commitWorkoutToFirebase(CreateWorkoutCallback callback) {
        String userId = loginPresenter.getUser().getUid();
        if (workout != null) {
            DatabaseReference database = FirebaseDatabase.getInstance().getReference();
            key = database.child(Schema.USERS).child(userId).child(Schema.WORKOUT).push().getKey();
            database.child(Schema.WORKOUT_TOP_LEVEL).child(key).setValue(workout);
            database.child(Schema.USERS).child(userId).child(Schema.WORKOUT).child(key).setValue(key, (databaseError, databaseReference) -> {
                if (databaseError != null) {
                    Log.d(TAG, "onComplete: " + databaseError.getMessage());
                    callback.onError();
                } else {
                    Log.d(TAG, "commitWorkoutToFirebase: Created Successfully");
                    callback.onComplete();
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
        workout.getDays().add(new Day("Day " + String.valueOf(currentDay + 1)));
        return workout.getDays().size();
    }

    public void initNextDay() {
        if (workout.getDays().size() < currentDay + 1) {
            workout.getDays().add(new Day("Day " + String.valueOf(currentDay + 1)));
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

    public String getDayTitle(){
        return getWorkout().getDays().get(getCurrentDay()).getName();
    }

    public void viewExercisesForToday() {
        Log.d(TAG, "Displaying Exercises for index: " + String.valueOf(currentDay));
        adapter.setData(getSetsForGivenDay(getCurrentDay()));
    }

    public void uploadImage(Context context, Uri uri) throws FileNotFoundException {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl("gs://fitnessapp-1cb48.appspot.com/");
        StorageReference imagesRef = storageRef.child("images/" + key);

        InputStream stream = context.getContentResolver().openInputStream(uri);

        UploadTask uploadTask = imagesRef.putStream(stream);
        uploadTask.addOnFailureListener(exception -> {
            Log.d(TAG, "uploadImage: Failed " + exception.toString());
            Toast.makeText(context, "Image Upload failed", Toast.LENGTH_SHORT).show();
            // Handle unsuccessful uploads
        }).addOnSuccessListener(taskSnapshot -> {
            // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
            Uri downloadUrl = taskSnapshot.getDownloadUrl();
        });
    }

    public void commitToFirebase(Context context, Uri uri, CreateWorkoutCallback callback) {
        commitWorkoutToFirebase(callback);
        try {
            uploadImage(context, uri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
