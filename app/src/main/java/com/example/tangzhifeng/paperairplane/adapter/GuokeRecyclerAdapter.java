package com.example.tangzhifeng.paperairplane.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tangzhifeng.paperairplane.R;
import com.example.tangzhifeng.paperairplane.data.guoke.GuoKe;
import com.example.tangzhifeng.paperairplane.data.guoke.sql.GuokeSQLDataHandle;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Administrator on 2016/12/30.
 */

public class  GuokeRecyclerAdapter extends RecyclerView.Adapter {
    List<GuoKe> GuoKelist;
    GuokeSQLDataHandle sqlDataHandle;
    public GuokeRecyclerAdapter(List<GuoKe> GuokeList, Context context){
        sqlDataHandle = new GuokeSQLDataHandle(context);
        this.GuoKelist = GuokeList;
    }

    public void setGuoKelist(List<GuoKe> guoKelist) {
        GuoKelist = guoKelist;
    }

    /*
        *  创建事件接口
        * */
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
    public interface OnItemLongClickListener{
        void onItemLongClick(View view, int position);
    }
    /*
    *   声明事件对象
    * */
    public OnItemClickListener onItemClickListener;
    public OnItemLongClickListener onItemLongClickListener;
    /*
    * 给事件赋值
    * */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }


    //自定义的ViewHolder，持有每个Item的的所有界面元素
    class MyViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView imageLineIcon,headIcon;
        TextView tvGroupName,tvContent,tvAuther,tvlikingCount,tvCommemtCount;
        public MyViewHolder(View itemView) {
            super(itemView);
            headIcon = (SimpleDraweeView) itemView.findViewById(R.id.head_icon);
            tvGroupName = (TextView) itemView.findViewById(R.id.group_name);
            imageLineIcon = (SimpleDraweeView) itemView.findViewById(R.id.img_item_guoke);
            tvContent = (TextView) itemView.findViewById(R.id.Content_item_guoke);
            tvAuther = (TextView) itemView.findViewById(R.id.auther_id);
            tvlikingCount = (TextView) itemView.findViewById(R.id.liking_count);
            tvCommemtCount = (TextView) itemView.findViewById(R.id.comment_count);
        }
    }
    //创建新View，被LayoutManager所调用
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.guokeitemforlist,parent,false);
          MyViewHolder viewHolder =  new MyViewHolder(view);
        return viewHolder;
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final MyViewHolder viewHolder = (MyViewHolder) holder;
        if (onItemClickListener != null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   int position = viewHolder.getLayoutPosition();
                    onItemClickListener.onItemClick(viewHolder.itemView,position);
                }
            });
        }
        if (onItemLongClickListener != null){
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int position = viewHolder.getLayoutPosition();
                    onItemLongClickListener.onItemLongClick(viewHolder.itemView,position);
                    return true;
                }
            });
        }

//        sqlDataHandle.addSQLiteData(GuoKelist.get(position));
//       boolean flag = true;
//        if (flag&&GuoKelist.size()== 25)
//        {
//            sqlDataHandle.selectData(GuoKelist.get(position));
//            GuoKelist.clear();
//            GuoKelist = sqlDataHandle.getSQLiteGuoList();
//            this.notifyDataSetChanged();
//            flag = false;
//        }

        String ItemIconUrl = GuoKelist.get(position).getResult().get(0).getHeadline_img();
        String HeadIconUrl = GuoKelist.get(position).getResult().get(0).getImage();

        viewHolder.imageLineIcon.setImageURI(ItemIconUrl);
        viewHolder.headIcon.setImageURI(HeadIconUrl);
        viewHolder.tvGroupName.setText(GuoKelist.get(position).getResult().get(0).getGroup_name());
        viewHolder.tvContent.setText(GuoKelist.get(position).getResult().get(0).getItemtitle());
        viewHolder.tvAuther.setText("By："+GuoKelist.get(position).getResult().get(0).getAuthor());
        viewHolder.tvlikingCount.setText(GuoKelist.get(position).getResult().get(0).getLikings_count()+"");
        viewHolder.tvCommemtCount.setText(GuoKelist.get(position).getResult().get(0).getReplies_count());

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return GuoKelist.size();
    }
}
