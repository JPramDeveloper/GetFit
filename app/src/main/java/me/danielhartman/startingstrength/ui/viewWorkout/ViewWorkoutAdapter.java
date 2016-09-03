package me.danielhartman.startingstrength.ui.viewWorkout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.model.Day;
import me.danielhartman.startingstrength.model.Exercise;
import me.danielhartman.startingstrength.model.Workout;


public class ViewWorkoutAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Workout> data;
    Context context;

    public ViewWorkoutAdapter() {
        this.data = new ArrayList<>();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_workout_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Workout w = data.get(position);
        ViewHolder h = (ViewHolder) holder;
        h.text.setText(w.getName());
        StringBuilder sb;
        sb = new StringBuilder()
                .append(String.valueOf(getNumberOfExercises(position)))
                .append(" Exercises over ")
                .append(new SpannableString(String.valueOf(w.getDays().size())))
                .append(" Days");
        h.numberOfExercises.setText(sb.toString());
        if (w.getId() != null && w.getId().length() >= 3) {
            Picasso.with(context).load(w.getId())
                    .fit().centerInside().into(h.workoutImage);
        } else {
            Picasso.with(context).load(R.drawable.lifting_man).fit().centerInside().into(h.workoutImage);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Workout> list) {
        this.data = list;
        notifyDataSetChanged();
    }

    public Integer getNumberOfExercises(int position) {
        Integer exerciseCount = 0;
        Workout w = data.get(position);
        for (Day d : w.getDays()) {
            for (Exercise ignored : d.getExercises()) {
                exerciseCount++;
            }
        }
        return exerciseCount;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        TextView text, numberOfExercises;
        ImageView workoutImage;

        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.textView);
            numberOfExercises = (TextView) itemView.findViewById(R.id.numberOfExercises);
            workoutImage = (ImageView) itemView.findViewById(R.id.workoutImage);
        }
    }

}
