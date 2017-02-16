package com.example.tangzhifeng.paperairplane.homepager.zhihu;

import com.example.tangzhifeng.paperairplane.BasePresenter;
import com.example.tangzhifeng.paperairplane.BaseView;
import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHu;
import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHuList;

import java.util.Date;
import java.util.List;

/**
 * 作者: tangzhifeng on 2017/2/15.
 * 邮箱: tzfjobmail@gmail.com
 */

public interface ZhiHuHomepagerContract {

    interface Presenter extends BasePresenter {

        /**
         * 加载zhiHu的详细内容
         * @param zhiHu
         */
        void loadDetailed(ZhiHu zhiHu);

        void obtainList(Date time);

        void addZHiHuList(List<ZhiHuList> zhiHuListArrayList,ZhiHuList zhiHuList);

        void addZHiHuList(ZhiHuList zhiHuList);
    }
    interface View extends BaseView<Presenter>{
        void showZhiHuList(List<ZhiHuList> zhiHuListArrayList);
        void showNetwordNotAvailable();
        void refreshUI();
    }

}
