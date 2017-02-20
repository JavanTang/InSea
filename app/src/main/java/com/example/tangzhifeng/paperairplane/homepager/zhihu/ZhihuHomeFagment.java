package com.example.tangzhifeng.paperairplane.homepager.zhihu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tangzhifeng.paperairplane.R;
import com.example.tangzhifeng.paperairplane.adapter.ZhihuRecycleAdapter;
import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHuList;
import com.example.tangzhifeng.paperairplane.data.zhihu.source.ZHihuDataRepository;
import com.example.tangzhifeng.paperairplane.data.zhihu.source.local.ZHihuLocalDataSource;
import com.example.tangzhifeng.paperairplane.data.zhihu.source.remote.ZhihuRemoteDataSource;
import com.example.tangzhifeng.paperairplane.util.HttpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;

/**
 * 作者: tangzhifeng on 2017/2/15.
 * 邮箱: tzfjobmail@gmail.com
 */

public class ZhihuHomeFagment extends Fragment implements ZhiHuHomepagerContract.View, BGARefreshLayout.BGARefreshLayoutDelegate {

    @InjectView(R.id.zhihuList)
    RecyclerView mZhihuList;
    ZhihuRecycleAdapter mZhihuRecycleAdapter;
    ZhiHuHomepagerContract.Presenter mPresenter;
    List<ZhiHuList> mZhiHuLists;
    @InjectView(R.id.rl_modulename_refresh)
    BGARefreshLayout mRefreshLayout;
    ZhiHuHomePresenter mRresenter;
    Bundle mBundle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRresenter = new ZhiHuHomePresenter(this, new ZHihuDataRepository(ZhihuRemoteDataSource.getInstance(getContext()), ZHihuLocalDataSource.
                getInstance(getContext())));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        ArrayList list=new ArrayList();
//        list.add(mZhiHuLists);
//        outState.putParcelableArrayList("list",list);
    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        if(savedInstanceState!=null){
//            mZhiHuLists= (List<ZhiHuList>) savedInstanceState.getParcelableArrayList("list").get(0);
//        }
        init();
    }

    private void init() {
        initRefreshLayout();
        mZhiHuLists = new ArrayList<>();
        mZhiHuLists.add(new ZhiHuList());
        mZhihuRecycleAdapter = new ZhihuRecycleAdapter(mZhiHuLists, getContext());
        mZhihuRecycleAdapter.setClickListener(new ZhihuRecycleAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int id) {
                mPresenter.ClickZhihuItem(id + "", getContext());
            }
        });
        mZhihuList.setLayoutManager(new LinearLayoutManager(getContext()));
        mZhihuList.setAdapter(mZhihuRecycleAdapter);
        mPresenter.start();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.zhihulistfragment, container, false);
        ButterKnife.inject(this, view);
        initViews(view);
        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void showDropDownRefresh() {
        mRefreshLayout.beginRefreshing();
    }

    @Override
    public void showZhiHuList(final List<ZhiHuList> zhiHuListArrayList) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mZhiHuLists = zhiHuListArrayList;
                mZhihuRecycleAdapter.setZhiHuLists(mZhiHuLists);
                mZhihuRecycleAdapter.notifyDataSetChanged();
            }
        });

    }


    @Override
    public void showNetwordNotAvailable() {
        Toast.makeText(getContext(), "网络不可用!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refreshUI() {
        mZhihuRecycleAdapter.notifyDataSetChanged();
    }

    @Override
    public void stopPullToRefresh() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.endRefreshing();
                refreshUI();
            }
        });
    }

    @Override
    public void stopDropToRefresh() {
        mRefreshLayout.endLoadingMore();
    }

    @Override
    public void showZhihuListNotUpdate() {
        Toast.makeText(getContext(), "知乎日报不需要更新!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(ZhiHuHomepagerContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void initViews(View view) {


    }


    private void initRefreshLayout() {
        // 为BGARefreshLayout 设置代理
        mRefreshLayout.setDelegate(this);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGARefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(getContext(), true);
        // 设置下拉刷新和上拉加载更多的风格
        mRefreshLayout.setRefreshViewHolder(refreshViewHolder);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mPresenter.dropDownRefresh(mZhiHuLists, mZhihuRecycleAdapter);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mZhiHuLists=mZhihuRecycleAdapter.getZhiHuLists();
        if (HttpUtil.isNetworkAvailable(getContext())) {
            mPresenter.pullToRefresh(mZhiHuLists, mZhihuRecycleAdapter);
            return true;
        } else {
            return false;
        }

    }

}
