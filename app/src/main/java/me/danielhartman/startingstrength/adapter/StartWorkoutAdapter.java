package me.danielhartman.startingstrength.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import me.danielhartman.startingstrength.Model.Exercise;
import me.danielhartman.startingstrength.R;

/**
 * Created by dphart9 on 10/21/2015.
 */
public class StartWorkoutAdapter extends RecyclerView.Adapter<StartWorkoutAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Exercise> mDataset;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position){
        viewHolder.workoutName.setText("");
        viewHolder.setsAndReps.setText("Sets");
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView workoutName, setsAndReps;
        public Button buttonDelete;

        public ViewHolder(View itemView) {
            super(itemView);

            workoutName = (TextView) itemView.findViewById(R.id.workoutName);
            setsAndReps = (TextView) itemView.findViewById(R.id.setsAndReps);
        }
    }

    public StartWorkoutAdapter(Context context, ArrayList<Exercise> objects) {
        this.mContext = context;
        this.mDataset = objects;

    }
    public void refreshAdapter(ArrayList<Exercise> list){
        this.mDataset = list;
        notifyDataSetChanged();
    }


}
