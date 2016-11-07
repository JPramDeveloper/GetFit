package me.danielhartman.startingstrength.network;

import android.net.Uri;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.InputStream;

import me.danielhartman.startingstrength.model.Workout;
import me.danielhartman.startingstrength.util.Schema;

public class FirebaseWorkoutSaver implements WorkoutSaver.Saver {

    public static String TAG = FirebaseWorkoutSaver.class.getSimpleName();

    LoginManager.Login loginManager;
    String imageUrl;
    boolean saveImageAndWorkoutSuccessful;

    public boolean isSaveImageAndWorkoutSuccessful() {
        return saveImageAndWorkoutSuccessful;
    }

    public void setSaveImageAndWorkoutSuccessful(boolean saveImageAndWorkoutSuccessful) {
        this.saveImageAndWorkoutSuccessful = saveImageAndWorkoutSuccessful;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public FirebaseWorkoutSaver(LoginManager.Login loginManager) {
        this.loginManager = loginManager;
    }


    @Override
    public void saveImage(WorkoutSaver.ImageCallback callback, InputStream stream, String key) {
        try {
            uploadImage(stream, callback, key);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            setImageUrl(null);
        }
    }

    @Override
    public void saveWorkout(Workout workout, WorkoutSaver.SaverCallback saverCallback, String key) {
        commitWorkoutToFirebase(saverCallback, workout, key);
    }

    @Override
    public synchronized void saveImageAndWorkout(Workout workout, WorkoutSaver.ImageSaverCallback callback, String key, InputStream stream) {
        saveImage(null, stream, key);
        Workout w = workout;
        w.setId(getImageUrl());
        commitWorkoutToFirebase(null, w, key);
        callback.uploadComplete(isSaveImageAndWorkoutSuccessful());
    }

    @Override
    public String generateKey() {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        String userId = loginManager.getUserId();
        String key = database.child(Schema.USERS).child(userId).child(Schema.WORKOUT).push().getKey();
        return key;
    }

    public void commitWorkoutToFirebase(WorkoutSaver.SaverCallback saverCallback, Workout workout, String key) {
        String userId = loginManager.getUserId();
        if (workout != null) {
            DatabaseReference database = FirebaseDatabase.getInstance().getReference();
            database.child(Schema.WORKOUT_TOP_LEVEL).child(key).setValue(workout);
            database.child(Schema.USERS).child(userId).child(Schema.WORKOUT).child(key).setValue(key, (databaseError, databaseReference) -> {
                if (databaseError != null) {
                    Log.d(TAG, "onUploadComplete: " + databaseError.getMessage());
                    if (saverCallback != null) {
                        saverCallback.saveWorkoutComplete();
                    }
                } else {
                    if (saverCallback != null) {
                        saverCallback.saveWorkoutFailed("Failed");
                    }
                }
            });
        }
    }

    public void uploadImage(InputStream stream, WorkoutSaver.ImageCallback callback, String key) throws FileNotFoundException {

        String imageUrl = null;

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl("gs://fitnessapp-1cb48.appspot.com/");
        StorageReference imagesRef = storageRef.child("images/" + key);

//        InputStream stream = context.getContentResolver().openInputStream(uri);

        UploadTask uploadTask = imagesRef.putStream(stream);
        uploadTask.addOnFailureListener(exception -> {
            Log.d(TAG, "uploadImage: Failed " + exception.toString());
            // Handle unsuccessful uploads
        }).addOnSuccessListener(taskSnapshot -> {
            // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
            Uri downloadUrl = taskSnapshot.getDownloadUrl();
            if (taskSnapshot.getMetadata() != null && downloadUrl != null) {
                setImageUrl(downloadUrl.toString());
                setSaveImageAndWorkoutSuccessful(true);
            }
        }).addOnFailureListener(taskSnapshot -> {
            setImageUrl(null);
        });
    }
}
