package com.hr.pereless.fragment.onboard;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.hr.pereless.activities.candidate.CandidateDetailActivity;
import com.hr.pereless.activities.onboarding.OnBoardingTaskActivity;
import com.hr.pereless.api.API;
import com.hr.pereless.api.BaseJsonObjectRequest;
import com.hr.pereless.application.AppController;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.dialog.SelectJobOneDialog;
import com.hr.pereless.model.onboarding.EmployeeModel;
import com.hr.pereless.model.onboarding.OnboardingModel;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class EmployeeDataFragment extends Fragment {
    View view;
    Unbinder unbinder;
    EmployeeModel employeeModel = new EmployeeModel();
    OnBoardingTaskActivity context;
    @BindView(R.id.lyt_email)
    LinearLayout lyt_email;
    @BindView(R.id.edt_email)
    EditText edt_email;
    @BindView(R.id.edt_firstname)
    EditText edt_firstname;
    @BindView(R.id.edt_last_name)
    EditText edt_last_name;
    @BindView(R.id.edt_company_email)
    EditText edt_company_email;
    @BindView(R.id.edt_alternate_email)
    EditText edt_alternate_email;
    @BindView(R.id.edt_address)
    EditText edt_address;
    @BindView(R.id.edt_salary)
    EditText edt_salary;
    @BindView(R.id.radio_annual)
    RadioButton radio_annual;
    @BindView(R.id.radio_button)
    RadioGroup radio_button;
    @BindView(R.id.radio_hourly)
    RadioButton radio_hourly;
    @BindView(R.id.edt_salary_currency)
    TextView edt_salary_currency;
    @BindView(R.id.lyt_currency)
    LinearLayout lyt_currency;
    @BindView(R.id.lyt_employee_date)
    LinearLayout lyt_employee_date;
    @BindView(R.id.edt_employement_date)
    TextView edt_employement_date;
    @BindView(R.id.lyt_current_supervisor)
    LinearLayout lyt_current_supervisor;
    @BindView(R.id.edt_current_supervisor)
    TextView edt_current_supervisor;
    @BindView(R.id.lyt_department)
    LinearLayout lyt_department;
    @BindView(R.id.edt_department)
    TextView edt_department;
    @BindView(R.id.edt_primary_phonenumber)
    EditText edt_primary_phonenumber;
    @BindView(R.id.edt_primary_workernumber)
    EditText edt_primary_workernumber;
    @BindView(R.id.edt_job_title)
    EditText edt_job_title;
    @BindView(R.id.lyt_job_location_state)
    LinearLayout lyt_job_location_state;
    @BindView(R.id.edt_job_locationstate)
    TextView edt_job_locationstate;
    @BindView(R.id.edt_employee_id)
    EditText edt_employee_id;
    @BindView(R.id.edt_program_name)
    EditText edt_program_name;
    @BindView(R.id.lyt_program_startdate)
    LinearLayout lyt_program_startdate;
    @BindView(R.id.edt_program_startdate)
    TextView edt_program_startdate;
    @BindView(R.id.lyt_program_enddate)
    LinearLayout lyt_program_enddate;
    @BindView(R.id.edt_program_enddate)
    TextView edt_program_enddate;
    @BindView(R.id.edt_bonus)
    EditText edt_bonus;
    @BindView(R.id.edt_location_name)
    EditText edt_location_name;
    @BindView(R.id.edt_additional_information)
    EditText edt_additional_information;
    @BindView(R.id.edt_car_budget)
    EditText edt_car_budget;
    @BindView(R.id.edt_onboard_complete_date)
    EditText edt_onboard_complete_date;
    @BindView(R.id.lyt_onboard_complete_date)
    LinearLayout lyt_onboard_complete_date;
    @BindView(R.id.edt_job_location)
    EditText edt_job_location;
    @BindView(R.id.btn_next)
    Button btn_next;
    int currency =0;
    String empdate = "";
    public static EmployeeDataFragment newInstance(String param1, String param2) {
        EmployeeDataFragment fragment = new EmployeeDataFragment();
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
        loadData();

    }

    void initLayout(){
        edt_firstname.setText(employeeModel.getCanfirstname());
        edt_last_name.setText(employeeModel.getCanlastname());
        edt_company_email.setText(employeeModel.getEmpemail());
        edt_address.setText(employeeModel.getCanaddress());
        edt_salary.setText(employeeModel.getCurrentpaystr());
        edt_employement_date.setText(Commons.dateTime(employeeModel.getEmpdate()));
        edt_primary_phonenumber.setText(employeeModel.getEmpprimaryphone());
        edt_primary_workernumber.setText(employeeModel.getEmpworkphone());
        edt_job_title.setText(employeeModel.getCurrentjobtitle());
        edt_job_location.setText(employeeModel.getCurrentlocation());
        edt_employee_id.setText(employeeModel.getEmployeeid());
        edt_program_name.setText(employeeModel.getEmpprogram());
        edt_program_startdate.setText(Commons.dateTime(employeeModel.getEmppstartdate()));
        edt_program_enddate.setText(Commons.dateTime(employeeModel.getEmppenddate()));
        edt_bonus.setText(employeeModel.getEmpbonus());

    }

    void loadData(){
        context.showProgress();
        String api_link = API.GET_ONBOARDING_EMPLOYEE + "/"+ String.valueOf(context.onboardingModel.getRid())  ;
        StringRequest myRequest = new StringRequest(
                Request.Method.GET,
                api_link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                        context.closeProgress();
                        try {
                            JSONArray jsonArray = new JSONArray(json);
                            if(jsonArray.length()>0){
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                employeeModel.initModel(jsonObject);
                                initLayout();
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
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = (OnBoardingTaskActivity) context;

    }


    void putEmployeeDate(){
        context.showProgress();
        String api_link = API.GET_ONBOARDING_EMPLOYEE;
        JSONObject params = new JSONObject();
        try {

            params.put("rid",context.onboardingModel.getRid());
            params.put("jid",context.onboardingModel.getJid());
            params.put("currentpaystr",edt_salary.getText().toString());
            params.put("canaddress",edt_address.getText().toString());
            params.put("empbonus",edt_bonus.getText().toString());

            params.put("emppstartdate",dateConvert(edt_program_startdate.getText().toString()));
            params.put("emppenddate",dateConvert(edt_program_enddate.getText().toString()));
            params.put("empdate",dateConvert(edt_employement_date.getText().toString()));
            params.put("empcurrency",currency);

            params.put("canzip","08755");
            params.put("currentdeptid",22295);
            params.put("canstate","TX");
            params.put("paytype",1);
            params.put("currentsupid","12706");
            params.put("empotheremail","kristen.nash@atsondemand.com");
            params.put("empstate","NJ");
            params.put("cancity","Dallas");

            params.put("empprimaryphone",edt_primary_phonenumber.getText().toString());
            params.put("empprogram",edt_program_name.getText().toString());
            params.put("employeeid",edt_employee_id.getText().toString());
            params.put("location",edt_job_location.getText().toString());
            params.put("canfirstname",edt_firstname.getText().toString());
            params.put("canlastname",edt_last_name.getText().toString());
            params.put("empworkphone",edt_primary_workernumber.getText().toString());
            params.put("currentjobtitle",edt_job_title.getText().toString());
            params.put("empemail",edt_company_email.getText().toString());


        } catch (JSONException e) {

        }

        Log.d("aaaaa",params.toString());
        new BaseJsonObjectRequest(
                Request.Method.PUT, api_link, params,
                response -> {
                    context.closeProgress();
                    Toast.makeText(context, "Employee Data updated Successfully!", Toast.LENGTH_SHORT).show();
                    context.viewPager.setCurrentItem(1);
                }, this::handleMultiPartResponseError);
    }
    private void handleMultiPartResponseError(VolleyError error) {
        context.closeProgress();
        Log.d("Exception ===" ,error.toString());
    }

    void selectOne(int type){
        ArrayList<String> arrayList = new ArrayList<>();
        if(type == 0) {
            for (int i = 0; i < Commons.currencyModels.size(); i++)
                arrayList.add(Commons.currencyModels.get(i).getCurrencyName());
        }else {
            for (int i = 0; i < 5; i++)
                arrayList.add("Option " + String.valueOf(i));
        }
        SelectJobOneDialog selectJobOneDialog = new SelectJobOneDialog();
        selectJobOneDialog.setOnConfirmListener(new SelectJobOneDialog.OnConfirmListener() {
            @Override
            public void onConfirm(int selectPosstion) {
                if(type ==0) {
                    edt_salary_currency.setText(Commons.currencyModels.get(selectPosstion).getCurrencyName());
                    currency = Commons.currencyModels.get(selectPosstion).getCurrencyID();
                }else if(type ==1){
                    edt_current_supervisor.setText(arrayList.get(selectPosstion));
                }else if(type ==2){
                    edt_department.setText(arrayList.get(selectPosstion));
                }else if(type ==3){
                    edt_job_locationstate.setText(arrayList.get(selectPosstion));
                }
            }
        },arrayList,1);
        selectJobOneDialog.show(getParentFragmentManager(), "DeleteMessage");
    }

    void SelectDay(int type){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog.OnDateSetListener mDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker , int year , int month , int day) {
                cal.set(year,month,day);
                SimpleDateFormat format = new SimpleDateFormat("dd'th' MMM, yyyy");
                if(type == 0) {
                    edt_employement_date.setText(format.format(cal.getTime()));
                }else if(type==1){
                    edt_program_startdate.setText(format.format(cal.getTime()));
                }else if(type==2){
                    edt_program_enddate.setText(format.format(cal.getTime()));
                }else if(type==3){
                    edt_onboard_complete_date.setText(format.format(cal.getTime()));
                }

            }
        };
        DatePickerDialog dialog =  new DatePickerDialog(
                getContext(),
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateListener,
                year,month,day
        );
        dialog.getWindow().setBackgroundDrawable( new ColorDrawable( Color.TRANSPARENT ) );
        dialog.show();


    }
    @OnClick({R.id.lyt_email,R.id.lyt_currency,R.id.lyt_current_supervisor, R.id.lyt_department,R.id.lyt_onboard_complete_date,
            R.id.lyt_job_location_state,R.id.lyt_program_startdate,R.id.lyt_program_enddate,R.id.btn_next,R.id.lyt_employee_date})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lyt_email:
                break;
            case R.id.lyt_currency:
                selectOne(0);
                break;
            case R.id.lyt_current_supervisor:
                selectOne(1);
                break;
            case R.id.lyt_department:
                selectOne(2);
                break;
            case R.id.lyt_job_location_state:
                selectOne(3);
                break;
            case R.id.lyt_program_startdate:
                SelectDay(1);
                break;
            case R.id.lyt_program_enddate:
                SelectDay(2);
                break;
            case R.id.lyt_onboard_complete_date:
                SelectDay(3);
                break;
            case R.id.lyt_employee_date:
                SelectDay(0);
                break;
            case R.id.btn_next:
                putEmployeeDate();
                break;
        }
    }
    String dateConvert(String time){
        DateFormat df = new SimpleDateFormat("dd'th' MMM, yyyy");
        Date result;
        String date = "";
        try {
            result = df.parse(time);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            date  = sdf.format(result);
        }catch (Exception e){
            Log.d("exception_ time", e.toString());
        }
        return date;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_employee_data, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }
}