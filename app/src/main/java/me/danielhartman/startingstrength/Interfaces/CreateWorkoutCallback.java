package me.danielhartman.startingstrength.Interfaces;


public interface CreateWorkoutCallback {
    void onUploadComplete();
    void onImageUploadError(String message);
    void onWorkoutUploadError(String message);
}
