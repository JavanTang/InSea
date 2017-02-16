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

    public void setZhiHuLists(List<ZhiHuList> zhiHuLists) {
        mZhiHuLists = zhiHuLists;
    }

    List<ZhiHuList> mZhiHuLists;
    LayoutInflater mInflater;






    public ZhihuRecycleAdapter(List<ZhiHuList> zhiHuLists, Context context) {
        mZhiHuLists = zhiHuLists;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }




    public List<ZhiHuList> getZhiHuLists() {
        return mZhiHuLists;
    }


    @Override
    public ZhihuRecycleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ZhihuRecycleHolder(mInflater.inflate(R.layout.zhihulistitem,parent,false));
    }

    @Override
    public void onBindViewHolder(ZhihuRecycleHolder holder, int position) {
        // TODO: 2017/2/16 这里还可以优化
        int current=0;
        for (ZhiHuList zhiHuList : mZhiHuLists) {
            for (ZhiHuList.StoriesBean bean : zhiHuList.getStories()) {
                if(current==position)
                {
                    Uri uri=Uri.parse(bean.getImages().get(0));
                    holder.mImageView.setImageURI(uri);
                    holder.mTextView.setText(bean.getTitle());
                }
                current++;

            }
        }
        

    }

    @Override
    public int getItemCount() {
        int sum=0;
        for (ZhiHuList zhiHuList : mZhiHuLists) {
            sum+=zhiHuList.getStories().size();
        }
        return sum;

    }


}
