package com.hr.pereless.activities.auth;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.hr.pereless.R;
import com.hr.pereless.activities.MainActivity;
import com.hr.pereless.adapter.SliderSplashAdapter;
import com.hr.pereless.api.API;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.commons.Constants;
import com.hr.pereless.model.SlideModel;
import com.hr.pereless.model.setting.UserModel;
import com.hr.pereless.model.candidate.RecruitflowModel;
import com.hr.pereless.model.onboarding.CurrencyModel;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends CommonActivity {
    @BindView(R.id.txt_skip)
    TextView txtSkip;
    @BindView(R.id.imv_slideview)
    SliderView imageSlider;
    SliderSplashAdapter setSliderAdapter;
    ArrayList<SlideModel> slideModels = new ArrayList<>();
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        txtSkip.setText(Html.fromHtml("<u>Skip</u>"));
        txtSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                Bundle bundle = new Bundle();
                bundle.putInt("mainactivitystatus",1);;
                goTo(WelcomeActivity.this, MainActivity.class,true,bundle);
            }
        });
        setSliderAdapter = new SliderSplashAdapter(this);
        imageSlider.setSliderAdapter(setSliderAdapter);
        imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        imageSlider.setScrollTimeInSec(3);
        imageSlider.setAutoCycle(true);
        imageSlider.startAutoCycle();
     //   getLandingPage();
        for(int i =0;i<5;i++){
            SlideModel slideModel = new SlideModel();
            slideModel.setImv_pic(Constants.slideImage[i]);
//            slideModel.setTitle(Constants.slideTitle[i]);
//            slideModel.setDescription(Constants.slideDescription[i]);
            slideModels.add(slideModel);
        }
        setSliderAdapter.renewItems(slideModels);

        getRecruitFlow();
        getCurrency();
        getLandingPage();
        getuserDetail();
    }

    void getRecruitFlow(){
        showProgress();
        Map<String, String> params = new HashMap<>();
        params.put("Authorization", Commons.token);
        apiConnection(API.GET_RECRUITFLOW,params, Request.Method.GET);
    }
    void getCurrency(){
        Map<String, String> params = new HashMap<>();
        params.put("Authorization", Commons.token);
        apiConnection(API.GET_JOB_CURRENCY,params, Request.Method.GET);
    }
    void getLandingPage(){
        Map<String, String> params = new HashMap<>();
        params.put("Authorization", Commons.token);
        apiConnection(API.GET_RECRUITFLOW,params, Request.Method.GET);

    }
    void getuserDetail(){
        showProgress();
        Map<String, String> params = new HashMap<>();
        params.put("Authorization", Commons.token);
        apiConnection(API.GET_USERDETAIL,params, Request.Method.GET);

    }
    @Override
    public void parseResponse(String api_link,String json){
        count++;

        if(count == 4)
            closeProgress();
        try {
            if(api_link.equals("API.GET_LANDINGPAGE")) {
                JSONObject jsonObject = new JSONObject(json);
                if (jsonObject.getInt("status") == 0) {
                    //showAlertDialog(jsonObject.getString("message"));
                    //return;
                }
            }else if(api_link.equals(API.GET_RECRUITFLOW)){
                Commons.recruitflowModels.clear();
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    RecruitflowModel recruitflowModel = new RecruitflowModel();
                    recruitflowModel.initModel(jsonObject);
                    Commons.recruitflowModels.add(recruitflowModel);
                }
            }else if(api_link.equals(API.GET_JOB_CURRENCY)){
                JSONArray jsonArray = new JSONArray(json);
                Commons.currencyModels.clear();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    CurrencyModel currencyModel = new CurrencyModel();
                    currencyModel.initModel(jsonObject);
                    Commons.currencyModels.add(currencyModel);
                }
            }else if(api_link.equals(API.GET_USERDETAIL)){
                UserModel userModel = new UserModel();
                JSONArray jsonArray = new JSONArray(json);
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                userModel.initModel(jsonObject);
                Commons.g_user = userModel;
            }
        }catch (Exception e){

        }
    }

}