package com.app.microyang.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.microyang.R;
import com.app.microyang.bean.NewsBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhengliang on 2017/11/20 0020.
 */

public class NewsAdapter extends BaseAdapter {

    private List<NewsBean.DataBean> newsList = new ArrayList<>();
    private OnItemClickListener listener;

    private Context mContext;

    public NewsAdapter(List<NewsBean.DataBean> newsList, Context mContext) {
        this.newsList = newsList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public Object getItem(int position) {
        return newsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        MyViewHolder holder;
        if (view == null) {
            holder = new MyViewHolder(view);
            view = View.inflate(mContext, R.layout.item_list_home, null);
            view.setTag(holder);
        } else {
            holder = (MyViewHolder) view.getTag();
        }
        NewsBean.DataBean news = newsList.get(position);
        holder.tvTitle.setText(news.getTitle());
        holder.tvDate.setText(news.getContent());

        //加载图片
        Glide.with(mContext)
                .load(news.getImageUrls().get(0))
                .into(holder.ivImg);
        //设置Item的点击事件
        if (this.listener != null) {
            holder.LLItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(view, position);
                }
            });
        }
        return view;
    }


    static class MyViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.iv_img)
        ImageView ivImg;
        @BindView(R.id.ll_item)
        LinearLayout LLItem;

        MyViewHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 自定义Item的点击事件接口
     */
    public interface OnItemClickListener {
        void onClick(View view, int position);
    }
}

