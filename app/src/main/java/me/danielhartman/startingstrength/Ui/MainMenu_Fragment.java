package me.danielhartman.startingstrength.Ui;


import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.Ui.AccountManagement.AccountActivity;
import me.danielhartman.startingstrength.Ui.AccountManagement.LoginPresenter;
import me.danielhartman.startingstrength.Ui.CreateWorkout.CreateWorkoutName;
import me.danielhartman.startingstrength.Ui.ViewWorkout.ViewWorkout_Activity;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.danielhartman.startingstrength.dagger.DaggerHolder;

public class MainMenu_Fragment extends Fragment {
    private View rootView;
    @Inject
    LoginPresenter loginPresenter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.main_menu_frag, container, false);
        DaggerHolder.getInstance().component().inject(this);
        ButterKnife.bind(this, rootView);
        getActivity().setTitle("Main Menu");
        return rootView;
    }

    @OnClick(R.id.startWorkout)
    public void startWorkout(){
        replaceFragment(new CreateWorkoutName(),R.id.container);
    }

    @OnClick(R.id.viewWorkouts)
    public void viewWorkoutsOnClick(){
        Intent i = new Intent(getActivity().getApplicationContext(), AccountActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.createWorkout)
    public void workoutManager(){
        Intent i = new Intent(getActivity().getApplicationContext(),ViewWorkout_Activity.class);
        startActivity(i);
    }

    @OnClick(R.id.logoutMenuButton)
    public void logoutClick(){
        loginPresenter.logout();
        Intent i = new Intent(getActivity().getApplicationContext(), AccountActivity.class);
        startActivity(i);
    }

    public void replaceFragment(Fragment fragment, Integer view){
      getFragmentManager().beginTransaction()
        .replace(view,fragment,null)
        .addToBackStack("menu")
        .commit();
    }


}
