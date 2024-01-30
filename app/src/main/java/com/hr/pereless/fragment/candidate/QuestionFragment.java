package com.hr.pereless.fragment.candidate;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hr.pereless.R;
import com.hr.pereless.adapter.candidate.TimelineAdapter;
import com.hr.pereless.model.candidate.TimeLineModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class QuestionFragment extends Fragment {
    View view;
    RecyclerView recycleTimeline;
    FloatingActionButton floating_action_button;
    TimelineAdapter timelineAdapter;
    public static QuestionFragment newInstance() {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycleTimeline = view.findViewById(R.id.recycleTimeline);
        floating_action_button = view.findViewById(R.id.floating_action_button);
        timelineAdapter = new TimelineAdapter(getContext());

        recycleTimeline.setAdapter(timelineAdapter);
        recycleTimeline.setLayoutManager(new LinearLayoutManager(getContext()));
        initLayout();
    }
    void initLayout(){
        List<TimeLineModel> timelinedatumList= new ArrayList<>();
        for(int i =0;i<20;i++){
            TimeLineModel timeLineModel = new TimeLineModel();
            timeLineModel.setType(i%3+1);
            timelinedatumList.add(timeLineModel);
        }
        timelineAdapter.setDate(timelinedatumList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_question, container, false);
        return view;
    }
}