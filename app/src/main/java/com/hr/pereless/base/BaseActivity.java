package com.hr.pereless.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hr.pereless.R;
import com.hr.pereless.commons.Commons;
import com.kaopiz.kprogresshud.KProgressHUD;

public abstract class BaseActivity extends AppCompatActivity implements Handler.Callback {

    public static Context _context = null;

    public Handler _handler = null;


    private static KProgressHUD _progressDlg;

    private Vibrator _vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _context = this;

        _vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        _handler = new Handler(this);

    }

    @Override
    protected void onResume() {
        _context = this;
        super.onResume();
    }

    @Override
    protected void onDestroy() {

       // closeProgress();

        try {
            if (_vibrator != null)
                _vibrator.cancel();
        } catch (Exception e) {
        }
        _vibrator = null;

        super.onDestroy();
    }

    public boolean _isEndFlag = true;

    public static final int BACK_TWO_CLICK_DELAY_TIME = 3000; // ms

    public Runnable _exitRunner = new Runnable() {

        @Override
        public void run() {

            _isEndFlag = false;
        }
    };

    public void onExit() {

        if (_isEndFlag == false) {

            Toast.makeText(this, getString(R.string.str_back_one_more_end),
                    Toast.LENGTH_SHORT).show();
            _isEndFlag = true;

            _handler.postDelayed(_exitRunner, BACK_TWO_CLICK_DELAY_TIME);

        } else if (_isEndFlag == true) {

            Commons.g_isAppRunning = false;
            finish(this);
        }
    }

    public static void showProgress(String strMsg,  boolean cancelable) {
        if (_progressDlg != null) {
            closeProgress();
            return;
        }

        _progressDlg = KProgressHUD.create(_context);
        _progressDlg
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(strMsg)
                .setCancellable(cancelable)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .setBackgroundColor(Color.TRANSPARENT)
                .setDimAmount(0.5f)
                .show();


    }

    public static void showProgress() {
        showProgress("", false);
    }

    public static void closeProgress() {
        if(_progressDlg == null) {
            return;
        }

        _progressDlg.dismiss();
        _progressDlg = null;
    }

    public void showAlertDialog(String msg) {

        AlertDialog alertDialog = new AlertDialog.Builder(_context,R.style.AlertDilogTheme).create();
       // alertDialog.setTitle(getString(R.string.app_name));
        alertDialog.setTitle("Pereless");
        alertDialog.setMessage(msg);

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, _context.getString(R.string.ok),

                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        //alertDialog.setIcon(R.drawable.banner);
        alertDialog.show();

    }

    /**
     *  show toast
     * @param toast_string
     */
    public static void showToast(String toast_string) {

        Toast.makeText(_context, toast_string, Toast.LENGTH_SHORT).show();
    }

    public void vibrate() {

        if (_vibrator != null)
            _vibrator.vibrate(500);
    }

    @Override
    public boolean handleMessage(Message msg) {

        switch (msg.what) {

            default:
                break;
        }

        return false;
    }

    public void goTo(Context context, Class aClass, boolean isFinish) {
        Intent intent = new Intent(context,aClass);
        context.startActivity(new Intent(context, aClass));
        overridePendingTransition(0, 0);
        if (isFinish) {
            ((Activity) context).finish();
        }
    }
    public void goTo(Context context, Class aClass, boolean isFinish,Bundle bundle) {
        Intent intent = new Intent(context,aClass);
        context.startActivity(new Intent(context, aClass).putExtra("data",bundle));
        overridePendingTransition(0, 0);
        if (isFinish) {
            ((Activity) context).finish();
        }
    }

    public void finish(Context context){
        ((Activity)context).finish();
        overridePendingTransition(0, 0);
    }

    public int dp(float value) {
        if (value == 0) {
            return 0;
        }
        return (int) Math.ceil(getResources().getDisplayMetrics().density * value);


    }




}