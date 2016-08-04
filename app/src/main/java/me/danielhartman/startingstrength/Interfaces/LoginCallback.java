package me.danielhartman.startingstrength.Interfaces;

import com.google.firebase.auth.FirebaseUser;


public interface LoginCallback {
    public void successfulLogin();
    public void failedLogin(String message);
}
