package com.hr.pereless.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.pereless.R;
import com.hr.pereless.adapter.job.ManageAdapter;
import com.hr.pereless.model.job.RecruiterModel;

import java.util.ArrayList;
import java.util.List;


public class JobHiringDetailDialog extends DialogFragment {

    private OnConfirmListener listener;
    int type;
    static  final List<RecruiterModel> managerModels = new ArrayList<>();
    List<RecruiterModel> selectModels = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return inflater.inflate(R.layout.dialog_hrmanager, container, false);
    }


    public JobHiringDetailDialog setOnConfirmListener(OnConfirmListener listener, int type, List<RecruiterModel> recruiterModels,List<RecruiterModel>hireModels) {
        this.listener = listener;
        this.type = type;
        managerModels.clear();
        if(type == 0){
            managerModels.addAll(hireModels);
        }else if(type ==1){
            managerModels.addAll(recruiterModels);
        }

        selectModels.clear();
        for(int i =0;i<managerModels.size();i++){
            RecruiterModel recruiterModel = new RecruiterModel();
            recruiterModel = managerModels.get(i);
            selectModels.add(recruiterModel);
        }

        return null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView txt_title = view.findViewById(R.id.txt_title);
        RecyclerView recyclerview = view.findViewById(R.id.recyclerview);
        TextView save = view.findViewById(R.id.save);
        if(type == 0){
            txt_title.setText("Hiring Manager");
        }else if(type == 1){
            txt_title.setText("Recruiter");
        }else {
            txt_title.setText("Agency");
        }
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(getContext());
        recyclerview.setLayoutManager(recyclerLayoutManager);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerview.getContext(),
                        recyclerLayoutManager.getOrientation());
        recyclerview.addItemDecoration(dividerItemDecoration);


        ManageAdapter manageAdapter = new
                ManageAdapter(managerModels, getContext(), new ManageAdapter.OnSelectListener() {
            @Override
            public void onEmailSelect(int id, boolean state) {
                selectModels.get(id).setSelected(state);
            }
        });
        recyclerview.setAdapter(manageAdapter);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onConfirm(selectModels);
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
        void onConfirm(List<RecruiterModel> selectModels);
    }
}

