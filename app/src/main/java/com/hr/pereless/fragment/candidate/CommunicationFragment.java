package com.hr.pereless.fragment.candidate;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hr.pereless.R;
import com.hr.pereless.adapter.candidate.CommunicateAdpter;
import com.hr.pereless.adapter.candidate.TimelineAdapter;
import com.hr.pereless.commons.Constants;
import com.hr.pereless.dialog.CommunicationDialog;
import com.hr.pereless.dialog.CountrySelectDialog;
import com.hr.pereless.model.candidate.CommunicationModel;
import com.hr.pereless.model.candidate.TimeLineModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

public class CommunicationFragment extends Fragment implements View.OnClickListener {
    View view;
    RecyclerView recycleTimeline;
    FloatingActionButton floating_action_button;
    TimelineAdapter communicateAdpter;
    public static CommunicationFragment newInstance() {
        CommunicationFragment fragment = new CommunicationFragment();
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

        communicateAdpter = new TimelineAdapter(getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recycleTimeline.setLayoutManager(mLayoutManager);
        recycleTimeline.setItemAnimator(new DefaultItemAnimator());
        recycleTimeline.setAdapter(communicateAdpter);
        floating_action_button.setOnClickListener(this);
        initLayout();
    }
    void initLayout(){
        List<TimeLineModel> timelinedatumList= new ArrayList<>();
        for(int i =0;i<20;i++){
            TimeLineModel timeLineModel = new TimeLineModel();
            if(i%6<=2)
                timeLineModel.setType(1);
            else if(i%6<=4)
                timeLineModel.setType(2);
            else
                timeLineModel.setType(3);
            timelinedatumList.add(timeLineModel);
        }
        communicateAdpter.setDate(timelinedatumList);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floating_action_button:
                CommunicationDialog countrySelectDialog = new CommunicationDialog();
                countrySelectDialog.setOnConfirmListener(new CommunicationDialog.OnConfirmListener() {
                    @Override
                    public void onConfirm(String str) {

                    }
                },"Enter Message");
                countrySelectDialog.show(getParentFragmentManager(), "DeleteMessage");
                break;

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_communication, container, false);
        return view;
    }
}