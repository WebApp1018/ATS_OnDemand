package com.hr.pereless.adapter.onboarding;

import android.content.Context;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.pereless.R;
import com.hr.pereless.fragment.onboard.TasksubFragment;
import com.hr.pereless.fragment.onboard.TeamNotificationFragment;
import com.hr.pereless.model.onboarding.TaskModel;
import com.hr.pereless.model.onboarding.TeamNotificationModel;

import java.util.ArrayList;
import java.util.List;

public class TaskListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // View Types
    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private static final int HERO = 2;


    private List<TaskModel> candidateslist= new ArrayList<>();;
    public List<TaskModel> selectModel= new ArrayList<>();;

    private Context context;

    private boolean isLoadingAdded = false;
    private boolean retryPageLoad = false;
    private String errorMsg;
    TasksubFragment fragment;
    public TaskListAdapter(Context context, TasksubFragment fragment) {
        this.context = context;
        this.fragment = fragment;
        Log.e("contextname",context.toString());
        //  this.mCallback = (PaginationAdapterCallback) context;
    }

    public List<TaskModel> getMovies() {
        return candidateslist;
    }

    public void setMovies(List<TaskModel> candidateslist) {
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
                View viewItem = inflater.inflate(R.layout.activity_task_item, parent, false);
                viewHolder = new TaskListAdapter.MovieVH(viewItem);
                break;
            case LOADING:
                View viewLoading = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new TaskListAdapter.LoadingVH(viewLoading);
                break;

        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TaskModel result = candidateslist.get(position); // Movie
        switch (getItemViewType(position)) {

            case ITEM:
                final TaskListAdapter.MovieVH movieVH = (TaskListAdapter.MovieVH) holder;
//
                movieVH.txv_title.setText(result.getTaskSubject());
                movieVH.edt_date.setText(String.valueOf(result.getDaysToComplete()));
                movieVH.checkbox.setChecked(false);

                for(int i =0;i<selectModel.size();i++){
                    if(result.getTask_id() == selectModel.get(i).getTask_id()) {
                        movieVH.checkbox.setChecked(true);
                        if(movieVH.edt_date.getText().toString().length()==0)
                            selectModel.get(i).setDaysToComplete(0);
                        else
                            selectModel.get(i).setDaysToComplete(Integer.parseInt(movieVH.edt_date.getText().toString()));
                        break;
                    }
                }
                movieVH.checkbox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for(int i =0;i<selectModel.size();i++){
                            if(selectModel.get(i).getTask_id() == result.getTask_id()){
                                if(!movieVH.checkbox.isChecked()){
                                    selectModel.remove(i);
                                    return;
                                }
                            }
                        }
                        selectModel.add(result);
                    }
                });

                movieVH.edt_date.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if(movieVH.edt_date.getText().toString().length()==0)
                            result.setDaysToComplete(0);
                        else
                         result.setDaysToComplete(Integer.parseInt(movieVH.edt_date.getText().toString()));
                    }
                });

                break;

            case LOADING:
                TaskListAdapter.LoadingVH loadingVH = (TaskListAdapter.LoadingVH) holder;

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
    public void add(TaskModel r) {
        candidateslist.add(r);
        notifyItemInserted(candidateslist.size() - 1);
    }

    public void addAll(List<TaskModel> moveResults,List<TaskModel> selectModels) {
        Log.e("list size",moveResults.size()+"size");
        for (TaskModel result : moveResults) {
            candidateslist.add(result);
        }
        this.selectModel =selectModels;
    }

    public void remove(TaskModel r) {
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

    public  TaskModel  getItem(int position) {
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
        public TextView txv_title;
        LinearLayout lyt_item;
        CheckBox checkbox;
        EditText edt_date;
        public MovieVH(View itemView) {
            super(itemView);

            txv_title = (TextView) itemView.findViewById(R.id.txv_title);
            checkbox=itemView.findViewById(R.id.checkbox);
            edt_date=itemView.findViewById(R.id.edt_date);
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
