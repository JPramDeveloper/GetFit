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

public class CreateDayAdapter extends RecyclerView.Adapter<CreateDayAdapter.ViewHolder>{

    List<Set> mData;

    public CreateDayAdapter(List<Set> mData) {
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        switch (viewType){
            case 0:
                v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.create_day_row, parent, false);
            default:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.create_day_row, parent, false);

        }
        CreateDayAdapter.ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Set set = mData.get(position);
        holder.text.setText(set.getExerciseName());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView)itemView.findViewById(R.id.dayText);
        }
    }
    public class ViewHolder2 extends RecyclerView.ViewHolder {
        TextView text;

        public ViewHolder2(View itemView) {
            super(itemView);
            text = (TextView)itemView.findViewById(R.id.dayText);
        }
    }
    public void setData(List<Set> list){
        mData = list;
        notifyDataSetChanged();
    }
    public List<Set> getData() {
        return mData;
    }
}
