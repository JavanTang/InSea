package com.example.tangzhifeng.paperairplane.detailedpager.zhihu;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.tangzhifeng.paperairplane.R;
import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHu;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.example.tangzhifeng.paperairplane.R.id.toolbar;

/**
 * 作者: tangzhifeng on 2017/2/16.
 * 邮箱: tzfjobmail@gmail.com
 */

@SuppressLint("ValidFragment")
public class ZhihuDetailedFragment extends Fragment implements ZhihuDetailedContract.View {

    ZhihuDetailedContract.Presenter mPresenter;
    @InjectView(toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.detailedTitleImg)
    ImageView mImageView;
    @InjectView(R.id.toolbar_layout)
    CollapsingToolbarLayout mToolbarLayout;
//    @InjectView(R.id.app_bar)
//    AppBarLayout mAppBar;
    @InjectView(R.id.web_view)
    WebView mWebView;

    @SuppressLint("ValidFragment")
    public ZhihuDetailedFragment(int current) {
        this.current = current;
    }

    int current;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detailszhihufragment, container, false);
        ButterKnife.inject(this, view);
        initViews(view);

        mPresenter.loadZhihu(current + "");
        return view;
    }

    @Override
    public void showTitile(String title) {
        mToolbar.setTitle(title);


    }

    @Override
    public void showContent(String body) {


    }


    @Override
    public void showTitlePicture(String uri) {

    }

    @Override
    public void showContent(final ZhiHu zhiHu) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mWebView.loadDataWithBaseURL("x-data://base",zhiHu.getBody(),"text/html","utf-8",null);
                mImageView.setImageURI(Uri.parse(zhiHu.getImage()));
                mToolbar.setTitle("");
                // 扩展后的Toorbar的title颜色
                mToolbarLayout.setExpandedTitleColor(Color.rgb(192,192,192));
//                mToolbarLayout.setExpandedTitleTextAppearance(R.style.Toorbar_style);
                mToolbarLayout.setCollapsedTitleTextColor(Color.rgb(202,235,216));

            }
        });

    }

    @Override
    public void setPresenter(ZhihuDetailedContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void initViews(View view) {
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setSubtitleTextColor(Color.WHITE);
        mToolbar.setTitleTextColor(android.graphics.Color.WHITE);
//        Drawable drawableIcon= AppCompatResources.getDrawable(getContext(), R.drawable.backicon);
//        drawableIcon.setBounds(24,24,24,24);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


}
