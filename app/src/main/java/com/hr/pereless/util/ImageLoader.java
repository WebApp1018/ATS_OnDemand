package com.hr.pereless.util;

import android.content.Context;
import android.net.Uri;
import android.widget.LinearLayout;

import com.hr.pereless.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ImageLoader {

    private int mItemWidth;
    private int mItemHeight;
    private int mItemBorderWidth;
    private int mMaxItemCount;
    private float mOffset;
    private int mBorderColor;
    private Context mContext;
    private LinearLayout mParentLayout;
    private LinearLayout default_mParentLayout;
    public ImageLoader(int mItemWidth, int mItemHeight, int mItemBorderWidth, int mMaxItemCount, float mOffset, int mBorderColor, Context mContext, LinearLayout parentLayout) {
        this.mItemWidth = mItemWidth;
        this.mItemHeight = mItemHeight;
        this.mItemBorderWidth = mItemBorderWidth;
        this.mMaxItemCount = mMaxItemCount;
        this.mOffset = mOffset;
        this.mBorderColor = mBorderColor;
        this.mContext = mContext;
        this.mParentLayout = parentLayout;
        this.default_mParentLayout = parentLayout;
    }

    public void setThumbnailUrl(ArrayList<String> imageUrlList, boolean removeOnSwipe) {
        mParentLayout.removeAllViews();
        mParentLayout = default_mParentLayout;
        if (imageUrlList != null && mContext != null) {
            for (int i = 0; i < imageUrlList.size(); i++) {
                final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.mItemWidth, this.mItemHeight);
                if (i > 0) {
                    layoutParams.setMargins(Math.round(mItemWidth * mOffset) * -1, 0, 0, 0);
                }
                final CircleImageView circleImageView = new CircleImageView(mContext);
                circleImageView.setBorderWidth(mItemBorderWidth);
                circleImageView.setBorderColor(mBorderColor);
                circleImageView.setLayoutParams(layoutParams);
                final String url = imageUrlList.get(i);
                if (i <= this.mMaxItemCount) {
                    Picasso.get().load(url).placeholder(R.drawable.profile_pic).into(circleImageView);
                    mParentLayout.addView(circleImageView);
                    if (removeOnSwipe)
                        circleImageView.setOnTouchListener(new OnSwipeTouchListener(mContext, mItemWidth));
                }
            }
        }
    }

    public void setThumbnailDrawableRes(ArrayList<Integer> imageDrawableResList, boolean removeOnSwipe) {
        mParentLayout.removeAllViews();
        mParentLayout = default_mParentLayout;
        if (imageDrawableResList != null && mContext != null) {
            for (int i = 0; i < imageDrawableResList.size(); i++) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.mItemWidth, this.mItemHeight);
                if (i > 0) {
                    layoutParams.setMargins(Math.round(mItemWidth * mOffset) * -1, 0, 0, 0);
                }
                CircleImageView circleImageView = new CircleImageView(mContext);
                circleImageView.setBorderWidth(mItemBorderWidth);
                circleImageView.setBorderColor(mBorderColor);
                circleImageView.setLayoutParams(layoutParams);
                if (i <= this.mMaxItemCount) {
                    Picasso.get().load(imageDrawableResList.get(i)).placeholder(R.drawable.profile_pic).into(circleImageView);
                    mParentLayout.addView(circleImageView);
                    if (removeOnSwipe)
                        circleImageView.setOnTouchListener(new OnSwipeTouchListener(mContext, mItemWidth));
                }
            }
        }
    }

    public void setThumbnailFile(ArrayList<File> imageFileList, boolean removeOnSwipe) {
        mParentLayout.removeAllViews();
        mParentLayout = default_mParentLayout;
        if (imageFileList != null && mContext != null) {
            for (int i = 0; i < imageFileList.size(); i++) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.mItemWidth, this.mItemHeight);
                if (i > 0) {
                    layoutParams.setMargins(Math.round(mItemWidth * mOffset) * -1, 0, 0, 0);
                }
                CircleImageView circleImageView = new CircleImageView(mContext);
                circleImageView.setBorderWidth(mItemBorderWidth);
                circleImageView.setBorderColor(mBorderColor);
                circleImageView.setLayoutParams(layoutParams);
                if (i <= this.mMaxItemCount) {
                    if (imageFileList.get(i) != null) {
                        Picasso.get().load(imageFileList.get(i)).placeholder(R.drawable.profile_pic).into(circleImageView);
                        mParentLayout.addView(circleImageView);
                        if (removeOnSwipe)
                            circleImageView.setOnTouchListener(new OnSwipeTouchListener(mContext, mItemWidth));
                    }
                }
            }
        }
    }

    public void setThumbnailUri(ArrayList<Uri> imageFileList, boolean removeOnSwipe) {
        mParentLayout.removeAllViews();
        mParentLayout = default_mParentLayout;
        if (imageFileList != null && mContext != null) {
            for (int i = 0; i < imageFileList.size(); i++) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.mItemWidth, this.mItemHeight);
                if (i > 0) {
                    layoutParams.setMargins(Math.round(mItemWidth * mOffset) * -1, 0, 0, 0);
                }
                CircleImageView circleImageView = new CircleImageView(mContext);
                circleImageView.setBorderWidth(mItemBorderWidth);
                circleImageView.setBorderColor(mBorderColor);
                circleImageView.setLayoutParams(layoutParams);
                if (i <= this.mMaxItemCount) {
                    if (imageFileList.get(i) != null) {
                        Picasso.get().load(imageFileList.get(i)).placeholder(R.drawable.profile_pic).into(circleImageView);
                        mParentLayout.addView(circleImageView);
                        if (removeOnSwipe)
                            circleImageView.setOnTouchListener(new OnSwipeTouchListener(mContext, mItemWidth));
                    }
                }
            }
        }
    }
}
