package com.hr.pereless.activities.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hr.pereless.R;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.model.candidate.CandidateModel;
import com.hr.pereless.view.weekview.WeekView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TermsConditionActivity extends CommonActivity {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.webview)
    WebView webview;
    int type = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_condition);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            Bundle bundle = getIntent().getBundleExtra("data");
            if (bundle != null) {
               type = bundle.getInt("type");
               if(type == 0)
                   txtTitle.setText(getResources().getString(R.string.terms));
               else
                   txtTitle.setText(getResources().getString(R.string.privacy));
            }
        }
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initLayout();
    }

    void initLayout(){
        String html = "<p>Physical Requirements</p>\r\n<ol>\r\n    <li>Withstand temperatures of 0 degrees Fahrenheit or less and 100 degrees Fahrenheit or more</li>\r\n    <li>Move throughout the restaurant for extended periods of time (up to 10-12 hours per day)</li>\r\n    <li>Move 50 lbs. for distances of up to 10 ft.</li>\r\n    <li>Balance and move up to 25 lbs. for distances of up to 50 ft.</li>\r\n    <li>Understand and respond to Crew Members’ and guests’ requests in a loud environment</li>\r\n    <li>Perform basic math and understand finances and cost management.</li>\r\n</ol>\r\n<p>Education/Experience Requirements</p>\r\n<ol>\r\n    <li>High school equivalency required; college coursework preferred</li>\r\n    <li>Previous supervisory and hospitality industry experience preferred</li>\r\n    <li>Proficient communication in English (verbal and in writing)</li>\r\n    <li>Minimum 21 years of age</li>\r\n    <li>Proven track record of success as a restaurant manager</li>\r\n</ol>\r\n<p>This description is not intended and should not be construed to be an exhaustive list of all responsibilities, skills, effort, or work conditions associated with the job. It is intended to be an accurate reflection of the principal job elements essential for making employment decisions.</p>\r\n<p>Management Referral Program:<br />\r\nAfter joining our Team, if you refer a manager candidate from outside the company and they are hired, you can receive a bonus from our Staffing Department. You can get details from our Staffing Department at (800) 248-4938</p>\r\n<p>Red Lobster is proud to be a leader in recognizing the value that diversity offers throughout the restaurant industry.</p>";
        Log.d("aaaaaaaa",html);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setPluginState(WebSettings.PluginState.ON);
        webview.loadDataWithBaseURL(null,html, "text/html", "UTF-8",null);
    }
}