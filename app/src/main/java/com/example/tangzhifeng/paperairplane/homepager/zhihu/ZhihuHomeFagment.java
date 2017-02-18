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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.zhihulistfragment, container, false);
        ButterKnife.inject(this, view);
        mRresenter= new ZhiHuHomePresenter(this, new ZHihuDataRepository(ZhihuRemoteDataSource.getInstance(getContext()),ZHihuLocalDataSource.
                getInstance(getContext()) ));
        initViews(view);
        mZhihuRecycleAdapter.setClickListener(new ZhihuRecycleAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int id) {
                mPresenter.ClickZhihuItem(id + "", getContext());
            }
        });
        return view;

    }


    @Override
    public void showDropDownRefresh() {

    }

    @Override
    public void showZhiHuList(final List<ZhiHuList> zhiHuListArrayList) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mZhiHuLists = zhiHuListArrayList;
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
                if (mRefreshLayout != null) {
                    mRefreshLayout.endRefreshing();

                }
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
        initRefreshLayout();

        mZhiHuLists = new ArrayList<>();
//        String str = "{\"date\":\"20170215\",\"stories\":[{\"images\":[\"http:\\/\\/pic1.zhimg.com\\/73d976c271b9ff157c5d541fccb44718.jpg\"],\"type\":0,\"id\":9221956,\"ga_prefix\":\"021522\",\"title\":\"小事 · 你去酒店干嘛\"},{\"images\":[\"http:\\/\\/pic2.zhimg.com\\/cd6604494873b3b0f2ac7614438000e5.jpg\"],\"type\":0,\"id\":9224016,\"ga_prefix\":\"021521\",\"title\":\"作为一部诉说离婚的剧，《最完美的离婚》几乎甜到忧伤\"},{\"images\":[\"http:\\/\\/pic4.zhimg.com\\/029c2b9ea5b8cea562dad2300e8874eb.jpg\"],\"type\":0,\"id\":9224602,\"ga_prefix\":\"021520\",\"title\":\"当我们说「哲学可以指导科学」时，我们到底在说什么\"},{\"title\":\"电脑上玩儿游戏，为什么是「WASD」？\",\"ga_prefix\":\"021519\",\"images\":[\"http:\\/\\/pic1.zhimg.com\\/5b5c0e4ad8eb36d2b1ae06ea9c120ee8.jpg\"],\"multipic\":true,\"type\":0,\"id\":9224446},{\"images\":[\"http:\\/\\/pic4.zhimg.com\\/10559b37341385639ae2c710bf00deeb.jpg\"],\"type\":0,\"id\":9224580,\"ga_prefix\":\"021518\",\"title\":\"现在试水打车业务，美团挑了一个好时机\"},{\"images\":[\"http:\\/\\/pic1.zhimg.com\\/2e45d0c0a93a5032a0b714b0b572b8f4.jpg\"],\"type\":0,\"id\":9222342,\"ga_prefix\":\"021517\",\"title\":\"赏花作诗之后，苏轼心想：「牡丹怎么做才好吃呢？」\"},{\"images\":[\"http:\\/\\/pic3.zhimg.com\\/a9e81a24d66bbeb35812e36c84904576.jpg\"],\"type\":0,\"id\":9224249,\"ga_prefix\":\"021516\",\"title\":\"做好防晒，你得先搞清楚防的是什么\"},{\"images\":[\"http:\\/\\/pic4.zhimg.com\\/cc43d1b6bd9d5dd94200be8b70282fa3.jpg\"],\"type\":0,\"id\":9224239,\"ga_prefix\":\"021515\",\"title\":\"22 年了，终于又有一家信托公司上市了\"},{\"images\":[\"http:\\/\\/pic2.zhimg.com\\/7daa90247a75091942b7b772199f1fa1.jpg\"],\"type\":0,\"id\":9224139,\"ga_prefix\":\"021514\",\"title\":\"当我沉迷在工作里，所有事都如行云流水一般发生\"},{\"images\":[\"http:\\/\\/pic1.zhimg.com\\/f8c28115d7afce5aacbd7deebba3b540.jpg\"],\"type\":0,\"id\":9224154,\"ga_prefix\":\"021513\",\"title\":\"看清楚，写了这些字的湿疹膏不要买\"},{\"images\":[\"http:\\/\\/pic1.zhimg.com\\/cf3c6cd45b2ff87891305d5a9e0f88e4.jpg\"],\"type\":0,\"id\":9223574,\"ga_prefix\":\"021512\",\"title\":\"大误 · 我是世界的中心\"},{\"images\":[\"http:\\/\\/pic3.zhimg.com\\/ccd4e6783a4a64e74230bc1c4728c42e.jpg\"],\"type\":0,\"id\":9222546,\"ga_prefix\":\"021511\",\"title\":\"大学会终身雇聘任教授，企业为什么不终身雇佣好员工呢？\"},{\"images\":[\"http:\\/\\/pic4.zhimg.com\\/ccd9e65d4723b79475649eabbd966a5b.jpg\"],\"type\":0,\"id\":9198798,\"ga_prefix\":\"021510\",\"title\":\"只有「擅长」是不够的，对职场新人来说探索自我更重要\"},{\"images\":[\"http:\\/\\/pic2.zhimg.com\\/295d50cf0353b1db26614b64826ce5c9.jpg\"],\"type\":0,\"id\":9222998,\"ga_prefix\":\"021509\",\"title\":\"情人节过去了，来用科学技术预测下你们的爱情能不能长久\"},{\"images\":[\"http:\\/\\/pic4.zhimg.com\\/b13302ecc63b3c99356f99f4ed01c237.jpg\"],\"type\":0,\"id\":9221188,\"ga_prefix\":\"021508\",\"title\":\"加入电竞行业并不一定要当选手，来听听职业俱乐部怎么说\"},{\"title\":\"其实，柚子茶和柚子没半毛钱关系\",\"ga_prefix\":\"021507\",\"images\":[\"http:\\/\\/pic3.zhimg.com\\/228d836547ccba5d0fbeae6755704bd6.jpg\"],\"multipic\":true,\"type\":0,\"id\":9223117},{\"images\":[\"http:\\/\\/pic3.zhimg.com\\/422d1535e3ecd6c4bed36ab366f8556e.jpg\"],\"type\":0,\"id\":9222989,\"ga_prefix\":\"021507\",\"title\":\"看完《爱乐之城》，回想起自己当初为什么会爱上电影\"},{\"images\":[\"http:\\/\\/pic2.zhimg.com\\/74eddd8c32dfaf063c06c34da6b7ce21.jpg\"],\"type\":0,\"id\":9222960,\"ga_prefix\":\"021507\",\"title\":\"去世后仍拿到五座格莱美，大卫 · 鲍伊如何影响了摇滚乐？\"},{\"images\":[\"http:\\/\\/pic4.zhimg.com\\/7bc5b680d1ab4f9dde3504409f756593.jpg\"],\"type\":0,\"id\":9218744,\"ga_prefix\":\"021506\",\"title\":\"瞎扯 · 如何正确地吐槽\"}]}";
//        Gson gson = new Gson();
//
//        ZhiHuList zhihu = new ZhiHuList();
//
//        zhihu = gson.fromJson(str, ZhiHuList.class);
//
//        mZhiHuLists.add(zhihu);
        mZhiHuLists.add(new ZhiHuList());
        mZhihuRecycleAdapter = new ZhihuRecycleAdapter(mZhiHuLists, getContext());
