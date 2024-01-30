package com.hr.pereless.fragment.candidate;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hr.pereless.R;
import com.hr.pereless.activities.MainActivity;
import com.hr.pereless.activities.candidate.CandidateDetailActivity;
import com.hr.pereless.adapter.candidate.TimelineAdapter;
import com.hr.pereless.api.API;
import com.hr.pereless.application.AppController;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.model.candidate.CandidateModel;
import com.hr.pereless.model.candidate.CandidateUserModel;
import com.hr.pereless.model.candidate.TimeLineModel;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeLineFragment extends Fragment {
    View view;
    RecyclerView recycleTimeline;
    FloatingActionButton floating_action_button;
    TimelineAdapter timelineAdapter;
    static CandidateModel candidateModel = new CandidateModel();
    CandidateDetailActivity context;
    public static TimeLineFragment newInstance(CandidateModel model) {
        candidateModel = model;
        TimeLineFragment fragment = new TimeLineFragment();
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
        initLayout();
    }
    void initLayout(){
        context.showProgress();
        String api_link = API.GET_TIMELINE + String.valueOf(candidateModel.getRid()) + "/" + String.valueOf(candidateModel.getJid()) ;
        StringRequest myRequest = new StringRequest(
                Request.Method.GET,
                api_link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                        context.closeProgress();
                        try {
                            JSONArray jsonArray = new JSONArray(json);
                            List<TimeLineModel> timelinedatumList= new ArrayList<>();
                            for(int i =0;i<jsonArray.length();i++){
                                TimeLineModel timeLineModel = new TimeLineModel();
                                timeLineModel.initModel(jsonArray.getJSONObject(i));
                                timelinedatumList.add(timeLineModel);
                            }
                            timelineAdapter = new TimelineAdapter(getContext());
                            recycleTimeline.setAdapter(timelineAdapter);
                            recycleTimeline.setLayoutManager(new LinearLayoutManager(getContext()));
                            timelineAdapter.setDate(timelinedatumList);
                        }catch (Exception e){
                            context.closeProgress();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        context.closeProgress();

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
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = (CandidateDetailActivity) context;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_time_line, container, false);
        return view;
    }
}