package com.hr.pereless.activities.setting;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.hr.pereless.R;
import com.hr.pereless.adapter.candidate.ScorecarddetailAdapter;
import com.hr.pereless.adapter.setting.NotificationSettingAdapter;
import com.hr.pereless.api.API;
import com.hr.pereless.application.AppController;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.model.setting.GeneralModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationSettingActivity extends CommonActivity {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.img_scorecard)
    ImageView imgScorecard;
    @BindView(R.id.logout)
    ImageView logout;
    @BindView(R.id.tick)
    ImageView tick;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.btn_update)
    Button btnUpdate;
    ArrayList<ArrayList<GeneralModel>>generalModels = new ArrayList<>();
    ArrayList<String>arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_setting);
        ButterKnife.bind(this);
        txtTitle.setText("Notifications");
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserPreferece();
            }
        });

        getUserPreferece();
    }

    void updateUserPreferece(){

        showProgress();
        try {
            JSONArray jsonArray =  new JSONArray();
            for(int i=0;i<generalModels.size();i++){
                ArrayList<GeneralModel>models = generalModels.get(i);
                for(int j=0;j<models.size();j++){
                    JSONObject jsonObject = new JSONObject();
                    int type=0;
                    if(models.get(j).isFlag())type = 1;
                    jsonObject.put("Notificationcode",models.get(j).getCode());
                    jsonArray.put(jsonObject);
                    jsonObject.put("value",type);
                    jsonArray.put(jsonObject);

                }
            }

            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.PUT, API.GET_NOTIFICATION_USER, jsonArray, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {
                    closeProgress();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    closeProgress();
                    if(error.toString().contains("Update Success!")){
                        showToast("Update Success!");
                        finish(NotificationSettingActivity.this);
                    }

                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Authorization", Commons.token);
                    return headers;
                }
            };
            jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            AppController.getInstance().addToRequestQueue(jsonArrayRequest, "tag");

        } catch (Exception e) {
            closeProgress();
        }

    }

    void getUserPreferece(){
        showProgress();
        Map<String, String> params = new HashMap<>();
        params.put("Authorization", Commons.token);
        apiConnection(API.GET_NOTIFICATION_USER,params, Request.Method.GET);
    }
    @Override
    public void parseResponse(String api_link,String json){
         closeProgress();
        try {
            JSONArray jsonArray = new JSONArray(json);
            generalModels.clear();
            arrayList.clear();
            for(int i =0;i<jsonArray.length();i++){
                JSONObject item = jsonArray.getJSONObject(i);
                arrayList.add(item.getString("type"));
                JSONArray array =  item.getJSONArray("references");
                ArrayList<GeneralModel>models = new ArrayList<>();

                for(int j =0;j< array.length();j++){
                    GeneralModel generalModel = new GeneralModel();
                    generalModel.setTitle(array.getJSONObject(j).getString("NotificationName"));
                    generalModel.setCode(array.getJSONObject(j).getString("Notificationcode"));
                    generalModel.setFlag(array.getJSONObject(j).getBoolean("value"));
                    models.add(generalModel);
                }
                generalModels.add(models);
            }

            NotificationSettingAdapter notificationSettingAdapter = new NotificationSettingAdapter(this);
            recyclerview.setLayoutManager( new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            notificationSettingAdapter.setHasStableIds(true);
            recyclerview.setAdapter(notificationSettingAdapter);
            notificationSettingAdapter.setDate(arrayList,generalModels);

        }catch (Exception e){
            Log.d("exception====", e.toString());
        }
    }

}