package com.hr.pereless.fragment.onboard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hr.pereless.R;
import com.hr.pereless.adapter.onboarding.HireSubAdapterPagenation;
import com.hr.pereless.api.API;
import com.hr.pereless.application.AppController;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.model.onboarding.OnboardingModel;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hr.pereless.base.BaseActivity.closeProgress;

public class OnboardingsubFragment extends Fragment {
    View view;
    LinearLayout layout_main;
    SwipeRefreshLayout main_swiperefresh;
    FloatingActionButton floating_action_button;
    RecyclerView rv_joblist;
    HireSubAdapterPagenation hireSubAdapterPagenation;
    ArrayList<OnboardingModel>onboardingModels = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        layout_main = view.findViewById(R.id.layout_main);
        main_swiperefresh = view.findViewById(R.id.main_swiperefresh);
        floating_action_button = view.findViewById(R.id.floating_action_button);
        rv_joblist = view.findViewById(R.id.rv_joblist);
        hireSubAdapterPagenation = new HireSubAdapterPagenation( getContext(),1);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rv_joblist.setLayoutManager(mLayoutManager);
        rv_joblist.setItemAnimator(new DefaultItemAnimator());
        rv_joblist.setAdapter(hireSubAdapterPagenation);
        main_swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                doRefresh();
            }
        });
        loadData();
    }

    void loadData(){
        onboardingModels.clear();
        ((CommonActivity)getContext()).showProgress();
        StringRequest myRequest = new StringRequest(
                Request.Method.GET,
                API.GET_ONBOARDING + "2",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                        closeProgress();
                        try {
                            JSONArray jsonArray = new JSONArray(json);
                            for(int i =0;i<jsonArray.length();i++){
                                OnboardingModel onboardingModel = new OnboardingModel();
                                onboardingModel.initModel(jsonArray.getJSONObject(i));
                                onboardingModels.add(onboardingModel);
                            }
                            hireSubAdapterPagenation.getMovies().clear();
                            hireSubAdapterPagenation.notifyDataSetChanged();
                            hireSubAdapterPagenation.addAll(onboardingModels);
                        }catch (Exception e){

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
        loadData();
        main_swiperefresh.setRefreshing(false);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_pre_hire_sub, container, false);
        return view;
    }
}