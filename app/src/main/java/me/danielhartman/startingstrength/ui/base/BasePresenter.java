package me.danielhartman.startingstrength.ui.base;


public interface BasePresenter<T> {
    void present(T view);
    void onDestroy();
}
