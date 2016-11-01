package me.danielhartman.startingstrength.network;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import me.danielhartman.startingstrength.model.Workout;
import me.danielhartman.startingstrength.util.Schema;

public class FirebaseWorkoutSaver implements WorkoutSaver.Saver {

    LoginManager.Login loginManager;

    public FirebaseWorkoutSaver(LoginManager.Login loginManager) {
        this.loginManager = loginManager;
    }

    @Override
    public void saveImage(WorkoutSaver.Callback callback) {

    }

    @Override
    public void saveWorkout(Workout workout) {

    }

    public String generateKey() {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        String userId = loginManager.getUserId();
        String key = database.child(Schema.USERS).child(userId).child(Schema.WORKOUT).push().getKey();
        return key;
    }
}
