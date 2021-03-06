package me.danielhartman.startingstrength.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.OnClick;
import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.ui.accountManagement.AccountActivity;
import me.danielhartman.startingstrength.ui.accountManagement.LoginPresenter;
import me.danielhartman.startingstrength.ui.createWorkout.CreateWorkoutActivity;
import butterknife.ButterKnife;
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
        Intent i = new Intent(getActivity().getApplicationContext(), CreateWorkoutActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.viewWorkouts)
    public void viewWorkoutsOnClick(){
        Intent i = new Intent(getActivity().getApplicationContext(), AccountActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.createWorkout)
    public void workoutManager(){

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
