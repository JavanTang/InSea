package com.example.tangzhifeng.paperairplane.detailedpager.zhihu;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.tangzhifeng.paperairplane.R;
import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHu;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作者: tangzhifeng on 2017/2/16.
 * 邮箱: tzfjobmail@gmail.com
 */

@SuppressLint("ValidFragment")
public class ZhihuDetailedFragment extends Fragment implements ZhihuDetailedContract.View {

    ZhihuDetailedContract.Presenter mPresenter;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.image_view)
    ImageView mImageView;
    @InjectView(R.id.toolbar_layout)
    CollapsingToolbarLayout mToolbarLayout;
    @InjectView(R.id.app_bar)
    AppBarLayout mAppBar;
    @InjectView(R.id.web_view)
    WebView mWebView;

    @SuppressLint("ValidFragment")
    public ZhihuDetailedFragment(int current) {
        this.current = current;
    }

    int current;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detailszhihufragment, container, false);
        ButterKnife.inject(this, view);
        mPresenter.loadZhihu(current+"");
        return view;
    }

    @Override
    public void showTitile(String title) {
        mToolbar.setTitle(title);
    }

    @Override
    public void showContent(String body) {


    }



    @Override
    public void showTitlePicture(String uri) {

    }

    @Override
    public void showContent(final ZhiHu zhiHu) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mWebView.getSettings().setJavaScriptEnabled(true);
                String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/css/news.css\" type=\"text/css\">";
                String html = "<html><head>" + css + "</head><body>" + zhiHu.getBody() + "</body></html>";
                html = html.replace("<div class=\"img-place-holder\">", "");
                mWebView.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);
            }
        });

    }

    @Override
    public void setPresenter(ZhihuDetailedContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void initViews(View view) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}