package me.danielhartman.startingstrength.Interfaces;

import com.google.firebase.auth.FirebaseUser;

import com.parse.ParseUser;

public interface LoginCallback {
    void successfulLogin();
    void failedLogin();
}
