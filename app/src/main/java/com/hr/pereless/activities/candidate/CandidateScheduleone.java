package com.hr.pereless.activities.candidate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hr.pereless.R;
import com.hr.pereless.activities.auth.LoginActivity;
import com.hr.pereless.adapter.candidate.CandidateoneAdapter;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.dialog.ConfirmDialog;
import com.hr.pereless.preference.PrefConst;
import com.hr.pereless.preference.Preference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CandidateScheduleone extends CommonActivity implements DatePickerDialog.OnDateSetListener{
    @BindView(R.id.btn_back)
    Button btnBack;
    @BindView(R.id.toolbarlayout)
    RelativeLayout toolbarlayout;
    @BindView(R.id.layoutone)
    LinearLayout layoutone;
    @BindView(R.id.layouttwo)
    LinearLayout layouttwo;
    @BindView(R.id.bottomlayout)
    LinearLayout bottomlayout;
    @BindView(R.id.layoutthree)
    LinearLayout layoutthree;
    @BindView(R.id.candidaterv)
    RecyclerView candidaterv;
    CandidateoneAdapter candidateoneAdapter;
    //@BindView(R.id.img_prev)
    //ImageView imgPrev;
    @BindView(R.id.txt_current_date)
    TextView txtCurrentDate;
    @BindView(R.id.calendar)
    ImageView imgNext;
    @BindView(R.id.list_add)
    ImageView listAdd;
    int count=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_scheduleone);
        ButterKnife.bind(this);


        candidateoneAdapter = new CandidateoneAdapter(this, new CandidateoneAdapter.OnDeleteListener() {
            @Override
            public void onDelete(int possition) {
                ConfirmDialog confirmDialog = new ConfirmDialog();
                confirmDialog.setOnConfirmListener(new ConfirmDialog.OnConfirmListener() {
                    @Override
                    public void onConfirm() {
                        count--;
                        candidateoneAdapter.setData(count);
                    }
                },getString(R.string.delete_interview),"Delete","Cancel");
                confirmDialog.show(getSupportFragmentManager(), "DeleteMessage");
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(CandidateScheduleone.this);
        candidaterv.setLayoutManager(mLayoutManager);
        candidaterv.setItemAnimator(new DefaultItemAnimator());
        candidaterv.setAdapter(candidateoneAdapter);
        candidateoneAdapter.setData(count);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String currentDateandTime = sdf.format(new Date());
        txtCurrentDate.setText(currentDateandTime);
    }

    private void showDatePickerDialog() {
        DatePickerDialog dpd=new DatePickerDialog(
                this,R.style.DialogTheme,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );

        dpd.show();

    }

    @OnClick({R.id.btn_back, R.id.calendar,R.id.list_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.calendar:
                showDatePickerDialog();
                break;
            case R.id.btn_back:
                finish(this);
                break;
            case R.id.list_add:
                count++;
                candidateoneAdapter.setData(count);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        month=month+1;
        String selected_date,month_str;
        if(month<10){
            month_str = "0"+month;
        }
        else{
            month_str=Integer.toString(month);
        }
        selected_date=year + "." + month_str + "."+ dayOfMonth;
        txtCurrentDate.setText(selected_date);


    }
}