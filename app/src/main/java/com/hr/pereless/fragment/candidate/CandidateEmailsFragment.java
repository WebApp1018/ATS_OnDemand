package com.hr.pereless.fragment.candidate;

import android.content.Context;
import android.content.Intent;
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

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hr.pereless.R;
import com.hr.pereless.activities.auth.LoginActivity;
import com.hr.pereless.activities.candidate.CandidateDetailActivity;
import com.hr.pereless.activities.email.EmailSendActivity;
import com.hr.pereless.adapter.candidate.CandiateEmailsAdapter;
import com.hr.pereless.api.API;
import com.hr.pereless.application.AppController;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.model.candidate.CandidateModel;
import com.hr.pereless.model.candidate.QuicknoteModel;
import com.hr.pereless.model.candidate.SkillLabelModel;
import com.hr.pereless.model.email.EmailModel;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CandidateEmailsFragment extends Fragment {
    View view;
    RecyclerView recycle;
    CandiateEmailsAdapter candiateEmailsAdapter;
    FloatingActionButton floating_action_button;
    CandidateDetailActivity context;
    static CandidateModel candidateModel = new CandidateModel();
    List< EmailModel > emailModels = new ArrayList<>();
    public static CandidateEmailsFragment newInstance(CandidateModel model) {
        CandidateEmailsFragment fragment = new CandidateEmailsFragment();
        candidateModel = model;
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
        recycle =view.findViewById(R.id.recycle);
        floating_action_button = view.findViewById(R.id.floating_action_button);
        candiateEmailsAdapter = new CandiateEmailsAdapter(getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recycle.setLayoutManager(mLayoutManager);
        recycle.setItemAnimator(new DefaultItemAnimator());
        recycle.setAdapter(candiateEmailsAdapter);
        floating_action_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.goTo(context,EmailSendActivity.class,false);
            }
        });
        loadData();
    }

    void loadData(){
        context.showProgress();
        String api_link = API.GETCANDIDATE_EMAILS + String.valueOf(candidateModel.getRid()) ;
        StringRequest myRequest = new StringRequest(
                Request.Method.GET,
                api_link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                        context.closeProgress();
                        try {
                            JSONArray jsonArray = new JSONArray(json);
                            for(int i =0;i<jsonArray.length();i++){
                                EmailModel emailModel = new EmailModel();
                                emailModel.initModel(jsonArray.getJSONObject(i));
                                emailModels.add(emailModel);
                            }
                            candiateEmailsAdapter.setDate(emailModels);
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

    private void handleMultiPartResponseError(VolleyError error) {
        context.closeProgress();
        Log.d("Exception ===" ,error.toString());
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
        view = inflater.inflate(R.layout.fragment_candidate_emails, container, false);
        return  view;
    }
}