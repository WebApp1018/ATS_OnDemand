package com.hr.pereless.fragment.schedule;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.hr.pereless.R;
import com.hr.pereless.activities.MainActivity;
import com.hr.pereless.model.BarChartModel;
import com.hr.pereless.model.schedule.ScheduleModel;
import com.hr.pereless.view.weekview.DateTimeInterpreter;
import com.hr.pereless.view.weekview.MonthLoader;
import com.hr.pereless.view.weekview.WeekView;
import com.hr.pereless.view.weekview.WeekViewEvent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ScheduleBasicActivity extends Fragment implements WeekView.EventClickListener, MonthLoader.MonthChangeListener, WeekView.EventLongPressListener, WeekView.EmptyViewLongPressListener{
    private static final int TYPE_DAY_VIEW = 1;
    private static final int TYPE_THREE_DAY_VIEW = 2;
    private static final int TYPE_WEEK_VIEW = 3;
    /*@BindView(R.id.tabs)
     TabLayout tabs;
     @BindView(R.id.viewpager)
     ViewPager viewpager;*/
    @BindView(R.id.lineartSchedule)
    LinearLayout lineartSchedule;
    @BindView(R.id.weekView)
    WeekView weekView;
    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.spinnerSubject)
    Spinner spinnerSubject;
    @BindView(R.id.btn_invite)
    Button btnInvite;
    @BindView(R.id.btn_mycalendar)
    Button btnMycalendar;
    @BindView(R.id.txt_first)
    TextView txtFirst;
    @BindView(R.id.txt_sec)
    TextView txtSec;
    @BindView(R.id.txt_third)
    TextView txtThird;
    @BindView(R.id.txt_four)
    TextView txtFour;
    @BindView(R.id.msg_send)
    Button msgSend;
    @BindView(R.id.linearEmpty)
    LinearLayout linearEmpty;
    BarChart barChart;
    @BindView(R.id.linearInvideSub)
    LinearLayout linearInvideSub;
    @BindView(R.id.linearMycalendar)
    LinearLayout linearMyCalendar;
    @BindView(R.id.apinnerLocation)
    Spinner spinnerLocation;
    public static List<ScheduleModel> ScheduleResponseList;
    BarChartModel barChartModel = new BarChartModel();
    Random rnd;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_dayview)
    AppCompatButton btnDayview;
    @BindView(R.id.btn_weekview)
    AppCompatButton btnWeekview;
    @BindView(R.id.btn_monthview)
    AppCompatButton btnMonthview;
    @BindView(R.id.barchart)
    BarChart barchart;
    @BindView(R.id.linearnvide)
    LinearLayout linearnvide;
    @BindView(R.id.linearOne)
    LinearLayout linearOne;

    private int mWeekViewType = TYPE_THREE_DAY_VIEW;
    private WeekView mWeekView;
    private List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
    boolean calledNetwork = false;
    Unbinder unbinder;
    Dialog eventdailog;
    MainActivity mainActivity;
    String token;
    String status = "0";
    String[] stringsTime = {
            "11 AM", "10 AM", "5", "6",
    };

    String Name, job, subject, location, interviewtime, notes;

    /* public static Fragment newInstance() {

         BaseActivity fragment = new BaseActivity();
         return fragment;

     }*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_schedule_basic, container, false);
        unbinder = ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        barChart = (BarChart) view.findViewById(R.id.barchart);
        rnd = new Random();


        barChartModel.setBarColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
        barChartModel.setBarTag(null);
        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.subject, R.layout.item_spinner_dropdown_layout);
        adapter1.setDropDownViewResource(R.layout.item_spinner_dropdown);
        spinnerSubject.setAdapter(adapter1);

        ArrayAdapter adapter3 = ArrayAdapter.createFromResource(getContext(), R.array.location, R.layout.item_spinner_dropdown_layout);
        adapter3.setDropDownViewResource(R.layout.item_spinner_dropdown);
        spinnerLocation.setAdapter(adapter3);
        callBarChatInvide();
        //  getsheduls();
        btnInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // callBarChatInvide();
                btnInvite.setBackground(getContext().getResources().getDrawable(R.drawable.btn_bg_primary_fill));
                btnMycalendar.setBackground(getContext().getResources().getDrawable(R.drawable.btn_bg_gray_full));
                linearEmpty.setVisibility(View.GONE);
                barChart.setVisibility(View.VISIBLE);
                linearInvideSub.setVisibility(View.VISIBLE);
                linearMyCalendar.setVisibility(View.GONE);
            }
        });

        btnMycalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // callBarChatMycalendar();
                //  callBarChatInvide();
                btnInvite.setBackground(getContext().getResources().getDrawable(R.drawable.btn_bg_gray_full));
                btnMycalendar.setBackground(getContext().getResources().getDrawable(R.drawable.btn_bg_primary_fill));
                linearEmpty.setVisibility(View.GONE);
                barChart.setVisibility(View.VISIBLE);
                linearInvideSub.setVisibility(View.GONE);
                linearMyCalendar.setVisibility(View.VISIBLE);

            }
        });
        mainActivity.btnbackarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (status.equalsIgnoreCase("1")) {
                    Log.e("stsusmaclic", status);
                    mainActivity.imagNav.setImageResource(R.drawable.nav_menu_icon);
                    mainActivity.txvTitle.setText("Schedule");
                    status = "0";
                    lineartSchedule.setVisibility(View.GONE);
                    mWeekView.setVisibility(View.VISIBLE);
                    mainActivity.drawerLayoutNew.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                    mainActivity.drawerLayoutNew.closeDrawers();


                } else {

                    Log.e("clickbas", "frage");
                    mainActivity.drawerLayoutNew.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                    mainActivity.drawerLayoutNew.openDrawer(GravityCompat.START);
                }


            }
        });


        msgSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // callBarChatMycalendar();
                //  callBarChatInvide();

            }
        });


       /* setupViewPager(viewpager);
        tabs.setupWithViewPager(viewpager);*/
        mWeekView = (WeekView) view.findViewById(R.id.weekView);

        // Show a toast message about the touched event.
        mWeekView.setOnEventClickListener(this);

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        mWeekView.setMonthChangeListener(this);

        // Set long press listener for events.
        mWeekView.setEventLongPressListener(this);

        // Set long press listener for empty view
        //   mWeekView.setEmptyViewLongPressListener(this);

        // Set up a date time interpreter to interpret how the date and time will be formatted in
        // the week view. This is optional.
        setupDateTimeInterpreter(false);


        return view;
    }



   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
