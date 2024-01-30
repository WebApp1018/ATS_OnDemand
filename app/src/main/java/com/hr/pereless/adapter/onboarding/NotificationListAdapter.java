package com.hr.pereless.adapter.onboarding;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.pereless.R;
import com.hr.pereless.fragment.onboard.TeamNotificationFragment;
import com.hr.pereless.model.onboarding.TeamNotificationModel;

import java.util.ArrayList;
import java.util.List;

public class NotificationListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // View Types
    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private static final int HERO = 2;


    private List<TeamNotificationModel> candidateslist= new ArrayList<>();;
    private Context context;

    private boolean isLoadingAdded = false;
    private boolean retryPageLoad = false;
    private String errorMsg;
    TeamNotificationFragment fragment;
    public NotificationListAdapter(Context context, TeamNotificationFragment fragment) {
        this.context = context;
        this.fragment = fragment;
        Log.e("contextname",context.toString());
        //  this.mCallback = (PaginationAdapterCallback) context;
    }

    public List<TeamNotificationModel> getMovies() {
        return candidateslist;
    }

    public void setMovies(List<TeamNotificationModel> candidateslist) {
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
                View viewItem = inflater.inflate(R.layout.adapter_teamnotification_item, parent, false);
                viewHolder = new NotificationListAdapter.MovieVH(viewItem);
                break;
            case LOADING:
                View viewLoading = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new NotificationListAdapter.LoadingVH(viewLoading);
                break;

        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TeamNotificationModel result = candidateslist.get(position); // Movie
        switch (getItemViewType(position)) {

            case ITEM:
                final NotificationListAdapter.MovieVH movieVH = (NotificationListAdapter.MovieVH) holder;

                movieVH.txv_title.setText(result.getTeamNotificationName());
                movieVH.txt_content.setText(Html.fromHtml(result.getTeamNotificationMessage()));
                movieVH.txv_date.setText(context.getResources().getString(R.string.complete_by) + String.valueOf(result.getTeamNotificationDaysToComplete())+ "days");

                movieVH.lyt_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fragment.NotificationDetail(position);
                    }
                });
                break;

            case LOADING:
                NotificationListAdapter.LoadingVH loadingVH = (NotificationListAdapter.LoadingVH) holder;

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
    public void add(TeamNotificationModel r) {
        candidateslist.add(r);
        notifyItemInserted(candidateslist.size() - 1);
    }

    public void addAll(List<TeamNotificationModel> moveResults) {
        Log.e("list size",moveResults.size()+"size");
        for (TeamNotificationModel result : moveResults) {
            candidateslist.add(result);
        }
    }

    public void remove(TeamNotificationModel r) {
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

    public  TeamNotificationModel  getItem(int position) {
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
        public TextView txv_title,txt_content,txv_date;
        LinearLayout lyt_item;
        public MovieVH(View itemView) {
            super(itemView);

            txv_title = (TextView) itemView.findViewById(R.id.txv_title);
            txt_content=(TextView)itemView.findViewById(R.id.txt_content);
            txv_date=(TextView)itemView.findViewById(R.id.txv_date);
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
