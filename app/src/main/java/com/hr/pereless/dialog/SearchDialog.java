package com.hr.pereless.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.hr.pereless.R;
import com.hr.pereless.activities.MainActivity;
import com.hr.pereless.activities.candidate.AssignjobActivity;
import com.hr.pereless.adapter.candidate.EEoAdapter;
import com.hr.pereless.adapter.candidate.SelectJobAdapter;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.pagination.PaginationScrollListener;

import java.util.ArrayList;
import java.util.List;


public class SearchDialog extends DialogFragment {

    private OnConfirmListener listener;
    int posstion =-1,type = 0;
    List<String> managerModels = new ArrayList<>();
    SelectJobAdapter selectJobAdapter;
    RecyclerView recyclerview;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return inflater.inflate(R.layout.search_dialog, container, false);
    }

    public SearchDialog setOnConfirmListener(OnConfirmListener listener, List<String> managerModels) {
        this.listener = listener;
        this.managerModels.clear();
        this.managerModels.addAll(managerModels);
        return null;
    }
    public SearchDialog setOnConfirmListener(OnConfirmListener listener, List<String> managerModels, int type) {
        this.listener = listener;
        this.managerModels.clear();
        this.managerModels.addAll(managerModels);
        this.type = type;
        return null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        TextView txt_title = view.findViewById(R.id.txt_title);
        EditText txt_search = view.findViewById(R.id.txt_search);
        TextView save = view.findViewById(R.id.save);
        recyclerview = view.findViewById(R.id.recyclerview);
        SwipeRefreshLayout main_swiperefresh = view.findViewById(R.id.main_swiperefresh);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        selectJobAdapter = new SelectJobAdapter(managerModels, getActivity(), new SelectJobAdapter.OnSelectListener() {
            @Override
            public void onSelect(int selectPosstion) {
                listener.onConfirm(selectPosstion);
                dismiss();
            }
        });
        recyclerview.setAdapter(selectJobAdapter);

        main_swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                main_swiperefresh.setRefreshing(false);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(posstion==-1){
                    ((CommonActivity)getContext()).showAlertDialog("Please choice one item");
                    return;
                }
                listener.onConfirm(posstion);
                dismiss();

//                Toast.makeText(getActivity(), "Repost Successfully", Toast.LENGTH_SHORT).show();
            }
        });
       // ((MainActivity)(getActivity())).searchJObandCandidate(txt_search.getText().toString().trim());

        txt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ((MainActivity)(getActivity())).searchJObandCandidate(txt_search.getText().toString().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txt_search.setText("");
    }


    public void setValues(ArrayList<String>arrayList){
        managerModels.clear();
        managerModels.addAll(arrayList);
        selectJobAdapter.setDate(managerModels);

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
        void onConfirm(int selectPosstion);
    }
}

