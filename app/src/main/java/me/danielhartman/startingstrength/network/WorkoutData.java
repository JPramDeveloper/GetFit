package me.danielhartman.startingstrength.network;

import java.util.List;

import me.danielhartman.startingstrength.model.Workout;



public interface WorkoutData {
    public void getUserWorkouts(DataGetterCallback callback, String uid);
}
