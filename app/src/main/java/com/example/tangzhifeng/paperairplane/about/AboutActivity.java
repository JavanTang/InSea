package com.example.tangzhifeng.paperairplane.about;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.tangzhifeng.paperairplane.R;
import com.vansuita.materialabout.builder.AboutBuilder;

/**
 * 作者: tangzhifeng on 2017/2/21.
 * 邮箱: tzfjobmail@gmail.com
 */

public class AboutActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = AboutBuilder.with(this)
                .setPhoto(R.mipmap.profile_picture)
                .setCover(R.mipmap.profile_cover)
                .setName("大大大大峰哥 And 爱看书的大凯凯")
                .setSubTitle("帅气与文艺的小骚猪")
                .setBrief("有一颗文艺的心的小90后.")
                .setAppIcon(R.mipmap.ic_launcher)
                .setAppName(R.string.app_name)
//                .addGooglePlayStoreLink("8002078663318221363")
                .addGitHubLink("https://github.com/DDDDDFG")
                .addFiveStarsAction()
                .setVersionAsAppTitle()
                .addShareAction(R.string.app_name)
                .build();

        setContentView(view);
    }
}
