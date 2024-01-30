package com.hr.pereless.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hr.pereless.R;
import com.hr.pereless.model.SlideModel;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;

public class SliderSplashAdapter extends
        SliderViewAdapter<SliderSplashAdapter.SliderAdapterVH> {

    private Context context;
    private ArrayList<SlideModel> mSliderItems = new ArrayList<>();

    public SliderSplashAdapter(Context context) {
        this.context = context;
    }

    public void renewItems(ArrayList<SlideModel> sliderItems) {
        this.mSliderItems = sliderItems;
        notifyDataSetChanged();
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {

        SlideModel sliderItem = mSliderItems.get(position);
        Glide.with(viewHolder.itemView)
                .load(sliderItem.getImv_pic())
                .fitCenter()
                .into(viewHolder.imageViewBackground);
        viewHolder.txt_title.setText(sliderItem.getTitle());
        viewHolder.txv_description.setText(sliderItem.getDescription());
    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mSliderItems.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        TextView txt_title,txv_description;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            txt_title = itemView.findViewById(R.id.txt_title);
            txv_description = itemView.findViewById(R.id.txv_description);
            this.itemView = itemView;
        }
    }

}
