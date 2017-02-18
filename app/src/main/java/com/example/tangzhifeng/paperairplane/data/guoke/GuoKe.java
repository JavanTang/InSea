package com.example.tangzhifeng.paperairplane.data.guoke;

import java.util.List;

/**
 * Created by Administrator on 2017/2/17.
 */

public class GuoKe {

    /**By : WKL
     * now : 2017-02-17T12:30:48.664248+08:00
     * ok : true
     * result : [{"link_v2_sync_img":
     * "http://jingxuan.guokr.com/pick/v2/78912/sync/","source_name":
     * "闪电侠！","video_url":"","current_user_has_collected":false,"likings_count":7,"images":["http://3.im.guokr
     */

    public String now;
    private boolean ok;
    public List<ResultBean> result;

    public String getNow() {
        return now;
    }

    public void setNow(String now) {
        this.now = now;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * link_v2_sync_img : http://jingxuan.guokr.com/pick/v2/78912/sync/
         * source_name : 闪电侠！
         * video_url :
         * current_user_has_collected : false
         * likings_count : 7
         * images : ["http://3.im.guokr.com/lNO8lm6vcSVWwSZYZfQxRp2IKrF8Pj2BvgOBv0IIHefQBwAA3AUAAEpQ.jpg?imageView2/1/w/480/h/360","http://2.im.guokr.com/29HW6pks7o5DxImWd10VpWf5ymU12iMMAG2qFIce1_SWAwAAXwIAAEpQ.jpg?imageView2/1/w/480/h/317"]
         * video_duration : null
         * id : 78912
         * category : science
         * style : article
         * title : 这张图片，你一定猜不到它是什么！
         * source_data : {"image":"http://2.im.guokr.com/r8PINb_RG_niPP_rsxxvHLK7HmQE9i1NZD6pWV_0VDKgAAAAoAAAAFBO.png?imageView2/1/w/160/h/160","summary":"今天你看了什么好玩儿的新闻！","id":52,"key":"闪电侠！","title":"闪电侠！"}
         * headline_img_tb : http://3.im.guokr.com/lNO8lm6vcSVWwSZYZfQxRp2IKrF8Pj2BvgOBv0IIHefQBwAA3AUAAEpQ.jpg?imageView2/1/w/288/h/216
         * link_v2 : http://jingxuan.guokr.com/pick/v2/78912/
         * date_picked : 1.48728966E9
         * is_top : false
         * link : http://jingxuan.guokr.com/pick/78912/
         * headline_img : http://3.im.guokr.com/lNO8lm6vcSVWwSZYZfQxRp2IKrF8Pj2BvgOBv0IIHefQBwAA3AUAAEpQ.jpg?imageView2/1/w/640/h/480
         * replies_count : 2
         * current_user_has_liked : false
         * page_source : http://jingxuan.guokr.com/pick/78912/?ad=1
         * author : ApoE
         * summary : 今天看到一个很有意思的照片，如果不做解释的话，我打赌你一定猜不到它究竟是什么！ 先来看看图吧： 嗯，有着非常丰富的细节，还有一些梦
         * source : group
         * reply_root_id : 772501
         * date_created : 1.487247887E9
         */
        private String link_v2_sync_img;
        private String source_name;
        private String video_url;
        private boolean current_user_has_collected;
        private int likings_count;
        private Object video_duration;
        private int id;
        private String category;
        private String style;
//        果壳小组名称
        public String group_name;
        private SourceDataBean source_data;
        /*
        item显示的Icon图片*/
        private String headline_img_tb;
        private String link_v2;
        private double date_picked;
        private boolean is_top;
        //        详情页地址适用于移动端
        public String link;
        /*
        item显示的Icon图片*/
        public String headline_img;
        private int replies_count;
        private boolean current_user_has_liked;
//        详情页地址
        private String page_source;
//        作者
        public String author;
//        概要
        public String summary;
        private String source;
        private int reply_root_id;
        private double date_created;
        public String image;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
        public String getLink_v2_sync_img() {
            return link_v2_sync_img;
        }

        public void setLink_v2_sync_img(String link_v2_sync_img) {
            this.link_v2_sync_img = link_v2_sync_img;
        }

        public String getSource_name() {
            return source_name;
        }

        public void setSource_name(String source_name) {
            this.source_name = source_name;
        }

        public String getVideo_url() {
            return video_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }

        public boolean isCurrent_user_has_collected() {
            return current_user_has_collected;
        }

        public void setCurrent_user_has_collected(boolean current_user_has_collected) {
            this.current_user_has_collected = current_user_has_collected;
        }

        public int getLikings_count() {
            return likings_count;
        }

        public void setLikings_count(int likings_count) {
            this.likings_count = likings_count;
        }

        public Object getVideo_duration() {
            return video_duration;
        }

        public void setVideo_duration(Object video_duration) {
            this.video_duration = video_duration;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getGroup_name() {
            return group_name;
        }

        public void setGroup_name(String group_name) {
            this.group_name = group_name;
        }

        public SourceDataBean getSource_data() {
            return source_data;
        }

        public void setSource_data(SourceDataBean source_data) {
            this.source_data = source_data;
        }

        public String getHeadline_img_tb() {
            return headline_img_tb;
        }

        public void setHeadline_img_tb(String headline_img_tb) {
            this.headline_img_tb = headline_img_tb;
        }

        public String getLink_v2() {
            return link_v2;
        }

        public void setLink_v2(String link_v2) {
            this.link_v2 = link_v2;
        }

        public double getDate_picked() {
            return date_picked;
        }

        public void setDate_picked(double date_picked) {
            this.date_picked = date_picked;
        }

        public boolean isIs_top() {
            return is_top;
        }

        public void setIs_top(boolean is_top) {
            this.is_top = is_top;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getHeadline_img() {
            return headline_img;
        }

        public void setHeadline_img(String headline_img) {
            this.headline_img = headline_img;
        }

        public int getReplies_count() {
            return replies_count;
        }

        public void setReplies_count(int replies_count) {
            this.replies_count = replies_count;
        }

        public boolean isCurrent_user_has_liked() {
            return current_user_has_liked;
        }

        public void setCurrent_user_has_liked(boolean current_user_has_liked) {
            this.current_user_has_liked = current_user_has_liked;
        }

        public String getPage_source() {
            return page_source;
        }

        public void setPage_source(String page_source) {
            this.page_source = page_source;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public int getReply_root_id() {
            return reply_root_id;
        }

        public void setReply_root_id(int reply_root_id) {
            this.reply_root_id = reply_root_id;
        }

        public double getDate_created() {
            return date_created;
        }

        public void setDate_created(double date_created) {
            this.date_created = date_created;
        }


        public static class SourceDataBean {
            /**
             * image : http://2.im.guokr.com/r8PINb_RG_niPP_rsxxvHLK7HmQE9i1NZD6pWV_0VDKgAAAAoAAAAFBO.png?imageView2/1/w/160/h/160
             * summary : 今天你看了什么好玩儿的新闻！
             * id : 52
             * key : 闪电侠！
             * title : 闪电侠！
             */

            private String summary;
            private int id;
            private String key;

            public String title;


            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
