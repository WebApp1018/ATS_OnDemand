package com.hr.pereless.activities.onboarding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.hr.pereless.R;
import com.hr.pereless.api.API;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.fragment.onboard.DocumentsubFragment;
import com.hr.pereless.fragment.onboard.EmployeeDataFragment;
import com.hr.pereless.fragment.onboard.PreviewSendFragment;
import com.hr.pereless.fragment.onboard.TasksubFragment;
import com.hr.pereless.fragment.onboard.TeamNotificationFragment;
import com.hr.pereless.model.candidate.CandidateModel;
import com.hr.pereless.model.candidate.RecruitflowModel;
import com.hr.pereless.model.onboarding.CurrencyModel;
import com.hr.pereless.model.onboarding.OnboardingModel;
import com.hr.pereless.model.onboarding.RecruitteamModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class OnBoardingTaskActivity extends CommonActivity {

    @BindView(R.id.btn_back)
    Button btnBack;
    @BindView(R.id.backlayout)
    RelativeLayout backlayout;
    @BindView(R.id.profileimage)
    CircleImageView profileimage;
    @BindView(R.id.layout_tool)
    RelativeLayout layoutTool;
    @BindView(R.id.txt_search)
    EditText txtSearch;
    @BindView(R.id.search_icon)
    ImageView searchIcon;
    @BindView(R.id.relative_layout)
    RelativeLayout relativeLayout;
    @BindView(R.id.search_layout)
    LinearLayout searchLayout;
    @BindView(R.id.toplayout)
    LinearLayout toplayout;
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    public ViewPager viewPager;
    @BindView(R.id.txv_name)
    TextView txv_name;
    @BindView(R.id.txv_title)
    TextView txv_title;
    public OnboardingModel onboardingModel = new OnboardingModel();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_task);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            Bundle bundle = getIntent().getBundleExtra("data");
            if (bundle != null) {
                String feed= bundle.getString("CandidateModel");
                Gson gson = new Gson();
                onboardingModel = gson.fromJson(feed, OnboardingModel.class);
            }
        }
        txv_name.setText(onboardingModel.getCandidatefirstname()+" " + onboardingModel.getCandidatelastname());
        txv_title.setText(onboardingModel.getJob_title());
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        loadRecruiterTeam();
    }

    void loadRecruiterTeam(){
        showProgress();
        Map<String, String> params = new HashMap<>();
        params.put("Authorization", Commons.token);
        apiConnection(API.GET_RECRUITTEAMS,params, Request.Method.GET);
    }

    @Override
    public void parseResponse(String api_link,String json){
        closeProgress();
        try {
            JSONArray jsonArray = new JSONArray(json);
            Commons.recruitteamModels.clear();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                RecruitteamModel recruitteamModel = new RecruitteamModel();
                recruitteamModel.initModel(jsonObject);
                Commons.recruitteamModels.add(recruitteamModel);
            }
        }catch (Exception e){

        }
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new EmployeeDataFragment(), getResources().getString(R.string.employee_data));
        adapter.addFragment(new TeamNotificationFragment(), getResources().getString(R.string.team_notification));
        adapter.addFragment(new DocumentsubFragment(), getResources().getString(R.string.document));
        adapter.addFragment(new TasksubFragment(), getResources().getString(R.string.tasks));
        adapter.addFragment(new PreviewSendFragment(), getResources().getString(R.string.preview_send));
        viewPager.setAdapter(adapter);
    }

    @OnClick({R.id.btn_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                onBackPressed();
                break;


        }
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            // PrefConnect.writeString(getContext(), PrefConnect.CATEGORY_ID, resp.getCategories().get(position).getCategoryId());

            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }


/*
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            mFragmentList.remove(position);
            super.destroyItem(container, position, object);
        }*/
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}