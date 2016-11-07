package me.danielhartman.startingstrength.ui.createWorkout;


import android.content.Context;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
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
import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.model.Day;
import me.danielhartman.startingstrength.model.Exercise;
import me.danielhartman.startingstrength.model.Set;
import me.danielhartman.startingstrength.model.Workout;
import me.danielhartman.startingstrength.network.LoginManager;
import me.danielhartman.startingstrength.network.WorkoutSaver;
import me.danielhartman.startingstrength.util.Schema;

public class CreateWorkoutPresenter implements WorkoutSaver.ImageSaverCallback{
    private static final String TAG = CreateWorkoutPresenter.class.getSimpleName();
    private Workout workout;
    private int currentDay = 0;
    private Boolean isAddFrameDisplayed;
    private String key;
    private LoginManager.Login loginPresenter;
    private CreateDayAdapter currentDayAdapter;
    private boolean isFirstRun;
    private Uri imageUri;
    private FrameLayout exerciseFrame;
    private FloatingActionButton fab;
    private WorkoutSaver.Saver workoutSaver;

    public CreateWorkoutPresenter(LoginManager.Login loginPresenter, WorkoutSaver.Saver saver) {
        this.loginPresenter = loginPresenter;
        this.workoutSaver = saver;
    }

    public boolean isFirstRun() {
        return isFirstRun;
    }

    public void setFirstRun(boolean firstRun) {
        isFirstRun = firstRun;
    }

    public void setCurrentDayAdapter(CreateDayAdapter currentDayAdapter) {
        this.currentDayAdapter = currentDayAdapter;
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
        String userId = loginPresenter.getUserId();
        if (workout != null) {
            DatabaseReference database = FirebaseDatabase.getInstance().getReference();
            database.child(Schema.WORKOUT_TOP_LEVEL).child(key).setValue(workout);
            database.child(Schema.USERS).child(userId).child(Schema.WORKOUT).child(key).setValue(key, (databaseError, databaseReference) -> {
                if (databaseError != null) {
                    Log.d(TAG, "onUploadComplete: " + databaseError.getMessage());
                    callback.onWorkoutUploadError("Failed to Upload Workout");
                } else {
                    Log.d(TAG, "commitWorkoutToFirebase: Created Successfully");
                    callback.onUploadComplete();
                }
            });
        }
    }

    public String generateKey() {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        String userId = loginPresenter.getUserId();
        key = database.child(Schema.USERS).child(userId).child(Schema.WORKOUT).push().getKey();
        return key;

    }

    public int getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(int index) {
        currentDay = index;
    }

    public List<Exercise> getExercisesForDay(int day) {
        return getWorkout().getDays().get(day).getExercises();
    }

    public int addDay(int day) {
        workout.getDays().add(new Day("Day " + String.valueOf(day + 1)));
        return workout.getDays().size();
    }

    public Workout initializeWorkout() {
        workout = new Workout();
        workout.setDays(new ArrayList<>());
        for (int i = 0; i <= 6; i++) {
            addDay(i);
            workout.getDays().get(i).setExercises(new ArrayList<>());
        }
        return workout;
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
        currentDayAdapter.setData(getSetsForGivenDay(currentDay));
    }

    public Exercise makeExercise(String exerciseName) {
        return findExistingExercise(exerciseName) != null ? findExistingExercise(exerciseName) : createNewExercise(exerciseName);
    }

    public Exercise findExistingExercise(String exerciseName) {
        for (Exercise e : getExercisesForDay(getCurrentDay())) {
            if (e.getName().equalsIgnoreCase(exerciseName)) {
                Log.d(TAG, "makeExercise: Returning Exercise");
                return e;
            }
        }
        return null;
    }

    public Exercise createNewExercise(String exerciseName) {
        Exercise newExercise = new Exercise();
        newExercise.setName(exerciseName);
        newExercise.setSets(new ArrayList<>());
        Log.d(TAG, "makeExercise: returning new exercise");
        getExercisesForDay(getCurrentDay()).add(newExercise);
        return newExercise;
    }

    public String getDayTitle() {
        return getWorkout().getDays().get(getCurrentDay()).getName();
    }

    public void uploadImage(Context context, Uri uri, CreateWorkoutCallback callback) throws FileNotFoundException {
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
            if (taskSnapshot.getMetadata() != null && taskSnapshot.getMetadata().getDownloadUrl() != null) {
                workout.setId(taskSnapshot.getMetadata().getDownloadUrl().toString());
            }
            commitWorkoutToFirebase(callback);
        }).addOnFailureListener(taskSnapshot -> {
            workout.setId(null);
            callback.onImageUploadError("Image Upload Failed");
            commitWorkoutToFirebase(callback);
        });
    }

    public void commitToFirebase(Context context, CreateWorkoutCallback callback) throws FileNotFoundException {
        workout = pruneUnusedDays(workout);
        generateKey();
        InputStream stream;

        if (imageUri!=null){
            stream = context.getContentResolver().openInputStream(imageUri);
            workoutSaver.saveImageAndWorkout(workout,this,key,stream);
        }  else {
            workoutSaver.saveWorkout(workout, null, key);
        }
//        try {
//            if (imageUri != null) {
//                uploadImage(context, imageUri, callback);
//            } else {
//                commitWorkoutToFirebase(callback);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    public Workout pruneUnusedDays(Workout workout) {
        Workout w = workout;
        List<Day> daysWithExercises = new ArrayList<>();
        for (Day day : w.getDays()) {
            if (day.getExercises().size() >= 1) {
                daysWithExercises.add(day);
            }
        }
        w.setDays(daysWithExercises);
        return w;
    }

    public void saveUri(Uri uri) {
        imageUri = uri;
    }

    public void onPageSelected(CreateWorkoutDay currentFragment, int currentDay) {
        setCurrentDay(currentDay);
        setCurrentDayAdapter(currentFragment.getAdapter());
        currentDayAdapter.setData(getSetsForGivenDay(getCurrentDay()));
        displayViewIfNoExercises(currentDay);
    }

    public void displayViewIfNoExercises(int currentDay) {
        if (getExercisesForDay(currentDay).size() <= 0) {
            displayExerciseFrame();
        } else {
            hideExerciseFrame();
        }
    }

    public void displayExerciseFrame() {
        exerciseFrame.setVisibility(View.VISIBLE);
        fab.setImageResource(R.drawable.ic_clear_black_24dp);
    }

    public void hideExerciseFrame() {
        exerciseFrame.setVisibility(View.GONE);
        fab.setImageResource(R.drawable.ic_add_black_24dp);
    }

    public void onFabClick() {
        if (exerciseFrame.getVisibility() == View.VISIBLE) {
            hideExerciseFrame();
        } else {
            displayExerciseFrame();
        }
    }

    public void setExerciseFrameAndButton(FrameLayout exerciseFrame, FloatingActionButton fab) {
        this.exerciseFrame = exerciseFrame;
        this.fab = fab;
    }

    public int getExerciseFrameVisiblity() {
        return exerciseFrame.getVisibility();
    }

    @Override
    public void uploadComplete(boolean imageSuccess) {
        Log.d(TAG, "uploadComplete: " + imageSuccess);
    }
}
