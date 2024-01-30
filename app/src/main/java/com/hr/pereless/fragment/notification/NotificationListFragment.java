package com.hr.pereless.fragment.notification;

import android.content.Context;
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

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hr.pereless.R;
import com.hr.pereless.activities.MainActivity;
import com.hr.pereless.adapter.notification.NotificationPageationAdapter;
import com.hr.pereless.api.API;
import com.hr.pereless.application.AppController;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.model.NotificationModel;
import com.hr.pereless.model.email.EmailModel;
import com.hr.pereless.pagination.PaginationScrollListener;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationListFragment extends Fragment {
    View view;
    SwipeRefreshLayout main_swiperefresh;
    RecyclerView rv_notificationlist;
    NotificationPageationAdapter notificationPageationAdapter;
    private static final int PAGE_START = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    MainActivity context;
    // limiting to 5 for this tutorial, since total pages in actual API is very large. Feel free to modify.
    private static int TOTAL_PAGES;
    private int currentPage = PAGE_START;
    ArrayList<NotificationModel>notificationModels = new ArrayList<>();
    public static NotificationListFragment newInstance() {
        NotificationListFragment fragment = new NotificationListFragment();
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
        rv_notificationlist = view.findViewById(R.id.rv_notificationlist);
        main_swiperefresh = view.findViewById(R.id.main_swiperefresh);
        notificationPageationAdapter = new NotificationPageationAdapter(getContext());
        Log.e("contextname", getContext().toString());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_notificationlist.setLayoutManager(linearLayoutManager);
        rv_notificationlist.setItemAnimator(new DefaultItemAnimator());
        rv_notificationlist.setAdapter(notificationPageationAdapter);

        loadNotification();


        main_swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                doRefresh();
            }
        });
    }
    void loadNotification(){
        context.showProgress();
        String api_link = API.ALLNOTIFICATION;
        StringRequest myRequest = new StringRequest(
                Request.Method.GET,
                api_link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                        context.closeProgress();
                        try {
                            JSONArray jsonArray = new JSONArray(json);
                            notificationModels.clear();
                            for(int i =0;i<jsonArray.length();i++){
                                NotificationModel notificationModel = new NotificationModel();
                                notificationModel.initModel(jsonArray.getJSONObject(i));
                                notificationModels.add(notificationModel);
                            }
                            notificationPageationAdapter.addAll(notificationModels);
                            rv_notificationlist.post(new Runnable() {
                                public void run() {
                                    notificationPageationAdapter.notifyDataSetChanged();
                                }
                            });
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
    private void doRefresh() {
        notificationPageationAdapter.getMovies().clear();
        notificationPageationAdapter.notifyDataSetChanged();
        loadNotification();
        main_swiperefresh.setRefreshing(false);
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        this.context = (MainActivity)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=  inflater.inflate(R.layout.fragment_notification_list, container, false);
        return  view;
    }
}