*/


    private void callBarChatMycalendar() {

        ArrayList<BarEntry> entries = new ArrayList<>();


        BarDataSet barDataSet = new BarDataSet(entries, " ");

        ArrayList<BarEntry> barEntries = new ArrayList<BarEntry>();

        barEntries.add(new BarEntry(0, 1.5f));
        barEntries.add(new BarEntry(1, 2));
        barEntries.add(new BarEntry(2, 3.8f));
        barEntries.add(new BarEntry(3, 3.5f));
        barEntries.add(new BarEntry(4, 2.9f));
        barEntries.add(new BarEntry(5, 2.5f));

        barDataSet = new BarDataSet(barEntries, "");
        barDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        //  barDataSet.setColor(getColor("defaultYellow"));
        barDataSet.setHighlightEnabled(false);
        barDataSet.setHighLightColor(Color.RED);
        //  barDataSet.setValueTextSize(defaultValueTextSize);
        //  barDataSet.setValueTextColor(getColor("primaryDark"));

        BarData barData = new BarData(barDataSet);
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.setPinchZoom(false);
        barChart.setDoubleTapToZoomEnabled(false);
        barChart.getDescription().setEnabled(false);

        barChart.getAxisLeft().setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return stringsTime[(int) value];
            }
        });

        barData.setDrawValues(false); // remove bar above value


        // barChart.getDescription().setText("No. of Contracts signed in 6 months");
        //barChart.getDescription().setTextSize(12);

        barChart.setDrawMarkers(true);
        // barChart.setMarker(markerView(context));
        // barChart.getAxisLeft().addLimitLine(lowerLimitLine(2,"Minimum",2,12,getColor("defaultOrange"),getColor("defaultOrange")));
        //  barChart.getAxisLeft().addLimitLine(upperLimitLine(5,"Target",2,12,getColor("defaultGreen"),getColor("defaultGreen")));
        barChart.getAxisLeft().setAxisMinimum(0);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTH_SIDED);

        ArrayList<String> labels = new ArrayList<String>();

        labels.add("Frank");
        labels.add("John");
        labels.add("Khalid");
        labels.add("Lee");
        labels.add("Frank");
        labels.add("John");


        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));

        barChart.getXAxis().setGranularityEnabled(true);
        barChart.getXAxis().setGranularity(1.0f);
        barChart.getXAxis().setLabelCount(barDataSet.getEntryCount());

        barChart.setData(barData);

        barChart.getXAxis().setEnabled(true);


        barChart.getAxisRight().setDrawTopYLabelEntry(true);

        YAxis rightYAxis = barChart.getAxisRight();
        rightYAxis.setEnabled(false);

        XAxis xAxis = barChart.getXAxis();
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
       /* xAxis.setPosition(XAxis.XAxisPosition.TOP);
        barChart.animateY(1000);*/

        barChart.setDrawBorders(true);
        barChart.setScaleYEnabled(false);

        barChart.setAutoScaleMinMaxEnabled(true);

    }

    private void callBarChatInvide() {


        ArrayList<BarEntry> entries = new ArrayList<>();
        BarDataSet barDataSet = new BarDataSet(entries, " ");
        ArrayList<BarEntry> barEntries = new ArrayList<BarEntry>();
        barEntries.add(new BarEntry(0, 1.5f));
        barEntries.add(new BarEntry(1, 2));
        barEntries.add(new BarEntry(2, 3.8f));
        barEntries.add(new BarEntry(3, 3.5f));
        barEntries.add(new BarEntry(4, 2.9f));
        barEntries.add(new BarEntry(5, 2.5f));
        barEntries.add(new BarEntry(6, 1.0f));

        barDataSet = new BarDataSet(barEntries, "");
        barDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setHighlightEnabled(false);
        barDataSet.setHighLightColor(Color.RED);
        barChart.setPinchZoom(false);
        barChart.setDoubleTapToZoomEnabled(false);

        BarData barData = new BarData(barDataSet);
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.getDescription().setEnabled(false);
        barData.setDrawValues(false); // remove bar above value
        barChart.setDrawMarkers(true);
        barChart.getAxisLeft().setAxisMinimum(0);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTH_SIDED);

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Mon");
        labels.add("Tue");
        labels.add("Wed");
        labels.add("The");
        labels.add("Fri");
        labels.add("Sat");
        labels.add("Sun");

        barChart.getAxisLeft().setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return stringsTime[(int) value];
            }
        });
        // barChart.setScaleY(2.0f);

        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        barChart.getXAxis().setGranularityEnabled(true);
        barChart.getXAxis().setGranularity(1.0f);
        barChart.getXAxis().setLabelCount(barDataSet.getEntryCount());

        // barChart.getYAx().setValueFormatter(new IndexAxisValueFormatter(labels));

        barChart.setData(barData);
        barChart.getXAxis().setEnabled(true);
        barChart.getAxisRight().setDrawTopYLabelEntry(true);
        YAxis rightYAxis = barChart.getAxisRight();
        rightYAxis.setEnabled(false);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        barChart.animateY(1000);
        barChart.setDrawBorders(true);
        barChart.setScaleYEnabled(false);
        barChart.setAutoScaleMinMaxEnabled(true);

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

    @OnClick({R.id.txt_first, R.id.txt_sec, R.id.txt_third, R.id.txt_four,R.id.btn_dayview, R.id.btn_weekview, R.id.btn_monthview})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_first:
                txtFirst.setBackground(getContext().getResources().getDrawable(R.drawable.btn_bg_blue_fill_two));
                txtSec.setBackground(getContext().getResources().getDrawable(R.drawable.btn_bg_white_fill_two));
                txtThird.setBackground(getContext().getResources().getDrawable(R.drawable.btn_bg_white_fill_two));
                txtFour.setBackground(getContext().getResources().getDrawable(R.drawable.btn_bg_white_fill_two));

                txtFirst.setTextColor(getContext().getResources().getColor(R.color.colorWhite));
                txtSec.setTextColor(getContext().getResources().getColor(R.color.black));
                txtThird.setTextColor(getContext().getResources().getColor(R.color.black));
                txtFour.setTextColor(getContext().getResources().getColor(R.color.black));
                break;
            case R.id.txt_sec:
                txtFirst.setBackground(getContext().getResources().getDrawable(R.drawable.btn_bg_white_fill_two));
                txtSec.setBackground(getContext().getResources().getDrawable(R.drawable.btn_bg_blue_fill_two));
                txtThird.setBackground(getContext().getResources().getDrawable(R.drawable.btn_bg_white_fill_two));
                txtFour.setBackground(getContext().getResources().getDrawable(R.drawable.btn_bg_white_fill_two));

                txtFirst.setTextColor(getContext().getResources().getColor(R.color.black));
                txtSec.setTextColor(getContext().getResources().getColor(R.color.colorWhite));
                txtThird.setTextColor(getContext().getResources().getColor(R.color.black));
                txtFour.setTextColor(getContext().getResources().getColor(R.color.black));
                break;
            case R.id.txt_third:
                txtFirst.setBackground(getContext().getResources().getDrawable(R.drawable.btn_bg_white_fill_two));
                txtSec.setBackground(getContext().getResources().getDrawable(R.drawable.btn_bg_white_fill_two));
                txtThird.setBackground(getContext().getResources().getDrawable(R.drawable.btn_bg_blue_fill_two));
                txtFour.setBackground(getContext().getResources().getDrawable(R.drawable.btn_bg_white_fill_two));

                txtFirst.setTextColor(getContext().getResources().getColor(R.color.black));
                txtSec.setTextColor(getContext().getResources().getColor(R.color.black));
                txtThird.setTextColor(getContext().getResources().getColor(R.color.colorWhite));
                txtFour.setTextColor(getContext().getResources().getColor(R.color.black));
                break;
            case R.id.txt_four:
                txtFirst.setBackground(getContext().getResources().getDrawable(R.drawable.btn_bg_white_fill_two));
                txtSec.setBackground(getContext().getResources().getDrawable(R.drawable.btn_bg_white_fill_two));
                txtThird.setBackground(getContext().getResources().getDrawable(R.drawable.btn_bg_white_fill_two));
                txtFour.setBackground(getContext().getResources().getDrawable(R.drawable.btn_bg_blue_fill_two));

                txtFirst.setTextColor(getContext().getResources().getColor(R.color.black));
                txtSec.setTextColor(getContext().getResources().getColor(R.color.black));
                txtThird.setTextColor(getContext().getResources().getColor(R.color.black));
                txtFour.setTextColor(getContext().getResources().getColor(R.color.colorWhite));
                break;
            case R.id.btn_dayview:
                if (mWeekViewType != TYPE_DAY_VIEW) {

                    mWeekViewType = TYPE_DAY_VIEW;
                    mWeekView.setNumberOfVisibleDays(1);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                }

                break;
            case R.id.btn_weekview:
                if (mWeekViewType != TYPE_THREE_DAY_VIEW) {

                    mWeekViewType = TYPE_THREE_DAY_VIEW;
                    mWeekView.setNumberOfVisibleDays(3);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                }
                break;
            case R.id.btn_monthview:

                if (mWeekViewType != TYPE_WEEK_VIEW) {

                    mWeekViewType = TYPE_WEEK_VIEW;
                    mWeekView.setNumberOfVisibleDays(7);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                }
                break;
        }
    }




   /* @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        return null;
    }*/

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        setupDateTimeInterpreter(id == R.id.action_week_view);
        switch (id) {
            case R.id.action_today:
                mWeekView.goToToday();
                return true;
            case R.id.action_day_view:
                if (mWeekViewType != TYPE_DAY_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_DAY_VIEW;
                    mWeekView.setNumberOfVisibleDays(1);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                }
                return true;
            case R.id.action_three_day_view:
                if (mWeekViewType != TYPE_THREE_DAY_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_THREE_DAY_VIEW;
                    mWeekView.setNumberOfVisibleDays(3);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                }
                return true;
            case R.id.action_week_view:
                if (mWeekViewType != TYPE_WEEK_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_WEEK_VIEW;
                    mWeekView.setNumberOfVisibleDays(7);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Set up a date time interpreter which will show short date values when in week view and long
     * date values otherwise.
     *
     * @param shortDate True if the date values should be short.
     */
    private void setupDateTimeInterpreter(final boolean shortDate) {
        mWeekView.setDateTimeInterpreter(new DateTimeInterpreter() {
            @Override
            public String interpretDate(Calendar date) {
                SimpleDateFormat weekdayNameFormat = new SimpleDateFormat("EEE", Locale.getDefault());
                String weekday = weekdayNameFormat.format(date.getTime());
                SimpleDateFormat format = new SimpleDateFormat(" M/d", Locale.getDefault());

                // All android api level do not have a standard way of getting the first letter of
                // the week day name. Hence we get the first char programmatically.
                // Details: http://stackoverflow.com/questions/16959502/get-one-letter-abbreviation-of-week-day-of-a-date-in-java#answer-16959657
                if (shortDate)
                    weekday = String.valueOf(weekday.charAt(0));
                return weekday.toUpperCase() + format.format(date.getTime());
            }

            @Override
            public String interpretTime(int hour) {
                return hour > 11 ? (hour - 12) + " PM" : (hour == 0 ? "12 AM" : hour + " AM");
            }
        });
    }

    protected String getEventTitle(Calendar time) {
        return String.format("Event of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.MONTH) + 1, time.get(Calendar.DAY_OF_MONTH));
    }


    public WeekView getWeekView() {
        return mWeekView;
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getActivity().getMenuInflater().inflate(R.menu.main1, menu);
        return true;
    }


    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {

        // Download events from network if it hasn't been done already. To understand how events are
        // downloaded using retrofit, visit http://square.github.io/retrofit
        if (!calledNetwork) {


           /* Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl("https://api.myjson.com/bins")
                    .addConverterFactory(GsonConverterFactory.create());
            *//*RestAdapter retrofit = new RestAdapter.Builder()
                    .setEndpoint("https://api.myjson.com/bins")
                    .build();
           */


            //service.listEvents((Callback<List<Event>>) getContext());
            calledNetwork = true;
        }

        // Return only the events that matches newYear and newMonth.
        List<WeekViewEvent> matchedEvents = new ArrayList<WeekViewEvent>();
        for (WeekViewEvent event : events) {
            if (eventMatches(event, newYear, newMonth)) {

                Log.e("Data", event.getName());
                matchedEvents.add(event);

            }
        }
        return matchedEvents;
    }

    private boolean eventMatches(WeekViewEvent event, int year, int month) {
        return (event.getStartTime().get(Calendar.YEAR) == year && event.getStartTime().get(Calendar.MONTH) == month - 1) || (event.getEndTime().get(Calendar.YEAR) == year && event.getEndTime().get(Calendar.MONTH) == month - 1);
    }

    @Override
    public void onEmptyViewLongPress(Calendar time) {
        Toast.makeText(getActivity(), "Empty view long pressed: " + getEventTitle(time), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
        //Toast.makeText(getActivity(), "Clicked  one" + event.getName(), Toast.LENGTH_SHORT).show();

        eventdailog = new Dialog(getActivity());
        eventdailog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        eventdailog.setContentView(R.layout.dialog_event);
        eventdailog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        eventdailog.show();
        Button edit = (Button) eventdailog.findViewById(R.id.edit);

        Button cancel = (Button) eventdailog.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventdailog.dismiss();
            }
        });


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventdailog.dismiss();

                //  {"scheduleID":110,"subject":"TEST HHH","location":"raipur","message":"sd","name":"developer job","interviewduration":30,"uid":000,"jobID":1,"condidateids":10,"image":"inh.jpg","notes":100,"interviewdatetime":"July, 24 2019 12:19:44","interviewerids":1}

                toupdateshedule("110", "TEST HHH", "raipur", "sd", "developerjpb", "job", "30", "01", "1", "10", "inh.jpg", "100", "March, 24 2020 12:19:44", "2");
                mainActivity.imagNav.setImageResource(R.drawable.back_icon);
                mainActivity.txvTitle.setText("Schedule");
                status = "1";
                lineartSchedule.setVisibility(View.VISIBLE);
                mWeekView.setVisibility(View.GONE);
                mainActivity.drawerLayoutNew.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }
        });


    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(getActivity(), "Long pressed event: " + event.getName(), Toast.LENGTH_SHORT).show();

    }
    private boolean valoidate() {
        boolean status = true;

        return status;
    }


    private void toupdateshedule(String scheduleID, String subject, String location, String message, String name, String job, String interviewDuration, String uid, String jobID, String condidateids, String image, String notes, String interviewdatetime, String interviewerids) {


    }


}
