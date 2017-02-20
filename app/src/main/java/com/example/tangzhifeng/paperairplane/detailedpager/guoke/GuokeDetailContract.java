package com.example.tangzhifeng.paperairplane.detailedpager.guoke;

import android.content.Intent;
import android.webkit.WebView;

/**
 * Created by Administrator on 2017/2/20.
 */


    public interface GuokeDetailContract {
        interface View{
            void LoadWeb(Intent guoKeIntent, WebView webView);
        }
    }
