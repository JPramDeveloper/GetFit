package me.danielhartman.startingstrength.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Log;


public abstract class BaseFragment<V> extends Fragment {
    String TAG = BaseFragment.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        initDagger();
        try {
            getPresenter().present(getContractView());
        } catch (NullPointerException npe) {
            Log.e(TAG, "Null Pointer. Presenter must be set in InitDagger();", npe);
            throw npe;
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        getPresenter().onDestroy();
        super.onDestroy();
    }

    public abstract void initDagger();

    public abstract BasePresenter getPresenter();

    public Context getContext() {
        return getActivity().getApplicationContext();
    }

    public abstract V getContractView();

    public ActionBar getActionBar() {
        return ((BaseActivity) (getActivity())).getActionbar();
    }
}
