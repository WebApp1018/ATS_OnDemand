package com.hr.pereless.base;


import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.core.content.FileProvider;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hr.pereless.R;
import com.hr.pereless.application.AppController;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.model.setting.TimeZoneModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public abstract class CommonActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
//        TouchEffectsFactory.initTouchEffects(this);
        super.onCreate(savedInstanceState);
        Commons.g_commentActivity = this;

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    public static String getCurrentTime(){
        Timestamp timestamp = new Timestamp(new Date().getTime());
        try {
            long currentTime = timestamp.getTime();
            Date df = new Date(currentTime);
            String timeStampString = new SimpleDateFormat("dd/MM/yyyy h:mm aa").format(df);
            return timeStampString;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    public Uri getUri(String filepath){
        File file = new File(filepath);
        if(file.exists()) {
            Uri imageUri = FileProvider.getUriForFile(
                    this,
                    "com.atb.app.fileprovider", //(use your app signature + ".provider" )
                    file);
            return imageUri;

        }
        return null;
    }

    public void apiConnection(String api_link,  Map<String, String> input_params ,  int request_method){
        StringRequest myRequest = new StringRequest(
                request_method,
                api_link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                        parseResponse(api_link,json);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        closeProgress();
                        Log.d("error Log=====" , error.toString());
//
//                        showToast(error.getMessage());

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
                params = input_params;
                return params;
            }
        };
        myRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(myRequest, "tag");
    }

    public void parseResponse(String apilik,String json){

    }

    public void readJson(){
        try {
            InputStreamReader is = new InputStreamReader(getResources().openRawResource(R.raw.timezone));

            BufferedReader reader = new BufferedReader(is);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            reader.close();
            String responce = stringBuilder.toString();
            JSONArray jsonArray = new JSONArray(responce);
            Commons.timeZoneModels.clear();
            for(int i =0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                TimeZoneModel timeZoneModel = new TimeZoneModel();
                timeZoneModel.setName(jsonObject.getString("name"));
                timeZoneModel.setAbbr(jsonObject.getString("abbr"));
                timeZoneModel.setOffset(jsonObject.getInt("offset"));
                Commons.timeZoneModels.add(timeZoneModel);
            }

        } catch (Exception e) {
            Log.d("aaaaaaaa",e.toString());
        }
    }
}
