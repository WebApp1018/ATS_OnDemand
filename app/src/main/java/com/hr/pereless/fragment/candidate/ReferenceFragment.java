package com.hr.pereless.fragment.candidate;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hr.pereless.R;
import com.hr.pereless.activities.candidate.CandidateDetailActivity;
import com.hr.pereless.api.API;
import com.hr.pereless.api.BaseJsonObjectRequest;
import com.hr.pereless.application.AppController;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.model.candidate.CandidateModel;
import com.hr.pereless.model.candidate.ReferenceModel;
import com.htmleditor.HtmlTextEditor;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ReferenceFragment extends Fragment {

    View view;
    HtmlTextEditor html_editor;
    LinearLayout lyt_send;
   static CandidateModel candidateModel = new CandidateModel();
    ReferenceModel referenceModel = new ReferenceModel();
    CandidateDetailActivity context;
    public static ReferenceFragment newInstance(CandidateModel model) {
        ReferenceFragment fragment = new ReferenceFragment();
        candidateModel =model;
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
        lyt_send = view.findViewById(R.id.lyt_send);
        html_editor = view.findViewById(R.id.html_editor);
        lyt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendReference();
            }
        });
        context.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        initLayout();
    }

    void initLayout(){
        context.showProgress();
        String api_link = API.GET_CANDIDATEREFERENCE + "8553975";// String.valueOf(candidateModel.getRid()) ;
        StringRequest myRequest = new StringRequest(
                Request.Method.GET,
                api_link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                        context.closeProgress();
                        try {
                            JSONArray jsonArray = new JSONArray(json);
                            if(jsonArray.length()>0){
                                referenceModel.initModel(jsonArray.getJSONObject(0));
                                html_editor.setText(referenceModel.getEmailbody());
                            }
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

    void sendReference(){

        context.showProgress();
        String api_link = API.PUT_REFERENCE ;
        JSONObject params = new JSONObject();
        try {

            params.put("jid",String.valueOf(referenceModel.getJid()));
            params.put("rid",String.valueOf(candidateModel.getRid()));

        } catch (JSONException e) {

        }
        new BaseJsonObjectRequest(
                Request.Method.POST, api_link, params,
                response -> {
                    context.closeProgress();
                    Toast.makeText(context, "Reference sent", Toast.LENGTH_SHORT).show();
                }, this::handleMultiPartResponseError);
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
        view = inflater.inflate(R.layout.fragment_reference, container, false);
        return  view;
    }
}