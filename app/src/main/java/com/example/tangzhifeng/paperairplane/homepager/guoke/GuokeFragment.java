package com.example.tangzhifeng.paperairplane.homepager.guoke;

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

import com.example.tangzhifeng.paperairplane.R;
import com.example.tangzhifeng.paperairplane.adapter.GuokeRecyclerAdapter;
import com.example.tangzhifeng.paperairplane.data.guoke.GuoKe;
import com.example.tangzhifeng.paperairplane.detailedpager.guoke.GuokeDetailActivity;
import com.example.tangzhifeng.paperairplane.util.HttpUtil;
import com.example.tangzhifeng.paperairplane.util.LoadNetUtil;
import com.example.tangzhifeng.paperairplane.util.RecycleItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;

/**
 * Created by WKL on 2017/2/17.
 */

public class GuokeFragment extends Fragment implements BGARefreshLayout.BGARefreshLayoutDelegate {
    @InjectView(R.id.recycle_id)
    RecyclerView recycleId;
    List<GuoKe> GuokeList;
    LoadNetUtil mLoadNetUtil;
    GuokeRecyclerAdapter mGuokeRecyclerAdapter;
    @InjectView(R.id.guoke_refresh)
    BGARefreshLayout guokeRefresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.guokefragment, container, false);
        ButterKnife.inject(this, view);
        initRefresh();
        initView();
        return view;
    }

    private void initRefresh() {
//        设置刷新与监听
        // 为BGARefreshLayout 设置代理
        guokeRefresh.setDelegate(this);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGARefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(getContext(), true);
        // 设置下拉刷新和上拉加载更多的风格
        guokeRefresh.setRefreshViewHolder(refreshViewHolder);
    }

    private void initView() {
//        mLoadNetUtil = new LoadNetUtil();
        //tzf start
        HttpUtil.sendHttpRequest(LoadNetUtil.GUOKE_URL, new HttpUtil.IHttpCallbackListenet() {
            @Override
            public void onFinish(final String response) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        GuokeList = LoadNetUtil.GetJsonData(response);
                        mGuokeRecyclerAdapter.setGuoKelist(GuokeList);
                        mGuokeRecyclerAdapter.notifyDataSetChanged();
                    }
                });

            }

            @Override
            public void onError(Exception e) {

            }
        });

        //tzf end

//        LoadNetUtil.MyAsyncTask mAsyncTask = new LoadNetUtil.MyAsyncTask();
//        mAsyncTask.execute(mLoadNetUtil.GUOKE_URL);
//        GuokeList = mAsyncTask.GuokeList1;
//        Log.i("TAG", "initView: " + GuokeList.size());
        GuokeList=new ArrayList<>();

        mGuokeRecyclerAdapter = new GuokeRecyclerAdapter(GuokeList,getActivity());

        LinearLayoutManager LinearLayoutForRecy =
            new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);

//        recycleId.setRecycledViewPool(new GuokeRecycledViewPool());
        recycleId.setLayoutManager(LinearLayoutForRecy);
        recycleId.addItemDecoration(
            new RecycleItemDecoration(getActivity(),
                LinearLayoutManager.VERTICAL,
                10, ContextCompat.getColor(getActivity(), R.color.mdtp_white)));

        mGuokeRecyclerAdapter.setOnItemClickListener(new GuokeRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent newIntent = new Intent(getActivity(), GuokeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("guoke",GuokeList.get(position));
                newIntent.putExtra("bundle",bundle);
                newIntent.putExtra("position",position);
                Log.i("wkl", "onItemClick: "+newIntent.getIntExtra("position",8)+GuokeList.get(position).getResult().get(0).getLink()

                );
                /*
                * Parcelable encountered IOException writing serializable object
                *抛出这个异常是因为传递的对象里面的对象也要实现Serializable接口
                * */
//                SimpleDraweeView draweeView = (SimpleDraweeView) view.findViewById(R.id.img_item_guoke);
//                ActivityOptionsCompat options = ActivityOptionsCompat
//                    .makeSceneTransitionAnimation(getActivity(), draweeView, "share");
//                getFragmentManager().beginTransaction().addSharedElement(draweeView,"share");
                startActivity(newIntent);
//                startActivity(newIntent, ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),draweeView,"share").toBundle());
            }
        });
        mGuokeRecyclerAdapter.setOnItemLongClickListener(new GuokeRecyclerAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        recycleId.setAdapter(mGuokeRecyclerAdapter);
        mGuokeRecyclerAdapter.notifyDataSetChanged();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    public void freshUI(){
//        guokeRefresh.endLoadingMore();
        guokeRefresh.endRefreshing();
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing( BGARefreshLayout refreshLayout) {
        Log.i("tzf", "onBGARefreshLayoutBeginRefreshing: 00000000000000000000000000000000000000000000000000000000000000000000000");
        HttpUtil.sendHttpRequest(LoadNetUtil.GUOKE_URL, new HttpUtil.IHttpCallbackListenet() {
            @Override
            public void onFinish(final String response) {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (GuokeList != null)
//                            清除原数据重新获取刷新的数据，不清除每刷新会add重复数据
                        GuokeList.clear();
                        GuokeList = LoadNetUtil.GetJsonData(response);
                        mGuokeRecyclerAdapter.setGuoKelist(GuokeList);
                        mGuokeRecyclerAdapter.notifyDataSetChanged();
                        freshUI();
                        }


                });
            }

            @Override
            public void onError(Exception e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        freshUI();
                    }
                });
            }
        });
        refreshLayout.endLoadingMore();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }
}
