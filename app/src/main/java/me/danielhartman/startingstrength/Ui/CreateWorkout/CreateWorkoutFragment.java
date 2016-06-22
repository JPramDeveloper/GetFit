package me.danielhartman.startingstrength.ui.createWorkout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.ButterKnife;
import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.ui.MainActivity;

public class CreateWorkoutFragment extends Fragment {
    private View rootView;
    private Fragment mFragment;

    @Inject
    public CreateWorkoutPresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.create_workout_fragment, container, false);
        ButterKnife.bind(this, rootView);

        mFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.container);
        ((MainActivity)getActivity()).getCreateWorkoutComponent().inject(this);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getActivity().getApplicationContext(), mPresenter.getTestText(), Toast.LENGTH_SHORT).show();
        createExerciseDialog();
    }

    public void createExerciseDialog(){
       AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
       final View dialogView = getActivity().getLayoutInflater().inflate(R.layout.alert_dialogue,null);
       dialog.setView(dialogView);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView)(dialogView).findViewById(R.id.alertExerciseName);
        dialog.setPositiveButton("Done", (d, which)-> {
           d.cancel();
       });
        dialog.setNegativeButton("cancel", (d, which) ->{
           d.cancel();
        });
       dialog.create().show();
    }
}

