package com.example.tangzhifeng.paperairplane.data.douban.source.remote;

import com.example.tangzhifeng.paperairplane.data.douban.Douban;
import com.example.tangzhifeng.paperairplane.data.douban.DoubanOpenDaily;
import com.example.tangzhifeng.paperairplane.data.douban.source.DoubanDateSource;
import com.example.tangzhifeng.paperairplane.util.Api;
import com.example.tangzhifeng.paperairplane.util.HttpUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: tangzhifeng on 2017/2/27.
 * 邮箱: tzfjobmail@gmail.com
 */

public class DoubanRemoteDateSource implements DoubanDateSource {


    static DoubanRemoteDateSource sRemoteDateSource;

    private DoubanRemoteDateSource() {
    }

    public static DoubanRemoteDateSource getInstance() {
        if (sRemoteDateSource == null) {
            sRemoteDateSource = new DoubanRemoteDateSource();
        }
        return sRemoteDateSource;
    }


    @Override
    public void getDouban(String uri, final DoubanCallback remoteDoubanCallback) {
        HttpUtil.sendHttpRequest(uri, new HttpUtil.IHttpCallbackListenet() {
            @Override
            public void onFinish(String response) {
                remoteDoubanCallback.onSuccess(response);
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
                remoteDoubanCallback.onFailure();
            }
        });
    }

    @Override
    public void getDoubanList(DoubanListCallback loadDoubanListCallback) {

    }

    String htmlTmp;

    @Override
    public void getDoubanList(String date, final DoubanListCallback remoteDoubanListCallback) {
        HttpUtil.sendHttpRequest(Api.GetContentByDate + date, new HttpUtil.IHttpCallbackListenet() {
            @Override
            public void onFinish(String response) {
                Gson gson = new Gson();
                DoubanOpenDaily doubanOpenDaily = gson.fromJson(response, DoubanOpenDaily.class);
                List<Douban> doubanList = new ArrayList<Douban>();
                for (DoubanOpenDaily.PostsBean postsBean : doubanOpenDaily.getPosts()) {
                    Douban douban = new Douban();
                    douban.setDouban_id(postsBean.getId());
                    douban.setDouban_author_name(postsBean.getAuthor().getName());
                    douban.setDouban_author_avatar(postsBean.getAuthor().getAvatar());
                    douban.setDouban_comments_count(postsBean.getComments_count());
                    douban.setDouban_created_time(postsBean.getCreated_time());
                    douban.setDouban_date(postsBean.getDate());
                    douban.setDouban_icon(postsBean.getThumbs().get(0).getSmall().getUrl());
                    douban.setDouban_like_count(postsBean.getLike_count());
                    douban.setDouban_title(postsBean.getTitle());
                    douban.setDouban_uri(postsBean.getOriginal_url());
                    getDouban(douban.getDouban_uri(), new DoubanCallback() {
                        @Override
                        public void onFailure() {

                        }

                        @Override
                        public void onSuccess(String html) {
                            htmlTmp=html;
                        }
                    });
                    douban.setDouban_html(htmlTmp);
                    doubanList.add(douban);
                }
                remoteDoubanListCallback.onSuccess(doubanList);
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
                remoteDoubanListCallback.onFailure();
            }
        });
    }
}
