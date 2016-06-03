package me.danielhartman.startingstrength.Model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by dphart9 on 8/17/2015.
 */
@ParseClassName("Plan")
public class Plan extends ParseObject {

    public static String WORKOUT_RELATION = "WorkoutRelation";
    private String SETS = "Sets";
    private String REPS = "Reps";
    private String EXERCISE = "Exercise";
    private String DAY = "Day";
    private String NOTES = "Notes";
    private String CREATEDBYUSER = "CreatedByUser";
    private String PLANNAME = "PlanName";


    public String getDay(){
        return getString(DAY);
    }
    public void setDay(String day){put(DAY,day);}

    public String getSets(){
        return getString(SETS);
    }
    public void setSets(String sets){put(SETS,sets);}

    public String getReps(){
        return getString(REPS);
    }
    public void setReps(String reps){put(REPS,reps);}

    public String getExercise(){
        return getString(EXERCISE);
    }
    public void setExercise(String exercise){put(EXERCISE,exercise);}

    public String getNotes(){return getString(NOTES);}
    public void setNotes(String notes){put(NOTES,notes);}

    public String getPlanName(){return getString(PLANNAME);}
    public void setPlanName(String planName){put(PLANNAME,planName);}

    public ParseUser getUser(){return getParseUser(CREATEDBYUSER);}
    public void setParseUser(ParseUser user){put(CREATEDBYUSER, user);}


    public void setWorkoutRelation(Workout workoutRelation) {put(WORKOUT_RELATION,workoutRelation);}

    public String getPlanId(){
        return getObjectId();
    }

    public Plan(Workout workoutRelation) {
        setWorkoutRelation(workoutRelation);
    }
    public Plan(){}

}
