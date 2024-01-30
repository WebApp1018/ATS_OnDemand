package com.hr.pereless.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.hr.pereless.R;
import com.hr.pereless.activities.candidate.AssignjobActivity;
import com.hr.pereless.adapter.candidate.EEoAdapter;
import com.hr.pereless.adapter.candidate.ScorecardCommentAdapter;
import com.hr.pereless.adapter.candidate.SelectJobAdapter;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.model.candidate.ScoreOptionModel;
import com.hr.pereless.pagination.PaginationScrollListener;

import java.util.ArrayList;
import java.util.List;


public class ScoreCardCommentDialog extends DialogFragment {

    private OnConfirmListener listener;
    int posstion =-1,type = 0;
    EEoAdapter recyclerViewAdapter;
    List<String> managerModels = new ArrayList<>();
    ScoreOptionModel scoreOptionModel = new ScoreOptionModel();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return inflater.inflate(R.layout.scorecard_comment_dialog, container, false);
    }

    public ScoreCardCommentDialog setOnConfirmListener(OnConfirmListener listener) {
        this.listener = listener;
        this.managerModels.clear();
        this.managerModels.addAll(managerModels);
        return null;
    }
    public ScoreCardCommentDialog setOnConfirmListener(OnConfirmListener listener, ScoreOptionModel scoreOptionModel,int type) {
        this.listener = listener;
        this.managerModels.clear();
        this.managerModels.addAll(managerModels);
        this.type = type;
        this.scoreOptionModel = scoreOptionModel;
        return null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        TextView txt_title = view.findViewById(R.id.txt_title);
        TextView save = view.findViewById(R.id.save);
        RecyclerView recyclerview = view.findViewById(R.id.recyclerview);
        SwipeRefreshLayout main_swiperefresh = view.findViewById(R.id.main_swiperefresh);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        ScorecardCommentAdapter scorecardCommentAdapter= new ScorecardCommentAdapter(getContext(),scoreOptionModel,type);
        recyclerview.setAdapter(scorecardCommentAdapter);

        main_swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                main_swiperefresh.setRefreshing(false);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        void onConfirm(int selectPosstion);
    }
}

