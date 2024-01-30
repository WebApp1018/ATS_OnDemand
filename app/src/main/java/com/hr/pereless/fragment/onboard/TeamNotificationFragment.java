package com.hr.pereless.fragment.onboard;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hr.pereless.R;
import com.hr.pereless.activities.job.JobDetailActivity;
import com.hr.pereless.activities.onboarding.OnBoardingTaskActivity;
import com.hr.pereless.adapter.job.JobListAdapter;
import com.hr.pereless.adapter.onboarding.NotificationListAdapter;
import com.hr.pereless.api.API;
import com.hr.pereless.api.BaseJsonObjectRequest;
import com.hr.pereless.application.AppController;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.dialog.ConfirmDialog;
import com.hr.pereless.dialog.TeamNotificationDetailDialog;
import com.hr.pereless.model.NotificationModel;
import com.hr.pereless.model.job.JobModel;
import com.hr.pereless.model.onboarding.RecruitteamModel;
import com.hr.pereless.model.onboarding.TeamNotificationModel;
import com.hr.pereless.pagination.PaginationScrollListener;

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

import static com.hr.pereless.base.BaseActivity.closeProgress;

public class TeamNotificationFragment extends Fragment {
    View view;
    Unbinder unbinder;
    OnBoardingTaskActivity context;

    @BindView(R.id.rv_notificationlist)
    RecyclerView rv_notificationlist;
    @BindView(R.id.main_swiperefresh)
    SwipeRefreshLayout main_swiperefresh;
    @BindView(R.id.btn_previous)
    Button btn_previous;
    @BindView(R.id.btn_next)
    Button btn_next;

    ArrayList<TeamNotificationModel>teamNotificationModels = new ArrayList<>();

    NotificationListAdapter notificationListAdapter;
    private static final int PAGE_START = 1;
    private int currentPage = PAGE_START;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private static int TOTAL_PAGES;
    public static TeamNotificationFragment newInstance(String param1, String param2) {
        TeamNotificationFragment fragment = new TeamNotificationFragment();
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

        notificationListAdapter = new NotificationListAdapter(context,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_notificationlist.setLayoutManager(linearLayoutManager);
        rv_notificationlist.setItemAnimator(new DefaultItemAnimator());
        rv_notificationlist.setAdapter(notificationListAdapter);
        rv_notificationlist.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                currentPage += 1;
                if (isLastPage == false) {
                    loadNotificationList(currentPage);
                } else {
                    isLoading = false;
                }
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
        main_swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                doRefresh();
            }
        });

        loadNotificationList(currentPage);
    }

    void loadNotificationList(int page){
        if(page == 1) teamNotificationModels.clear();
        context.showProgress();
        StringRequest myRequest = new StringRequest(
                Request.Method.GET,
                API.GET_TEAM_NOTIFICATION + "?recordsPerPage=20" + "&page=" + String.valueOf(page),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                        closeProgress();
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            if(jsonObject.getJSONObject("pagination").getInt("totalPages") == currentPage){
                                isLastPage = true;
                            }
                            for(int i =0;i<jsonArray.length();i++){
                                TeamNotificationModel teamNotificationModel = new TeamNotificationModel();
                                teamNotificationModel.initModel(jsonArray.getJSONObject(i));
                                teamNotificationModels.add(teamNotificationModel);
                            }
                            notificationListAdapter.addAll(teamNotificationModels);
                            rv_notificationlist.post(new Runnable() {
                                public void run() {
                                    notificationListAdapter.notifyDataSetChanged();
                                }
                            });
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


    void updateNotification(int checked_state,int recruiterID,String content, int posstion){
        context.showProgress();
        String api_link = API.GET_TEAM_NOTIFICATION + "/" +String.valueOf(teamNotificationModels.get(posstion).getTaskid());
        JSONObject params = new JSONObject();
        try {
            RecruitteamModel recruitteamModel = Commons.recruitteamModels.get(recruiterID);
            params.put("contact",recruitteamModel.getName());
            params.put("isGlobal",checked_state);
            params.put("teamNotificationMessage",content);
            params.put("taskid",teamNotificationModels.get(posstion).getTaskid());
            params.put("teamNotificationAssignedTo",recruitteamModel.getRecruiter_id());
            params.put("teamNotificationName",teamNotificationModels.get(posstion).getTeamNotificationName());
            params.put("teamNotificationDaysToComplete",teamNotificationModels.get(posstion).getTeamNotificationDaysToComplete());
            params.put("status",1);
            params.put("priority",1);
            params.put("documentID",0);

        } catch (JSONException e) {

        }

        Log.d("aaaaa",params.toString());
        new BaseJsonObjectRequest(
                Request.Method.PUT, api_link, params,
                response -> {
                    context.closeProgress();
                    Toast.makeText(context, "Team Notification updated Successfully!", Toast.LENGTH_SHORT).show();
                    doRefresh();
                    context.viewPager.setCurrentItem(1);
                }, this::handleMultiPartResponseError);
    }
    private void handleMultiPartResponseError(VolleyError error) {
        context.closeProgress();
        Log.d("Exception ===" ,error.toString());
    }
    public void NotificationDetail(int posstion){

        TeamNotificationDetailDialog teamNotificationDetailDialog = new TeamNotificationDetailDialog();
        teamNotificationDetailDialog.setOnConfirmListener(new TeamNotificationDetailDialog.OnConfirmListener() {
            @Override
            public void onConfirm(int checked_state,int recruiterTeamID,String content ) {
                updateNotification(checked_state,recruiterTeamID,content,posstion);
            }
        }, teamNotificationModels.get(posstion));
        teamNotificationDetailDialog.show(getParentFragmentManager(), "DeleteMessage");
    }
    private void doRefresh() {
        notificationListAdapter.getMovies().clear();
        notificationListAdapter.notifyDataSetChanged();
        currentPage = PAGE_START;
        loadNotificationList(currentPage);
        main_swiperefresh.setRefreshing(false);
    }

    @OnClick({R.id.btn_next,R.id.btn_previous})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_next:
                context.viewPager.setCurrentItem(2);
                break;
            case R.id.btn_previous:
                context.viewPager.setCurrentItem(0);
                break;
        }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = (OnBoardingTaskActivity) context;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_team_notification, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }
}