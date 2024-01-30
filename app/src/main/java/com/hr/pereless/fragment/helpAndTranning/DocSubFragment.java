package com.hr.pereless.fragment.helpAndTranning;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hr.pereless.R;
import com.hr.pereless.adapter.helpTraning.DochelpAdapter;
import com.hr.pereless.model.NotificationModel;
import com.hr.pereless.model.TraningModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DocSubFragment extends Fragment {
    View view;
    LinearLayout layout_main;
    SwipeRefreshLayout main_swiperefresh;
    FloatingActionButton floating_action_button;
    DochelpAdapter dochelpAdapter;
    RecyclerView rv_joblist;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        layout_main = view.findViewById(R.id.layout_main);
        main_swiperefresh = view.findViewById(R.id.main_swiperefresh);
        floating_action_button = view.findViewById(R.id.floating_action_button);
        rv_joblist = view.findViewById(R.id.rv_joblist);
        dochelpAdapter = new DochelpAdapter( getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rv_joblist.setLayoutManager(mLayoutManager);
        rv_joblist.setItemAnimator(new DefaultItemAnimator());
        rv_joblist.setAdapter(dochelpAdapter);
        main_swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doRefresh();
                    }
                }, 2500);
            }
        });
        loadData();
    }

    void loadData(){
        List<TraningModel> candidateslist = new ArrayList<>();
        for(int i =0;i<10;i++){
            TraningModel candidateModel = new TraningModel();
            candidateslist.add(candidateModel);
        }
        dochelpAdapter.addAll(candidateslist);
    }
    private void doRefresh() {
        dochelpAdapter.getMovies().clear();
        dochelpAdapter.notifyDataSetChanged();
        loadData();
        main_swiperefresh.setRefreshing(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_doc_sub, container, false);
        return view;
    }
}