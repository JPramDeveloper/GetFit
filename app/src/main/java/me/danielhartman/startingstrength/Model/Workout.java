package me.danielhartman.startingstrength.Model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.io.Serializable;

/**
 * Created by dphart9 on 8/17/2015.
 */
@ParseClassName("Workout")
public class Workout extends ParseObject implements Serializable {

    private String WORKOUT_NAME =  "WorkoutName";
    private String WORKOUT_ROUTINES = "NumberOfDays";
    public static String CREATEDBYUSER = "CreatedByUser";
    private String NOTES = "Notes";
    private String SESSION = "Session";
    private String NUMBEROFPLANS = "NumberOfPlans";


    public String getNumberOfPlans(){return getString(NUMBEROFPLANS);}
    public void setNumberOfPlans(String numberOfPlans){put(NUMBEROFPLANS,numberOfPlans);}

    public ParseUser getUser(){return getParseUser(CREATEDBYUSER);}
    public void setParseUser(ParseUser user){put(CREATEDBYUSER, user);}

    public String getNotes(){return getString(NOTES);}
    public void setNotes(String notes){put(NOTES,notes);}

    public String getWorkoutRoutines(){return getString(WORKOUT_ROUTINES);}
    public void setWorkoutRoutines(String workoutRoutines){put(WORKOUT_ROUTINES, workoutRoutines);}

    public String getWorkoutName() {return getString(WORKOUT_NAME); }
    public void setWorkoutName(String workoutName) {
        put(WORKOUT_NAME,workoutName);
    }

    public String getWorkoutId(){
        return getObjectId();
    }

    public Workout(String workoutName) {
       setWorkoutName(workoutName);
    }
    public Workout(){}

}
