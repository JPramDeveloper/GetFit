package me.danielhartman.startingstrength.ui.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;


public abstract class BaseFragment extends Fragment {

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().onResume();
    }

    @Override
    public void onPause() {
        getPresenter().onPause();
        super.onPause();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        initDagger();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        getPresenter().onDestroy();
        super.onDestroy();
    }

    public abstract void initDagger();

    public abstract void presentViews();

    public abstract Presenter getPresenter();


}
