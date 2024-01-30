package com.hr.pereless.fragment.job;

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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hr.pereless.R;
import com.hr.pereless.adapter.candidate.CandidateAdapterpagenation;
import com.hr.pereless.model.candidate.CandidateModel;
import com.hr.pereless.pagination.PaginationScrollListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class CandidatesubFragment extends Fragment {
    View view ;
    RelativeLayout relative_layout;
    TextView txt_search,txt_title;
    SwipeRefreshLayout main_swiperefresh;
    ProgressBar main_progress;
    CandidateAdapterpagenation candidateAdapterpagenation;
    RecyclerView rv_joblist;
    private static final int PAGE_START = 1;
    private int currentPage = PAGE_START;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private static int TOTAL_PAGES;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        relative_layout = view.findViewById(R.id.relative_layout);
        txt_search = view.findViewById(R.id.txt_search);
        txt_title = view.findViewById(R.id.txt_title);
        main_swiperefresh = view.findViewById(R.id.main_swiperefresh);
        main_progress = view.findViewById(R.id.main_progress);
        rv_joblist = view.findViewById(R.id.rv_joblist);
        candidateAdapterpagenation = new CandidateAdapterpagenation(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_joblist.setLayoutManager(linearLayoutManager);
        rv_joblist.setItemAnimator(new DefaultItemAnimator());
        rv_joblist.setAdapter(candidateAdapterpagenation);

        rv_joblist.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {

                currentPage += 1;

                if (isLastPage == false) {
                    isLoading = true;
                    loadCandidateList(currentPage);

                } else {
                    isLoading = false;
                }
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
        loadCandidateList(0);
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
    }

    void loadCandidateList(int page){

    }
    private void doRefresh() {

        main_progress.setVisibility(View.VISIBLE);

        candidateAdapterpagenation.getMovies().clear();
        candidateAdapterpagenation.notifyDataSetChanged();
        loadCandidateList(0);
        main_swiperefresh.setRefreshing(false);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_candidatesub, container, false);
        return  view;
    }
}