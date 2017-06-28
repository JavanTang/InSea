package com.example.tangzhifeng.paperairplane.detailedpager.zhihu;

import android.content.Context;
import android.content.res.Configuration;

import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHu;
import com.example.tangzhifeng.paperairplane.data.zhihu.source.ZHihuDataRepository;
import com.example.tangzhifeng.paperairplane.data.zhihu.source.ZhihuDateSource;

/**
 * 作者: tangzhifeng on 2017/2/16.
 * 邮箱: tzfjobmail@gmail.com
 */

public class ZhihuDetailedPresenter implements ZhihuDetailedContract.Presenter {

    ZhihuDetailedContract.View mView;

    ZHihuDataRepository mZhihuDateRepository;

    Context mContext;

    public ZhihuDetailedPresenter(ZhihuDetailedContract.View view, ZHihuDataRepository zhihuDateRepository, Context context) {
        mView = view;
        mZhihuDateRepository = zhihuDateRepository;
        mContext = context;
        mView.setPresenter(this);
    }

    private String convertZhihuContent(String preResult) {

        preResult = preResult.replace("<div class=\"img-place-holder\">", "");
        preResult = preResult.replace("<div class=\"headline\">", "");

        String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/zhihu_daily.css\" type=\"text/css\">";
        String theme = "<body className=\"\" onload=\"onLoaded()\">";
        if ((mContext.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK)
                == Configuration.UI_MODE_NIGHT_YES) {
            theme = "<body className=\"\" onload=\"onLoaded()\" class=\"night\">";
        }
        preResult = new StringBuilder()
                .append("<!DOCTYPE html>\n")
                .append("<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\">\n")
                .append("<head>\n")
                .append("\t<meta charset=\"utf-8\" />")
                .append(css)
                .append("\n</head>\n")
                .append(theme)
                .append(preResult)
                .append("</body></html>").toString();
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
            public void onZhiHuObtainFailure() {

            }
        });
    }

    @Override
    public void start() {

    }
}
