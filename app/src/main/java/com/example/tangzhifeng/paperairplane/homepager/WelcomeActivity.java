package com.example.tangzhifeng.paperairplane.homepager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.example.tangzhifeng.paperairplane.R;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by WKL on 2017/3/6.
 * Facebook:wang.kailiang.7
 * Email:wkl919955081@gmail.com
 */

public class WelcomeActivity extends AppCompatActivity {
    private static final int GO_HOME = 1000;
    private static final int GO_GUIDE = 1001;
    private static final int TIME = 3000;
    @InjectView(R.id.welcome_page)
    SimpleDraweeView welcomePage;
    private boolean state = false;
    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_HOME:
                    gohome();
                    break;
                case GO_GUIDE:
                    break;
            }
        }
    };

    private void gohome() {
        startActivity(new Intent(this, HomepagerActivity.class));
        finish();
    }


    private void init() {
        mhandler.sendEmptyMessageDelayed(GO_HOME, TIME);
        welcomePage.setImageURI("http://sjbz.fd.zol-img.com.cn/t_s320x510c/g5/M00/0F/09/ChMkJlfJQV-IcONQAAltFeAFr3oAAU7cgKlPZgACW0t792.jpg");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wlecome);
        ButterKnife.inject(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
    }
}
