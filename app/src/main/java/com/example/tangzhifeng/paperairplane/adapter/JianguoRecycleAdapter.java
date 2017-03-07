package com.example.tangzhifeng.paperairplane.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tangzhifeng.paperairplane.R;
import com.example.tangzhifeng.paperairplane.data.jiandan.Jiandan;

import java.util.List;

/**
 * 作者: tangzhifeng on 2017/3/6.
 * 邮箱: tzfjobmail@gmail.com
 */

public class JianguoRecycleAdapter extends RecyclerView.Adapter<JianguoRecycleHolder> {
    List<Jiandan> mJiandanList;
    Context mContext;
    LayoutInflater mInflater;

    private OnItemClickListener clickListener;

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public static interface OnItemClickListener {
        void onClick(View view, String  uri);
    }

    public JianguoRecycleAdapter(List<Jiandan> jiandanList, Context context) {
        mJiandanList = jiandanList;
        mContext = context;
        mInflater=LayoutInflater.from(context);
    }

    @Override
    public JianguoRecycleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new JianguoRecycleHolder(mInflater.inflate(R.layout.jianguohomeitem,parent,false));
    }

    @Override
    public void onBindViewHolder(JianguoRecycleHolder holder, final int position) {
        holder.mSimpleDraweeView.setImageURI(mJiandanList.get(position).getUrl());
        holder.mSimpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(v,mJiandanList.get(position).getUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mJiandanList.size();
    }

    public void setJiandanList(List<Jiandan> jiandanList) {
        mJiandanList=jiandanList;

    }
}
