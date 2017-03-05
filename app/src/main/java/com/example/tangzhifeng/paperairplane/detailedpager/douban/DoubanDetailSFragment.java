package com.example.tangzhifeng.paperairplane.detailedpager.douban;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tangzhifeng.paperairplane.R;
import com.example.tangzhifeng.paperairplane.detailedpager.guoke.GuoKeDetailPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/2/27.
 */

public class DoubanDetailSFragment extends Fragment {
    @InjectView(R.id.douban_detail_title)
    TextView doubanDetailTitle;
    @InjectView(R.id.douban_detail_img)
    SimpleDraweeView doubanDetailImg;
    @InjectView(R.id.douban_webview)
    WebView doubanWebview;
    @InjectView(R.id.douban_ProgressBar_load)
    ProgressBar doubanProgressBarLoad;

    private String doubanDetail;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.douban_details_fragment, container, false);
        ButterKnife.inject(this, view);
        initView();
        return view;
    }

    private void initView() {
        Intent intent = getActivity().getIntent();
         String doubanDetail = intent.getStringExtra("douban_detail");
        String doubanTitle = intent.getStringExtra("douban_title");
        String doubanDetailimg = intent.getStringExtra("douban_detail_img");
//        Log.i("wkl", "wkl: " + doubanDetail);
        doubanDetailImg.setImageURI(doubanDetailimg);
        doubanDetailTitle.setText("\u3000"+doubanTitle);
        LoadWebView(doubanDetail);
    }

    private void LoadWebView(String doubanDetail) {
        doubanWebview.loadUrl(doubanDetail);
        doubanWebview.getSettings().setJavaScriptEnabled(true);
        doubanWebview.getSettings().setSupportZoom(true);
        // 设置出现缩放工具
        doubanWebview.getSettings().setBuiltInZoomControls(true);
        //扩大比例的缩放
//        doubanWebview.getSettings().setUseWideViewPort(true);
        //自适应屏幕
        doubanWebview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        doubanWebview.getSettings().setLoadWithOverviewMode(true);
        doubanWebview.setWebChromeClient(new GuoKeDetailPresenter.MyWebChromeClient(doubanProgressBarLoad));
        doubanWebview.setWebViewClient(new GuoKeDetailPresenter.NoAdWebViewClient(getActivity(),doubanDetail,doubanProgressBarLoad));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
