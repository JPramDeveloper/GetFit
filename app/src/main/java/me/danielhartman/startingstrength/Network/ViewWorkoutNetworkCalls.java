package me.danielhartman.startingstrength.Network;


import android.util.Log;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;

import me.danielhartman.startingstrength.Interfaces.ViewWorkoutCallback;
import me.danielhartman.startingstrength.Model.Plan;
import me.danielhartman.startingstrength.Model.Workout;

public class ViewWorkoutNetworkCalls {
    private String WORKOUT_RELATION = "WorkoutRelation";
    private ArrayList<ParseObject> mWorkoutList;
    private ArrayList<ParseObject> mPlanList;
    public void fetchWorkouts(ViewWorkoutCallback callback){
        mWorkoutList = new ArrayList<>();
        ParseQuery<Workout> query = ParseQuery.getQuery(Workout.class);
        query.whereExists("WorkoutName");
        query.whereEqualTo(Workout.CREATEDBYUSER, ParseUser.getCurrentUser());
        query.findInBackground((list, e)-> {
               if (e==null){
                   Log.d("parseDebug", "done: with fetchWorkout ");
                   Log.d("parseDebug", "Returned : " + String.valueOf(list.size()));
                   for(Workout workout: list){
                      mWorkoutList.add(workout);
                  }
                  callback.requestResult(mWorkoutList);
               }else{
                   callback.failedToRetreiveData();
                   e.printStackTrace();
               }
        });
    }
    public void fetchPlans(ViewWorkoutCallback callback, Workout workout){
        mPlanList = new ArrayList<>();
        ParseQuery<Plan> query = ParseQuery.getQuery(Plan.class);
        query.whereExists("Sets");
        query.whereEqualTo(WORKOUT_RELATION, workout);
        query.findInBackground((list, e)-> {
            if (e==null){
                Log.d("parseDebug", "done: with fetchPlan ");
                Log.d("parseDebug", "Returned : " + String.valueOf(list.size()));
                for(Plan plan: list){
                    mPlanList.add(plan);
                }
                callback.requestResult(mPlanList);
            }else{
                callback.failedToRetreiveData();
                e.printStackTrace();
            }
        });
    }

}
