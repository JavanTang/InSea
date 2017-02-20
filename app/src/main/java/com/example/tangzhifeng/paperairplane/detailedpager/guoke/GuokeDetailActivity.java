package com.example.tangzhifeng.paperairplane.detailedpager.guoke;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.ScaleAnimation;
import android.webkit.WebView;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guoke_datail);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.inject(this);
        initView();
    }

    private void initView() {
        LoadWebView();

    }

    private void LoadWebView() {
        final Intent intent = this.getIntent();
        final Bundle bundle = intent.getBundleExtra("bundle");
        final GuoKe guoKe = (GuoKe) bundle.getSerializable("guoke");
        final String DetailUrl = guoKe.getResult().get(0).getLink();
        final String DetailTopImageUrl = guoKe.getResult().get(0).getHeadline_img();
        mNoadvWebViewClient = new GuoKeDetailPresenter.NoAdWebViewClient(this, DetailUrl);
        Log.i("wkl", "initView: " + guoKe.getResult().get(0).getLink() + intent.getIntExtra("position", 34343));
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (DetailUrl == null)
                    Toast.makeText(GuokeDetailActivity.this, "详情页Url为空", Toast.LENGTH_SHORT).show();
                else {
                    imageTop.setImageURI(DetailTopImageUrl);
                    webView.loadUrl(DetailUrl);
                    webView.setAnimation(new ScaleAnimation(1, 0, 1, 0));
                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.setWebViewClient(mNoadvWebViewClient);
                }
            }
        });
    }
}