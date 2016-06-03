package me.danielhartman.startingstrength.Interfaces;

public interface FirebaseUserCallback {
    void returnUserSuccess(com.google.firebase.auth.FirebaseUser user);
    void returnNullUser(com.google.firebase.auth.FirebaseUser user);
}
