package com.hr.pereless.activities;

import android.os.Bundle;
import android.os.Handler;

import com.hr.pereless.R;
import com.hr.pereless.activities.auth.LoginActivity;
import com.hr.pereless.activities.job.CreateJobActivity;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.commons.Commons;

import java.text.DateFormatSymbols;
import java.util.Locale;

public class SplashActivity extends CommonActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Commons.Months = new DateFormatSymbols(Locale.ENGLISH).getShortMonths();
        readJson();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goTo(SplashActivity.this, LoginActivity.class, true);
//                goTo(SplashActivity.this, CreateJobActivity.class, true);
            }
        }, 3000);
    }
}