package com.example.tangzhifeng.paperairplane.detailedpager.guoke;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
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

public class GuoKeDetailPresenter implements GuokeDetailContract.view {
    VelocityTracker mVelocityTracker;
    int xVelocity;

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

    private void releaseVelocityTracker() {
        if(null != mVelocityTracker) {
            mVelocityTracker.clear();
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    @Override
    public void SildingTouchEvent(View decorView, MotionEvent event, float Xdown,float Ydown, float screenWidth,Activity activity) {
        if (mVelocityTracker == null){
            mVelocityTracker = VelocityTracker.obtain();
            mVelocityTracker.addMovement(event);
            mVelocityTracker.computeCurrentVelocity(1000);
        }

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Xdown = event.getX();
                Ydown = event.getY();
                Log.i("wkl", "ACTION_DOWN: "+Xdown);
                break;
            case MotionEvent.ACTION_MOVE:
                float xMoveDistance = event.getX() - Xdown;
                float yMoveDistance = event.getY() - Ydown;
                xVelocity  = (int) Math.abs(mVelocityTracker.getXVelocity());
//                Log.i("wkl", "yMoveDistance: "+yMoveDistance+"\t"+Ydown);

                Log.i("wkl", "ACTION_MOVE: "+event.getX()+"\t"+event.getY()+"\t"+xVelocity);
                if (xMoveDistance > yMoveDistance)
                if (xMoveDistance > 0&&Xdown<event.getX())
                {
                    decorView.setX(xMoveDistance);
                    decorView.setY(0);
                }
                break;
            case MotionEvent.ACTION_UP:

                Log.i("wkl", "ACTION_UP: "+event.getX()+"\t"+Xdown);
                float xmoveDistance = event.getX() - Xdown;
                float ymoveDistance = event.getY() - Ydown;
                if (ymoveDistance < xmoveDistance){
                    if (xmoveDistance > screenWidth/3||xVelocity>700){
                        continueMove(xmoveDistance,decorView,activity,screenWidth);
                    }
                    else {
                        rebackToLeft(xmoveDistance,decorView);
                    }
                }
                releaseVelocityTracker();
                break;
        }
    }
    /**
     * 从当前位置一直往右滑动到消失。
     * 这里使用了属性动画。
     */
    private void continueMove(float moveDistanceX, final View decorView, final Activity activity,float screenWidth){
        // 从当前位置移动到右侧。
        ValueAnimator anim = ValueAnimator.ofFloat(moveDistanceX, screenWidth);
        anim.setDuration(500); // 一秒的时间结束, 为了简单这里固定为1秒
        anim.start();

        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 位移
                float x = (float) (animation.getAnimatedValue());
                decorView.setX(x);
                decorView.setY(0);
            }
        });

        // 动画结束时结束当前Activity
        anim.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                activity.finish();
            }

        });
    }

    /**
     * Activity被滑动到中途时，滑回去~
     */
    private void rebackToLeft(float moveDistanceX,View decorView ){
        ObjectAnimator.ofFloat(decorView, "X", moveDistanceX, 0).setDuration(300).start();
    }
    public static class NoAdWebViewClient extends WebViewClient {
        private  String homeurl;
        private Context context;
        private ProgressBar progressBar;

        public NoAdWebViewClient(Context context,String homeurl,ProgressBar progressBar) {
            this.context = context;
            this.homeurl = homeurl;
            this.progressBar = progressBar;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            progressBar.setVisibility(View.GONE);
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
           mProgressBar.setBackgroundResource(R.drawable.loading_animotion);
           AnimationDrawable animationDrawable = (AnimationDrawable) mProgressBar.getBackground();
           animationDrawable.start();
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
