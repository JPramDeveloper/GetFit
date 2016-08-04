package me.danielhartman.startingstrength.Ui.ViewWorkout;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import me.danielhartman.startingstrength.Model.Workout;
import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.dagger.DaggerHolder;

public class ViewPlan_Fragment extends Fragment {

    private RecyclerView recyclerView;
    private View rootView;
    private Fragment mFragment;
    private Workout currentWorkout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.recyclerview, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        ButterKnife.bind(this, rootView);

        DaggerHolder.getInstance().component().inject(this);
        mFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.container);

        if(getArguments()!=null) {
            Bundle b = getArguments();
            currentWorkout = (Workout) b.getSerializable(ViewWorkout_Fragment.SELECTED_WORKOUT);
        }else{
            currentWorkout = new Workout();
        }
        return rootView;
    }
//
//
//    @Override
//    public void requestResult(ArrayList<ParseObject> list) {
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
//        mViewPlanAdapter = new ViewPlanAdapter(getActivity().getApplicationContext(),list, (WorkoutSelector) mFragment);
//        recyclerView.setAdapter(sectionedList(list,mViewPlanAdapter));
//    }
//
//    @Override
//    public void failedToRetreiveData() {
//    }
//
//    @Override
//    public void onItemClick(ParseObject parseObject) {
//        String notes = ((Plan)parseObject).getNotes();
//        if(notes.length()<=1){
//            notes = "No notes for this item";
//        }
//        ViewNotes(notes);
//    }
//
//    public void ViewNotes(String message){
//        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
//        alertDialog.setTitle("Notes");
//        alertDialog.setMessage(message);
//        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                (dialog, which)-> {
//                        dialog.dismiss();
//                });
//        alertDialog.show();
//    }
//    public SimpleSectionedRecyclerViewAdapter sectionedList(ArrayList<ParseObject> planArrayList, ViewPlanAdapter adapter )    {
//        List<SimpleSectionedRecyclerViewAdapter.Section> sections =new ArrayList<>();
//        int firstTitle=0;
//        int current;
//        for(int i = 0; i< planArrayList.size(); i++){
//            Plan p = (Plan)planArrayList.get(i);
//            current = Integer.parseInt(p.getDay());
//            if(current>firstTitle )
//            {
//                sections.add(new SimpleSectionedRecyclerViewAdapter.Section(i, p.getPlanName()));
//                firstTitle=current;
//            }
//        }
//
//        SimpleSectionedRecyclerViewAdapter.Section[] dummy = new SimpleSectionedRecyclerViewAdapter.Section[sections.size()];
//        SimpleSectionedRecyclerViewAdapter mSectionedAdapter =
//                new SimpleSectionedRecyclerViewAdapter(getActivity().getApplicationContext(),
//                        R.layout.recyclerview_divider, R.id.section_text, adapter);
//        mSectionedAdapter.setSections(sections.toArray(dummy));
//
//        return mSectionedAdapter;
//    }

}

