package com.example.tangzhifeng.paperairplane.homepager.douban;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tangzhifeng.paperairplane.R;
import com.example.tangzhifeng.paperairplane.adapter.DoubanRecycleAdapter;
import com.example.tangzhifeng.paperairplane.data.douban.Douban;
import com.example.tangzhifeng.paperairplane.detailedpager.douban.DoubanDetailsActivity;
import com.example.tangzhifeng.paperairplane.util.DoubanUtil;
import com.example.tangzhifeng.paperairplane.util.HttpUtil;
import com.example.tangzhifeng.paperairplane.util.RecycleItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;

/**
 * 作者: tangzhifeng on 2017/2/21.
 * 邮箱: tzfjobmail@gmail.com
 */

public class DoubouHomeFragment extends Fragment implements DoubanHomeContract.View,BGARefreshLayout.BGARefreshLayoutDelegate {

    DoubanHomeContract.Presenter mPresenter;

    @InjectView(R.id.recycle_douban)
    RecyclerView DoubanRecycle;
    @InjectView(R.id.douban_refresh)
    BGARefreshLayout doubanRefresh;
    DoubanRecycleAdapter DoubanAdapter;
    List<Douban> mDoubanList;
    @InjectView(R.id.unhappy_img)
    ImageView unhappyImg;
    @InjectView(R.id.connect_error_hint)
    TextView connectErrorHint;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.doubanfragment, container, false);
        ButterKnife.inject(this, view);
        initViews(view);
        mPresenter.start();
        return view;
    }

    @Override
    public void showNetwordNotAvailable() {
        if (HttpUtil.isNetworkAvailable(getActivity())) {
            unhappyImg.setVisibility(View.VISIBLE);
            connectErrorHint.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showDataObtainFailure() {

    }

    @Override
    public void updateAdapter(final List<Douban> doubanList) {
        if (mDoubanList == null){
            mDoubanList = doubanList;
        }
        Log.i("wkl", "updateAdapter: "+mDoubanList.size());
        if (mDoubanList != null || mDoubanList.get(mDoubanList.size()-1).getDouban_date()!=doubanList.get(doubanList.size()-1).getDouban_created_time()){
            for (int i = 0; i < doubanList.size(); i++) {
                mDoubanList.add(doubanList.get(i));
            }
            Log.i("wkl", "updateAdapter: "+mDoubanList.size());
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                DoubanAdapter.setDoubanList(mDoubanList);
                DoubanAdapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public void stopRefreshView() {
            doubanRefresh.endRefreshing();
    }

    @Override
    public void setPresenter(DoubanHomeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void initViews(View view) {
        initRefresh();
        mDoubanList = new ArrayList<>();
        DoubanAdapter = new DoubanRecycleAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()
            , LinearLayoutManager.VERTICAL, false);
        DoubanRecycle.setLayoutManager(linearLayoutManager);
        DoubanRecycle.addItemDecoration(
            new RecycleItemDecoration(getActivity(),
                LinearLayoutManager.VERTICAL,
                10, ContextCompat.getColor(getActivity(), R.color.mdtp_white)));
        DoubanAdapter.setOnItemClickListener(new DoubanRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), DoubanDetailsActivity.class);
                if (mDoubanList != null){
                    intent.putExtra("douban_detail",mDoubanList.get(position).getDouban_uri());
                    intent.putExtra("douban_title",mDoubanList.get(position).getDouban_title());
                    intent.putExtra("douban_detail_img",mDoubanList.get(position).getDouban_icon());
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getActivity(), "List没有数据", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        DoubanRecycle.setAdapter(DoubanAdapter);
    }

    private void initRefresh() {
        doubanRefresh.setDelegate(this);
        BGARefreshViewHolder refreshViewHold = new BGANormalRefreshViewHolder(getActivity(),true);
        // 为了增加下拉刷新头部和加载更多的通用性，提供了以下可选配置选项  -------------START
        // 设置正在加载更多时不显示加载更多控件
        //  mRefreshLayout.setIsShowLoadingMoreView(true);
        // 设置正在加载更多时的文本
        // refreshViewHolder.setLoadingMoreText("eee");
        // 设置整个加载更多控件的背景颜色资源id
        //refreshViewHolder.setLoadMoreBackgroundColorRes(R.color.srl_blue_bright);
        // 设置整个加载更多控件的背景drawable资源id
        //refreshViewHolder.setLoadMoreBackgroundDrawableRes(R.mipmap.ic_launcher);
        // 设置下拉刷新控件的背景颜色资源id
        //  refreshViewHolder.setRefreshViewBackgroundColorRes(R.color.colorPrimaryDark);
        // 设置下拉刷新控件的背景drawable资源id
        //  refreshViewHolder.setRefreshViewBackgroundDrawableRes(R.mipmap.bga_refresh_loading01);
        // 设置自定义头部视图（也可以不用设置）     参数1：自定义头部视图（例如广告位）， 参数2：上拉加载更多是否可用
        //    mRefreshLayout.setCustomHeaderView(mBanner, false);
        // 可选配置  -------------END
        doubanRefresh.setRefreshViewHolder(refreshViewHold);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {

                if (mDoubanList != null){
                    mPresenter.dropRefreshEvent(mDoubanList);
                }
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        if (mDoubanList != null){
            mPresenter.pullRefreshEvent(DoubanUtil.getSpecifiedDayBefore(mDoubanList.get(mDoubanList.size()-1).getDouban_date()));
        }
        return false;
    }
}
