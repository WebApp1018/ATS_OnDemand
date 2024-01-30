package com.hr.pereless.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.hr.pereless.R;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.model.onboarding.TeamNotificationModel;
import com.htmleditor.HtmlTextEditor;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;


public class TeamNotificationDetailDialog extends DialogFragment {

    private OnConfirmListener listener;
    TeamNotificationModel teamNotificationModel = new TeamNotificationModel();
    int recruiter_id;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return inflater.inflate(R.layout.dialog_team_notification, container, false);
    }

    public TeamNotificationDetailDialog setOnConfirmListener(OnConfirmListener listener, String string, String deactivate, String cancel) {
        this.listener = listener;
        return null;
    }
    public TeamNotificationDetailDialog setOnConfirmListener(OnConfirmListener listener, TeamNotificationModel teamNotificationModel) {
        this.listener = listener;
        this.teamNotificationModel = teamNotificationModel;
        return null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        TextView txv_title = view.findViewById(R.id.txv_title);
        RadioGroup  radioGroup= view.findViewById(R.id.radio_button);
        RadioButton radio_yes  = view.findViewById(R.id.radio_yes);
        RadioButton radio_no  = view.findViewById(R.id.radio_no);
        TextView txv_email = view.findViewById(R.id.txv_email);
        MaterialSpinner spiner_email = view.findViewById(R.id.spiner_email);
        HtmlTextEditor html_editor = view.findViewById(R.id.html_editor);
        TextView txv_update = view.findViewById(R.id.txv_update);
        ImageView close = view.findViewById(R.id.close);
        TextView txv_date = view.findViewById(R.id.txv_date);

        txv_title.setText(teamNotificationModel.getTeamNotificationName());
        if(teamNotificationModel.getStatus()==1)
            radio_yes.setChecked(true);
        else
            radio_no.setChecked(true);
            html_editor.setText(teamNotificationModel.getTeamNotificationMessage());
        txv_date.setText(getContext().getResources().getString(R.string.complete_by) + String.valueOf(teamNotificationModel.getTeamNotificationDaysToComplete())+ "days");
        if(teamNotificationModel.getTeamNotificationAssignedTo().length()>0){
            spiner_email.setVisibility(View.GONE);
            txv_email.setVisibility(View.VISIBLE);
            getRecruiter_id();
            txv_email.setText(teamNotificationModel.getContact()+"\n"+ Commons.recruitteamModels.get(recruiter_id).getEmail());
        }else {
            ArrayList<String>arrayList = new ArrayList<>();
            for(int i =0;i<Commons.recruitteamModels.size();i++){
                arrayList.add(Commons.recruitteamModels.get(i).getName());
            }
            spiner_email.setItems(arrayList);
            spiner_email.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                @Override
                public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                    recruiter_id = position;
                }
            });
        }
        txv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checked=0;
                if(radio_yes.isChecked()) checked =1;

                listener.onConfirm(checked,recruiter_id,html_editor.getText().toString());
                dismiss();

//                Toast.makeText(getActivity(), "Repost Successfully", Toast.LENGTH_SHORT).show();
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }


    void getRecruiter_id(){
        recruiter_id =0;
        for(int i = 0;i<Commons.recruitteamModels.size();i++){
            if(Commons.recruitteamModels.get(i).getRecruiter_id().equals(teamNotificationModel.getTeamNotificationAssignedTo())){
                recruiter_id = i;
                break;
            }
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.WRAP_CONTENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    public interface OnConfirmListener {
        void onConfirm(int checked_state,int recruiterTeamID,String content );
    }
}

