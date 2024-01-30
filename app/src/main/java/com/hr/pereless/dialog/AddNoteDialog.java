package com.hr.pereless.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.hr.pereless.R;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.model.candidate.QuicknoteModel;
import com.hr.pereless.model.candidate.SkillLabelModel;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

public class AddNoteDialog extends DialogFragment {

    private OnConfirmListener listener;
    List<SkillLabelModel> skillLabelModels = new ArrayList<>();
    List<String>colormodels = new ArrayList<>();
    int type = 0;
    int spiner_skill= 0,spiner_color =0;
    QuicknoteModel quicknoteModel = new QuicknoteModel();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return inflater.inflate(R.layout.dialog_addnote, container, false);
    }

    public CommunicationDialog setOnConfirmListener(OnConfirmListener listener, List<SkillLabelModel> skillLabelModels,    List<String>colormodels) {
        this.listener = listener;
        this.skillLabelModels = skillLabelModels;
        this.colormodels = colormodels;
        this.type = 0;
        return null;
    }
    public CommunicationDialog setOnConfirmListener(OnConfirmListener listener, List<SkillLabelModel> skillLabelModels, List<String>colormodels, QuicknoteModel quicknoteModel) {
        this.listener = listener;
        this.skillLabelModels = skillLabelModels;
        this.colormodels = colormodels;
        this.type =1;
        this.quicknoteModel= quicknoteModel;
        return null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        EditText notes_box=(EditText)view.findViewById(R.id.notes_box);
        TextView send=(TextView)view.findViewById(R.id.send);
        TextView txttitletext=(TextView)view.findViewById(R.id.txttitletext);
        ImageView close=(ImageView)view.findViewById(R.id.close);
        MaterialSpinner spinner_skill = view.findViewById(R.id.spinner_skill);
        EditText edit_score = view.findViewById(R.id.edit_score);
        MaterialSpinner spinner_color = view.findViewById(R.id.spinner_color);

        List<String> skill_label = new ArrayList<>();
        for(int i =0;i<skillLabelModels.size();i++){
            skill_label.add(skillLabelModels.get(i).getHotbookName());
        }
        spinner_skill.setItems(skill_label);
        spinner_skill.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                spiner_skill =position;
            }
        });

        spinner_color.setItems(colormodels);
        spinner_color.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                spiner_color = position;
            }
        });


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        if(type == 1){
            txttitletext.setText(getResources().getString(R.string.edit_note));
            send.setText(getResources().getString(R.string.update));
            for(int i =0;i<skillLabelModels.size();i++){
                if(skillLabelModels.get(i).getHotbookID().equals(quicknoteModel.getQnhotbook())){
                    spinner_skill.setSelectedIndex(i);
                    break;
                }
            }
            for(int i =0;i<colormodels.size();i++){
                if(colormodels.get(i).equals(quicknoteModel.getBgcolor())){
                    spinner_color.setSelectedIndex(i);
                    break;
                }
            }

            //edit_score.setText(quicknoteModel.getRole());
            notes_box.setText(quicknoteModel.getQnotes());

        }
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(notes_box.getText().toString().length()==0){
                    ((CommonActivity)getContext()).showAlertDialog("Please input Quick Note");
                    return;
                }
                listener.onConfirm(spiner_skill,notes_box.getText().toString(),edit_score.getText().toString().trim(),spiner_color);
                dismiss();
            }
        });
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
        void onConfirm(int label,String quick_note, String score, int color);
    }
}

