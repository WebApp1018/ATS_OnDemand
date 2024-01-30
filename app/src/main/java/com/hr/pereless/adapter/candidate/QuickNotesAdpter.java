package com.hr.pereless.adapter.candidate;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hr.pereless.R;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.model.candidate.QuicknoteModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class QuickNotesAdpter extends RecyclerView.Adapter<QuickNotesAdpter.MyViewHolder> {


    private List<QuicknoteModel> quicknoteListList = new ArrayList<>();

    Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_notestitle;
        public TextView txv_content,txt_time;
        public MyViewHolder(View view) {
            super(view);

            txt_notestitle = (TextView) view.findViewById(R.id.txt_notestitle);
            txv_content = (TextView) view.findViewById(R.id.lesstext);
            txt_time = view.findViewById(R.id.txt_time);

        }
    }


    public QuickNotesAdpter( Context context) {
        this.context = context;

    }

    public void setData(List<QuicknoteModel> quicknoteListList){
        this.quicknoteListList = quicknoteListList;
        notifyDataSetChanged();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_quiknotes, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final QuicknoteModel quicknoteModel = quicknoteListList.get(position);
        holder.txt_notestitle.setText(quicknoteModel.getHotbookname());
        holder.txv_content.setText(quicknoteModel.getQnotes());
        holder.txt_time.setText(Commons.TimelinedateTime(quicknoteModel.getNotedate()));
    }

    @Override
    public int getItemCount() {
        return quicknoteListList.size();
    }
}




