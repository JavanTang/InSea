package com.example.tangzhifeng.paperairplane.detailedpager.zhihu;

import com.example.tangzhifeng.paperairplane.BasePresenter;
import com.example.tangzhifeng.paperairplane.BaseView;
import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHu;

/**
 * 作者: tangzhifeng on 2017/2/16.
 * 邮箱: tzfjobmail@gmail.com
 */

public interface ZhihuDetailedContract   {
    interface Presenter extends BasePresenter{
        //返回
        void backZhihuListFragment();
        //分享
        void share(String info);
        //获取数据
        void loadZhihu(String id);

    }
    interface View extends BaseView<Presenter>{
        //显示标题
        void showTitile(String title);
        //显示正文
        void showContent(String body);

        //显示标题图片
        void showTitlePicture(String uri);

        void showContent(ZhiHu zhiHu);

    }
}
