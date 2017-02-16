package com.example.tangzhifeng.paperairplane.adapter;


import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.tangzhifeng.paperairplane.R;
import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHuList;

import java.util.List;

/**
 * 作者: tangzhifeng on 2017/2/15.
 * 邮箱: tzfjobmail@gmail.com
 */

public class ZhihuRecycleAdapter extends RecyclerView.Adapter<ZhihuRecycleHolder> {


    Context mContext;

    List<ZhiHuList> mZhiHuLists;
    LayoutInflater mInflater;

    public ZhihuRecycleAdapter(List<ZhiHuList> zhiHuLists, Context context) {
        mZhiHuLists = zhiHuLists;
        mContext=context;
        mInflater=LayoutInflater.from(context);
    }

    public List<ZhiHuList> getZhiHuLists() {
        return mZhiHuLists;
    }

    public void setZhiHuLists(List<ZhiHuList> zhiHuLists) {
        mZhiHuLists = zhiHuLists;
    }

    @Override
    public ZhihuRecycleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ZhihuRecycleHolder(mInflater.inflate(R.layout.zhihulistitem,parent,false));
    }

    @Override
    public void onBindViewHolder(ZhihuRecycleHolder holder, int position) {


        ZhiHuList.StoriesBean storiesBean=mZhiHuLists.get(0).getStories().get(position);
        Uri uri=Uri.parse(storiesBean.getImages().get(0));
        holder.mImageView.setImageURI(uri);
        holder.mTextView.setText(storiesBean.getTitle());
    }

    @Override
    public int getItemCount() {
        return mZhiHuLists.get(0).getStories().size();
    }
}
