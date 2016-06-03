package me.danielhartman.startingstrength.adapter;

import com.parse.ParseObject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import me.danielhartman.startingstrength.Interfaces.WorkoutSelector;
import me.danielhartman.startingstrength.Model.Workout;
import me.danielhartman.startingstrength.R;

public class ViewWorkoutAdapter extends RecyclerView.Adapter<ViewWorkoutAdapter.SimpleViewHolder> {

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        TextView routines;
        TextView workoutName, notes;
        View body;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            notes = (TextView) itemView.findViewById(R.id.workoutNotes);
            workoutName = (TextView) itemView.findViewById(R.id.workoutName);
            routines = (TextView) itemView.findViewById(R.id.numberOfRoutines);
            body = itemView.findViewById(R.id.body);
        }
    }

    private ArrayList<ParseObject> mDataset;
    private WorkoutSelector mCallback;

    //protected SwipeItemRecyclerMangerImpl mItemManger = new SwipeItemRecyclerMangerImpl(this);

    public ViewWorkoutAdapter(Context context, ArrayList<ParseObject> objects, WorkoutSelector callback) {
        this.mDataset = objects;
        mCallback = callback;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_workout_list_item, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder viewHolder, final int position) {
        Workout myWorkout = (Workout)mDataset.get(position);
        viewHolder.workoutName.setText(myWorkout.getWorkoutName());
        if (myWorkout.getNotes()==null || myWorkout.getNotes().length()<1) {
           viewHolder.notes.setText("No Description Available");
        }else { viewHolder.notes.setText(myWorkout.getNotes());}
        viewHolder.routines.setText(myWorkout.getNumberOfPlans() + " Exercises over " + myWorkout.getWorkoutRoutines()+ " Day(s)");

        viewHolder.body.setOnClickListener((view)->{
            mCallback.onItemClick(myWorkout);
        });

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void refreshAdapter(ArrayList<ParseObject> list){
        this.mDataset = list;
        notifyDataSetChanged();
    }


}
