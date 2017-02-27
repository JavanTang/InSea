package com.example.tangzhifeng.paperairplane.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.tangzhifeng.paperairplane.R;
import com.facebook.drawee.view.SimpleDraweeView;


public class DoubanRecycleHolder extends RecyclerView.ViewHolder {
    TextView Author_tv,Title_tv,Liking_count_tv,Comment_count_tv,Create_time_tv;
    SimpleDraweeView Author_img,Item_img;
    public DoubanRecycleHolder(View itemView) {
        super(itemView);
        Author_img = (SimpleDraweeView) itemView.findViewById(R.id.author_img);
        Item_img =  (SimpleDraweeView) itemView.findViewById(R.id.item_img);
        Author_tv = (TextView) itemView.findViewById(R.id.author_name);
        Title_tv = (TextView) itemView.findViewById(R.id.douban_title);
        Liking_count_tv = (TextView) itemView.findViewById(R.id.douban_liking_count);
        Comment_count_tv = (TextView) itemView.findViewById(R.id.douban_comment_count);
        Create_time_tv = (TextView) itemView.findViewById(R.id.create_time);
    }
}
