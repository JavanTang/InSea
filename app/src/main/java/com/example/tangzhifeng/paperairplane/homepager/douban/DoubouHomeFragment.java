package com.example.tangzhifeng.paperairplane.homepager.douban;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class DoubouHomeFragment extends Fragment implements DoubanHomeContract.View, BGARefreshLayout.BGARefreshLayoutDelegate {

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

    public static final int OPERATION_PULL_UP = 1;
    public static final int OPERATION_DROP_DOWN = 2;
    public static final int OPERATION_INIT=-1;

    int current_opearation = -1;


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
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (HttpUtil.isNetworkAvailable(getActivity())) {
                    unhappyImg.setVisibility(View.VISIBLE);
                    connectErrorHint.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override
    public void showDataObtainFailure() {

    }

    @Override
    public void updateAdapter(final List<Douban> doubanList) {
        switch (current_opearation){
            case OPERATION_INIT:
                mDoubanList=doubanList;
            case OPERATION_DROP_DOWN:
                mDoubanList=doubanList;
                break;
            case OPERATION_PULL_UP:
                //验证是不是有重复的数据,如果出现没有出现过的数据则将它存储

                for (Douban douban : doubanList) {
                    boolean make=false;
                    for (Douban douban1 : mDoubanList) {
                        if(douban.getDouban_id()==douban1.getDouban_id()){
                            make=true;
                            break;
                        }
                    }
                    if(!make){
                        mDoubanList.add(douban);
                    }
                }
                break;
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
                if (mDoubanList != null) {
                    intent.putExtra("douban_detail", mDoubanList.get(position).getDouban_uri());
                    intent.putExtra("douban_title", mDoubanList.get(position).getDouban_title());
                    intent.putExtra("douban_detail_img", mDoubanList.get(position).getDouban_icon());
                    startActivity(intent);
                } else {
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
        BGARefreshViewHolder refreshViewHold = new BGANormalRefreshViewHolder(getActivity(), true);
        doubanRefresh.setRefreshViewHolder(refreshViewHold);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        current_opearation = OPERATION_DROP_DOWN;
        if (mDoubanList != null) {
            mPresenter.dropRefreshEvent(mDoubanList);
        }
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        if (mDoubanList != null) {
            mPresenter.pullRefreshEvent(DoubanUtil.getSpecifiedDayBefore(mDoubanList.get(mDoubanList.size() - 1).getDouban_date()));
            current_opearation = OPERATION_PULL_UP;
        }
        return false;
    }
}
