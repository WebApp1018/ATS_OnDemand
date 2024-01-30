package com.hr.pereless.fragment.candidate;

import android.content.Context;
import android.graphics.Color;
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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hr.pereless.R;
import com.hr.pereless.activities.candidate.CandidateDetailActivity;
import com.hr.pereless.adapter.candidate.AttachmentAdpter;
import com.hr.pereless.adapter.candidate.QuickNotesAdpter;
import com.hr.pereless.api.API;
import com.hr.pereless.api.BaseJsonObjectRequest;
import com.hr.pereless.application.AppController;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.dialog.AddNoteDialog;
import com.hr.pereless.dialog.CommunicationDialog;
import com.hr.pereless.model.candidate.CandidateModel;
import com.hr.pereless.model.candidate.QuicknoteModel;
import com.hr.pereless.model.candidate.SkillLabelModel;
import com.hr.pereless.model.candidate.TimeLineModel;
import com.hr.pereless.util.SwipeHelper;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuickNotesFragment extends Fragment implements View.OnClickListener {
    View view;
    RecyclerView rv_attachment;
    FloatingActionButton floating_action_button;
    QuickNotesAdpter quickNotesAdpter;
    static CandidateModel candidateModel = new CandidateModel();
    CandidateDetailActivity context;
    List<QuicknoteModel> quicknoteModels= new ArrayList<>();
    List<SkillLabelModel>skillLabelModels = new ArrayList<>();
    List<String>colormodels = new ArrayList<>();
    int count = 0;
    public static QuickNotesFragment newInstance(CandidateModel model) {
        QuickNotesFragment fragment = new QuickNotesFragment();
        Bundle args = new Bundle();
        candidateModel = model;
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

        quickNotesAdpter = new QuickNotesAdpter(getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rv_attachment.setLayoutManager(mLayoutManager);
        rv_attachment.setItemAnimator(new DefaultItemAnimator());
        rv_attachment.setAdapter(quickNotesAdpter);
        floating_action_button.setOnClickListener(this);

        SwipeHelper swipeHelper = new SwipeHelper(context, rv_attachment) {
            @Override
            public void instantiateUnderlayButton(RecyclerView.ViewHolder viewHolder, List<UnderlayButton> underlayButtons) {

                underlayButtons.add(new SwipeHelper.UnderlayButton(
                        "Delete",
                        0,
                        Color.parseColor("#C7C7CB"),
                        new SwipeHelper.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                deleteSkillTag(pos);
                            }
                        }
                ));
                underlayButtons.add(new SwipeHelper.UnderlayButton(
                        "Edit",
                        0,
                        Color.parseColor("#FF9502"),
                        new SwipeHelper.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                editSkillTag(pos);
                            }
                        }
                ));
            }
        };

        count = 0;
        initLayout(0);
        initLayout(1);
        initLayout(2);

    }

    void initLayout(int type){
        context.showProgress();
        String api_link = API.GET_SKILLTAG + String.valueOf(candidateModel.getRid()) ;
        if(type == 1)
            api_link = API.GET_SKILLLABEL;
        else if(type == 2)
            api_link= API.GET_COLOR;
        StringRequest myRequest = new StringRequest(
                Request.Method.GET,
                api_link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                        count++;
                        try {
                            JSONArray jsonArray = new JSONArray(json);
                            if(type == 0) {
                                quicknoteModels.clear();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    QuicknoteModel timeLineModel = new QuicknoteModel();
                                    timeLineModel.initModel(jsonArray.getJSONObject(i));
                                    quicknoteModels.add(timeLineModel);
                                }
                                quickNotesAdpter.setData(quicknoteModels);
                            }else if(type ==1 ){
                                skillLabelModels.clear();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    SkillLabelModel timeLineModel = new SkillLabelModel();
                                    timeLineModel.initModel(jsonArray.getJSONObject(i));
                                    skillLabelModels.add(timeLineModel);
                                }
                            }else if(type ==2) {
                                colormodels.clear();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    colormodels.add((String) jsonArray.get(i));
                                }
                            }
                            if(count ==3)
                                context.closeProgress();
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

    void editSkillTag(int posstion){
        AddNoteDialog addNoteDialog = new AddNoteDialog();
        addNoteDialog.setOnConfirmListener(new AddNoteDialog.OnConfirmListener() {
            @Override
            public void onConfirm(int label,String quick_note, String score, int color) {
                EditkillTag(label,quick_note,score,color,quicknoteModels.get(posstion).getNID());

            }
        },skillLabelModels,colormodels,quicknoteModels.get(posstion));
        addNoteDialog.show(getParentFragmentManager(), "DeleteMessage");
    }

    void EditkillTag(int label,String quick_note, String score, int color,int nid){
        context.showProgress();
        String api_link = API.PUT_SKILLTAG + String.valueOf(candidateModel.getRid()) + "/" + String.valueOf(nid) ;
        JSONObject params = new JSONObject();
        try {

            params.put("qnhotbook",skillLabelModels.get(label).getHotbookID());
            params.put("qngradenum",score);
            params.put("qnotes",quick_note);
            params.put("bgcolor",colormodels.get(color));
            params.put("qnprivate",0);
        } catch (JSONException e) {

        }
        new BaseJsonObjectRequest(
                Request.Method.PUT, api_link, params,
                response -> {
                    context.closeProgress();
                    Toast.makeText(context, "Skill tag Updated", Toast.LENGTH_SHORT).show();
                    count=2;
                    initLayout(0);
                }, this::handleMultiPartResponseError);
    }

    void deleteSkillTag(int posstion){
        context.showProgress();
        String api_link = API.DELETE_SKILLTAG + String.valueOf(candidateModel.getRid()) + "/"+ String.valueOf(quicknoteModels.get(posstion).getQnjid())+"/" +String.valueOf(quicknoteModels.get(posstion).getNID()) ;
        StringRequest myRequest = new StringRequest(
                Request.Method.PUT,
                api_link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                        context.closeProgress();
                        try {
                            context.showToast(context.getResources().getString(R.string.delete_skill_tag));
                            quicknoteModels.remove(posstion);
                            quickNotesAdpter.setData(quicknoteModels);
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

    void addSkillTag(int label,String quick_note, String score, int color){
        context.showProgress();
        String api_link = API.ADD_SKILLTAG ;
        JSONObject params = new JSONObject();
        try {

            params.put("qnlabel",skillLabelModels.get(label).getHotbookID());
            params.put("qnscore",score);
            params.put("qnprivate",1);
            params.put("RID",String.valueOf(candidateModel.getRid()));
            params.put("mynote",quick_note);
            params.put("qbkcolor",colormodels.get(color));
            params.put("showqntype",2);
            params.put("jid",String.valueOf(candidateModel.getJid()));

        } catch (JSONException e) {

        }

        Log.d("aaaaaa", params.toString());
        new BaseJsonObjectRequest(
                Request.Method.POST, api_link, params,
                response -> {
                    context.closeProgress();
                    Toast.makeText(context, "Skill tag added", Toast.LENGTH_SHORT).show();
                    count=2;
                    initLayout(0);
                }, this::handleMultiPartResponseError);
    }

    private void handleMultiPartResponseError(VolleyError error) {
        context.closeProgress();
        Log.d("Exception ===" ,error.toString());
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floating_action_button:
                AddNoteDialog addNoteDialog = new AddNoteDialog();
                addNoteDialog.setOnConfirmListener(new AddNoteDialog.OnConfirmListener() {
                    @Override
                    public void onConfirm(int label,String quick_note, String score, int color) {
                        addSkillTag(label,quick_note,score,color);
                    }
                },skillLabelModels,colormodels);
                addNoteDialog.show(getParentFragmentManager(), "DeleteMessage");
                break;

        }
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
        view =  inflater.inflate(R.layout.fragment_quick_notes, container, false);
        return view;
    }
}