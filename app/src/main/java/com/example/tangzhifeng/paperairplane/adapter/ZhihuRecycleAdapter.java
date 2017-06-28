package com.example.tangzhifeng.paperairplane.adapter;


import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tangzhifeng.paperairplane.R;
import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHuList;

import java.util.List;

/**
 * 作者: tangzhifeng on 2017/2/15.
 * 邮箱: tzfjobmail@gmail.com
 */

public class ZhihuRecycleAdapter extends RecyclerView.Adapter<ZhihuRecycleHolder> {

    List<ZhiHuList> mZhiHuLists;
    LayoutInflater mInflater;
    Context mContext;
    public void setZhiHuLists(List<ZhiHuList> zhiHuLists) {
        mZhiHuLists = zhiHuLists;
    }

    private OnItemClickListener clickListener;

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public static interface OnItemClickListener {
        void onClick(View view, int position);
    }


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
        return new ZhihuRecycleHolder(mInflater.inflate(R.layout.zhihulistitem, parent, false));
    }

    @Override
    public void onBindViewHolder(final ZhihuRecycleHolder holder, final int position) {
        // TODO: 2017/2/16 这里还可以优化
        int current = 0;
        //这里显示current序列为position
        for (ZhiHuList zhiHuList : mZhiHuLists) {
            for (final ZhiHuList.StoriesBean bean : zhiHuList.getStories()) {
                if (current == position) {
                    Uri uri = Uri.parse(bean.getImages().get(0));
//                    if(bean.isClick()){
//                        holder.mTextView.setTextColor(Color.rgb(98,98,98));
//                    }
                    holder.mImageView.setImageURI(uri);
                    holder.mTextView.setText(bean.getTitle());
                    holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            bean.setClick(1);
                            holder.mTextView.setTextColor(Color.rgb(98,98,98));

                            clickListener.onClick(v, bean.getId());
                        }
                    });

                }
                current++;
            }
        }
    }

    @Override
    public int getItemCount() {
        int sum = 0;
        try {
            if (mZhiHuLists.size() > 0)
                for (ZhiHuList zhiHuList : mZhiHuLists) {
                    if (zhiHuList != null || zhiHuList.getStories() != null)
                        sum += zhiHuList.getStories().size();
                }
        } catch (NullPointerException e) {
            // TODO: 2017/2/18 BUG
        }

        return sum;

    }


}
