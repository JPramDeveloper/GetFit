package me.danielhartman.startingstrength.data;

import java.util.ArrayList;
import java.util.List;

import me.danielhartman.startingstrength.model.Day;
import me.danielhartman.startingstrength.model.Exercise;
import me.danielhartman.startingstrength.model.Set;
import me.danielhartman.startingstrength.model.Workout;

public class WorkoutCreator {

    private Workout workout;
    private int currentDay = 0;

    public Workout getWorkout() {
        if (workout == null) {
            initializeWorkout();
        }
        return workout;
    }

    Workout initializeWorkout() {
        workout = new Workout();
        workout.setDays(new ArrayList<>());
        for (int i = 0; i <= 6; i++) {
            workout.getDays().add(new Day("Day " + String.valueOf(i + 1)));
            workout.getDays().get(i).setExercises(new ArrayList<>());
        }
        return workout;
    }

    Workout clearWorkout() {
        workout = null;
        return workout;
    }

    List<Object> getSetsForGivenDay(int day) {
        List<Object> list = new ArrayList<>();
        for (Exercise e : getExercisesForDay(day)) {
            list.add(e);
            for (Set s : e.getSets()) {
                list.add(s);
            }
        }
        return list;
    }

    Exercise createExercise(String name, String weight, String reps) {
        Exercise exercise = makeExercise(name);

        Set set = new Set();
        set.setExerciseName(name);
        set.setReps(reps);
        set.setWeight(weight);
        exercise.getSets().add(set);

        return exercise;
    }

    public void addExerciseToDay(String name, String weight, String reps) {
        createExercise(name, weight, reps);
//        currentDayAdapter.setData(getSetsForGivenDay(currentDay));
    }

    public Exercise makeExercise(String exerciseName) {
        return findExistingExercise(exerciseName) != null ? findExistingExercise(exerciseName) : createNewExercise(exerciseName);
    }

    public Exercise findExistingExercise(String exerciseName) {
        for (Exercise e : getExercisesForDay(currentDay)) {
            if (e.getName().equalsIgnoreCase(exerciseName)) {
                return e;
            }
        }
        return null;
    }

    public Exercise createNewExercise(String exerciseName) {
        Exercise newExercise = new Exercise();
        newExercise.setName(exerciseName);
        newExercise.setSets(new ArrayList<>());
        getExercisesForDay(currentDay).add(newExercise);
        return newExercise;
    }

    public Workout pruneUnusedDays(Workout workout) {
        Workout w = workout;
        List<Day> daysWithExercises = new ArrayList<>();
        for (Day day : w.getDays()) {
            if (day.getExercises().size() >= 1) {
                daysWithExercises.add(day);
            }
        }
        w.setDays(daysWithExercises);
        return w;
    }

    public List<Exercise> getExercisesForDay(int day) {
        return getWorkout().getDays().get(day).getExercises();
    }


}
