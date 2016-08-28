package me.danielhartman.startingstrength.ui.viewWorkout;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.model.Workout;


public class ViewWorkoutAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Workout> data;

    public ViewWorkoutAdapter() {
        this.data = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_workout_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Workout w = data.get(position);
        ViewHolder h = (ViewHolder)holder;
        h.text.setText(w.getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.textView);
        }
    }
    public void setData(List<Workout> list){
        this.data = list;
        notifyDataSetChanged();
    }
}
