package com.hr.pereless.fragment.candidate;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hr.pereless.R;
import com.hr.pereless.activities.candidate.CandidateDetailActivity;
import com.hr.pereless.adapter.candidate.EducationAdpter;
import com.hr.pereless.adapter.candidate.TimelineAdapter;
import com.hr.pereless.adapter.candidate.WorkhistoryAdpter;
import com.hr.pereless.api.API;
import com.hr.pereless.application.AppController;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.model.candidate.CandidateModel;
import com.hr.pereless.model.candidate.EducationModel;
import com.hr.pereless.model.candidate.PointModel;
import com.hr.pereless.model.candidate.TimeLineModel;
import com.hr.pereless.model.candidate.WorkHistoryModel;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.biubiubiu.justifytext.library.JustifyTextView;

public class ExperiencekFragment extends Fragment {
    View view;
    JustifyTextView txt_summery;
    RecyclerView recycle_workhistory,recycle_edu;
    //WorkhistoryAdpter workhistoryAdpter;
   // EducationAdpter educationAdpter;
    CandidateDetailActivity context;
    WebView webview;
    static CandidateModel candidateModel = new CandidateModel();
    public static ExperiencekFragment newInstance(CandidateModel model) {
        candidateModel = model;
        ExperiencekFragment fragment = new ExperiencekFragment();
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
        txt_summery = view.findViewById(R.id.txt_summery);
        recycle_workhistory = view.findViewById(R.id.recycle_workhistory);
        recycle_edu = view.findViewById(R.id.recycle_edu);
        webview = view.findViewById(R.id.webview);

//        workhistoryAdpter = new WorkhistoryAdpter( getContext());
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
//        recycle_workhistory.setLayoutManager(mLayoutManager);
//        recycle_workhistory.setItemAnimator(new DefaultItemAnimator());
//        recycle_workhistory.setAdapter(workhistoryAdpter);
//        educationAdpter = new EducationAdpter(getContext());
//         mLayoutManager = new LinearLayoutManager(getContext());
//        recycle_edu.setLayoutManager(mLayoutManager);
//        recycle_edu.setItemAnimator(new DefaultItemAnimator());
//        recycle_edu.setAdapter(educationAdpter);
        //initLayout();

        loadLayout();
    }
    void loadLayout(){
        context.showProgress();
        String api_link = API.GET_RESUME + String.valueOf(candidateModel.getRid()) + "/" + String.valueOf(candidateModel.getJid()) ;
        StringRequest myRequest = new StringRequest(
                Request.Method.GET,
                api_link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                        context.closeProgress();
                        try {
                            JSONArray jsonArray = new JSONArray(json);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String html = jsonObject.getString("OrgHtmlText");
                            Log.d("aaaaaaaa",html);
                            webview.getSettings().setJavaScriptEnabled(true);
                            webview.getSettings().setLoadWithOverviewMode(true);
                            webview.getSettings().setUseWideViewPort(true);
                            webview.getSettings().setBuiltInZoomControls(true);
                            webview.getSettings().setPluginState(WebSettings.PluginState.ON);
                            webview.loadDataWithBaseURL(null,html, "text/html", "UTF-8",null);
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
    void initLayout(){
//        List<WorkHistoryModel> workHistoryList =new ArrayList<>();
//        List<EducationModel> educationModels =new ArrayList<>();
//        for(int i=0;i<3;i++){
//            WorkHistoryModel workHistoryModel = new WorkHistoryModel();
//            List<PointModel> pointModels =new ArrayList<>();
//            for(int j =0;j<2;j++){
//                PointModel pointModel = new PointModel();
//                pointModels.add(pointModel);
//            }
//            workHistoryModel.setPointModels(pointModels);
//            workHistoryList.add(workHistoryModel);
//
//            educationModels.add(new EducationModel());
//
//        }
//        educationAdpter.setData(educationModels);
//        workhistoryAdpter.setData(workHistoryList);
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
        view =  inflater.inflate(R.layout.fragment_experiencek, container, false);
        return view;
    }
}