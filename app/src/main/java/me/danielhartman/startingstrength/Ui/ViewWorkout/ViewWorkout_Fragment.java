package me.danielhartman.startingstrength.Ui.ViewWorkout;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.dagger.DaggerHolder;

public class ViewWorkout_Fragment extends Fragment{

    public static String SELECTED_WORKOUT = "selectedworkout";
    private RecyclerView recyclerView;
    private View rootView;
    private Fragment mFragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.recyclerview, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        ButterKnife.bind(this, rootView);

        DaggerHolder.getInstance().component().inject(this);
        mFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.container);

        return rootView;
    }

//    @Override
//    public void requestResult(ArrayList<ParseObject> workouts) {
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
//        mViewWorkoutAdapter = new ViewWorkoutAdapter(getActivity().getApplicationContext(), workouts,(WorkoutSelector)mFragment );
//        recyclerView.setAdapter(mViewWorkoutAdapter);
//    }
//
//    @Override
//    public void failedToRetreiveData() {
//
//    }

//    @Override
//    public void onItemClick(ParseObject workout) {
//        ViewPlan_Fragment fragment = new ViewPlan_Fragment();
//        Bundle b = new Bundle();
//        b.putSerializable(SELECTED_WORKOUT,(Workout)workout);
//        fragment.setArguments(b);
//        getActivity().getSupportFragmentManager().beginTransaction()
//                .replace(R.id.container,fragment)
//                .addToBackStack(null)
//                .commit();
//    }

}

