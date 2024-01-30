package com.hr.pereless.activities.auth;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.hr.pereless.R;
import com.hr.pereless.activities.job.CreateJobActivity;
import com.hr.pereless.api.API;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.preference.PrefConst;
import com.hr.pereless.preference.Preference;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends CommonActivity implements View.OnClickListener {
    @BindView(R.id.edt_username)
    EditText edtUsername;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.txt_forgotpassword)
    TextView txtForgotpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        txtForgotpassword.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        edtUsername.setText(Preference.getInstance().getValue(this, PrefConst.PREFKEY_USEREMAIL, ""));
        edtPassword.setText(Preference.getInstance().getValue(this, PrefConst.PREFKEY_USERPWD, ""));
        if(edtUsername.getText().length()>0)gotoLogin();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.txt_forgotpassword:
                goTo(this, ForgotPasswordActivity.class,false);
                break;
            case R.id.btn_login:
                if(validation())
                    gotoLogin();
                break;
        }
    }
    private boolean validation() {
        if (edtUsername.getText().toString().trim().equals("")) {
            showAlertDialog( "Enter emailId");
            return false;
        } else if (!edtUsername.getText().toString().trim().contains("@")) {
            showAlertDialog( "Enter valid emailId");
            return false;
        } else if (edtPassword.getText().toString().trim().equals("")) {
            showAlertDialog( "Enter password");
            return false;
        }
        return true;
    }
    void gotoLogin(){
        showProgress();
        Map<String, String> params = new HashMap<>();
        params.put("username", edtUsername.getText().toString().trim());
        params.put("password",edtPassword.getText().toString());
        params.put("apikey",Commons.apiKey);
        params.put("language-iso","en");
        apiConnection(API.LOG0IN_API,params,Request.Method.POST);
    }
    @Override
    public void parseResponse(String apilink,String json){
        closeProgress();
        try {
            JSONObject jsonObject = new JSONObject(json);
            if(jsonObject.getInt("status") ==0){
                showAlertDialog(jsonObject.getString("message"));
                return;
            }
            Commons.token = jsonObject.getString("token");
            Preference.getInstance().put(this, PrefConst.PREFKEY_USEREMAIL, edtUsername.getText().toString());
            Preference.getInstance().put(this, PrefConst.PREFKEY_USERPWD, edtPassword.getText().toString());
            goTo(this,WelcomeActivity.class,false);
//            goTo(this, CreateJobActivity.class,false);

        }catch (Exception e){

        }
    }
}