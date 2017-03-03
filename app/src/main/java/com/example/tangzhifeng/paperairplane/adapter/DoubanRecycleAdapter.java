package com.example.tangzhifeng.paperairplane.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tangzhifeng.paperairplane.R;
import com.example.tangzhifeng.paperairplane.data.douban.Douban;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: tangzhifeng on 2017/2/27.
 * 邮箱: tzfjobmail@gmail.com
 */

public class DoubanRecycleAdapter extends RecyclerView.Adapter<DoubanRecycleHolder> {
    private static final int TYPE_TOP_ITEM = 0;
    private static final int TYPE_NORMAL_ITEM = 1;


    List<Douban> DoubanList;
    DoubanRecycleHolder doubanHolder;
    public OnItemClickListener onItemClickListener;



    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;

    }


    public List<Douban> getDoubanList() {
        return DoubanList;
    }

    public void setDoubanList(List<Douban> doubanList) {
        DoubanList = doubanList;
    }



    public DoubanRecycleAdapter(){
        DoubanList = new ArrayList<>();
    }
    public interface OnItemClickListener{
        void onItemClick(View view ,int position);
        void onItemLongClick(View view, int position);
    }

    @Override
    public DoubanRecycleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_TOP_ITEM:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.douban_top_item_list,parent,false);
                doubanHolder = new DoubanRecycleHolder(view);
                return doubanHolder;

            case TYPE_NORMAL_ITEM:
                View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.douban_item_list,parent,false);
                doubanHolder = new DoubanRecycleHolder(view1);
                return doubanHolder;

        }
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.douban_item_list,parent,false);
//        doubanHolder = new DoubanRecycleHolder(view);
        return null;
    }

    @Override
    public void onBindViewHolder(final DoubanRecycleHolder holder, final int position) {
            if (onItemClickListener != null){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = holder.getLayoutPosition();
                        onItemClickListener.onItemClick(view,position);
                    }
                });
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        onItemClickListener.onItemLongClick(view,position);
                        return true;
                    }
                });
            }

        String AuthorImgUrl = DoubanList.get(position).getDouban_author_avatar();
        String ItemImgUrl = DoubanList.get(position).getDouban_icon();

        holder.Author_img.setImageURI(AuthorImgUrl);
        holder.Item_img.setImageURI(ItemImgUrl);
        holder.Author_tv.setText(DoubanList.get(position).getDouban_author_name());
        holder.Title_tv.setText(DoubanList.get(position).getDouban_title());
        holder.Liking_count_tv.setText(""+DoubanList.get(position).getDouban_like_count());
        holder.Comment_count_tv.setText(""+DoubanList.get(position).getDouban_comments_count());
        holder.Create_time_tv.setText(DoubanList.get(position).getDouban_created_time());
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? TYPE_TOP_ITEM:TYPE_NORMAL_ITEM ;
    }

    @Override
    public int getItemCount() {
        return DoubanList.size();
    }

}
