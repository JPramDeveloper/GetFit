package me.danielhartman.startingstrength.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;


public abstract class BaseFragment<V> extends Fragment{

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
        getPresenter().present(getContractView());
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        getPresenter().onDestroy();
        super.onDestroy();
    }

    public abstract void initDagger();

    public abstract BasePresenter getPresenter();

    public Context getContext(){
        return getActivity().getApplicationContext();
    }

    public abstract V getContractView();





}
