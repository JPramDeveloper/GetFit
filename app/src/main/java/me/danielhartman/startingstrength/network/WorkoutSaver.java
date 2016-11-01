package me.danielhartman.startingstrength.network;

import me.danielhartman.startingstrength.model.Workout;

public class WorkoutSaver {
    public interface Saver {
        void saveImage(Callback callback);

        void saveWorkout(Workout workout);
    }

    public interface Callback {
        boolean saveImageComplete(String message);

        boolean saveWorkoutComplete(String message);
    }

}
