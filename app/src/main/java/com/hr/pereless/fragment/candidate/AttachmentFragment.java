package com.hr.pereless.fragment.candidate;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hr.pereless.R;
import com.hr.pereless.adapter.candidate.AttachmentAdpter;
import com.hr.pereless.dialog.AttachmentDialog;
import com.hr.pereless.dialog.CommunicationDialog;
import com.hr.pereless.model.candidate.AttachmentModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class AttachmentFragment extends Fragment implements View.OnClickListener {
    View view;
    RecyclerView rv_attachment;
    FloatingActionButton floating_action_button;
    AttachmentAdpter attachmentAdpter ;
    SwipeRefreshLayout main_swiperefresh;
    public static AttachmentFragment newInstance() {
        AttachmentFragment fragment = new AttachmentFragment();
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
        rv_attachment = view.findViewById(R.id.rv_attachment);
        floating_action_button = view.findViewById(R.id.floating_action_button);
        main_swiperefresh = view.findViewById(R.id.main_swiperefresh);

        attachmentAdpter = new AttachmentAdpter(getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rv_attachment.setLayoutManager(mLayoutManager);
        rv_attachment.setItemAnimator(new DefaultItemAnimator());
        rv_attachment.setAdapter(attachmentAdpter);
        main_swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doRefresh();
                    }
                }, 1000);
            }
        });
        floating_action_button.setOnClickListener(this);

        initLayout();

    }
    private void doRefresh() {
        main_swiperefresh.setRefreshing(false);
    }
    void initLayout(){
        List<AttachmentModel> attachementList = new ArrayList<>();
        for(int i =0;i<10;i++){
            AttachmentModel attachmentModel = new AttachmentModel();
            attachementList.add(attachmentModel);
        }
        attachmentAdpter.setData(attachementList);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floating_action_button:
                AttachmentDialog attachmentDialog = new AttachmentDialog();
                attachmentDialog.setOnConfirmListener(new AttachmentDialog.OnConfirmListener() {
                    @Override
                    public void galleryIntent() {

                    }

                    @Override
                    public void selectLocation() {

                    }

                    @Override
                    public void getWord() {

                    }

                    @Override
                    public void getPdf() {

                    }

                    @Override
                    public void getCamera() {

                    }
                });
                attachmentDialog.show(getParentFragmentManager(), "DeleteMessage");
                break;

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_attachment, container, false);
        return view;
    }
}