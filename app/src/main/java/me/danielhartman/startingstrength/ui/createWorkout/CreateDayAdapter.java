package me.danielhartman.startingstrength.ui.createWorkout;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.model.Exercise;

public class CreateDayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    List<Object> mData;

    public CreateDayAdapter(List<Object> mData) {
        this.mData = mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        switch (viewType){
            case 0:
                v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.create_day_row_title, parent, false);
                RecyclerView.ViewHolder vh = new TitleViewHolder(v);
                return vh;
            default:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.create_day_row, parent, false);
               RecyclerView.ViewHolder vh2 = new SetViewHolder(v);
                return vh2;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(mData.get(position)instanceof Exercise){
            return 0;
        }
        return 1;
                }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType()==0){
            Exercise exercise =  (Exercise)mData.get(position);
            TitleViewHolder vh2 = (TitleViewHolder)holder;
            vh2.text.setText(exercise.getName());
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class SetViewHolder extends RecyclerView.ViewHolder {
        TextView text;

        public SetViewHolder(View itemView) {
            super(itemView);
            text = (TextView)itemView.findViewById(R.id.dayText);
        }
    }
    public class TitleViewHolder extends RecyclerView.ViewHolder {
        TextView text;

        public TitleViewHolder(View itemView) {
            super(itemView);
            text = (TextView)itemView.findViewById(R.id.ExerciseNameRowTitle);
        }

    }
    public void setData(List<Object> list){
        mData = list;
        notifyDataSetChanged();
    }
    public List<Object> getData() {
        return mData;
    }
}
