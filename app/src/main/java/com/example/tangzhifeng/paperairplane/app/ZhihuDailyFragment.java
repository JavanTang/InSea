package com.example.tangzhifeng.paperairplane.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tangzhifeng.paperairplane.been.ZhihuDailyNews;
import com.example.tangzhifeng.paperairplane.interfaze.ZhihuDailyContract;

import java.util.List;

/**
 * Created by tangzhifeng on 2017/2/8.
 */

public class ZhihuDailyFragment extends Fragment implements ZhihuDailyContract.View{

    public static ZhihuDailyFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ZhihuDailyFragment fragment = new ZhihuDailyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return null;
    }

    @Override
    public void showError() {

    }

    @Override
    public void showLoading() {

    }

    String str;

    @Override
    public void stopLoading() {

    }

    @Override
    public void showResults(List<ZhihuDailyNews.StoriesBean> list) {

    }

    @Override
    public void showPickDialog() {

    }

    @Override
    public void setPresenter(ZhihuDailyContract.Presenter presenter) {

    }

    @Override
    public void initViews(View view) {

    }
}
