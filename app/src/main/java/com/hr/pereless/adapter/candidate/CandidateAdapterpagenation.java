package com.hr.pereless.adapter.candidate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.hr.pereless.R;
import com.hr.pereless.activities.candidate.CandidateDetailActivity;
import com.hr.pereless.activities.job.JobDetailActivity;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.model.candidate.CandidateModel;
import com.hr.pereless.pagination.PaginationAdapterCallback;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class CandidateAdapterpagenation extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // View Types
    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private static final int HERO = 2;
    private List<CandidateModel> candidateslist;
    private Context context;
    private boolean isLoadingAdded = false;
    private boolean retryPageLoad = false;
    private PaginationAdapterCallback mCallback;

    private String errorMsg;

    public CandidateAdapterpagenation(Context context) {
        this.context = context;
        candidateslist = new ArrayList<>();
    }

    public List<CandidateModel> getMovies() {
        return candidateslist;
    }

    public void setMovies(List<CandidateModel> candidateslist) {
        this.candidateslist = candidateslist;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case ITEM:
                View viewItem = inflater.inflate(R.layout.adapter_candidates, parent, false);
                viewHolder = new MovieVH(viewItem);
                break;
            case LOADING:
                View viewLoading = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new LoadingVH(viewLoading);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CandidateModel result = candidateslist.get(position); // Movie
        switch (getItemViewType(position)) {
            case ITEM:
                final MovieVH movieVH = (MovieVH) holder;
                movieVH.title.setText(result.getCanname());
                movieVH.txt_time.setText(result.getEnterdate());
                movieVH.address.setText(result.getEmail());
                movieVH.txv_source.setText(result.getSource());
                movieVH.jobappliedfor.setText(result.getJob_title());
                movieVH.txt_recruit_flow.setText(result.getFlowname());
                movieVH.txv_name.setText(result.getDisplayName());
                movieVH.lyt_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        Gson gson = new Gson();
                        String newfeedentity = gson.toJson(result);
                        bundle.putString("CandidateModel",newfeedentity);
                        ((CommonActivity)context).startActivityForResult(new Intent(context, CandidateDetailActivity.class).putExtra("data",bundle),1);
                        ((CommonActivity)context).overridePendingTransition(0, 0);
                    }
                });
                break;

            case LOADING:
                LoadingVH loadingVH = (LoadingVH) holder;
                if (retryPageLoad) {
                    loadingVH.mErrorLayout.setVisibility(View.VISIBLE);
                    loadingVH.mProgressBar.setVisibility(View.GONE);
                } else {
                    loadingVH.mErrorLayout.setVisibility(View.GONE);
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return candidateslist == null ? 0 : candidateslist.size();
    }

    @Override
    public int getItemViewType(int position) {

        return (position == candidateslist.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    public void add(CandidateModel r) {
        candidateslist.add(r);
        notifyItemInserted(candidateslist.size() - 1);
    }

    public void addAll(List<CandidateModel> moveResults) {
        Log.e("list size", moveResults.size() + "size");
        for (CandidateModel result : moveResults) {
            candidateslist.add(result);
        }
    }

    public void remove(CandidateModel r) {
        int position = candidateslist.indexOf(r);
        if (position > -1) {
            candidateslist.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }


    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;
    }

    public CandidateModel getItem(int position) {
        return candidateslist.get(position);
    }

    /**
     * Displays Pagination retry footer view along with appropriate errorMsg
     *
     * @param show
     * @param errorMsg to display if page load fails
     */
    public void showRetry(boolean show, @Nullable String errorMsg) {
        retryPageLoad = show;
        notifyItemChanged(candidateslist.size() - 1);

        if (errorMsg != null) this.errorMsg = errorMsg;
    }

    protected class MovieVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title, address, score,jobappliedfor;
        ImageView star;
        RelativeLayout candidates;
        private ProgressBar mProgress;
        RoundedImageView userIcon;
        TextView txv_name,txt_time,txv_source,txt_recruit_flow;
        LinearLayout lyt_item;
        public MovieVH(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.txt_name);
            address = (TextView) itemView.findViewById(R.id.txt_address);
            score = (TextView) itemView.findViewById(R.id.txt_score);
            jobappliedfor=(TextView)itemView.findViewById(R.id.txt_jobapplied);
            userIcon=(RoundedImageView)itemView.findViewById(R.id.imageView1);
            txv_name = itemView.findViewById(R.id.txv_name);
            txt_time = itemView.findViewById(R.id.txt_time);
            txv_source = itemView.findViewById(R.id.txv_source);
            txt_recruit_flow = itemView.findViewById(R.id.txt_recruit_flow);
            lyt_item = itemView.findViewById(R.id.lyt_item);
        }

        @Override
        public void onClick(View view) {

        }
    }

    protected class LoadingVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ProgressBar mProgressBar;
        private ImageButton mRetryBtn;
        private TextView mErrorTxt;
        private LinearLayout mErrorLayout;
        public LoadingVH(View itemView) {
            super(itemView);

            mProgressBar = itemView.findViewById(R.id.loadmore_progress);
            mRetryBtn = itemView.findViewById(R.id.loadmore_retry);
            mErrorTxt = itemView.findViewById(R.id.loadmore_errortxt);
            mErrorLayout = itemView.findViewById(R.id.loadmore_errorlayout);
            mRetryBtn.setOnClickListener(this);
            mErrorLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}

