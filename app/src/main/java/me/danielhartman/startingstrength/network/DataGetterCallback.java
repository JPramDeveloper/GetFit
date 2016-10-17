package me.danielhartman.startingstrength.network;

import java.util.List;

import me.danielhartman.startingstrength.model.Workout;



public interface DataGetterCallback {
     void returnWorkoutList(List<Workout> list);
     void detatchListener();
}
