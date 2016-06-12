package me.danielhartman.startingstrength.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.ui.accountManagement.LoginFragment;
import me.danielhartman.startingstrength.ui.createWorkout.CreateWorkoutName;
import me.danielhartman.startingstrength.ui.viewWorkout.ViewWorkout_Activity;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainMenu_Fragment extends Fragment {


    private View rootView;
    private Boolean expanded = true;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.main_menu_frag, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.startWorkout)
    public void startWorkout(){
        replaceFragment(new CreateWorkoutName(),R.id.container);
    }

    @OnClick(R.id.viewWorkouts)
    public void viewWorkoutsOnClick(){
        replaceFragment(new LoginFragment(),R.id.container);
    }

    @OnClick(R.id.createWorkout)
    public void workoutManager(){
        Intent i = new Intent(getActivity().getApplicationContext(),ViewWorkout_Activity.class);
        startActivity(i);
    }

    @OnClick(R.id.graphMenuButton)
    public void graphButtonClick(){}

    public void replaceFragment(Fragment fragment, Integer view){
    getActivity().getSupportFragmentManager().beginTransaction().replace(view,fragment).addToBackStack(null).commit();
    }


}
