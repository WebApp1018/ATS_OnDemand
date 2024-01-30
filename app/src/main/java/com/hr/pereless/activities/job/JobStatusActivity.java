package com.hr.pereless.activities.job;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hr.pereless.R;
import com.hr.pereless.adapter.candidate.CandidateAdapterpagenation;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.model.candidate.CandidateModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class JobStatusActivity extends CommonActivity {
    private List<CandidateModel> candidateModels = new ArrayList<>();
    private CandidateAdapterpagenation jobListAdapter;
    @BindView(R.id.backarrow)
    ImageView backarrow;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.dotshorizontal)
    ImageView dotshorizontal;
    @BindView(R.id.profile_image)
    CircleImageView profileImage;
    @BindView(R.id.rv_newjobs)
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_status);
        ButterKnife.bind(this);

        jobListAdapter = new CandidateAdapterpagenation(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(jobListAdapter);
        loadJobList();
    }

    void loadJobList(){
        List<CandidateModel> candidateslist = new ArrayList<>();
        for(int i =0;i<10;i++){
            CandidateModel jobModel = new CandidateModel();
            candidateslist.add(jobModel);
        }
        jobListAdapter.addAll(candidateslist);
        recyclerView.post(new Runnable() {
            public void run() {
                jobListAdapter.notifyDataSetChanged();
            }
        });
    }
    @OnClick({R.id.backarrow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.backarrow:
                onBackPressed();
                break;
        }
    }

}