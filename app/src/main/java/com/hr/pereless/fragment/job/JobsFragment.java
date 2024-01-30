package com.hr.pereless.fragment.job;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hr.pereless.R;
import com.hr.pereless.activities.MainActivity;
import com.hr.pereless.adapter.job.JobChartAdapter;
import com.hr.pereless.api.API;
import com.hr.pereless.application.AppController;
import com.hr.pereless.commons.Commons;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.hr.pereless.base.BaseActivity.closeProgress;

public class JobsFragment extends Fragment {
    View view;
    Unbinder unbinder;
    MainActivity mainActivity;


    ProgressDialog progressDialog;
    String token, userId;
    @BindView(R.id.txtactivjobvalue)
    TextView txtactivjobvalue;

    @BindView(R.id.txtinactivjobvalue)
    TextView txtinactivjobvalue;
    @BindView(R.id.txtclosedjobvalue)
    TextView txtclosedjobvalue;
    @BindView(R.id.txtpotentialvalue)
    TextView txtpotentialvalue;
    @BindView(R.id.txtsavedvalue)
    TextView txtsavedvalue;
    @BindView(R.id.list_jobchart)
    ListView listJobChart;

    @BindView(R.id.main_swiperefresh)
    SwipeRefreshLayout mainSwiperefresh;
    int count = 0;
    ArrayList<Integer> values = new ArrayList<>();
    ArrayList<String>titles = new ArrayList<>();
    int activty_count = 0,inactive_count =0,closed_count =0,approved_count =0,saved_count = 0 , potential_count = 0;
    JobChartAdapter jobChartAdapter = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainSwiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               doRefresh();
            }
        });
        mainActivity.showProgress();
        getJobstatics(0);
        getJobstatics(1);
    }

    void initLayout(){
        jobChartAdapter = new JobChartAdapter(getContext());
        listJobChart.setAdapter(jobChartAdapter);
        jobChartAdapter.setRoomData(values,titles);
        txtactivjobvalue.setText(String.valueOf(activty_count));
        txtinactivjobvalue.setText(String.valueOf(inactive_count));
        txtclosedjobvalue.setText(String.valueOf(closed_count));
        txtpotentialvalue.setText(String.valueOf(potential_count));
        txtsavedvalue.setText(String.valueOf(saved_count));
    }

    void getJobstatics(int type){
        String api_link = API.GET_JOB_CHART;
        if(type == 1)
            api_link = API.STATE5LANDING;

        StringRequest myRequest = new StringRequest(
                Request.Method.GET,
                api_link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                        try {
                            if(type == 0){
                                JSONArray jsonArray = new JSONArray(json);
                                values.clear();
                                titles.clear();
                                for(int i =0;i<jsonArray.length();i++){
                                    values.add(jsonArray.getJSONObject(i).getInt("count"));
                                    titles.add(jsonArray.getJSONObject(i).getString("label"));
                                }

                            }else if(type ==1){
                                JSONObject jsonObject = new JSONObject(json);
                                activty_count = jsonObject.getInt("job_active");
                                inactive_count = jsonObject.getInt("job_inactive");
                                closed_count = jsonObject.getInt("job_closed");
                                approved_count = jsonObject.getInt("job_approval");
                                saved_count = jsonObject.getInt("job_saved");
                                potential_count = jsonObject.getInt("job_potential");


                            }
                            count++;
                            if(count == 2) {
                                closeProgress();
                                initLayout();
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

    private void doRefresh() {
        count =0;
        mainActivity.showProgress();
        getJobstatics(0);
        getJobstatics(1);
        mainSwiperefresh.setRefreshing(false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;
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
        view =  inflater.inflate(R.layout.fragment_jobs, container, false);
        unbinder = ButterKnife.bind(this, view);
        return  view;
    }
}