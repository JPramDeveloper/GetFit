package me.danielhartman.startingstrength.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Day implements Parcelable {
    public static final Creator<Day> CREATOR = new Creator<Day>() {
        @Override
        public Day createFromParcel(Parcel in) {
            return new Day(in);
        }

        @Override
        public Day[] newArray(int size) {
            return new Day[size];
        }
    };
    String name;
    List<Exercise> exercises;
    String description;

    public Day(String name) {
        this.name = name;
    }

    public Day() {
    }

    protected Day(Parcel in) {
        name = in.readString();
        exercises = in.createTypedArrayList(Exercise.CREATOR);
        description = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeTypedList(exercises);
        parcel.writeString(description);
    }
}
