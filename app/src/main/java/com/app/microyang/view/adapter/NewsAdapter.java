package com.app.microyang.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private Context context;
    private List<NewsBean.DataBean> newsList = new ArrayList<>();
    private OnItemClickListener listener;

    private static final int TYPE_HEADER = 0, TYPE_ITEM = 1, TYPE_FOOT = 2;
    private View headView;
    private View footView;
    private int headViewSize = 0;
    private int footViewSize = 0;
    private ChangeGridLayoutManagerSpance changeGridLayoutManager;
    private boolean isAddFoot = false;
    private boolean isAddHead = false;

    public interface ChangeGridLayoutManagerSpance {
        void change(int size, boolean isAddHead, boolean isAddFoot);
    }

    //提供接口给 让LayoutManager根据添加尾部 头部与否来做判断 显示头部与底部的SpanSize要在添加头部和尾部之后
    public void setChangeGridLayoutManager(ChangeGridLayoutManagerSpance changeGridLayoutManager) {
        this.changeGridLayoutManager = changeGridLayoutManager;
        changeGridLayoutManager.change(getItemCount() - 1, isAddHead, isAddFoot);
    }


    public NewsAdapter(Context context, List<NewsBean.DataBean> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void addHeadView(View view) {
        headView = view;
        headViewSize = 1;
        isAddHead = true;
    }

    public void addFootView(View view) {
        footView = view;
        footViewSize = 1;
        isAddFoot = true;
    }

    @Override
    public int getItemViewType(int position) {
        int type = TYPE_ITEM;

        if (headViewSize == 1 && position == 0) {
            type = TYPE_HEADER;
        } else if (footViewSize == 1 && position == getItemCount() - 1) {
            //最后一个位置
            type = TYPE_FOOT;
        }
        return type;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case TYPE_HEADER:
                view = headView;
                break;

            case TYPE_ITEM:
                view = View.inflate(parent.getContext(), R.layout.item_list_home, null);
                break;

            case TYPE_FOOT:
                view = footView;
                break;
        }
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        int viewType = getItemViewType(position);
        if (viewType == TYPE_HEADER) {
            return;
        }
        if (viewType == TYPE_ITEM) {
            NewsBean.DataBean dataBean = newsList.get(position);
            holder.tvTitle.setText(dataBean.getTitle());
            holder.tvDate.setText(dataBean.getContent());

            //加载图片
            Glide.with(context)
                    .load(dataBean.getImageUrls().get(0))
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
        }
        if (viewType == TYPE_FOOT) {
            return;
        }
    }

    @Override
    public int getItemCount() {
        return this.newsList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.iv_img)
        ImageView ivImg;
        @BindView(R.id.ll_item)
        LinearLayout LLItem;

        MyViewHolder(View itemView) {
            super(itemView);
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

