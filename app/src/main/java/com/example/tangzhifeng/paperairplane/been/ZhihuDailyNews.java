package com.example.tangzhifeng.paperairplane.been;

import java.util.List;

/**
 * Created by tangzhifeng on 2017/2/8.
 */

public class ZhihuDailyNews {

    /**
     * date : 20170121
     * stories : [{"images":["http://pic1.zhimg.com/ffcca2b2853f2af791310e6a6d694e80.jpg"],"type":0,"id":9165434,"ga_prefix":"012121","title":"谁说普通人的生活就不能精彩有趣呢？"},"..."]
     */

    private String date;
    private List<StoriesBean> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public static class StoriesBean {
        /**
         * images : ["http://pic1.zhimg.com/ffcca2b2853f2af791310e6a6d694e80.jpg"]
         * type : 0
         * id : 9165434
         * ga_prefix : 012121
         * title : 谁说普通人的生活就不能精彩有趣呢？
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }


    }
}
