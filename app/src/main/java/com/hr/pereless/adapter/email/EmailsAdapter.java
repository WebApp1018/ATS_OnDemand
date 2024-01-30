package com.hr.pereless.adapter.email;

import android.content.Context;
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

import com.hr.pereless.R;
import com.hr.pereless.activities.email.EmailSendActivity;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.model.email.EmailModel;
import com.hr.pereless.pagination.PaginationAdapterCallback;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class EmailsAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // View Types
    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private static final int HERO = 2;


    private List<EmailModel> candidateslist;
    private Context context;

    private boolean isLoadingAdded = false;
    private boolean retryPageLoad = false;

    private PaginationAdapterCallback mCallback;

    private String errorMsg;

    public EmailsAdapter(Context context) {
        this.context = context;
        Log.e("contextname", context.toString());
        //  this.mCallback = (PaginationAdapterCallback) context;
        candidateslist = new ArrayList<>();
    }

    public List<EmailModel> getMovies() {
        return candidateslist;
    }

    public void setMovies(List<EmailModel> candidateslist) {
        this.candidateslist = candidateslist;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        Log.e("oncreateviewholder", "viewtype" + viewType);
        switch (viewType) {
            case ITEM:
                View viewItem = inflater.inflate(R.layout.adapter_email, parent, false);
                viewHolder = new EmailsAdapter.MovieVH(viewItem);
                break;
            case LOADING:
                View viewLoading = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new EmailsAdapter.LoadingVH(viewLoading);
                break;

        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EmailModel result = candidateslist.get(position); // Movie


        Log.e("onBindViewHolder", "viewtype" + position);
        switch (getItemViewType(position)) {

            case ITEM:
                final EmailsAdapter.MovieVH movieVH = (EmailsAdapter.MovieVH) holder;

//                movieVH.name.setText(result.getSendername());
//                movieVH.email.setText(result.getEmail());
//                movieVH.desc.setText(result.getDescriptionofcontent());
//                Glide.with(context).load(result.getSenderimage()).into(movieVH.userIcon);
//
//                Log.e("name", result.getSendername());


                break;

            case LOADING:
                EmailsAdapter.LoadingVH loadingVH = (EmailsAdapter.LoadingVH) holder;

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


    public void add(EmailModel r) {
        candidateslist.add(r);
        notifyItemInserted(candidateslist.size() - 1);
    }

    public void addAll(List<EmailModel> moveResults) {
        Log.e("list size", moveResults.size() + "size");
        for (EmailModel result : moveResults) {
            candidateslist.add(result);

            // candidateslist.add(result);
        }
    }

    public void remove(EmailModel r) {
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

    public EmailModel getItem(int position) {
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
        public TextView name, email, desc, txt_date;
        CircleImageView userIcon;
        ImageView star;
        RelativeLayout candidates;
        private ProgressBar mProgress;

        public MovieVH(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.txt_name);
            email = (TextView) itemView.findViewById(R.id.txtemail);
            desc = (TextView) itemView.findViewById(R.id.txt_description);

            userIcon = (CircleImageView) itemView.findViewById(R.id.usericon);
            // mProgress = itemView.findViewById(R.id.movie_progress);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((CommonActivity) context).goTo(context, EmailSendActivity.class, false);
                }
            });


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
