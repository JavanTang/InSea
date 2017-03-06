package com.example.tangzhifeng.paperairplane.homepager.jiandanmeizi;

import com.example.tangzhifeng.paperairplane.data.jiandan.Jiandan;
import com.example.tangzhifeng.paperairplane.data.jiandan.source.JiandanDateRepository;
import com.example.tangzhifeng.paperairplane.data.jiandan.source.JiandanDateSource;
import com.example.tangzhifeng.paperairplane.data.jiandan.source.remote.JiandanDateRemoteSource;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: tangzhifeng on 2017/3/6.
 * 邮箱: tzfjobmail@gmail.com
 */

public class JiandanPresenter implements JiandanContract.Presenter {
    JiandanDateRepository mJiandanDateRepository;
    JiandanContract.View mView;
    List<Jiandan> mJiandanList;
    public JiandanPresenter( JiandanContract.View view) {
        mJiandanDateRepository = new JiandanDateRepository(JiandanDateRemoteSource.getInstance());
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void dropRefreshEvent() {
        mJiandanList=new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            mJiandanDateRepository.getJiandan(new JiandanDateSource.JiandanCallback() {
                @Override
                public void onFailure(Exception e) {
                    mView.showDataObtainFailure();

                }

                @Override
                public void onSuccess(Jiandan jiandan) {
                    mJiandanList.add(jiandan);
                    if(mJiandanList.size()==18){
                        mView.updateAdapter(mJiandanList);
                        mView.stopRefreshView();
                    }
                }
            });
        }

    }

    @Override
    public void pullRefreshEvent() {
        dropRefreshEvent();

    }


    @Override
    public void start() {
        dropRefreshEvent();
    }
}
