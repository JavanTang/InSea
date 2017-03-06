package com.example.tangzhifeng.paperairplane.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.tangzhifeng.paperairplane.R;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 作者: tangzhifeng on 2017/3/6.
 * 邮箱: tzfjobmail@gmail.com
 */

public class JianguoRecycleHolder extends RecyclerView.ViewHolder{
    SimpleDraweeView mSimpleDraweeView;
    public JianguoRecycleHolder(View itemView) {
        super(itemView);
        mSimpleDraweeView= (SimpleDraweeView) itemView.findViewById(R.id.jianguoitemImg);
    }
}
