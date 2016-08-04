package me.danielhartman.startingstrength.ui.createWorkout;


import me.danielhartman.startingstrength.model.Workout;

public class CreateWorkoutPresenter {
    private Workout workout;

    public Workout getWorkout(){
        if (workout==null){
            return new Workout();
        }
        return workout;
    }

}
