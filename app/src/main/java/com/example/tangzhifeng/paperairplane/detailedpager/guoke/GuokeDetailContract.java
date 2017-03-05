package com.example.tangzhifeng.paperairplane.detailedpager.guoke;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.example.tangzhifeng.paperairplane.data.guoke.GuoKe;

/**
 * Created by Administrator on 2017/2/20.
 */


    public interface GuokeDetailContract {
        interface view{
            /**
             *  加载webview配置进度条
             */
            void LoadWeb( String DetailUrl,WebView webView,ProgressBar mProgressBar);
            /**
             *  获取主页
             */
            String GetWebUrl(GuoKe guoKe);
            /**
             *  获取主页顶部图片
             */
            String GetDetailTopIcon(GuoKe guoKe);
            /**
             *  右侧滑触摸事件监听
             */
            void SildingTouchEvent(View decorView, MotionEvent event, float xDown,float Ydown, float screenWidth,Activity activity);


        }

    }
