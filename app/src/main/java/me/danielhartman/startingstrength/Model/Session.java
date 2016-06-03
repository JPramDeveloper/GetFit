package me.danielhartman.startingstrength.Model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by dphart9 on 8/17/2015.
 */
@ParseClassName("Session")
public class Session extends ParseObject implements Serializable {

    private String WORKOUT = "Workout";
    private String WORKOUTDAY = "WorkoutDay";
    private String PLAN = "Plan";

    public Workout getWorkout(){return (Workout)get(WORKOUT);}
    public void setWorkout(Workout workout){put(WORKOUT,workout);}

    public String getWorkoutDay(){return getString(WORKOUTDAY);}
    public void setWorkoutDay(String workoutDay){put(WORKOUTDAY,workoutDay);}

    public Date getDate(){return getCreatedAt();}

    public Session(Workout w, String day){
        put(WORKOUT, w);
        put(WORKOUTDAY, day);
    }
    public Session(){}








}
