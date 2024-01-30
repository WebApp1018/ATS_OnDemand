package com.hr.pereless.adapter.onboarding;

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

import com.diffey.view.progressview.ProgressView;
import com.google.gson.Gson;
import com.hr.pereless.R;
import com.hr.pereless.activities.candidate.CandidateDetailActivity;
import com.hr.pereless.activities.onboarding.OnBoardingTaskActivity;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.model.onboarding.OnboardingModel;
import com.hr.pereless.pagination.PaginationAdapterCallback;

import java.util.ArrayList;
import java.util.List;

public class HireSubAdapterPagenation extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // View Types
    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private static final int HERO = 2;

    private static final String BASE_URL_IMG = "https://image.tmdb.org/t/p/w200";

    private List<OnboardingModel> boardingdatumList;
    private Context context;

    private boolean isLoadingAdded = false;
    private boolean retryPageLoad = false;

    private PaginationAdapterCallback mCallback;

    private String errorMsg;
    int type ; //0 Prehire , 1 Onboarding, 2; Emplyoee

    public HireSubAdapterPagenation(Context context,int type) {
        this.context = context;
        Log.e("contextname",context.toString());
        //  this.mCallback = (PaginationAdapterCallback) context;
        boardingdatumList = new ArrayList<>();
    }

    public List<OnboardingModel> getMovies() {
        return boardingdatumList;
    }

    public void setMovies(List<OnboardingModel> candidateslist) {
        this.boardingdatumList = candidateslist;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        Log.e("oncreateviewholder","viewtype"+viewType);
        switch (viewType) {
            case ITEM:
                View viewItem = inflater.inflate(R.layout.adapter_onboardingcandidates, parent, false);
                viewHolder = new HireSubAdapterPagenation.MovieVH(viewItem);
                break;
            case LOADING:
                View viewLoading = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new HireSubAdapterPagenation.LoadingVH(viewLoading);
                break;

        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        OnboardingModel result = boardingdatumList.get(position); // Movie

        switch (getItemViewType(position)) {
            case ITEM:
                final HireSubAdapterPagenation.MovieVH movieVH = (HireSubAdapterPagenation.MovieVH) holder;
                movieVH.txv_name.setText(result.getDisplayName());
                movieVH.txt_name.setText(result.getCandidatefirstname()+ " " + result.getCandidatelastname());
                movieVH.txv_email.setText(result.getCandidateemail());
                movieVH.txv_title.setText(result.getJob_title());
                movieVH.txv_startdate.setText(Commons.dateTime(result.getStartingdate()));
                movieVH.end_date.setText(Commons.dateTime(result.getHirelogdate()));
                movieVH.progressBarthree.setProgress(result.getCurrentsteppercentage());
                movieVH.txv_progress.setText(String.valueOf(result.getCurrentsteppercentage()) + "%");
                movieVH.progressBarthree.setProgressColor(context.getResources().getColor(R.color.green));

                movieVH.lyt_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        Gson gson = new Gson();
                        String newfeedentity = gson.toJson(result);
                        bundle.putString("CandidateModel",newfeedentity);
                        ((CommonActivity)context).startActivityForResult(new Intent(context, OnBoardingTaskActivity.class).putExtra("data",bundle),1);
                        ((CommonActivity)context).overridePendingTransition(0, 0);
                    }
                });
                break;

            case LOADING:
                HireSubAdapterPagenation.LoadingVH loadingVH = (HireSubAdapterPagenation.LoadingVH) holder;

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
        return boardingdatumList == null ? 0 : boardingdatumList.size();
    }

    @Override
    public int getItemViewType(int position) {

        return (position == boardingdatumList.size() - 1 && isLoadingAdded) ? LOADING : ITEM;

    }






    public void add(OnboardingModel r) {
        boardingdatumList.add(r);
        notifyItemInserted(boardingdatumList.size() - 1);
    }

    public void addAll(List<OnboardingModel> moveResults) {
        Log.e("list size",moveResults.size()+"size");
        for (OnboardingModel result : moveResults) {
            boardingdatumList.add(result);
        }
    }

    public void remove(OnboardingModel r) {
        int position = boardingdatumList.indexOf(r);
        if (position > -1) {
            boardingdatumList.remove(position);
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

       /* int position = candidateslist.size() - 1;
        CandidatesResponse.Datum result = getItem(position);

        if (result != null) {
            candidateslist.remove(position);
            notifyItemRemoved(position);
        }*/
    }

    public  OnboardingModel  getItem(int position) {
        return boardingdatumList.get(position);
    }

    /**
     * Displays Pagination retry footer view along with appropriate errorMsg
     *
     * @param show
     * @param errorMsg to display if page load fails
     */
    public void showRetry(boolean show, @Nullable String errorMsg) {
        retryPageLoad = show;
        notifyItemChanged(boardingdatumList.size() - 1);

        if (errorMsg != null) this.errorMsg = errorMsg;
    }



    protected class MovieVH extends RecyclerView.ViewHolder{
        public TextView txt_name, year, genre,txv_name,txv_notidate,txv_email,txv_title,
                txv_startdate,end_date,txv_progress;
        ImageView star;
        RelativeLayout candidates;
        private ProgressView progressBarthree;
        LinearLayout lyt_item;
        public MovieVH(View itemView) {
            super(itemView);

            txt_name = (TextView) itemView.findViewById(R.id.txt_name);
            txv_name = itemView.findViewById(R.id.txv_name);
            txv_notidate = itemView.findViewById(R.id.txv_notidate);
            txv_email = itemView.findViewById(R.id.txv_email);
            txv_title = itemView.findViewById(R.id.txv_title);
            txv_startdate= itemView.findViewById(R.id.txv_startdate);
            end_date = itemView.findViewById(R.id.end_date);
            progressBarthree = itemView.findViewById(R.id.progressBarthree);
            txv_progress = itemView.findViewById(R.id.txv_progress);
            lyt_item = itemView.findViewById(R.id.lyt_item);
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

