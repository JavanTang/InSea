package com.example.tangzhifeng.paperairplane.homepager.guoke;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Administrator on 2017/2/23.
 */

public class GuokeRecycledViewPool extends RecyclerView.RecycledViewPool {
    public GuokeRecycledViewPool() {
        super();
    }
    @Override
    public void setMaxRecycledViews(int viewType, int max) {
        super.setMaxRecycledViews(viewType, max);
    }

    @Override
    public int getRecycledViewCount(int viewType) {
        return super.getRecycledViewCount(viewType);
    }

    @Override
    public RecyclerView.ViewHolder getRecycledView(int viewType) {
        return super.getRecycledView(viewType);
    }

    @Override
    public void putRecycledView(RecyclerView.ViewHolder scrap) {
        super.putRecycledView(scrap);
    }
}
