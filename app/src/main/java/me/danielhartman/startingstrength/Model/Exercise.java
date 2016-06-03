package me.danielhartman.startingstrength.Model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by dphart9 on 8/17/2015.
 */
@ParseClassName("Exercise")
public class Exercise extends ParseObject implements Serializable {

    private String WORKOUT = "Workout";
    private String WORKOUTDAYNUBMER = "WorkoutDayNumber";
    private String REPS = "Reps";
    private String EXERCISENAME = "ExerciseName";
    private String WEIGHT = "Weight";
    private String SESSION = "Session";
    private String PLAN = "Plan";
    private String PASS = "Pass";

    public Boolean getPass(){return getBoolean(PASS);}
    public void setPass(Boolean b){put(PASS,b);}

    public Workout getWorkout(){return (Workout)get(WORKOUT);}
    public void setWorkout(Workout workout){put(WORKOUT,workout);}

    public String getWorkoutDay(){return getString(WORKOUTDAYNUBMER);}
    public void setWorkoutDay(String workoutDay){put(WORKOUTDAYNUBMER,workoutDay);}

    public String getReps(){return getString(REPS);}
    public void setReps(String reps){put(REPS,reps);}

    public String getWeight(){return getString(WEIGHT);}
    public void setWeight(String weight){put(WEIGHT,weight);}

    public String getExerciseName(){return getString(EXERCISENAME);}
    public void setExerciseName(String exerciseName){put(EXERCISENAME,exerciseName);}

    public Session getSession(){return (Session)get(SESSION);}
    public void setSession(Session session){put(SESSION,session);}

    public Plan getPlan(){return (Plan)get(PLAN);}
    public void setPlan(Plan plan){put(SESSION,plan);}

    public Date getDate(){return getCreatedAt();}








}
