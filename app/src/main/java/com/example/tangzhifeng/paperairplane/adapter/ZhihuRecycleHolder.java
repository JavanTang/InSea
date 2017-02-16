package com.example.tangzhifeng.paperairplane.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import android.widget.TextView;

import com.example.tangzhifeng.paperairplane.R;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 作者: tangzhifeng on 2017/2/15.
 * 邮箱: tzfjobmail@gmail.com
 */

public class ZhihuRecycleHolder extends RecyclerView.ViewHolder{

    SimpleDraweeView mImageView;
    TextView mTextView;


    public ZhihuRecycleHolder(View itemView) {
        super(itemView);
        mImageView= (SimpleDraweeView) itemView.findViewById(R.id.zhihulistImg);
        mTextView= (TextView) itemView.findViewById(R.id.zhihulistTv);

    }
}
