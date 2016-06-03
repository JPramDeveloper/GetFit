package me.danielhartman.startingstrength.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import me.danielhartman.startingstrength.Model.Exercise;
import me.danielhartman.startingstrength.R;

/**
 * Created by dphart9 on 10/21/2015.
 */
public class ViewSessionAdapter extends RecyclerView.Adapter<ViewSessionAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Exercise> mDataset;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_session_recycler_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position){
        Exercise exercise = mDataset.get(position);
        viewHolder.repsText.setText(exercise.getReps() + " reps at " + exercise.getWeight() + "lbs");
        if(exercise.getPass()) {
            viewHolder.passFail.setBackgroundResource(R.drawable.check);
        }else{viewHolder.passFail.setBackgroundResource(R.drawable.cancel);}
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView repsText;
        public ImageView passFail;
        public ViewHolder(View itemView) {
            super(itemView);
            repsText=(TextView)itemView.findViewById(R.id.repsAndWeight);
            passFail = (ImageView)itemView.findViewById(R.id.passFailImage);

        }
    }

    public ViewSessionAdapter(Context context, ArrayList<Exercise> objects) {
        this.mContext = context;
        this.mDataset = objects;

    }
    public void refreshAdapter(ArrayList<Exercise> list){
        this.mDataset = list;
        notifyDataSetChanged();
    }


}
