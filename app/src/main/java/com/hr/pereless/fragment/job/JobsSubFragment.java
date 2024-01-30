package com.hr.pereless.fragment.job;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.LinearLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hr.pereless.R;
import com.hr.pereless.activities.MainActivity;
import com.hr.pereless.activities.job.CreateJobActivity;
import com.hr.pereless.activities.schedule.CreateScheduleActivity;
import com.hr.pereless.adapter.job.JobListAdapter;
import com.hr.pereless.api.API;
import com.hr.pereless.application.AppController;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.model.home.ActivityChatModel;
import com.hr.pereless.model.home.StatusModel;
import com.hr.pereless.model.job.JobModel;
import com.hr.pereless.pagination.PaginationScrollListener;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hr.pereless.base.BaseActivity._context;
import static com.hr.pereless.base.BaseActivity.closeProgress;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link JobsSubFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JobsSubFragment extends Fragment {
    View view;
    MainActivity mainActivity ;
    LinearLayout layout_main;
    SwipeRefreshLayout main_swiperefresh;
    FloatingActionButton floating_action_button;
    RecyclerView rv_joblist;
    JobListAdapter jobListAdapter;
    private static final int PAGE_START = 1;
    private int currentPage = PAGE_START;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private static int TOTAL_PAGES;
    Dialog filter;
    List<JobModel> candidateslist = new ArrayList<>();
    public static JobsSubFragment newInstance() {
        JobsSubFragment fragment = new JobsSubFragment();
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
        layout_main = view.findViewById(R.id.layout_main);
        main_swiperefresh = view.findViewById(R.id.main_swiperefresh);
        floating_action_button = view.findViewById(R.id.floating_action_button);
        rv_joblist = view.findViewById(R.id.rv_joblist);
        jobListAdapter = new JobListAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_joblist.setLayoutManager(linearLayoutManager);
        rv_joblist.setItemAnimator(new DefaultItemAnimator());
        rv_joblist.setAdapter(jobListAdapter);

        floating_action_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivityForResult(new Intent(_context, CreateJobActivity.class),1);
                getActivity().overridePendingTransition(0, 0);
            }
        });
        rv_joblist.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                currentPage += 1;
                if (isLastPage == false) {
                    loadJobList(currentPage);
                } else {
                    isLoading = false;
                }
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
        main_swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                doRefresh();
            }
        });

        loadJobList(currentPage);
    }

    void loadJobList(int page){
        if(page == 1) candidateslist.clear();
        mainActivity.showProgress();
        StringRequest myRequest = new StringRequest(
                Request.Method.GET,
                API.GET_JOBS + "&page=" + String.valueOf(page),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                       closeProgress();
                       try {
                           JSONObject jsonObject = new JSONObject(json);
                           JSONArray jsonArray = jsonObject.getJSONArray("data");
                           if(jsonObject.getJSONObject("pagination").getInt("totalPages") == currentPage-1){
                               isLastPage = true;
                           }
                           for(int i =0;i<jsonArray.length();i++){
                               JobModel jobModel = new JobModel();
                               jobModel.initModel(jsonArray.getJSONObject(i));
                               candidateslist.add(jobModel);
                           }
                           jobListAdapter.addAll(candidateslist);
                           rv_joblist.post(new Runnable() {
                               public void run() {
                                   jobListAdapter.notifyDataSetChanged();
                               }
                           });
                       }catch (Exception e){

                       }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        closeProgress();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", Commons.token);
                return params;
            }
        };
        myRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(myRequest, "tag");
    }
    private void doRefresh() {
        jobListAdapter.getMovies().clear();
        jobListAdapter.notifyDataSetChanged();
        currentPage = PAGE_START;
        loadJobList(currentPage);
        main_swiperefresh.setRefreshing(false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=  inflater.inflate(R.layout.fragment_jobs_sub, container, false);
        return view;
    }
}