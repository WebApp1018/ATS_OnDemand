package com.hr.pereless.fragment.report;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.hr.pereless.R;
import com.hr.pereless.adapter.reporter.ReportAdapter;
import com.hr.pereless.model.NotificationModel;
import com.hr.pereless.model.report.ReportModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ReporterFragment extends Fragment {
    View view;
    SwipeRefreshLayout main_swiperefresh;
    RecyclerView report_rv;
    ReportAdapter reportAdapter ;
    WebView webview;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        main_swiperefresh = view.findViewById(R.id.main_swiperefresh);
        report_rv = view.findViewById(R.id.report_rv);
        webview = view.findViewById(R.id.webview);
        reportAdapter = new ReportAdapter(getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        report_rv.setLayoutManager(mLayoutManager);
        report_rv.setItemAnimator(new DefaultItemAnimator());
        report_rv.setAdapter(reportAdapter);
        main_swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doRefresh();
                    }
                }, 2500);
            }
        });
        getReportDate();

        initLayout();

    }
    void initLayout(){
        String html = "<p>Physical Requirements</p>\r\n<ol>\r\n    <li>Withstand temperatures of 0 degrees Fahrenheit or less and 100 degrees Fahrenheit or more</li>\r\n    <li>Move throughout the restaurant for extended periods of time (up to 10-12 hours per day)</li>\r\n    <li>Move 50 lbs. for distances of up to 10 ft.</li>\r\n    <li>Balance and move up to 25 lbs. for distances of up to 50 ft.</li>\r\n    <li>Understand and respond to Crew Members’ and guests’ requests in a loud environment</li>\r\n    <li>Perform basic math and understand finances and cost management.</li>\r\n</ol>\r\n<p>Education/Experience Requirements</p>\r\n<ol>\r\n    <li>High school equivalency required; college coursework preferred</li>\r\n    <li>Previous supervisory and hospitality industry experience preferred</li>\r\n    <li>Proficient communication in English (verbal and in writing)</li>\r\n    <li>Minimum 21 years of age</li>\r\n    <li>Proven track record of success as a restaurant manager</li>\r\n</ol>\r\n<p>This description is not intended and should not be construed to be an exhaustive list of all responsibilities, skills, effort, or work conditions associated with the job. It is intended to be an accurate reflection of the principal job elements essential for making employment decisions.</p>\r\n<p>Management Referral Program:<br />\r\nAfter joining our Team, if you refer a manager candidate from outside the company and they are hired, you can receive a bonus from our Staffing Department. You can get details from our Staffing Department at (800) 248-4938</p>\r\n<p>Red Lobster is proud to be a leader in recognizing the value that diversity offers throughout the restaurant industry.</p>";
        Log.d("aaaaaaaa",html);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setPluginState(WebSettings.PluginState.ON);
        webview.loadDataWithBaseURL(null,html, "text/html", "UTF-8",null);
    }

    void getReportDate(){
        List<ReportModel> candidateslist = new ArrayList<>();
        for(int i =0;i<10;i++){
            ReportModel candidateModel = new ReportModel();
            candidateslist.add(candidateModel);
        }
        reportAdapter.addAll(candidateslist);
    }
    private void doRefresh() {
        reportAdapter.getMovies().clear();
        reportAdapter.notifyDataSetChanged();
        getReportDate();
        main_swiperefresh.setRefreshing(false);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_reporter, container, false);
        return  view;
    }
}