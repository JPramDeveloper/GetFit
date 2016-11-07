package me.danielhartman.startingstrength.network;

import java.util.List;

import me.danielhartman.startingstrength.model.Workout;

public class DatabaseManager {

    public interface WorkoutData {
        void getUserWorkouts(DataGetterCallback callback, String uid);
    }

    public interface DataGetterCallback {
        void returnWorkoutList(List<Workout> list);

        void detatchListener();
    }
}
