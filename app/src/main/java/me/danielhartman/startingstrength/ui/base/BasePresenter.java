package me.danielhartman.startingstrength.ui.base;


public interface BasePresenter<T> {
    void present(T view);
    void remove();
    void onResume();
    void onPause();
    void onDestroy();
}
