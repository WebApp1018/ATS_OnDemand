package com.hr.pereless.fragment.scheduleNew;

import static com.hr.pereless.base.BaseActivity._context;
import static com.hr.pereless.base.BaseActivity.closeProgress;
import static com.hr.pereless.base.BaseActivity.showProgress;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hr.pereless.R;
import com.hr.pereless.activities.schedule.CreateScheduleActivity;
import com.hr.pereless.adapter.schedule.ScheduleAdapter;
import com.hr.pereless.api.API;
import com.hr.pereless.application.AppController;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.dialog.ConfirmDialog;
import com.hr.pereless.model.schedule.ScheduleModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScheduleViewFragment extends Fragment {
    View view;
    ListView recyclerView;
    ScheduleAdapter scheduleAdapter ;
    public static ArrayList<ScheduleModel> scheduleModels = new ArrayList<>();
    CalendarView calendarView;
    FloatingActionButton add_schedule;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycle_calendar);
        calendarView = view.findViewById(R.id.calendarView);
        add_schedule = view.findViewById(R.id.add_schedule);
        scheduleAdapter = new ScheduleAdapter(getContext(), this);
        recyclerView.setAdapter(scheduleAdapter);


        add_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivityForResult(new Intent(_context, CreateScheduleActivity.class),1);
                getActivity().overridePendingTransition(0, 0);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_schedule_view, container, false);

        return view;
    }

    void getAllschedule(){
        showProgress();
        String api_link = API.GET_SCHEDULES;
        StringRequest myRequest = new StringRequest(
                Request.Method.GET,
                api_link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                        closeProgress();
                        try {
                            JSONObject jsonDate = new JSONObject(json);
                            JSONArray jsonArray = jsonDate.getJSONArray("data");
                            List<EventDay> events = new ArrayList<>();
                            scheduleModels.clear();
                            for(int i =0;i<jsonArray.length();i++){
                                ScheduleModel scheduleModel = new ScheduleModel();
                                scheduleModel.initModel(jsonArray.getJSONObject(i));
                                scheduleModels.add(scheduleModel);
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime(Commons.DateFromString(scheduleModel.getStart_time()));
                                events.add(new EventDay(calendar, R.drawable.horizontal_dots, Color.parseColor("#228B22")));
                            }
                            Collections.reverse(scheduleModels);
                            scheduleAdapter.setRoomData(scheduleModels);

                            calendarView.setEvents(events);

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

    public void confirmSchedule(String schid){
        ConfirmDialog confirmDialog = new ConfirmDialog();
        confirmDialog.setOnConfirmListener(new ConfirmDialog.OnConfirmListener() {
            @Override
            public void onConfirm() {
                showProgress();
                String api_link = API.CONFIRM_SCHEDULES;
                StringRequest myRequest = new StringRequest(
                        Request.Method.POST,
                        api_link,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String json) {
                                Log.d("aaaaa",json);
                                getAllschedule();
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
                    public byte[] getBody() {
                        try {
                            JSONObject jsonObject = new JSONObject();
                            /* fill your json here */
                            jsonObject.put("schid",schid);
                            return jsonObject.toString().getBytes("utf-8");
                        } catch (Exception e) { }

                        return null;
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
        },getString(R.string.confirm_schedule));
        confirmDialog.show(getParentFragmentManager(), "DeleteMessage");
    }


    @Override
    public void onResume() {
        super.onResume();
        getAllschedule();
    }
}