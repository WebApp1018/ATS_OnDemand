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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hr.pereless.R;
import com.hr.pereless.activities.onboarding.OnBoardingTaskActivity;
import com.hr.pereless.adapter.onboarding.NotificationListAdapter;
import com.hr.pereless.adapter.onboarding.TaskListAdapter;
import com.hr.pereless.api.API;
import com.hr.pereless.api.BaseJsonObjectRequest;
import com.hr.pereless.application.AppController;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.dialog.SelectJobOneDialog;
import com.hr.pereless.model.onboarding.DocumentModel;
import com.hr.pereless.model.onboarding.TaskModel;
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

public class TasksubFragment extends Fragment {

    View view ;
    Unbinder unbinder;
    OnBoardingTaskActivity context;
    @BindView(R.id.btn_previous)
    Button btn_previous;
    @BindView(R.id.btn_next)
    Button btn_next;
    @BindView(R.id.rv_list)
    RecyclerView rv_list;
    @BindView(R.id.main_swiperefresh)
    SwipeRefreshLayout main_swiperefresh;

    private static final int PAGE_START = 1;
    private int currentPage = PAGE_START;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private static int TOTAL_PAGES;
    TaskListAdapter taskListAdapter;
    ArrayList<TaskModel>taskModels = new ArrayList<>();
    ArrayList<TaskModel>selectedModel = new ArrayList<>();
    public static TasksubFragment newInstance(String param1, String param2) {
        TasksubFragment fragment = new TasksubFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        taskListAdapter = new TaskListAdapter(context,this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_list.setLayoutManager(linearLayoutManager);
        rv_list.setItemAnimator(new DefaultItemAnimator());
        rv_list.setAdapter(taskListAdapter);
        rv_list.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                currentPage += 1;
                if (isLastPage == false) {
                    getOnboardingTask(currentPage,0);

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
        getOnboardingTask(currentPage,1);
        getOnboardingTask(currentPage,0);
    }

    void getOnboardingTask(int page,int type){
        if(page == 1) taskModels.clear();
        String api_link = API.GET_TASKS + "?recordsPerPage=100000" + "&page=" + String.valueOf(page);
        if(type == 1){
            api_link = API.GET_ONBOARDING_TASK + "/"+ context.onboardingModel.getRid();
        }
        context.showProgress();
        StringRequest myRequest = new StringRequest(
                Request.Method.GET,
                api_link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                        closeProgress();
                        try {
                            if (type == 0) {
                                JSONObject jsonObject = new JSONObject(json);

                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                if (jsonObject.getJSONObject("pagination").getInt("totalPages") == currentPage) {
                                    isLastPage = true;
                                }
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    TaskModel teamNotificationModel = new TaskModel();
                                    teamNotificationModel.initModel(jsonArray.getJSONObject(i));
                                    taskModels.add(teamNotificationModel);
                                }
                                taskListAdapter.addAll(taskModels,selectedModel);
                                rv_list.post(new Runnable() {
                                    public void run() {
                                        taskListAdapter.notifyDataSetChanged();
                                    }
                                });
                            }else if(type ==1){
                                JSONArray jsonArray = new JSONArray(json);
                                Log.d("aaaaaa",json);
                                selectedModel.clear();
                                for(int i =0;i<jsonArray.length();i++){
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    TaskModel taskModel = new TaskModel();
                                    taskModel.setTask_id(jsonObject.getInt("mastertaskid"));
                                    selectedModel.add(taskModel);
                                }
                            }
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
        taskListAdapter.getMovies().clear();
        taskListAdapter.notifyDataSetChanged();
        currentPage = PAGE_START;
        getOnboardingTask(currentPage,0);

        main_swiperefresh.setRefreshing(false);
    }
    void updateTask(){

        context.showProgress();
        String api_link = API.GET_ONBOARDING_TASK;
        JSONObject params = new JSONObject();
        try {

            params.put("rid",context.onboardingModel.getRid());
            JSONArray jsonArray = new JSONArray();
            for(int i =0;i<taskListAdapter.selectModel.size();i++){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("task_id",taskListAdapter.selectModel.get(i).getTask_id());
                jsonObject.put("completedate",taskListAdapter.selectModel.get(i).getDaysToComplete());
                jsonArray.put(jsonObject);
            }
            params.put("tasks",jsonArray);

        } catch (JSONException e) {

        }

        Log.d("aaaaa",params.toString());
        new BaseJsonObjectRequest(
                Request.Method.PUT, api_link, params,
                response -> {
                    context.closeProgress();
                    Toast.makeText(context, "Task Data updated Successfully!", Toast.LENGTH_SHORT).show();
                    context.viewPager.setCurrentItem(4);
                }, this::handleMultiPartResponseError);
    }
    private void handleMultiPartResponseError(VolleyError error) {
        context.closeProgress();
        Log.d("Exception ===" ,error.toString());
    }
    @OnClick({R.id.btn_previous,R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_next:
                updateTask();
                break;
            case R.id.btn_previous:
                context.viewPager.setCurrentItem(2);
                break;

        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        view =  inflater.inflate(R.layout.fragment_tasksub, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }
}