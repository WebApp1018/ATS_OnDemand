package com.hr.pereless.fragment.home;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.model.GradientColor;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.hr.pereless.R;
import com.hr.pereless.activities.MainActivity;
import com.hr.pereless.api.API;
import com.hr.pereless.application.AppController;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.commons.Constants;
import com.hr.pereless.model.home.ActivityChatModel;
import com.hr.pereless.model.home.StatusModel;
import com.hr.pereless.view.XYMarkerView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hr.pereless.base.BaseActivity.closeProgress;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragmentTest#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragmentTest extends Fragment implements View.OnClickListener {
    View view;
    FrameLayout framelayout;
    NestedScrollView nestedScroll;
    LinearLayout layout_one,layout_two,lyt_three,lyt_four,linearJobhistory;
    TextView movedtxt,jobstxt,newcandidatetxt,todaytxt,tvXMax,tvYMax,savedtxt;
    PieChart chart1;
    BarChart barchart1;
    SeekBar seekBar2,seekBar1;
    RecyclerView rv_history;
    MainActivity mainActivity;
    int api_number = 0;
    int year = 0;
    LinearLayout lyt_year,lyt_month,lyt_week;
    TextView txv_year;
    HashMap<Integer, ArrayList<Integer>> chartFlwowData = new HashMap<>();
    ArrayList<Integer> values = new ArrayList<>();
    ArrayList<String>titles = new ArrayList<>();
    public static HomeFragmentTest newInstance(String param1, String param2) {
        HomeFragmentTest fragment = new HomeFragmentTest();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        todaytxt =view.findViewById(R.id.todaytxt);
        tvXMax =view.findViewById(R.id.tvXMax);
        tvYMax =view.findViewById(R.id.tvYMax);
        chart1 =view.findViewById(R.id.chart1);
        barchart1 =view.findViewById(R.id.barchart1);
        seekBar2 =view.findViewById(R.id.seekBar2);
        seekBar1 =view.findViewById(R.id.seekBar1);
        rv_history =view.findViewById(R.id.rv_history);
        framelayout =view.findViewById(R.id.framelayout);
        nestedScroll =view.findViewById(R.id.nestedScroll);
        layout_one =view.findViewById(R.id.layout_one);
        layout_two =view.findViewById(R.id.layout_two);
        lyt_three =view.findViewById(R.id.lyt_three);
        lyt_four =view.findViewById(R.id.lyt_four);
        linearJobhistory =view.findViewById(R.id.linearJobhistory);
        movedtxt =view.findViewById(R.id.movedtxt);
        jobstxt =view.findViewById(R.id.jobstxt);
        newcandidatetxt =view.findViewById(R.id.newcandidatetxt);
        savedtxt =view.findViewById(R.id.savedtxt);
        newcandidatetxt =view.findViewById(R.id.newcandidatetxt);
        lyt_year = view.findViewById(R.id.lyt_year);
        lyt_month = view.findViewById(R.id.lyt_month);
        lyt_week = view.findViewById(R.id.lyt_week);
        txv_year = view.findViewById(R.id.txv_year);
        lyt_year.setOnClickListener(this);
        lyt_month.setOnClickListener(this);
        lyt_week.setOnClickListener(this);
        initLayout();
    }



    void initLayout(){
        mainActivity.showProgress();
        getApi(API.GET_STATE4LANDING);
//        getApi(API.GET_CHART_ACTIVITY);
        getApi(API.GET_JOB_CHART);
        getApi(API.GET_CHART_FLOW);

    }

    void setLayout(){
        jobstxt.setText(String.valueOf(Commons.g_user.getStatusModel().getJobcCount()));
        movedtxt.setText(String.valueOf(Commons.g_user.getStatusModel().getMoveCount()));
        newcandidatetxt.setText(String.valueOf(Commons.g_user.getStatusModel().getCandidateCount()));
        todaytxt.setText(String.valueOf(Commons.g_user.getStatusModel().getEventCount()));
        savedtxt.setText(String.valueOf(Commons.g_user.getStatusModel().getSavedJobCount()));
        setData(4, 10);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        setBarchartValues();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lyt_year:
                selectYear();
                break;
            case R.id.lyt_month:
                break;
            case R.id.lyt_week:
                break;
        }
    }

    void selectYear(){
        DatePickerDialog.OnDateSetListener mDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker , int _year , int month , int day) {
                month = month + 1;
                year = _year;
                setBarchartValues();


            }
        };
        DatePickerDialog dpd =  new DatePickerDialog(
                mainActivity,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateListener,
                year,1,1
        );
        try {
            java.lang.reflect.Field[] datePickerDialogFields = dpd.getClass().getDeclaredFields();
            for (java.lang.reflect.Field datePickerDialogField : datePickerDialogFields) {
                if (datePickerDialogField.getName().equals("mDatePicker")) {
                    datePickerDialogField.setAccessible(true);
                    DatePicker datePicker = (DatePicker) datePickerDialogField.get(dpd);
                    java.lang.reflect.Field[] datePickerFields = datePickerDialogField.getType().getDeclaredFields();
                    for (java.lang.reflect.Field datePickerField : datePickerFields) {
                        Log.i("test", datePickerField.getName());
                        if ("mDaySpinner".equals(datePickerField.getName())) {
                            datePickerField.setAccessible(true);
                            Object dayPicker = datePickerField.get(datePicker);
                            ((View) dayPicker).setVisibility(View.GONE);
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
        }
        dpd.getWindow().setBackgroundDrawable( new ColorDrawable( Color.TRANSPARENT ) );
        dpd.show();
    }

    void getApi(String api_link){
        StringRequest myRequest = new StringRequest(
                Request.Method.GET,
                api_link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                        if(api_link.equals(API.GET_STATE4LANDING)){
                            StatusModel statusModel = new StatusModel();
                            statusModel.initModel(json);
                            Commons.g_user.setStatusModel(statusModel);
                        }else if(api_link.equals(API.GET_JOB_CHART)){
//                            ActivityChatModel activityChatModel = new ActivityChatModel();
//                            activityChatModel.initModel(json);
//                            Commons.g_user.setActivityChatModel(activityChatModel);
                            JSONArray jsonArray = null;
                            try {
                                jsonArray = new JSONArray(json);
                                values.clear();
                                titles.clear();
                                for(int i =0;i<jsonArray.length();i++){
                                    values.add(jsonArray.getJSONObject(i).getInt("count"));
                                    titles.add(jsonArray.getJSONObject(i).getString("label"));
                                }
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }

                        }else if(api_link.equals(API.GET_CHART_FLOW)){
                            try {
                                JSONArray jsonArray = new JSONArray(json);
                                for(int i =0;i<jsonArray.length();i++){
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    ArrayList<Integer>arrayList = new ArrayList<>();
                                    int year_number = jsonObject.getInt("name");
                                    JSONArray year_data = jsonObject.getJSONArray("data");
                                    for(int j = 0 ; j<year_data.length();j++){
                                        arrayList.add(Integer.parseInt(year_data.get(j).toString()));
                                    }
                                    chartFlwowData.put(year_number,arrayList);
                                }
                            }catch (Exception e){
                                closeProgress();
                            }
                           Commons.g_user.setChartFlwowData(chartFlwowData);
                        }
                        api_number++;
                        if(api_number==3){
                            closeProgress();
                            setLayout();
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

    private void setData(int count, float range) {
        ArrayList<PieEntry> entries = new ArrayList<>();

//        int crhome =60;
//        Double explorer =55.0;
//        Double firefox =45.6;
//        Double opera=89.1;

        for(int i = 0 ;i<values.size();i++){
            entries.add(new PieEntry((Float.valueOf(values.get(i))),titles.get(i),getResources().getDrawable(R.drawable.filledstart)));
        }
        PieDataSet dataSet = new PieDataSet(entries, "");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(0f);
        dataSet.setIconsOffset(new MPPointF(30, 20));
        dataSet.setSelectionShift(15f);

        // add a lot of colors
        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter(chart1));
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);

        chart1.setData(data);

        // undo all highlights
        chart1.highlightValues(null);

        chart1.invalidate();
    }

    private void setBarchartValues( ) {
        // set the bar vaues or y axis values
        txv_year.setText(String.valueOf(year) + " Year");
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        for(int i =0;i<12;i++){
            if(chartFlwowData.get(year) !=null)
                barEntries.add(new BarEntry(i, chartFlwowData.get(year).get(i)));
            else {
                barEntries.add(new BarEntry(i, 0));
            }
        }
        BarDataSet barDataSet = new BarDataSet(barEntries, "Months");
        //set the color to the bar
        int startColor1 = ContextCompat.getColor(getActivity(), android.R.color.holo_orange_light);
        int startColor2 = ContextCompat.getColor(getActivity(), android.R.color.holo_blue_light);
        int startColor3 = ContextCompat.getColor(getActivity(), android.R.color.holo_orange_light);
        int startColor4 = ContextCompat.getColor(getActivity(), android.R.color.holo_green_light);
        int startColor5 = ContextCompat.getColor(getActivity(), android.R.color.holo_red_light);

        int endColor1 = ContextCompat.getColor(getActivity(), android.R.color.holo_orange_light);
        int endColor2 = ContextCompat.getColor(getActivity(), android.R.color.holo_blue_light);
        int endColor3 = ContextCompat.getColor(getActivity(), android.R.color.holo_orange_light);
        int endColor4 = ContextCompat.getColor(getActivity(), android.R.color.holo_green_light);
        int endColor5 = ContextCompat.getColor(getActivity(), android.R.color.holo_red_light);

        List<GradientColor> gradientColors = new ArrayList<>();
        gradientColors.add(new GradientColor(startColor1, endColor1));
        gradientColors.add(new GradientColor(startColor2, endColor2));
        gradientColors.add(new GradientColor(startColor3, endColor3));
        gradientColors.add(new GradientColor(startColor4, endColor4));
        gradientColors.add(new GradientColor(startColor5, endColor5));
        barDataSet.setGradientColors(gradientColors);

        BarData theData = new BarData(barDataSet);
        theData.setBarWidth(0.9f);
        theData.setValueTextSize(10f);
        barchart1.setData(theData);

        barchart1.setTouchEnabled(true);
        barchart1.setDragEnabled(true);
        barchart1.setScaleEnabled(true);
        barchart1.setFitBars(true);
        //set the x axis value
        XAxis xAxis = barchart1.getXAxis();
        ValueFormatter xAxisFormatter=new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return Constants.stringsMonth[(int) value];
            }
        };
        xAxis.setValueFormatter(xAxisFormatter);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(7);
        xAxis.setDrawLabels(true);

        XYMarkerView mv = new XYMarkerView(getActivity(), xAxisFormatter);
        mv.setChartView(barchart1);
        barchart1.setMarker(mv);
        barchart1.notifyDataSetChanged();
        barchart1.invalidate();
    }

    private void barchart() {

        barchart1.setDrawBarShadow(false);
        barchart1.setDrawValueAboveBar(true);
        barchart1.getDescription().setEnabled(false);
        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        barchart1.setMaxVisibleValueCount(60);
        // scaling can now only be done on x- and y-axis separately
        barchart1.setPinchZoom(false);
        barchart1.setDoubleTapToZoomEnabled(false);
        barchart1.getAxisLeft().setDrawGridLines(false);
        barchart1.getXAxis().setDrawGridLines(false);
        barchart1.getAxisRight().setDrawTopYLabelEntry(false);
        barchart1.getAxisRight().setDrawGridLines(false);
        barchart1.setDrawGridBackground(false);
        barchart1.setDrawGridBackground(false);
        barchart1.setDrawBorders(false);
        barchart1.setScaleYEnabled(false);
        barchart1.setAutoScaleMinMaxEnabled(false);

        Legend l = barchart1.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(10f);

        YAxis yAxis = barchart1.getAxisRight();
        yAxis.setDrawAxisLine(false);
        yAxis.setLabelCount(0, false);
        yAxis.setDrawLabels(false);

        YAxis XAxis1 = barchart1.getAxisLeft();
        XAxis1.setDrawAxisLine(false);
        XAxis1.setLabelCount(0, false);
        XAxis1.setDrawLabels(false);

        // setData1(12, 1);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home_test, container, false);
        return view;
    }
}