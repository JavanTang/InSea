package com.example.tangzhifeng.paperairplane.detailedpager.guoke;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.ScaleAnimation;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.tangzhifeng.paperairplane.R;
import com.example.tangzhifeng.paperairplane.data.guoke.GuoKe;

/**
 * Created by Administrator on 2017/2/20.
 */

public class GuoKeDetailPresenter implements GuokeDetailContract.View{
    @Override
    public void LoadWeb(Intent guoKeIntent,  WebView webView) {
       Bundle bundle = guoKeIntent.getBundleExtra("bundle");
        GuoKe guoKe = (GuoKe) bundle.getSerializable("guoke");
        final WebView web = webView;
        final String DetailUrl = guoKe.getResult().get(0).getLink();
        if (DetailUrl == null)
            Log.i("wkl", "LoadWeb: "+"详情页Url为空");
        else
        {

                    web.loadUrl(DetailUrl);
                    web.setAnimation(new ScaleAnimation(0,1,0,1));
                    web.getSettings().setJavaScriptEnabled(true);
                    web.setWebViewClient(new WebViewClient(){
                        @Override
                        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                            web.loadUrl(DetailUrl);
                            return true;
                        }
                    });
                }

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
}
