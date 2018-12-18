package com.app.microyang.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.microyang.R;
import com.app.microyang.bean.MatchBean;
import com.app.microyang.bean.NewsBean;

import java.util.List;

import butterknife.ButterKnife;

public class ItemViewAdapter extends RecyclerView.Adapter<ItemViewAdapter.ItemViewHolder> {

    private Context mContext;

    private List<MatchBean.DataBean.ListBean> dataBeanList;

    private OnItemClickListener listener;

    public ItemViewAdapter( List<MatchBean.DataBean.ListBean> dataBeanList) {
        this.dataBeanList = dataBeanList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_item_list,null);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        MatchBean.DataBean.ListBean matchBean = dataBeanList.get(position);
        holder.tv_item_activity_title.setText(matchBean.getTheme());
        holder.tv_organize.setText(matchBean.getOrganizer());
        //设置Item的点击事件
        if (this.listener != null) {
            final int finalPosition = position;
            holder.item_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(view, finalPosition);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_item_activity_pic;

        TextView tv_item_activity_title;

        TextView tv_organize;

        CardView item_card;

        ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            iv_item_activity_pic = itemView.findViewById(R.id.iv_item_activity_pic);
            tv_item_activity_title = itemView.findViewById(R.id.tv_item_activity_title);
            tv_organize = itemView.findViewById(R.id.tv_organize);
            item_card = itemView.findViewById(R.id.item_card);
        }
    }

    public void refresh(List<MatchBean.DataBean.ListBean> addList) {
        dataBeanList.remove(dataBeanList);
        dataBeanList.addAll(addList);
        notifyDataSetChanged();
    }

    public void add(List<MatchBean.DataBean.ListBean> addMessageList) {
        int position = dataBeanList.size();
        dataBeanList.addAll(position, addMessageList);
        notifyDataSetChanged();
    }

    /**
     * 自定义Item的点击事件接口
     */
    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

}
