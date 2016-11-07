package me.danielhartman.startingstrength.network;

import android.support.annotation.Nullable;

import java.io.InputStream;

import me.danielhartman.startingstrength.model.Workout;

public class WorkoutSaver {

    public interface Saver {
        void saveImage(@Nullable ImageCallback callback, InputStream inputStream, String key);

        void saveWorkout(Workout workout, @Nullable SaverCallback saverCallback, String key);

        void saveImageAndWorkout(Workout workout, ImageSaverCallback callback, String key, InputStream stream);

        String generateKey();
    }

    public interface SaverCallback {
        void saveWorkoutFailed(String message);

        void saveWorkoutComplete();
    }

    public interface ImageCallback {
        void saveImageSuccess();

        void saveImageFailed(String error);
    }

    public interface ImageSaverCallback {
        void uploadComplete(boolean imageSuccess);
    }

}
