package me.danielhartman.startingstrength.Ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.Ui.AccountManagement.Login_Fragment;
import me.danielhartman.startingstrength.Ui.CreateWorkout.CreateWorkoutName;
import me.danielhartman.startingstrength.Ui.ViewWorkout.ViewWorkout_Activity;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainMenu_Fragment extends Fragment {


    private View rootView;
    private Boolean expanded = true;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.main_menu_frag, container, false);
        ButterKnife.bind(this, rootView);
        getActivity().setTitle("Main Menu");

        return rootView;
    }
    @OnClick(R.id.startWorkout)
    public void startWorkout(){
        nextFragmentForMain(new CreateWorkoutName(),R.id.container);
    }
    @OnClick(R.id.viewWorkouts)
    public void viewWorkoutsOnClick(){
        nextFragmentForMain(new Login_Fragment(),R.id.container);
    }
    @OnClick(R.id.createWorkout)
    public void workoutManager(){
        Intent i = new Intent(getActivity().getApplicationContext(),ViewWorkout_Activity.class);
        startActivity(i);
    }

    @OnClick(R.id.graphMenuButton)
    public void graphButtonClick(){

        
    }

    public void nextFragmentForMain(Fragment fragment, Integer view){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(view,fragment,null);
        fragmentTransaction.addToBackStack("menu");
        fragmentTransaction.commit();
    }


}
