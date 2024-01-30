package com.hr.pereless.adapter.job;

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
import com.hr.pereless.commons.Commons;
import com.hr.pereless.model.job.JobModel;

import java.util.ArrayList;
import java.util.List;

public class JobListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // View Types
    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private static final int HERO = 2;


    private List<JobModel> candidateslist= new ArrayList<>();;
    private Context context;

    private boolean isLoadingAdded = false;
    private boolean retryPageLoad = false;
    private String errorMsg;

    public JobListAdapter(Context context) {
        this.context = context;
        Log.e("contextname",context.toString());
        //  this.mCallback = (PaginationAdapterCallback) context;
    }

    public List<JobModel> getMovies() {
        return candidateslist;
    }

    public void setMovies(List<JobModel> candidateslist) {
        this.candidateslist = candidateslist;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        Log.e("oncreateviewholder","viewtype"+viewType);
        switch (viewType) {
            case ITEM:
                View viewItem = inflater.inflate(R.layout.adapter_joblist, parent, false);
                viewHolder = new JobListAdapter.MovieVH(viewItem);
                break;
            case LOADING:
                View viewLoading = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new JobListAdapter.LoadingVH(viewLoading);
                break;

        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        JobModel result = candidateslist.get(position); // Movie
        switch (getItemViewType(position)) {

            case ITEM:
                final JobListAdapter.MovieVH movieVH = (JobListAdapter.MovieVH) holder;

                movieVH.title.setText(result.getJob_title());
                movieVH.address.setText(result.getLocation());
                movieVH.salery.setText(result.getJobtype()+ " - $"+result.getSalary());
                movieVH.txt_date.setText(Commons.dateTime(result.getStatusdate()));
                movieVH.txv_star.setText(String.valueOf(result.getCandidatecount()));

                movieVH.lyt_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        Gson gson = new Gson();
                        String newfeedentity = gson.toJson(result);
                        bundle.putString("JobModel",newfeedentity);
                        ((CommonActivity)context).startActivityForResult(new Intent(context, JobDetailActivity.class).putExtra("data",bundle),1);
                        ((CommonActivity)context).overridePendingTransition(0, 0);
                    }
                });
                break;

            case LOADING:
                JobListAdapter.LoadingVH loadingVH = (JobListAdapter.LoadingVH) holder;

                if (retryPageLoad) {
                    loadingVH.mErrorLayout.setVisibility(View.VISIBLE);
                    loadingVH.mProgressBar.setVisibility(View.GONE);

                /*        loadingVH.mErrorTxt.setText(
                                errorMsg != null ?
                                        errorMsg :
                                        context.getString(R.string.error_msg_unknown));*/

                } else {
                    loadingVH.mErrorLayout.setVisibility(View.GONE);
                    //loadingVH.mProgressBar.setVisibility(View.VISIBLE);
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
    public void add(JobModel r) {
        candidateslist.add(r);
        notifyItemInserted(candidateslist.size() - 1);
    }

    public void addAll(List<JobModel> moveResults) {
        Log.e("list size",moveResults.size()+"size");
        for (JobModel result : moveResults) {
            candidateslist.add(result);
        }
    }

    public void remove(JobModel r) {
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
        //add(new CandidatesResponse());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;


    }

    public  JobModel  getItem(int position) {
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



    protected class MovieVH extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView title,address,salery,txt_date;
        ImageView star;
        RelativeLayout candidates;
        private ProgressBar mProgress;
        TextView txv_star;
        LinearLayout lyt_item;
        public MovieVH(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.txt_position);
            address=(TextView)itemView.findViewById(R.id.txtloction);
            salery=(TextView)itemView.findViewById(R.id.txtsalery);
            txt_date=(TextView)itemView.findViewById(R.id.txt_date);
            candidates = (RelativeLayout) itemView.findViewById(R.id.candidates);
            star = (ImageView) itemView.findViewById(R.id.star);
            txv_star =  (TextView) itemView.findViewById(R.id.txv_star);
            lyt_item = itemView.findViewById(R.id.lyt_item);
            // mProgress = itemView.findViewById(R.id.movie_progress);

        }

        @Override
        public void onClick(View view) {
                /*switch (view.getId()) {
                    case R.id.loadmore_retry:
                    case R.id.loadmore_errorlayout:

                        showRetry(false, null);
                        mCallback.retryPageLoad();

                        break;
                }*/
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
                /*switch (view.getId()) {
                    case R.id.loadmore_retry:
                    case R.id.loadmore_errorlayout:

                        showRetry(false, null);
                        mCallback.retryPageLoad();

                        break;
                }*/
        }
    }

}
