package me.danielhartman.startingstrength.Interfaces;

public interface LoginCallback {
    public void successfulLogin();

    public void failedLogin(String message);
}
