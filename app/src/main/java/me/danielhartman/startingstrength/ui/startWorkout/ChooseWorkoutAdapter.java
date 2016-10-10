package me.danielhartman.startingstrength.ui.startWorkout;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.model.Workout;

public class ChooseWorkoutAdapter extends RecyclerView.Adapter<ChooseWorkoutAdapter.ChooseWorkoutHolder> {

    private List<Workout> list;

    public ChooseWorkoutAdapter() {
        list = new ArrayList<>();
    }

    @Override
    public ChooseWorkoutHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.create_day_row_title, parent, false);
        return new ChooseWorkoutHolder(v);
    }


    @Override
    public void onBindViewHolder(ChooseWorkoutHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(List<Workout> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    protected class ChooseWorkoutHolder extends RecyclerView.ViewHolder

    {
        public ChooseWorkoutHolder(View itemView) {
            super(itemView);
        }
    }
}
