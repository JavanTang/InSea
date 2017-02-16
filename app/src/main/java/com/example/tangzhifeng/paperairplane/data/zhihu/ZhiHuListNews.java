package com.example.tangzhifeng.paperairplane.data.zhihu;

import java.util.List;

/**
 * 作者: tangzhifeng on 2017/2/16.
 * 邮箱: tzfjobmail@gmail.com
 */

public class ZhiHuListNews {


    /**
     * date : 20170216
     * stories : [{"images":["http://pic1.zhimg.com/f7951c130be476221e0a3083221f0b30.jpg"],"type":0,"id":9226209,"ga_prefix":"021612","title":"大误 · 宿舍是用来学习的，你们居然用它\u2026\u2026"},{"images":["http://pic3.zhimg.com/92a2a1e445e19bf9ca433fb852e66766.jpg"],"type":0,"id":9226270,"ga_prefix":"021611","title":"盯着风扇看，怎么扇叶越转越慢甚至还开始倒转了？"},{"images":["http://pic1.zhimg.com/4b60b01a89e88f251c8fa5ff3fedaea8.jpg"],"type":0,"id":9219566,"ga_prefix":"021610","title":"「老板，来一打爱，我抑郁了」"},{"images":["http://pic2.zhimg.com/e97decc645704321feb486ba6155bca9.jpg"],"type":0,"id":9224872,"ga_prefix":"021609","title":"去年中国人是怎么出国花钱的？"},{"images":["http://pic1.zhimg.com/3d796146f66437204770a4cf622ae13c.jpg"],"type":0,"id":9223539,"ga_prefix":"021608","title":"常和数据打交道，一定知道做好「数据清洗」有多重要"},{"title":"在法国，有这样一座并没有玫瑰的「玫瑰之城」","ga_prefix":"021607","images":["http://pic4.zhimg.com/c11adc0e2e50ead0c50df398594fe1b7.jpg"],"multipic":true,"type":0,"id":9225004},{"images":["http://pic3.zhimg.com/951db5c891a917a53444b77d06460e4a.jpg"],"type":0,"id":9224136,"ga_prefix":"021607","title":"王健林叫板迪士尼，但主题乐园这门生意首富也玩不转"},{"images":["http://pic1.zhimg.com/7a066aa1f5922189374eefe7549621b4.jpg"],"type":0,"id":9223793,"ga_prefix":"021607","title":"同时发射 104 颗卫星创了纪录，印度的航天技术其实很强"},{"images":["http://pic3.zhimg.com/40a9b7c1bd395495a4925474a27cc82a.jpg"],"type":0,"id":9219279,"ga_prefix":"021606","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"http://pic1.zhimg.com/c8d69fc6c07f3e7024c4defc3879ab10.jpg","type":0,"id":9224136,"ga_prefix":"021607","title":"王健林叫板迪士尼，但主题乐园这门生意首富也玩不转"},{"image":"http://pic3.zhimg.com/8c29af378fd0c11464fd75d625e9f64e.jpg","type":0,"id":9224872,"ga_prefix":"021609","title":"去年中国人是怎么出国花钱的？"},{"image":"http://pic1.zhimg.com/2f3705c35a3d622efa452e556782a244.jpg","type":0,"id":9224580,"ga_prefix":"021518","title":"现在试水打车业务，美团挑了一个好时机"},{"image":"http://pic3.zhimg.com/5adc0efbf3d5d1346289b27770211ad2.jpg","type":0,"id":9224446,"ga_prefix":"021519","title":"电脑上玩儿游戏，为什么是「WASD」？"},{"image":"http://pic4.zhimg.com/ad3bc9b0e8bf90f625ba25a389cd2563.jpg","type":0,"id":9224249,"ga_prefix":"021516","title":"做好防晒，你得先搞清楚防的是什么"}]
     */

    private String date;
    private List<ZhiHuList.StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ZhiHuList.StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<ZhiHuList.StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }



    public static class TopStoriesBean {
        /**
         * image : http://pic1.zhimg.com/c8d69fc6c07f3e7024c4defc3879ab10.jpg
         * type : 0
         * id : 9224136
         * ga_prefix : 021607
         * title : 王健林叫板迪士尼，但主题乐园这门生意首富也玩不转
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

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
    }
}
