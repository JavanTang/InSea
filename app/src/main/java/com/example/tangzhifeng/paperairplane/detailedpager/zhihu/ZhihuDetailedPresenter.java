package com.example.tangzhifeng.paperairplane.detailedpager.zhihu;

import android.content.Context;
import android.content.res.Configuration;

import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHu;
import com.example.tangzhifeng.paperairplane.data.zhihu.source.ZhihuDateRepository;
import com.example.tangzhifeng.paperairplane.data.zhihu.source.ZhihuDateSource;

/**
 * 作者: tangzhifeng on 2017/2/16.
 * 邮箱: tzfjobmail@gmail.com
 */

public class ZhihuDetailedPresenter implements ZhihuDetailedContract.Presenter {

    ZhihuDetailedContract.View mView;

    ZhihuDateRepository mZhihuDateRepository;

    Context mContext;

    public ZhihuDetailedPresenter(ZhihuDetailedContract.View view, ZhihuDateRepository zhihuDateRepository, Context context) {
        mView = view;
        mZhihuDateRepository = zhihuDateRepository;
        mContext = context;
        mView.setPresenter(this);
    }

    public ZhihuDetailedPresenter(ZhihuDetailedContract.View view, ZhihuDateRepository zhihuDateRepository) {
        mView = view;
        mZhihuDateRepository = zhihuDateRepository;
        mView.setPresenter(this);
    }



    private String convertZhihuContent(String preResult) {

        preResult = preResult.replace("<div class=\"img-place-holder\">", "");
        preResult = preResult.replace("<div class=\"headline\">", "");

        // 在api中，css的地址是以一个数组的形式给出，这里需要设置
        // in fact,in api,css addresses are given as an array
        // api中还有js的部分，这里不再解析js
        // javascript is included,but here I don't use it
        // 不再选择加载网络css，而是加载本地assets文件夹中的css
        // use the css file from local assets folder,not from network
//        Log.i(TAG, "convertZhihuContent: ---------1-----------");
        String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/zhihu_daily.css\" type=\"text/css\">";

//        Log.i(TAG, "convertZhihuContent: ---------2-----------"+css);

        // 根据主题的不同确定不同的加载内容
        // load content judging by different theme
        String theme = "<body className=\"\" onload=\"onLoaded()\">";
        if ((mContext.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK)
                == Configuration.UI_MODE_NIGHT_YES){
            theme = "<body className=\"\" onload=\"onLoaded()\" class=\"night\">";
        }
        preResult=new StringBuilder()
                .append("<!DOCTYPE html>\n")
                .append("<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\">\n")
                .append("<head>\n")
                .append("\t<meta charset=\"utf-8\" />")
                .append(css)
                .append("\n</head>\n")
                .append(theme)
                .append(preResult)
                .append("</body></html>").toString();
//        Log.i(TAG, "convertZhihuContent: ###################\n"+preResult);
        return preResult;
    }

    @Override
    public void backZhihuListFragment() {

    }

    @Override
    public void share(String info) {

    }

    @Override
    public void loadZhihu(String id) {
        mZhihuDateRepository.getZhihu(id, new ZhihuDateSource.GetZhiHuCallback() {
            @Override
            public void onZhiHuLoaded(ZhiHu zhiHu) {
                zhiHu.setBody(convertZhihuContent(zhiHu.getBody()));
                mView.showContent(zhiHu);
            }

            @Override
            public void onZhiHuNotAvailable() {

            }
        });
    }

    @Override
    public void start() {

    }
}
