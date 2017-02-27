package com.example.tangzhifeng.paperairplane.homepager.douban;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tangzhifeng.paperairplane.data.douban.Douban;

import java.util.List;

/**
 * 作者: tangzhifeng on 2017/2/21.
 * 邮箱: tzfjobmail@gmail.com
 */

public class DoubouHomeFragment extends Fragment implements DoubanHomeContract.View{

    DoubanHomeContract.Presenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=null;

        initViews(view);

        return view;
    }

    @Override
    public void showNetwordNotAvailable() {

    }

    @Override
    public void showDataObtainFailure() {

    }

    @Override
    public void updateAdapter(List<Douban> doubanList) {

    }


    @Override
    public void stopRefreshView() {

    }

    @Override
    public void setPresenter(DoubanHomeContract.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void initViews(View view) {

    }
}
