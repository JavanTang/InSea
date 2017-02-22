package com.example.tangzhifeng.paperairplane.detailedpager.guoke;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.tangzhifeng.paperairplane.R;
import com.example.tangzhifeng.paperairplane.data.guoke.GuoKe;

/**
 * Created by Administrator on 2017/2/20.
 */

public class GuoKeDetailPresenter implements GuokeDetailContract.View {
    @Override
    public void LoadWeb(String DetailUrl,WebView webView,ProgressBar mProgressBar) {
        if (DetailUrl == null)
            Log.i("wkl", "LoadWeb: " + "详情页Url为空");
        else {
            webView.loadUrl(DetailUrl);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebChromeClient(new GuoKeDetailPresenter.MyWebChromeClient(mProgressBar));
    }
    }


    @Override
    public String GetWebUrl(GuoKe guoKe) {
        String DetailUrl = guoKe.getResult().get(0).getLink();
        return DetailUrl;
    }

    @Override
    public String GetDetailTopIcon(GuoKe guoKe) {
        String DetailTopImageUrl = guoKe.getResult().get(0).getHeadline_img();
        return DetailTopImageUrl;
    }

    public static class NoAdWebViewClient extends WebViewClient {
        private  String homeurl;
        private Context context;

        public NoAdWebViewClient(Context context,String homeurl) {
            this.context = context;
            this.homeurl = homeurl;
        }
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            url = url.toLowerCase();
            if(!url.contains(homeurl)){
                if (!ADFilterTool.hasAd(context, url)) {
                    return super.shouldInterceptRequest(view, url);
                }else{
                    return new WebResourceResponse(null,null,null);
                }
            }else{
                return super.shouldInterceptRequest(view, url);
            }


        }

        public static class ADFilterTool {
            public static boolean hasAd(Context context, String url) {
                Resources res = context.getResources();
                String[] adUrls = res.getStringArray(R.array.adBlockUrl);
                for (String adUrl : adUrls) {
                    if (url.contains(adUrl)) {
                        return true;
                    }
                }
                return false;
            }
        }
    }
  public static class MyWebChromeClient extends WebChromeClient{
        ProgressBar mProgressBar;
       public MyWebChromeClient(ProgressBar mProgressBar){
           this.mProgressBar= mProgressBar;
       }
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100){
                mProgressBar.setVisibility(View.GONE);
            }
            else
            {
                mProgressBar.setVisibility(View.VISIBLE);
                mProgressBar.setProgress(newProgress);
            }
        }
    }
}
