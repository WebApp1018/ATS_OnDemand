package com.hr.pereless.fragment.onboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonArray;
import com.hr.pereless.R;
import com.hr.pereless.activities.auth.LoginActivity;
import com.hr.pereless.activities.onboarding.OnBoardingTaskActivity;
import com.hr.pereless.api.API;
import com.hr.pereless.api.BaseJsonObjectRequest;
import com.hr.pereless.application.AppController;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.dialog.ConfirmDialog;
import com.hr.pereless.dialog.PDFViewDialog;
import com.hr.pereless.dialog.SelectJobOneDialog;
import com.hr.pereless.model.onboarding.DocumentModel;
import com.hr.pereless.preference.PrefConst;
import com.hr.pereless.preference.Preference;
import com.jaredrummler.materialspinner.MaterialSpinner;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;

public class DocumentsubFragment extends Fragment {
    View view ;
    Unbinder unbinder;
    OnBoardingTaskActivity context;
    @BindView(R.id.spiner_doc)
    MaterialSpinner spiner_doc;
    @BindView(R.id.lyt_assign_document)
    LinearLayout lyt_assign_document;
    @BindView(R.id.tag_assignjob)
    TagContainerLayout tag_assignjob;
    @BindView(R.id.btn_previous)
    Button btn_previous;
    @BindView(R.id.btn_next)
    Button btn_next;
    ArrayList<String>tags = new ArrayList<>();
    ArrayList<String>allDoc = new ArrayList<>();
    ArrayList<DocumentModel>allDocument = new ArrayList<>();
    ArrayList<DocumentModel>assignDocument = new ArrayList<>();

    int count =0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context.showProgress();
        count =0;
        loadDate(1);
        loadDate(2);

        tag_assignjob.setOnTagClickListener(new TagView.OnTagClickListener() {

            @Override
            public void onTagClick(int position, String text) {
                // ...

                ConfirmDialog confirmDialog = new ConfirmDialog();
                confirmDialog.setOnConfirmListener(new ConfirmDialog.OnConfirmListener() {
                    @Override
                    public void onConfirm() {
                        tag_assignjob.removeTag(position);
                        assignDocument.remove(position);
                    }
                },getString(R.string.delete_document));
                confirmDialog.show(getParentFragmentManager(), "DeleteMessage");
            }

            @Override
            public void onTagLongClick(final int position, String text) {
                // ...
            }

            @Override
            public void onSelectedTagDrag(int position, String text){
                // ...
            }

            @Override
            public void onTagCrossClick(int position) {
                // ...
            }
        });

        spiner_doc.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                PDFViewDialog insuranceViewDialog = new PDFViewDialog();
                insuranceViewDialog.setOnConfirmListener(new PDFViewDialog.OnConfirmListener() {
                    @Override
                    public void onDownload() {
                        //new DownloadFileFromURL().execute(newsFeedEntity.getInsuranceModels().get(0).getFile());
                    }
                },allDocument.get(position),0);
                insuranceViewDialog.show(getParentFragmentManager(), "DeleteMessage");
            }
        });
    }

    void loadDate(int type){
        String api_link = API.GET_ONBOARDING_DOCUMENT + "/"+ String.valueOf(context.onboardingModel.getRid())  ;
        if(type ==2)
            api_link = API.GET_ONBOARDING_RECOARD_DOCUMENT ;
        StringRequest myRequest = new StringRequest(
                Request.Method.GET,
                api_link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {

                        try {

                            if(type == 1){
                                tags.clear();
                                assignDocument.clear();
                                JSONArray jsonArray = new JSONArray(json);
                                for(int i =0;i<jsonArray.length();i++){
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    DocumentModel documentModel = new DocumentModel();
                                    documentModel.setDocumentID(jsonObject.getString("DOCID"));
                                    documentModel.setDocumentType(jsonObject.getString("DOCFILETYPE"));
                                    documentModel.setDocumentInternalName(jsonObject.getString("DOCDSPNAME"));
                                    assignDocument.add(documentModel);
                                    tags.add(documentModel.getDocumentInternalName());
                                }
                                tag_assignjob.setTags( tags);
                            }else {
                                JSONObject jsonObject = new JSONObject(json);
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                allDocument.clear();
                                allDoc.clear();
                                for(int i =0;i<jsonArray.length();i++){
                                    DocumentModel documentModel = new DocumentModel();
                                    documentModel.initModel(jsonArray.getJSONObject(i));
                                    allDocument.add(documentModel);
                                    allDoc.add(documentModel.getDocumentInternalName());
                                }
                                spiner_doc.setItems(allDoc);
                            }
                            count++;
                            if(count==2){
                                context.closeProgress();
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


    void updateDocument(){
        context.showProgress();
        String api_link = API.GET_ONBOARDING_DOCUMENT;
        JSONObject params = new JSONObject();
        try {

            params.put("rid",context.onboardingModel.getRid());
            JSONArray jsonArray = new JSONArray();
            for(int i =0;i<assignDocument.size();i++){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("docid",assignDocument.get(i).getDocumentID());
                jsonArray.put(jsonObject);
            }
            params.put("docs",jsonArray);

        } catch (JSONException e) {

        }

        Log.d("aaaaa",params.toString());
        new BaseJsonObjectRequest(
                Request.Method.PUT, api_link, params,
                response -> {
                    context.closeProgress();
                    Toast.makeText(context, "Document Data updated Successfully!", Toast.LENGTH_SHORT).show();
                    context.viewPager.setCurrentItem(3);
                }, this::handleMultiPartResponseError);
    }
    private void handleMultiPartResponseError(VolleyError error) {
        context.closeProgress();
        Log.d("Exception ===" ,error.toString());
    }

    @OnClick({R.id.lyt_assign_document,R.id.btn_previous,R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lyt_assign_document:
                ArrayList<String>arrayList = new ArrayList<>();
                ArrayList<DocumentModel>unselectList = new ArrayList<>();
                for(int i =0;i<allDocument.size();i++){
                    boolean flag = false;
                    for(int j = 0;j<assignDocument.size();j++){
                        if(allDocument.get(i).getDocumentID().equals(assignDocument.get(j).getDocumentID())){
                            flag =true;break;
                        }
                    }
                    if(!flag) {

                        arrayList.add(allDocument.get(i).getDocumentInternalName());
                        unselectList.add(allDocument.get(i));
                    }
                }
                SelectJobOneDialog selectJobOneDialog = new SelectJobOneDialog();
                selectJobOneDialog.setOnConfirmListener(new SelectJobOneDialog.OnConfirmListener() {
                    @Override
                    public void onConfirm(int selectPosstion) {
                        assignDocument.add(unselectList.get(selectPosstion));
                        tags.add(unselectList.get(selectPosstion).getDocumentInternalName());
                        tag_assignjob.setTags( tags);
                    }
                },arrayList,1);
                selectJobOneDialog.show(getParentFragmentManager(), "DeleteMessage");
                break;
            case R.id.btn_next:
                updateDocument();
                break;
            case R.id.btn_previous:
                context.viewPager.setCurrentItem(1);
                break;

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = (OnBoardingTaskActivity) context;

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_documentsub, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }
}