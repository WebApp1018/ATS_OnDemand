package com.hr.pereless.fragment.email;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hr.pereless.R;
import com.hr.pereless.adapter.email.EmailsAdapter;
import com.hr.pereless.model.email.EmailModel;
import com.hr.pereless.pagination.PaginationScrollListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EmailSubFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EmailSubFragment extends Fragment {
    View view;
    SwipeRefreshLayout main_swiperefresh;
    RecyclerView rv_joblist;
    EmailsAdapter emailsAdapter;
    private static final int PAGE_START = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    // limiting to 5 for this tutorial, since total pages in actual API is very large. Feel free to modify.
    private static int TOTAL_PAGES;
    private int currentPage = PAGE_START;
    public static EmailSubFragment newInstance(String param1, String param2) {
        EmailSubFragment fragment = new EmailSubFragment();
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
        rv_joblist = view.findViewById(R.id.rv_joblist);
        main_swiperefresh = view.findViewById(R.id.main_swiperefresh);
        emailsAdapter = new EmailsAdapter(getContext());
        Log.e("contextname", getContext().toString());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_joblist.setLayoutManager(linearLayoutManager);
        rv_joblist.setItemAnimator(new DefaultItemAnimator());
        rv_joblist.setAdapter(emailsAdapter);

        rv_joblist.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                currentPage += 1;
                Log.e("current page", " " + currentPage);
                Log.e("isLastPage page", " " + isLastPage);

                if (isLastPage == false) {
                    isLoading = true;
                    loadEmailList(currentPage);
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

        loadEmailList(0);

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
    void loadEmailList(int page){
        List<EmailModel> candidateslist = new ArrayList<>();
        for(int i =0;i<10;i++){
            EmailModel jobModel = new EmailModel();
            candidateslist.add(jobModel);
        }
        emailsAdapter.addAll(candidateslist);
        rv_joblist.post(new Runnable() {
            public void run() {
                emailsAdapter.notifyDataSetChanged();
            }
        });
    }
    private void doRefresh() {
        emailsAdapter.getMovies().clear();
        emailsAdapter.notifyDataSetChanged();
        loadEmailList(0);
        main_swiperefresh.setRefreshing(false);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_email_sub, container, false);
        return view;

    }
}