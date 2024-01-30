package com.hr.pereless.fragment.setting;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hr.pereless.R;
import com.hr.pereless.activities.MainActivity;
import com.hr.pereless.activities.auth.LoginActivity;
import com.hr.pereless.activities.setting.EditProfileActivity;
import com.hr.pereless.activities.setting.NotificationSettingActivity;
import com.hr.pereless.activities.setting.TermsConditionActivity;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.commons.Constants;
import com.hr.pereless.dialog.ConfirmDialog;
import com.hr.pereless.dialog.CountrySelectDialog;
import com.hr.pereless.preference.PrefConst;
import com.hr.pereless.preference.Preference;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;


public class SettingsFragment extends Fragment implements View.OnClickListener {
    View view;
    LinearLayout editlayout,notificationlayout,termsandcondition,privacylayout,timezonelayout,languagelayout,logoutlayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logoutlayout = view.findViewById(R.id.logoutlayout);
        languagelayout = view.findViewById(R.id.languagelayout);
        timezonelayout = view.findViewById(R.id.timezonelayout);
        privacylayout = view.findViewById(R.id.privacylayout);
        termsandcondition = view.findViewById(R.id.termsandcondition);
        notificationlayout = view.findViewById(R.id.notificationlayout);
        editlayout = view.findViewById(R.id.editlayout);

        logoutlayout.setOnClickListener(this);
        editlayout.setOnClickListener(this);
        notificationlayout.setOnClickListener(this);
        termsandcondition.setOnClickListener(this);
        privacylayout.setOnClickListener(this);
        timezonelayout.setOnClickListener(this);
        languagelayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.editlayout:
                ((CommonActivity)getActivity()).goTo(getActivity(), EditProfileActivity.class,false);
                break;
            case R.id.notificationlayout:
                ((CommonActivity)getActivity()).goTo(getActivity(), NotificationSettingActivity.class,false);
                break;
            case R.id.termsandcondition:
                Bundle bundle = new Bundle();
                bundle.putInt("type",0);
                ((CommonActivity)getActivity()).goTo(getActivity(), TermsConditionActivity.class,false,bundle);
                break;
            case R.id.privacylayout:
                bundle = new Bundle();
                bundle.putInt("type",1);
                ((CommonActivity)getActivity()).goTo(getActivity(), TermsConditionActivity.class,false,bundle);
                break;
            case R.id.timezonelayout:

                break;
            case R.id.languagelayout:
                selectLangauge();
                break;
            case R.id.logoutlayout:
                gotoLogout();
                break;
        }
    }
    void selectLangauge(){
        CountrySelectDialog countrySelectDialog = new CountrySelectDialog();
        countrySelectDialog.setOnConfirmListener(new CountrySelectDialog.OnConfirmListener() {
            @Override
            public void onConfirm(String str,int posstion) {

            }
        });
        countrySelectDialog.show(getParentFragmentManager(), "DeleteMessage");
    }
    void gotoLogout(){
        ConfirmDialog confirmDialog = new ConfirmDialog();
        confirmDialog.setOnConfirmListener(new ConfirmDialog.OnConfirmListener() {
            @Override
            public void onConfirm() {
                Preference.getInstance().put(getContext(), PrefConst.PREFKEY_USEREMAIL, "");
                Preference.getInstance().put(getContext(), PrefConst.PREFKEY_USERPWD, "");

                Intent intent = new Intent(getContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        },getString(R.string.logout_description));
        confirmDialog.show(getParentFragmentManager(), "DeleteMessage");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_settings, container, false);
        return view;
    }
}