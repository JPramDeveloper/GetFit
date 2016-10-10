package me.danielhartman.startingstrength.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Set implements Parcelable {
    public static final Creator<Set> CREATOR = new Creator<Set>() {
        @Override
        public Set createFromParcel(Parcel in) {
            return new Set(in);
        }

        @Override
        public Set[] newArray(int size) {
            return new Set[size];
        }
    };
    String exerciseName;
    String reps;
    String weight;
    String description;

    protected Set(Parcel in) {
        exerciseName = in.readString();
        reps = in.readString();
        weight = in.readString();
        description = in.readString();
    }

    public Set() {
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
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
        parcel.writeString(exerciseName);
        parcel.writeString(reps);
        parcel.writeString(weight);
        parcel.writeString(description);
    }
}
