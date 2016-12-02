package me.danielhartman.startingstrength.ui.createWorkout;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.model.Day;
import me.danielhartman.startingstrength.model.Exercise;
import me.danielhartman.startingstrength.model.Set;

public class CreateDayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Object> mData;

    public CreateDayAdapter(List<Object> mData) {
        this.mData = mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        switch (viewType) {
            case 0:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.create_day_row_title, parent, false);
                return new TitleViewHolder(v);
            case 1:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.create_day_row, parent, false);
                return new SetViewHolder(v);
            case 2:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.create_day_title, parent, false);
                return new DayViewHolder(v);
            default:
                return null;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mData.get(position) instanceof Exercise) {
            return 0;
        } else if (mData.get(position) instanceof Set) {
            return 1;
        } else if (mData.get(position) instanceof Day) {
            return 2;
        } else {
            return -1;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 0) {
            Exercise exercise = (Exercise) mData.get(position);
            TitleViewHolder vh2 = (TitleViewHolder) holder;
            vh2.text.setText(exercise.getName());
        } else if (holder.getItemViewType() == 1) {
            Set s = (Set) mData.get(position);
            SetViewHolder setVH = (SetViewHolder) holder;
            String sb = String.valueOf(s.getReps()) +
                    " Reps at " +
                    s.getWeight() +
                    " lbs";
            setVH.text.setText(sb);
        } else if (holder.getItemViewType() == 2) {
            Day d = (Day) mData.get(position);
            DayViewHolder dayVH = (DayViewHolder) holder;
            dayVH.text.setText(d.getName());
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public List<Object> getData() {
        return mData;
    }

    public void setData(List<Object> list) {
        mData = list;
        notifyDataSetChanged();
    }

    public class SetViewHolder extends RecyclerView.ViewHolder {
        TextView text;

        public SetViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.dayText);
        }
    }

    public class TitleViewHolder extends RecyclerView.ViewHolder {
        TextView text;

        public TitleViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.ExerciseNameRowTitle);
        }

    }

    public class DayViewHolder extends RecyclerView.ViewHolder {
        TextView text;

        public DayViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.ExerciseNameRowTitle);
        }

    }
}
