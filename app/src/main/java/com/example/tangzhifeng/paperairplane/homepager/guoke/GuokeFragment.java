package com.example.tangzhifeng.paperairplane.homepager.guoke;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tangzhifeng.paperairplane.R;
import com.example.tangzhifeng.paperairplane.adapter.GuokeRecyclerAdapter;
import com.example.tangzhifeng.paperairplane.data.guoke.GuoKe;
import com.example.tangzhifeng.paperairplane.util.HttpUtil;
import com.example.tangzhifeng.paperairplane.util.LoadNetUtil;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by WKL on 2017/2/17.
 */

public class GuokeFragment extends Fragment {
    @InjectView(R.id.recycle_id)
    RecyclerView recycleId;
    List<GuoKe> GuokeList;
    LoadNetUtil mLoadNetUtil;
    GuokeRecyclerAdapter mGuokeRecyclerAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.guokefragment, container, false);
        ButterKnife.inject(this, view);
        initView();
        return view;
    }

    private void initView() {
        mLoadNetUtil=new LoadNetUtil();
        //tzf start
        HttpUtil.sendHttpRequest(LoadNetUtil.GUOKE_URL, new HttpUtil.IHttpCallbackListenet() {
            @Override
            public void onFinish(String response) {
                GuokeList=LoadNetUtil.GetJsonData(response);
                mGuokeRecyclerAdapter.setGuoKelist(GuokeList);
                mGuokeRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Exception e) {

            }
        });

        //tzf end

        LoadNetUtil.MyAsyncTask mAsyncTask = new LoadNetUtil.MyAsyncTask();
        mAsyncTask.execute(mLoadNetUtil.GUOKE_URL);
        GuokeList = mAsyncTask.GuokeList1;
        Log.i("TAG", "initView: " + GuokeList.size());
        mGuokeRecyclerAdapter = new GuokeRecyclerAdapter(GuokeList);
        LinearLayoutManager LinearLayoutForRecy = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recycleId.setLayoutManager(LinearLayoutForRecy);
        mGuokeRecyclerAdapter.setOnItemClickListener(new GuokeRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), ""+GuokeList.get(position).getResult().get(position).getSummary(), Toast.LENGTH_SHORT).show();
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
}
