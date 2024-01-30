package com.hr.pereless.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.hr.pereless.R;
import com.hr.pereless.activities.auth.LoginActivity;
import com.hr.pereless.activities.candidate.CandidateDetailActivity;
import com.hr.pereless.activities.job.JobDetailActivity;
import com.hr.pereless.adapter.LeftNavAdapter;
import com.hr.pereless.api.API;
import com.hr.pereless.application.AppController;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.commons.Constants;
import com.hr.pereless.dialog.ConfirmDialog;
import com.hr.pereless.dialog.CountrySelectDialog;
import com.hr.pereless.dialog.SearchDialog;
import com.hr.pereless.fragment.candidate.CandidateFragment;
import com.hr.pereless.fragment.email.EmailsFragment;
import com.hr.pereless.fragment.helpAndTranning.HelpandTraining;
import com.hr.pereless.fragment.home.HomeFragmentTest;
import com.hr.pereless.fragment.job.JobsFragment;
import com.hr.pereless.fragment.job.JobsSubFragment;
import com.hr.pereless.fragment.notification.NotificationListFragment;
import com.hr.pereless.fragment.onboard.OnBoardingFragment;
import com.hr.pereless.fragment.report.ReporterFragment;
import com.hr.pereless.fragment.saved.SavedFragment;
import com.hr.pereless.fragment.scheduleNew.ScheduleFragment;
import com.hr.pereless.fragment.setting.SettingsFragment;
import com.hr.pereless.fragment.setting.SupportFragment;
import com.hr.pereless.fragment.schedule.ScheduleActivity;
import com.hr.pereless.model.MenuModel;
import com.hr.pereless.model.SearchModel;
import com.hr.pereless.model.candidate.CandidateModel;
import com.hr.pereless.model.job.JobModel;
import com.hr.pereless.preference.PrefConst;
import com.hr.pereless.preference.Preference;
import com.hr.pereless.util.RoundedCornersTransformation;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends CommonActivity implements AdapterView.OnItemClickListener, View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener {
    List<MenuModel> rowItems;
    LeftNavAdapter leftNavAdapter;
    public static final Integer[] images = {R.drawable.home_icon, R.drawable.saved_icon, R.drawable.icon_jobs, R.drawable.candidate_icon, R.drawable.schedule_icon, R.drawable.help_support_icon, R.drawable.onboard_icon, R.drawable.report_icon, R.drawable.support_icon, R.drawable.setting_icon};
    public static String[] titles;
    public Button btnbackarrow;
    public ImageView imagNav;
    public DrawerLayout drawerLayoutNew;
    @BindView(R.id.lst_nave)
    ListView lstNave;

    @BindView(R.id.img_lang_icon)
    ImageView img_lang_icon;
    @BindView(R.id.img_down_arrow)
    ImageView img_down_arrow;
    @BindView(R.id.lyt_country)
    LinearLayout lyt_country;
    @BindView(R.id.relativBottomNavigation)
    RelativeLayout relativinavigatio;
    CircleImageView profileImage, profile_image2;
    TextView txt_email,txt_name,txt_logout;
    public  TextView txtSearch;
    public TextView txvTitle;
    ImageView img_company,search_icon,setting,filter,imgExit,search,searchIcon,notification;
    Spinner spinner;
    int spinner_selected_position = 0;
    FrameLayout frame_container;
    Fragment selectedFragment;
    LinearLayout searchLayout;
    BottomNavigationView navigation;
    int statussearch = 0;
    int status = 0 ;
    SearchDialog searchDialog;
    ArrayList<SearchModel>searchModels = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getIntent() != null) {
            Bundle bundle = getIntent().getBundleExtra("data");
            if (bundle != null) {
                status = bundle.getInt("mainactivitystatus");
            }
        }
        ButterKnife.bind(this);
        btnbackarrow = (Button) findViewById(R.id.btn_back_arrow);
        drawerLayoutNew = (DrawerLayout) findViewById(R.id.drawer_layout_new);
        profile_image2 = (CircleImageView) findViewById(R.id.profile_image);
        profileImage = (CircleImageView) findViewById(R.id.profile_image2);
        txt_email = findViewById(R.id.txt_email);
        txt_name = findViewById(R.id.txt_name);
        img_company = findViewById(R.id.img_company);
        search_icon = findViewById(R.id.search_icon);
        txtSearch = findViewById(R.id.txt_search);
        spinner = findViewById(R.id.spinner);
        setting = findViewById(R.id.setting);
        search = findViewById(R.id.search);
        filter = (ImageView) findViewById(R.id.filter);
        notification = findViewById(R.id.notification);
        imgExit = findViewById(R.id.img_exit);
        frame_container = findViewById(R.id.frame_container);
        txvTitle = findViewById(R.id.txv_title);
        imagNav = (ImageView) findViewById(R.id.imv_back);
        searchLayout = findViewById(R.id.search_layout);
        searchIcon = findViewById(R.id.search_icon);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        txt_logout = findViewById(R.id.txt_logout);
        txt_logout.setOnClickListener(this);
        setting.setOnClickListener(this);
        lyt_country.setOnClickListener(this);
        btnbackarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("clickbtn", "backarrow");
                drawerLayoutNew.openDrawer(GravityCompat.START);
            }
        });
        ArrayAdapter adapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.list, R.layout.item_spinner_dropdown_layout);
        adapter.setDropDownViewResource(R.layout.item_spinner_dropdown);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner_selected_position = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        navigation.setOnNavigationItemSelectedListener(this);
        txtSearch.setOnClickListener(this);
        initLayout();
    }

    @SuppressLint("ResourceType")
    void initSet(){
        Glide.with(_context).load(Commons.g_user.getAvatar()).placeholder(R.drawable.profile_pic).dontAnimate().apply(RequestOptions.bitmapTransform(
                new RoundedCornersTransformation(_context, Commons.glide_radius, Commons.glide_magin, getResources().getString(R.color.white), Commons.glide_boder))).into(profile_image2);
        Glide.with(_context).load(Commons.g_user.getAvatar()).placeholder(R.drawable.profile_pic).dontAnimate().apply(RequestOptions.bitmapTransform(
                new RoundedCornersTransformation(_context, Commons.glide_radius, Commons.glide_magin, getResources().getString(R.color.white), Commons.glide_boder))).into(profileImage);
        txt_name.setText(Commons.g_user.getFirstname()+" " + Commons.g_user.getLastname());
        txt_email.setText(Commons.g_user.getEmail());
    }
    void initLayout() {

        setupnavigation();
        if(status==1){
            beginTransction(new HomeFragmentTest());
            profileImage.setVisibility(View.VISIBLE);
            setting.setVisibility(View.GONE);
            filter.setVisibility(View.GONE);
            txvTitle.setText(titles[0]);
            searchLayout.setVisibility(View.VISIBLE);
            spinner.setVisibility(View.VISIBLE);
            search.setVisibility(View.GONE);
            navigation.getMenu().getItem(0).setCheckable(false);
            navigation.getMenu().getItem(1).setCheckable(false);
            navigation.getMenu().getItem(2).setCheckable(false);
            navigation.getMenu().getItem(3).setCheckable(false);
            navigation.getMenu().getItem(4).setCheckable(false);


        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lyt_country:
                CountrySelectDialog countrySelectDialog = new CountrySelectDialog();
                countrySelectDialog.setOnConfirmListener(new CountrySelectDialog.OnConfirmListener() {
                    @Override
                    public void onConfirm(String language_name,int posstion) {
                        img_lang_icon.setImageResource(Constants.country_flag[posstion]);
//                        Locale locale = new Locale(language_name);
//                        switch (language_name) {
//
//                            case "English":
//                                language_name = "en";
//                                locale = new Locale("en");
//                                break;
//
//                            case "Spanish":
//                                language_name = "es";
//                                locale = new Locale("es");
//                                break;
//
//
//                            case "Canadian French":
//                                language_name = "fr";
//                                locale = new Locale("fr");
//                                break;
//
//
//                            case "Chinese":
//                                language_name = "zh";
//                                locale = new Locale("zh");
//                                break;
//
//
//                        }
//                        Locale.setDefault(locale);
//                        Configuration configTurkey = new Configuration();
//                        configTurkey.locale = locale;
//                        getResources().updateConfiguration(configTurkey, getResources().getDisplayMetrics());
//                        Intent intent = new Intent(MainActivity.this,MainActivity.class);
//                        //intent.putExtra("mainactivitystatus","4");
//                        startActivity(intent);
//                        finishAffinity();
                    }
                });
                countrySelectDialog.show(this.getSupportFragmentManager(), "DeleteMessage");
                break;
            case R.id.setting:

                break;
            case R.id.txt_logout:
                gotoLogout();
                break;
            case R.id.txt_search:
                ArrayList<String>arrayList = new ArrayList<>();
                searchDialog = new SearchDialog();
                searchDialog.setOnConfirmListener(new SearchDialog.OnConfirmListener() {
                    @Override
                    public void onConfirm(int selectPosstion) {
                        getdetailModel(selectPosstion);
                    }
                },arrayList);
                searchDialog.show(getSupportFragmentManager(), "DeleteMessage");
                break;
        }
    }

    void getdetailModel(int posstion){
        showProgress();
        String api_link = API.JOB_DETAIL+ "/"+String.valueOf(searchModels.get(posstion).getId());
        if(spinner_selected_position==1)
            api_link = API.CANDIDATE_DETAIL+"/"+String.valueOf(searchModels.get(posstion).getId());
        Map<String, String> params = new HashMap<>();
        params.put("Authorization", Commons.token);
        apiConnection(api_link,params, Request.Method.GET);
    }
    @Override
    public void parseResponse(String api_link,String json){
        closeProgress();
        try {
            if(spinner_selected_position==0) {
                JSONArray jsonArray = new JSONArray(json);
                if(jsonArray.length()==0){
                    showAlertDialog(getResources().getString(R.string.non_job));
                    return;
                }
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                JobModel jobModel = new JobModel();
                jobModel.initModel(jsonObject);
                Bundle bundle = new Bundle();
                Gson gson = new Gson();
                String newfeedentity = gson.toJson(jobModel);
                bundle.putString("JobModel",newfeedentity);
                startActivityForResult(new Intent(MainActivity.this, JobDetailActivity.class).putExtra("data",bundle),1);
                overridePendingTransition(0, 0);

            }else{

                JSONArray jsonArray = new JSONArray(json);
                if(jsonArray.length()==0){
                    showAlertDialog(getResources().getString(R.string.non_candidate));
                    return;
                }
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                CandidateModel candidateModel = new CandidateModel();
                candidateModel.initDetailModel(jsonObject);
                Bundle bundle = new Bundle();
                Gson gson = new Gson();
                String newfeedentity = gson.toJson(candidateModel);
                bundle.putString("CandidateModel",newfeedentity);
                startActivityForResult(new Intent(MainActivity.this, CandidateDetailActivity.class).putExtra("data",bundle),1);
                overridePendingTransition(0, 0);
            }
        }catch (Exception e){

        }
    }
    public void searchJObandCandidate(String key){

        showProgress();
        String api_link = API.SEARCH_JOB;
        if(spinner_selected_position==1)
            api_link = API.SEARCH_CANDIDATE;
        StringRequest myRequest = new StringRequest(
                Request.Method.POST,
                api_link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                        closeProgress();
                        try {
                            JSONArray jsonArray = new JSONArray(json);
                            searchModels.clear();
                            ArrayList<String>arrayList = new ArrayList<>();
                           for(int i =0;i<jsonArray.length();i++){
                               JSONObject jsonObject = jsonArray.getJSONObject(i);
                               SearchModel searchModel = new SearchModel();
                               searchModel.setId(jsonObject.getInt("id"));
                               searchModel.setLabel(jsonObject.getString("label"));
                               searchModel.setValue(jsonObject.getString("value"));
                               arrayList.add(searchModel.getValue());
                               searchModels.add(searchModel);
                           }
                           searchDialog.setValues(arrayList);
                        }catch (Exception e){

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
            public byte[] getBody() {
                try {
                    JSONObject jsonObject = new JSONObject();
                    /* fill your json here */
                    jsonObject.put("term",key);
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


    private void setupnavigation() {

        rowItems = new ArrayList<MenuModel>();
        titles = getResources().getStringArray(R.array.mainmenu_small);
        for (int i = 0; i < titles.length; i++) {
            MenuModel item = new MenuModel(images[i], titles[i], "0");
            rowItems.add(item);
        }
        leftNavAdapter = new LeftNavAdapter(this, R.layout.activity_main_left_drawer_menu, rowItems);
        lstNave.setAdapter(leftNavAdapter);
        lstNave.setOnItemClickListener(this);


    }
    public void beginTransction(final Fragment fragment) {

        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.frame_container, fragment);
            //transaction.addToBackStack(null);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        drawerLayoutNew.closeDrawers();
        if (position == 0) {
            beginTransction(new HomeFragmentTest());
            profileImage.setVisibility(View.VISIBLE);
            setting.setVisibility(View.GONE);
            filter.setVisibility(View.GONE);
            txvTitle.setText(titles[0]);
            imgExit.setVisibility(View.GONE);
            searchLayout.setVisibility(View.VISIBLE);
            spinner.setVisibility(View.VISIBLE);
            search.setVisibility(View.GONE);
            final String txtsearch1 = txtSearch.getText().toString().trim();

            relativinavigatio.setVisibility(View.VISIBLE);
            navigation.getMenu().getItem(0).setCheckable(false);
            navigation.getMenu().getItem(1).setCheckable(false);
            navigation.getMenu().getItem(2).setCheckable(false);
            navigation.getMenu().getItem(3).setCheckable(false);
            navigation.getMenu().getItem(4).setCheckable(false);
        } else if (position == 1) {
            beginTransction(new SavedFragment());
            profileImage.setVisibility(View.GONE);
            setting.setVisibility(View.GONE);
            filter.setVisibility(View.GONE);
            searchLayout.setVisibility(View.GONE);
            search.setVisibility(View.GONE);
            imgExit.setVisibility(View.GONE);
            txvTitle.setText(titles[1]);
            relativinavigatio.setVisibility(View.VISIBLE);
            navigation.getMenu().getItem(0).setCheckable(false);
            navigation.getMenu().getItem(1).setCheckable(false);
            navigation.getMenu().getItem(2).setCheckable(false);
            navigation.getMenu().getItem(3).setCheckable(false);
            navigation.getMenu().getItem(4).setCheckable(false);

        } else if (position == 2) {
            beginTransction(new JobsFragment());
            spinner_selected_position = 0;
            imgExit.setVisibility(View.GONE);
            profileImage.setVisibility(View.GONE);
            setting.setVisibility(View.GONE);
            filter.setVisibility(View.GONE);
            txvTitle.setText(titles[2]);
            search.setVisibility(View.GONE);
            searchLayout.setVisibility(View.VISIBLE);
            txtSearch.setText("");
            spinner.setVisibility(View.GONE);

            navigation.getMenu().getItem(0).setCheckable(true);
            navigation.getMenu().getItem(1).setCheckable(false);
            navigation.getMenu().getItem(2).setCheckable(false);
            navigation.getMenu().getItem(3).setCheckable(false);
            navigation.getMenu().getItem(4).setCheckable(false);


            //navigation.setSelectedItemId(R.id.navigation_jobs);

            setting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    beginTransction(new SettingsFragment());
                    imgExit.setVisibility(View.VISIBLE);
                    profileImage.setVisibility(View.GONE);
                    setting.setVisibility(View.GONE);
                    filter.setVisibility(View.GONE);
                    txvTitle.setText(titles[9]);
                    relativinavigatio.setVisibility(View.GONE);
                    searchLayout.setVisibility(View.GONE);

                }
            });
            relativinavigatio.setVisibility(View.VISIBLE);
        } else if (position == 3) {
            beginTransction(new CandidateFragment());
            imgExit.setVisibility(View.GONE);
            profileImage.setVisibility(View.GONE);
            setting.setVisibility(View.GONE);
            filter.setVisibility(View.GONE);
            txvTitle.setText(titles[3]);
            search.setVisibility(View.GONE);
            searchLayout.setVisibility(View.VISIBLE);
            txtSearch.setText("");
            spinner.setVisibility(View.GONE);
            spinner_selected_position = 1;
            navigation.getMenu().getItem(0).setCheckable(false);
            navigation.getMenu().getItem(1).setCheckable(true);
            navigation.getMenu().getItem(2).setCheckable(false);
            navigation.getMenu().getItem(3).setCheckable(false);
            navigation.getMenu().getItem(4).setCheckable(false);

            relativinavigatio.setVisibility(View.VISIBLE);
            //navigation.setSelectedItemId(R.id.navigation_candidates);

        } else if (position == 4) {

            // beginTransction(new ScheduleActivity()); //schedule
            beginTransction(new ScheduleFragment()); //schedule
            imgExit.setVisibility(View.GONE);
            profileImage.setVisibility(View.GONE);
            setting.setVisibility(View.GONE);
            filter.setVisibility(View.GONE);
            notification.setVisibility(View.GONE);
            searchLayout.setVisibility(View.GONE);
            imgExit.setVisibility(View.GONE);
            txvTitle.setText(titles[4]);
            search.setVisibility(View.GONE);
            relativinavigatio.setVisibility(View.VISIBLE);
            Log.e("i am in", " schedule " + position);
//            navigation.setSelectedItemId(R.id.navigation_schedule);
            navigation.getMenu().getItem(0).setCheckable(false);
            navigation.getMenu().getItem(1).setCheckable(false);
            //navigation.getMenu().getItem(R.id.navigation_schedule).setCheckable(true);
            navigation.getMenu().getItem(3).setCheckable(false);
            navigation.getMenu().getItem(4).setCheckable(false);

        } else if (position == 5) {
            imgExit.setVisibility(View.GONE);
            profileImage.setVisibility(View.GONE);
            setting.setVisibility(View.GONE);
            filter.setVisibility(View.GONE);
            notification.setVisibility(View.GONE);
            searchLayout.setVisibility(View.GONE);
            imgExit.setVisibility(View.GONE);
            beginTransction(new HelpandTraining());
            relativinavigatio.setVisibility(View.GONE);
            txvTitle.setText(titles[position]);


        } else if (position == 6) {
            imgExit.setVisibility(View.GONE);
            beginTransction(new OnBoardingFragment());
            txvTitle.setText(titles[position]);
            imgExit.setVisibility(View.GONE);
            profileImage.setVisibility(View.GONE);
            setting.setVisibility(View.GONE);
            filter.setVisibility(View.GONE);
            search.setVisibility(View.GONE);
            searchLayout.setVisibility(View.GONE);
            txtSearch.setHint("Search Candidate");
            spinner.setVisibility(View.INVISIBLE);
            spinner.setVisibility(View.GONE);
            imgExit.setVisibility(View.GONE);
            searchIcon.setVisibility(View.VISIBLE);
            search.setVisibility(View.GONE);
            filter.setVisibility(View.GONE);
            relativinavigatio.setVisibility(View.GONE);
        } else if (position == 7) {
            imgExit.setVisibility(View.GONE);
            profileImage.setVisibility(View.GONE);
            setting.setVisibility(View.GONE);
            filter.setVisibility(View.GONE);
            search.setVisibility(View.GONE);
            searchLayout.setVisibility(View.GONE);
            txtSearch.setHint("Search Candidate");
            spinner.setVisibility(View.INVISIBLE);
            imgExit.setVisibility(View.GONE);
            searchIcon.setVisibility(View.GONE);
            filter.setVisibility(View.GONE);
            beginTransction(new ReporterFragment());
            txvTitle.setText(titles[position]);
            Log.e("TAG", "Position: " + position);
            relativinavigatio.setVisibility(View.GONE);
        } else if (position == 8) {
            Log.e("TAG", "Position: " + position);
            beginTransction(new SupportFragment());
            txvTitle.setText(titles[position]);
            imgExit.setVisibility(View.GONE);
            profileImage.setVisibility(View.GONE);
            setting.setVisibility(View.GONE);
            filter.setVisibility(View.GONE);
            search.setVisibility(View.GONE);
            searchLayout.setVisibility(View.GONE);
            txtSearch.setText("");
            spinner.setVisibility(View.INVISIBLE);
            imgExit.setVisibility(View.GONE);
            searchIcon.setVisibility(View.GONE);
            search.setVisibility(View.GONE);
            filter.setVisibility(View.GONE);
            relativinavigatio.setVisibility(View.GONE);
        } else if (position == 9) {
            Log.e("TAG", "Text");
            beginTransction(new SettingsFragment());
            txvTitle.setText(titles[position]);
            imgExit.setVisibility(View.GONE);
            searchLayout.setVisibility(View.GONE);
            search.setVisibility(View.GONE);
            profileImage.setVisibility(View.GONE);
            setting.setVisibility(View.GONE);
            imgExit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent exit = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(exit);
                }
            });
            relativinavigatio.setVisibility(View.GONE);
        }
    }

    void gotoLogout(){
        ConfirmDialog confirmDialog = new ConfirmDialog();
        confirmDialog.setOnConfirmListener(new ConfirmDialog.OnConfirmListener() {
            @Override
            public void onConfirm() {
                Preference.getInstance().put(MainActivity.this, PrefConst.PREFKEY_USEREMAIL, "");
                Preference.getInstance().put(MainActivity.this, PrefConst.PREFKEY_USERPWD, "");

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                overridePendingTransition(0, 0);
                startActivity(intent);
            }
        },getString(R.string.logout_description));
        confirmDialog.show(this.getSupportFragmentManager(), "DeleteMessage");
    }

    @Override
    public void onBackPressed() {
        gotoExit();
    }
    void gotoExit(){
        ConfirmDialog confirmDialog = new ConfirmDialog();
        confirmDialog.setOnConfirmListener(new ConfirmDialog.OnConfirmListener() {
            @Override
            public void onConfirm() {
                onExit();
            }
        },getString(R.string.finish_app));
        confirmDialog.show(this.getSupportFragmentManager(), "DeleteMessage");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        selectedFragment = null;
        switch (item.getItemId()) {
            case R.id.navigation_jobs:
                spinner_selected_position = 0;
                navigation.getMenu().getItem(0).setCheckable(true);

                selectedFragment = JobsSubFragment.newInstance();

                imgExit.setVisibility(View.GONE);
                profileImage.setVisibility(View.GONE);
                setting.setVisibility(View.VISIBLE);
                filter.setVisibility(View.GONE);
                //txtLogout.setVisibility(View.GONE);
                txvTitle.setText(titles[2]);
                search.setVisibility(View.GONE);
                searchLayout.setVisibility(View.VISIBLE);
                txtSearch.setText("");
                spinner.setVisibility(View.GONE);

                setting.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        beginTransction(new SettingsFragment());
                        imgExit.setVisibility(View.VISIBLE);
                        profileImage.setVisibility(View.GONE);
                        setting.setVisibility(View.GONE);
                        filter.setVisibility(View.GONE);
                        txvTitle.setText(titles[9]);
                        searchLayout.setVisibility(View.GONE);
                    }
                });
                break;
            case R.id.navigation_candidates:
                spinner_selected_position = 1;
                txtSearch.setText("");
                navigation.getMenu().getItem(1).setCheckable(true);
                imgExit.setVisibility(View.GONE);
                txvTitle.setText(titles[3]);
                filter.setVisibility(View.GONE);
                search.setVisibility(View.GONE);
                searchLayout.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.GONE);
                profileImage.setVisibility(View.GONE);
                setting.setVisibility(View.GONE);
                selectedFragment = CandidateFragment.newInstance();
                break;
            case R.id.navigation_schedule:
                // beginTransction(new Sche());
                navigation.getMenu().getItem(2).setCheckable(true);
                selectedFragment = ScheduleActivity.newInstance();
                imgExit.setVisibility(View.GONE);
                profileImage.setVisibility(View.GONE);
                setting.setVisibility(View.GONE);
                filter.setVisibility(View.GONE);
                notification.setVisibility(View.GONE);
                searchLayout.setVisibility(View.GONE);
                imgExit.setVisibility(View.GONE);
                search.setVisibility(View.GONE);
                txvTitle.setText(titles[4]);
                break;
            case R.id.navigation_communication:
                navigation.getMenu().getItem(3).setCheckable(true);
                selectedFragment = EmailsFragment.newInstance();
                imgExit.setVisibility(View.GONE);
                profileImage.setVisibility(View.GONE);
                setting.setVisibility(View.GONE);
                filter.setVisibility(View.GONE);
                notification.setVisibility(View.VISIBLE);
                searchLayout.setVisibility(View.GONE);
                imgExit.setVisibility(View.GONE);
                txvTitle.setText("Email");
                search.setVisibility(View.GONE);
                break;
            case R.id.navigation_settings:
                navigation.getMenu().getItem(4).setCheckable(true);
                selectedFragment = EmailsFragment.newInstance();
                imgExit.setVisibility(View.GONE);
                profileImage.setVisibility(View.GONE);
                setting.setVisibility(View.GONE);
                filter.setVisibility(View.GONE);
                notification.setVisibility(View.GONE);
                searchLayout.setVisibility(View.GONE);
                imgExit.setVisibility(View.GONE);
                search.setVisibility(View.GONE);
                txvTitle.setText("Notifications");
                selectedFragment = NotificationListFragment.newInstance();
                break;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, selectedFragment);
        transaction.commit();
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK){
            onResume();
            if(data!=null) {
                int page = data.getExtras().getInt("mainactivitystatus");
                if(page ==2 ){
                    navigation.setSelectedItemId(R.id.navigation_settings);
                }else if(page ==1){
                    navigation.setSelectedItemId(R.id.navigation_jobs);
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initSet();
    }
}