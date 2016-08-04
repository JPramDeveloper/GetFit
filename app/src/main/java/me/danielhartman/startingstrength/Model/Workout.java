package me.danielhartman.startingstrength.Model;

import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class Workout {
    List<Day> days;
    String description;
    FirebaseUser createdBy;
}
