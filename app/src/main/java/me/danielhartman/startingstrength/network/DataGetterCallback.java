package me.danielhartman.startingstrength.network;

import java.util.List;

import me.danielhartman.startingstrength.model.Workout;

/**
 * Created by Daniel on 10/16/2016.
 */

public interface DataGetterCallback {
     void returnWorkoutList(List<Workout> list);
}
