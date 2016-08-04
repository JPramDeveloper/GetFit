package me.danielhartman.startingstrength.Ui.General;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import butterknife.ButterKnife;
import butterknife.OnClick;
import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.Ui.ViewWorkout.ViewWorkout_Activity;

public class settings_frag extends Fragment {
    private View rootView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.settings_fragment, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
    @OnClick(R.id.logOut)
    public void clickLogout(){

    }

}
