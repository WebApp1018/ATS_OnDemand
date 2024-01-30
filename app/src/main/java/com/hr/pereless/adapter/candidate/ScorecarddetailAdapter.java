package com.hr.pereless.adapter.candidate;

import android.content.Context;
import android.graphics.PorterDuff;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.pereless.R;
import com.hr.pereless.fragment.candidate.SectionModel;
import com.hr.pereless.model.candidate.ScoreOptionModel;
import com.hr.pereless.model.email.EmailModel;

import java.util.ArrayList;
import java.util.List;

public class ScorecarddetailAdapter extends RecyclerView.Adapter<ScorecarddetailAdapter.MyViewHolder>{


    Context context;
    private List<ScoreOptionModel> scoreOptionModels = new ArrayList<>();
    int pos;
    int status;

    void selectItem(MyViewHolder myViewHolder){
        scoreOptionModels.get(pos).setScore(status);
        myViewHolder.img_double_dislike.setBackgroundResource(R.drawable.back);
        myViewHolder.img_dislike.setBackgroundResource(R.drawable.btn_layout_red_unselect);
        myViewHolder.img_none.setBackgroundResource(R.drawable.btn_layout_red_unselect);
        myViewHolder.img_like.setBackgroundResource(R.drawable.btn_layout_red_unselect);
        myViewHolder.img_double_like.setBackgroundResource(R.drawable.btn_layout_red_unselect);
        myViewHolder.img_double_dislike.setColorFilter(ContextCompat.getColor(context, R.color.colorlightred), PorterDuff.Mode.MULTIPLY);
        myViewHolder.img_dislike.setColorFilter(ContextCompat.getColor(context, R.color.colorlightred), PorterDuff.Mode.MULTIPLY);
        myViewHolder.img_like.setColorFilter(ContextCompat.getColor(context, R.color.colorlightred), PorterDuff.Mode.MULTIPLY);
        myViewHolder.img_double_like.setColorFilter(ContextCompat.getColor(context, R.color.colorlightred), PorterDuff.Mode.MULTIPLY);

        switch (status) {
            case -2:
                myViewHolder.img_double_dislike.setBackgroundResource(R.drawable.btn_layout_select_red);
                myViewHolder.img_double_dislike.setColorFilter(ContextCompat.getColor(context, R.color.colorWhite), PorterDuff.Mode.MULTIPLY);
                break;
            case -1:
                myViewHolder.img_dislike.setBackgroundResource(R.drawable.btn_layout_select_red_center);
                myViewHolder.img_dislike.setColorFilter(ContextCompat.getColor(context, R.color.colorWhite), PorterDuff.Mode.MULTIPLY);
                break;
            case 0:
                myViewHolder.img_none.setBackgroundResource(R.drawable.btn_layout_select_red_center);
                myViewHolder.img_none.setColorFilter(ContextCompat.getColor(context, R.color.colorWhite), PorterDuff.Mode.MULTIPLY);
                break;
            case 1:
                myViewHolder.img_like.setBackgroundResource(R.drawable.btn_layout_select_red_center);
                myViewHolder.img_like.setColorFilter(ContextCompat.getColor(context, R.color.colorWhite), PorterDuff.Mode.MULTIPLY);
                break;
            case 2:
                myViewHolder.img_double_like.setBackgroundResource(R.drawable.btn_layout_select_red_right);
                myViewHolder.img_double_like.setColorFilter(ContextCompat.getColor(context, R.color.colorWhite), PorterDuff.Mode.MULTIPLY);
                break;
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txv_title;
        ImageView img_double_dislike,img_like,img_none,img_dislike,img_double_like;
        EditText edit_score;
        public MyViewHolder(View view) {
            super(view);
            txv_title = view.findViewById(R.id.txv_title);
            img_double_dislike = view.findViewById(R.id.img_double_dislike);
            img_like = view.findViewById(R.id.img_like);
            img_none = view.findViewById(R.id.img_none);
            img_dislike = view.findViewById(R.id.img_dislike);
            img_double_like = view.findViewById(R.id.img_double_like);
            edit_score = view.findViewById(R.id.edit_score);
            edit_score.setVisibility(View.VISIBLE);
        }
    }


    public ScorecarddetailAdapter(Context context) {
        this.context = context;
    }

    public void setDate(List<ScoreOptionModel> moveResults) {
        scoreOptionModels = moveResults;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_scorecard, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final ScoreOptionModel scoreOptionModel = scoreOptionModels.get(position);
        holder.txv_title.setText(scoreOptionModel.getOptionName());
        pos = position;
        status = scoreOptionModel.getScore();
        selectItem(holder);
        holder.edit_score.setText(scoreOptionModel.getNote());

        holder.edit_score.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                scoreOptionModels.get(position).setNote(holder.edit_score.getText().toString());
            }
        });
        holder.img_double_dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = -2;
                selectItem(holder);
            }
        });
        holder.img_dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = -1;
                selectItem(holder);
            }
        });
        holder.img_none.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = -0;
                selectItem(holder);
            }
        });
        holder.img_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = 1;
                selectItem(holder);
            }
        });
        holder.img_double_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = 2;
                selectItem(holder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return scoreOptionModels.size();
    }
}