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

public class CreateDayAdapter extends RecyclerView.Adapter<CreateDayAdapter.ViewHolder>{

    List<Exercise> mData;

    public CreateDayAdapter(List<Exercise> mData) {
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.create_day_row, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Exercise e = mData.get(position);
        holder.text.setText(e.getName());
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
    public void setData(List<Exercise> list){
        mData = list;
        notifyDataSetChanged();
    }
}
