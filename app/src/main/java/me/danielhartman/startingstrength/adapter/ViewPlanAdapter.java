package me.danielhartman.startingstrength.adapter;

import com.parse.ParseObject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import me.danielhartman.startingstrength.Interfaces.WorkoutSelector;
import me.danielhartman.startingstrength.Model.Plan;
import me.danielhartman.startingstrength.R;

public class ViewPlanAdapter extends RecyclerView.Adapter<ViewPlanAdapter.ViewHolder> {

    private ArrayList<ParseObject> mDataset;
    private WorkoutSelector mCallback;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_item, parent, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position){
        Plan plan = (Plan)mDataset.get(position);
        viewHolder.workoutName.setText(plan.getExercise());
        viewHolder.setsAndReps.setText("Sets: " + plan.getSets() + " Reps: "+ plan.getReps());
        viewHolder.clipboardIcon.setOnClickListener((v)-> {
                mCallback.onItemClick(plan);
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView workoutName, setsAndReps;
        public Button clipboardIcon;

        public ViewHolder(View itemView) {
            super(itemView);

            workoutName = (TextView) itemView.findViewById(R.id.workoutName);
            setsAndReps = (TextView) itemView.findViewById(R.id.setsAndReps);
            clipboardIcon = (Button) itemView.findViewById(R.id.clipboardIcon);

        }
    }
    public ViewPlanAdapter(Context context, ArrayList<ParseObject> objects, WorkoutSelector callback) {
        this.mDataset = objects;
        this.mCallback = callback;

    }



}
