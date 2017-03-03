package com.example.tangzhifeng.paperairplane.detailedpager.guoke;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.example.tangzhifeng.paperairplane.R;
import com.example.tangzhifeng.paperairplane.data.guoke.GuoKe;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class GuokeDetailActivity extends AppCompatActivity {

    @InjectView(R.id.web_view)
    WebView webView;
    GuoKeDetailPresenter mGuokePresenter;
    GuoKeDetailPresenter.NoAdWebViewClient mNoadvWebViewClient;
    @InjectView(R.id.image_top)
    SimpleDraweeView imageTop;
    @InjectView(R.id.ProgressBar_load)
    ProgressBar ProgressBarLoad;
    GuoKe mGuoKe;
    @InjectView(R.id.share_id)
    FloatingActionButton shareId;
    @InjectView(R.id.floatingActionButton_back)
    FloatingActionButton floatingActionButtonBack;
    @InjectView(R.id.coord)
    CoordinatorLayout coord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
//        Transition transition = getWindow().getSharedElementEnterTransition();
//        getWindow().setEnterTransition(transition);
//        getWindow().setExitTransition(new Slide());
        setContentView(R.layout.activity_guoke_datail);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.inject(this);
        initView();
    }

    private void initView() {
//        LoadWebView();
        mGuokePresenter = new GuoKeDetailPresenter();
        Intent intent = this.getIntent();
        LoadDetail(intent);
        floatingActionButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        shareId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");// setType("audio/*");
                intent.putExtra(Intent.EXTRA_SUBJECT, "share");
                intent.putExtra(Intent.EXTRA_TEXT, "此处是要分享的内容");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(intent, getTitle()));
            }
        });
    }

    private void LoadDetail(Intent intent) {
        Bundle bundle = intent.getBundleExtra("bundle");
        mGuoKe = (GuoKe) bundle.getSerializable("guoke");
        mGuokePresenter.LoadWeb(
            mGuokePresenter.GetWebUrl(mGuoKe), webView, ProgressBarLoad);
        imageTop.setImageURI(mGuokePresenter.GetDetailTopIcon(mGuoKe));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO 自动生成的方法存根
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {           //当webview不是处于第一页面时，返回上一个页面
                webView.goBack();
                return true;
            } else {                              //当webview处于第一页面时,直接退出程序
//                System.exit(0);
            }


        }
        return super.onKeyDown(keyCode, event);
    }
}