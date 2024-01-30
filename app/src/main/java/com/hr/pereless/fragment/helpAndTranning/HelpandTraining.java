package com.hr.pereless.fragment.helpAndTranning;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.google.android.material.tabs.TabLayout;
import com.hr.pereless.R;
import com.hr.pereless.fragment.email.EmailSubFragment;
import com.hr.pereless.fragment.email.EmailsFragment;
import com.hr.pereless.fragment.email.SMSFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HelpandTraining#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HelpandTraining extends Fragment {
    View view;
    TabLayout tabs;
    ViewPager viewpager;
    WebView webview;
    public static HelpandTraining newInstance(String param1, String param2) {
        HelpandTraining fragment = new HelpandTraining();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabs = view.findViewById(R.id.tabs);
        webview = view.findViewById(R.id.webview);
        viewpager = view.findViewById(R.id.viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag(new DocSubFragment(), "Doc");
        adapter.addFrag(new VideosubFragment(), "Video");
        viewpager.setAdapter(adapter);
        tabs.setupWithViewPager(viewpager);

        loadHtml();
    }

    void loadHtml(){
        String html = "<p>Physical Requirements</p>\r\n<ol>\r\n    <li>Withstand temperatures of 0 degrees Fahrenheit or less and 100 degrees Fahrenheit or more</li>\r\n    <li>Move throughout the restaurant for extended periods of time (up to 10-12 hours per day)</li>\r\n    <li>Move 50 lbs. for distances of up to 10 ft.</li>\r\n    <li>Balance and move up to 25 lbs. for distances of up to 50 ft.</li>\r\n    <li>Understand and respond to Crew Members’ and guests’ requests in a loud environment</li>\r\n    <li>Perform basic math and understand finances and cost management.</li>\r\n</ol>\r\n<p>Education/Experience Requirements</p>\r\n<ol>\r\n    <li>High school equivalency required; college coursework preferred</li>\r\n    <li>Previous supervisory and hospitality industry experience preferred</li>\r\n    <li>Proficient communication in English (verbal and in writing)</li>\r\n    <li>Minimum 21 years of age</li>\r\n    <li>Proven track record of success as a restaurant manager</li>\r\n</ol>\r\n<p>This description is not intended and should not be construed to be an exhaustive list of all responsibilities, skills, effort, or work conditions associated with the job. It is intended to be an accurate reflection of the principal job elements essential for making employment decisions.</p>\r\n<p>Management Referral Program:<br />\r\nAfter joining our Team, if you refer a manager candidate from outside the company and they are hired, you can receive a bonus from our Staffing Department. You can get details from our Staffing Department at (800) 248-4938</p>\r\n<p>Red Lobster is proud to be a leader in recognizing the value that diversity offers throughout the restaurant industry.</p>";
        Log.d("aaaaaaaa",html);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setPluginState(WebSettings.PluginState.ON);
        webview.loadDataWithBaseURL(null,html, "text/html", "UTF-8",null);
    }

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_helpand_training, container, false);
        return  view;
    }
}