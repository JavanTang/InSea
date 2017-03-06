package com.example.tangzhifeng.paperairplane.homepager.jiandanmeizi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tangzhifeng.paperairplane.R;
import com.example.tangzhifeng.paperairplane.adapter.JianguoRecycleAdapter;
import com.example.tangzhifeng.paperairplane.data.jiandan.Jiandan;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;

/**
 * 作者: tangzhifeng on 2017/3/6.
 * 邮箱: tzfjobmail@gmail.com
 */

public class JiandanFragment extends Fragment implements JiandanContract.View, BGARefreshLayout.BGARefreshLayoutDelegate {

    JiandanContract.Presenter mPresenter;
    JianguoRecycleAdapter mJianguoRecycleAdapter;
    List<Jiandan> mJiandanList;

    @InjectView(R.id.jianguohomeRecycle)
    RecyclerView mJianguohomeRecycle;
    @InjectView(R.id.jianguohomeFreshLayout)
    BGARefreshLayout mJianguohomeFreshLayout;

    public static final int OPERATION_PULL_UP = 1;
    public static final int OPERATION_DROP_DOWN = 2;
    public static final int OPERATION_INIT=-1;

    int current_opearation = -1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        current_opearation=OPERATION_INIT;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.jianguohomefragment, container, false);
        ButterKnife.inject(this, view);
        initViews(view);
        return view;
    }

    @Override
    public void showNetwordNotAvailable() {
        Toast.makeText(getContext(), "网络不可用!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDataObtainFailure() {
        Toast.makeText(getContext(), "数据获取失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateAdapter(List<Jiandan> doubanList) {
        switch (current_opearation){
            case OPERATION_DROP_DOWN:
                mJiandanList=doubanList;
                break;
            case OPERATION_INIT:
                mJiandanList=doubanList;
                break;
            case OPERATION_PULL_UP:
                for (Jiandan jiandan : doubanList) {
                    mJiandanList.add(jiandan);
                }
                break;
        }

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mJianguoRecycleAdapter.setJiandanList(mJiandanList);
                mJianguoRecycleAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void stopRefreshView() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mJianguohomeFreshLayout.endLoadingMore();
                mJianguohomeFreshLayout.endRefreshing();
            }
        });
    }

    @Override
    public void setPresenter(JiandanContract.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void initViews(View view) {
        initRefresh();
        mJiandanList=new ArrayList<>();
        mJianguoRecycleAdapter=new JianguoRecycleAdapter(mJiandanList,getContext());
        mJianguohomeRecycle.setAdapter(mJianguoRecycleAdapter);
        mJianguohomeRecycle.setLayoutManager(new GridLayoutManager(getContext(),3));
        mPresenter.start();
    }

    private void initRefresh() {
        mJianguohomeFreshLayout.setDelegate(this);
        BGARefreshViewHolder refreshViewHold = new BGANormalRefreshViewHolder(getActivity(), true);
        mJianguohomeFreshLayout.setRefreshViewHolder(refreshViewHold);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mPresenter.dropRefreshEvent();
        current_opearation=OPERATION_DROP_DOWN;
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mPresenter.pullRefreshEvent();
        current_opearation=OPERATION_PULL_UP;
        return false;
    }
}
