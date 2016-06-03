package me.danielhartman.startingstrength.Interfaces;

import com.parse.ParseObject;

import me.danielhartman.startingstrength.Model.Workout;

/**
 * Created by danielhartman on 5/4/16.
 */
public interface WorkoutSelector {
    void onItemClick(ParseObject parseObject);
}
