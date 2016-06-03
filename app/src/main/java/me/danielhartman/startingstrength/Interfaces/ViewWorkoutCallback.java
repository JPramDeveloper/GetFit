package me.danielhartman.startingstrength.Interfaces;


import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

import me.danielhartman.startingstrength.Model.Plan;
import me.danielhartman.startingstrength.Model.Workout;

public interface ViewWorkoutCallback {
    void requestResult(ArrayList<ParseObject> list);
    void failedToRetreiveData();
}
