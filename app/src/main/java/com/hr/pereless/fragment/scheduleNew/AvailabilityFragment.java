package com.hr.pereless.fragment.scheduleNew;

import static com.hr.pereless.base.BaseActivity._context;
import static com.hr.pereless.base.BaseActivity.closeProgress;
import static com.hr.pereless.base.BaseActivity.showProgress;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hr.pereless.R;
import com.hr.pereless.adapter.schedule.AvailablityAdapter;
import com.hr.pereless.api.API;
import com.hr.pereless.api.BaseJsonObjectRequest;
import com.hr.pereless.application.AppController;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.model.job.RecruiterModel;
import com.hr.pereless.model.schedule.AvailabilityModel;
import com.jaredrummler.materialspinner.MaterialSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.zakariya.stickyheaders.StickyHeaderLayoutManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AvailabilityFragment extends Fragment {
    View view;
    @BindView(R.id.spinner_manager)
    MaterialSpinner spinner_manager;
    @BindView(R.id.spinner_timezone)
    MaterialSpinner spinner_timezone;
    @BindView(R.id.recycle_availablity)
    RecyclerView recycle_availablity;
    @BindView(R.id.btn_add)
    Button btn_add;
    @BindView(R.id.btn_update)
    Button btn_update;
    @BindView(R.id.view)
    View insteadView;
    List<RecruiterModel> hireModels = new ArrayList<>();
    ArrayList<AvailabilityModel> availabilityModels = new ArrayList<>();
    AvailablityAdapter availablityAdapter;
    AvailabilityFragment availabilityFragment;
    int apiCount = 0;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        apiCount = 0;
        List<String> timezone = new ArrayList<>();
        for (int i = 0; i < Commons.timeZoneModels.size(); i++) {
            timezone.add(Commons.timeZoneModels.get(i).getName());
        }
        spinner_timezone.setItems(timezone);
        StickyHeaderLayoutManager stickyHeaderLayoutManager = new StickyHeaderLayoutManager();
        recycle_availablity.setLayoutManager(stickyHeaderLayoutManager);

        spinner_manager.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                loadAvailable(hireModels.get(position).getUid());
            }
        });
        availabilityFragment = this;
        RecruiterModel recruiterModel = new RecruiterModel();
        recruiterModel.setUid(Commons.g_user.getUid());
        hireModels.add(recruiterModel);
        getHireManager();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(availabilityModels.size()>0){
                    availablityAdapter.addModel(0);
                }else{
                    AvailabilityModel availabilityModel = new AvailabilityModel();
                    availabilityModel.setShiftfrom("64800");
                    availabilityModel.setShiftto("68400");
                    availabilityModel.setAllDate(String.valueOf(0));
                    availabilityModels.add(availabilityModel);
                    insteadView.setVisibility(View.GONE);
                    recycle_availablity.setVisibility(View.VISIBLE);
                    availablityAdapter = new AvailablityAdapter(availabilityFragment, getContext(),true, false, false, true,availabilityModels);
                    recycle_availablity.setAdapter(availablityAdapter);
                    availablityAdapter.setHasStableIds(true);
                }

            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(availabilityModels.size()>0)
                    updateAvailability();
            }
        });

    }

    void updateAvailability(){

        ((CommonActivity)getContext()).showProgress();
        String api_link = API.GET_AVAILABILITY ;
        JSONObject params = new JSONObject();
        try {
            String removeIDS = availablityAdapter.deleteIds;
            String currentids = "", mytime = "";
            for(int i = 0 ;i<availablityAdapter._roomDatas.size();i++){
                AvailabilityModel availabilityModel = availablityAdapter._roomDatas.get(i);
                if(availabilityModel.getSch_id().length()>0){
                    currentids +=  availabilityModel.getSch_id() + "|" + availabilityModel.getAllDate() +"_" + availabilityModel.getShiftfrom() + "$" + availabilityModel.getShiftto() + ",";
                }else{
                    mytime += availabilityModel.getAllDate() +"_" + availabilityModel.getShiftfrom() + "$" + availabilityModel.getShiftto() + ",";
                }
            }
            if(removeIDS.length()>0)removeIDS = removeIDS.substring(0,removeIDS.length()-1);
            if(currentids.length()>0)currentids = currentids.substring(0,currentids.length()-1);
            if(mytime.length()>0)mytime = mytime.substring(0,mytime.length()-1);
            params.put("UID",String.valueOf(hireModels.get(spinner_manager.getSelectedIndex()).getUid()));
            params.put("removeid",removeIDS);
            params.put("ws_timezone", Commons.timeZoneModels.get(spinner_timezone.getSelectedIndex()).getOffset() );
            params.put("mytime",mytime);
            params.put("currentid",currentids);
            Log.d("Request ", params.toString());

        } catch (JSONException e) {

        }
        new BaseJsonObjectRequest(
                Request.Method.POST, api_link, params,
                response -> {
                    closeProgress();
                    Toast.makeText(getContext(), "Availability Updated", Toast.LENGTH_SHORT).show();
                }, this::handleMultiPartResponseError);
    }

    private void handleMultiPartResponseError(VolleyError error) {
        closeProgress();
        Log.d("Exception ===" ,error.toString());
    }
    void getHireManager(){
        ((CommonActivity)getContext()).showProgress();
        String api_link = API.GET_HMMANGERS;
        StringRequest myRequest = new StringRequest(
                Request.Method.GET,
                api_link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                        Log.d("Response==",json);
                        try {
                            closeProgress();

                            JSONArray jsonArray = new JSONArray(json);
                            List<String> hires = new ArrayList<>();
                            hires.add(Commons.g_user.getFirstname() + " " + Commons.g_user.getLastname());

                            for(int i =0;i<jsonArray.length();i++){
                                RecruiterModel recruiterModel = new RecruiterModel();
                                recruiterModel.initModel(jsonArray.getJSONObject(i));
                                hireModels.add(recruiterModel);
                                hires.add(recruiterModel.getUserName());
                            }
                            spinner_manager.setItems(hires);
                            if(hireModels.size()>0){
                                loadAvailable(hireModels.get(0).getUid());
                            }


                        }catch (Exception e){
                            closeProgress();
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


    void loadAvailable(int uid){
        showProgress();
        String api_link = API.GET_AVAILABILITY + "?uid=" + String.valueOf(uid);
        StringRequest myRequest = new StringRequest(
                Request.Method.GET,
                api_link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                        Log.d("Response==",json);
                        try {
                            closeProgress();
                            availabilityModels.clear();
                            JSONArray jsonArray = new JSONArray(json);
                            for(int i =0;i<jsonArray.length();i++){
                                AvailabilityModel availabilityModel = new AvailabilityModel();
                                availabilityModel.initModel(jsonArray.getJSONObject(i));
                                if(availabilityModel.getShiftfrom().length() == 0 || availabilityModel.getShiftto().length() == 0 )continue;
                                availabilityModels.add(availabilityModel);
                            }

                            if(availabilityModels.size()>0){
                                insteadView.setVisibility(View.GONE);
                                recycle_availablity.setVisibility(View.VISIBLE);
                                availablityAdapter = new AvailablityAdapter(availabilityFragment, getContext(),true, false, false, true,availabilityModels);
                                recycle_availablity.setAdapter(availablityAdapter);
                                availablityAdapter.setHasStableIds(true);
                            }else{
                                insteadView.setVisibility(View.VISIBLE);
                                recycle_availablity.setVisibility(View.GONE);
                            }


                        }catch (Exception e){
                            closeProgress();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_availability, container, false);
        return view;
    }
}