//        Log.i(TAG, "initViews:+++++++++++ " + mZhihuRecycleAdapter.getItemCount());
        mZhihuList.setAdapter(mZhihuRecycleAdapter);
        mZhihuList.setLayoutManager(new LinearLayoutManager(getContext()));

        mPresenter.start();

    }

    private void initRefreshLayout() {
        // 为BGARefreshLayout 设置代理
        mRefreshLayout.setDelegate(this);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGARefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(getContext(), true);
        // 设置下拉刷新和上拉加载更多的风格
        mRefreshLayout.setRefreshViewHolder(refreshViewHolder);
//        // 为了增加下拉刷新头部和加载更多的通用性，提供了以下可选配置选项  -------------START
//        // 设置正在加载更多时不显示加载更多控件
//        // mRefreshLayout.setIsShowLoadingMoreView(false);
//        // 设置正在加载更多时的文本
//        refreshViewHolder.setLoadingMoreText(loadingMoreText);
//        // 设置整个加载更多控件的背景颜色资源 id
//        refreshViewHolder.setLoadMoreBackgroundColorRes(loadMoreBackgroundColorRes);
//        // 设置整个加载更多控件的背景 drawable 资源 id
//        refreshViewHolder.setLoadMoreBackgroundDrawableRes(loadMoreBackgroundDrawableRes);
//        // 设置下拉刷新控件的背景颜色资源 id
//        refreshViewHolder.setRefreshViewBackgroundColorRes(refreshViewBackgroundColorRes);
//        // 设置下拉刷新控件的背景 drawable 资源 id
//        refreshViewHolder.setRefreshViewBackgroundDrawableRes(refreshViewBackgroundDrawableRes);
//        // 设置自定义头部视图（也可以不用设置）     参数1：自定义头部视图（例如广告位）， 参数2：上拉加载更多是否可用
//        mRefreshLayout.setCustomHeaderView(mBanner, false);
//        // 可选配置  -------------END
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mPresenter.dropDownRefresh(mZhiHuLists, mZhihuRecycleAdapter);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mPresenter.pullToRefresh(mZhiHuLists, mZhihuRecycleAdapter);
        return true;
    }

}
