package com.hr.pereless.activities.report;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hr.pereless.R;
import com.hr.pereless.activities.auth.LoginActivity;
import com.hr.pereless.adapter.reporter.ReportdetailAdapter;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.model.report.ReportModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReportActivity extends CommonActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.img_scorecard)
    ImageView imgScorecard;
    @BindView(R.id.logout)
    ImageView logout;
    @BindView(R.id.rv_report)
    RecyclerView recyclerView;
    private List<ReportModel> jobListList = new ArrayList<>();
    private ReportdetailAdapter reportdetailAdapter;
    public AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);
        txtTitle.setText("Report Name");
        reportdetailAdapter = new ReportdetailAdapter(ReportActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ReportActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(reportdetailAdapter);
        logout.setVisibility(View.VISIBLE);
        logout.setVisibility(View.GONE);
        prepareData();

    }

    private void prepareData() {
        List<ReportModel> candidateslist = new ArrayList<>();
        for(int i =0;i<10;i++){
            ReportModel candidateModel = new ReportModel();
            candidateslist.add(candidateModel);
        }
        reportdetailAdapter.addAll(candidateslist);
    }
    @OnClick({R.id.logout,R.id.img_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {


            case  R.id.img_back:

                onBackPressed();

                break;
            case R.id.logout:

                break;
        }
    }

}