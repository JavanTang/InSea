package com.example.tangzhifeng.paperairplane.detailedpager.guoke;

import android.webkit.WebView;
import android.widget.ProgressBar;

import com.example.tangzhifeng.paperairplane.data.guoke.GuoKe;

/**
 * Created by Administrator on 2017/2/20.
 */


    public interface GuokeDetailContract {
        interface View{
            void LoadWeb( String DetailUrl,WebView webView,ProgressBar mProgressBar);
            String GetWebUrl(GuoKe guoKe);
            String GetDetailTopIcon(GuoKe guoKe);
        }

    }
