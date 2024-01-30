package com.hr.pereless.fragment.candidate;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hr.pereless.R;
import com.hr.pereless.activities.MainActivity;
import com.hr.pereless.adapter.candidate.CandidateAdapterpagenation;
import com.hr.pereless.api.API;
import com.hr.pereless.api.BaseJsonObjectRequest;
import com.hr.pereless.application.AppController;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.model.candidate.CandidateModel;
import com.hr.pereless.model.job.JobModel;
import com.hr.pereless.pagination.PaginationScrollListener;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hr.pereless.base.BaseActivity.closeProgress;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CandidateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CandidateFragment extends Fragment {
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
    Button btn_dop_down;
    String Candidate="AA";
    List<CandidateModel> candidateslist = new ArrayList<>();
    MainActivity mainActivity ;
    public static CandidateFragment newInstance() {
        CandidateFragment fragment = new CandidateFragment();
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
        relative_layout = view.findViewById(R.id.relative_layout);
        txt_search = view.findViewById(R.id.txt_search);
        txt_title = view.findViewById(R.id.txt_title);
        main_swiperefresh = view.findViewById(R.id.main_swiperefresh);
        main_progress = view.findViewById(R.id.main_progress);
        rv_joblist = view.findViewById(R.id.rv_joblist);
        btn_dop_down = view.findViewById(R.id.btn_dop_down);
        candidateAdapterpagenation = new CandidateAdapterpagenation(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_joblist.setLayoutManager(linearLayoutManager);
        rv_joblist.setItemAnimator(new DefaultItemAnimator());
        rv_joblist.setAdapter(candidateAdapterpagenation);

        main_swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                doRefresh();
            }
        });
        btn_dop_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dailog_candidate_title);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                TextView txtall = (TextView) dialog.findViewById(R.id.txt_all);
                TextView txtnew = (TextView) dialog.findViewById(R.id.txt_new);
                TextView txtinterview = (TextView) dialog.findViewById(R.id.txt_interview);
                TextView txthired = (TextView) dialog.findViewById(R.id.txt_hired);
                TextView txt_referral = (TextView) dialog.findViewById(R.id.txt_referral);
                TextView txt_career = (TextView) dialog.findViewById(R.id.txt_career);
                TextView txt_employee = (TextView) dialog.findViewById(R.id.txt_employee);
                ImageView immgClose = (ImageView) dialog.findViewById(R.id.close);
                immgClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                txtall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        candidateAdapterpagenation.clear();
                        Candidate="AA";
                        loadCandidateList();
                        txt_title.setText(getResources().getString(R.string.all_candiates));
                    }
                });
                txtnew.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        candidateAdapterpagenation.clear();
                        Candidate="JB";
                        loadCandidateList();
                        txt_title.setText(getResources().getString(R.string.job_board));
                    }
                });
                txtinterview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        candidateAdapterpagenation.clear();
                        Candidate="AG";
                        loadCandidateList();
                        txt_title.setText(getResources().getString(R.string.agency));
                    }
                });
                txthired.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        candidateAdapterpagenation.clear();
                        Candidate="CP";
                        loadCandidateList();
                        txt_title.setText(getResources().getString(R.string.future_potential));
                    }
                });
                txt_referral.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        candidateAdapterpagenation.clear();
                        Candidate="RF";
                        loadCandidateList();
                        txt_title.setText(getResources().getString(R.string.referral));
                    }
                });
                txt_career.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        candidateAdapterpagenation.clear();
                        Candidate="BK";
                        loadCandidateList();
                        txt_title.setText(getResources().getString(R.string.career));
                    }
                });
                txt_employee.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        candidateAdapterpagenation.clear();
                        Candidate="EP";
                        loadCandidateList();
                        txt_title.setText(getResources().getString(R.string.employee));
                    }
                });
            }
        });

        loadCandidateList();
    }

    void loadCandidateList(){
        candidateslist.clear();
        ((CommonActivity)getContext()).showProgress();
        StringRequest myRequest = new StringRequest(
                Request.Method.GET,
                API.GET_CANDIDATE + Candidate,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                        closeProgress();
                        try {
                            JSONArray jsonArray = new JSONArray(json);
                            for(int i =0;i<jsonArray.length();i++){
                                CandidateModel candidateModel = new CandidateModel();
                                candidateModel.initModel(jsonArray.getJSONObject(i));
                                candidateslist.add(candidateModel);
                            }
                            candidateAdapterpagenation.addAll(candidateslist);
                            rv_joblist.post(new Runnable() {
                                public void run() {
                                    candidateAdapterpagenation.notifyDataSetChanged();
                                }
                            });
                        }catch (Exception e){

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        closeProgress();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", Commons.token);
                return params;
            }
        };
        myRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(myRequest, "tag");

    }


    private void doRefresh() {

        main_progress.setVisibility(View.VISIBLE);

        candidateAdapterpagenation.getMovies().clear();
        candidateAdapterpagenation.notifyDataSetChanged();
        loadCandidateList();
        main_swiperefresh.setRefreshing(false);
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_candidate, container, false);
        return  view;
    }
}