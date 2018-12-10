package com.app.microyang.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class MyViewAdapter extends RecyclerView.Adapter<MyViewAdapter.MyViewHolder> {

    private RecyclerView mRecyclerView;

    private List<NewsBean.DataBean> newsList;
    private Context mContext;

    private View VIEW_FOOTER;
    private View VIEW_HEADER;

    private NewsBean.DataBean newsBean;

    //Type
    private int TYPE_NORMAL = 1000;
    private int TYPE_HEADER = 1001;
    private int TYPE_FOOTER = 1002;

    private OnItemClickListener listener;

    public MyViewAdapter(Context mContext, List<NewsBean.DataBean> dataBeans) {
        this.mContext = mContext;
        this.newsList = dataBeans;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            return new MyViewHolder(VIEW_FOOTER);
        } else if (viewType == TYPE_HEADER) {
            return new MyViewHolder(VIEW_HEADER);
        } else {
            return new MyViewHolder(getLayout(R.layout.item_list_home));
        }

    }

    private View getLayout(int layoutId) {
        return LayoutInflater.from(mContext).inflate(layoutId, null);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
//        if (!isHeaderView(position) && !isFooterView(position)) {
//            if (haveHeaderView()) position--;
//            TextView content = (TextView) holder.itemView.findViewById(R.id.tv_title);
//            TextView time = (TextView) holder.itemView.findViewById(R.id.tv_date);
//            content.setText(data.get(position));
//            time.setText("2016-1-1");
//        }
        int viewType = getItemViewType(position);
        if (viewType == TYPE_HEADER) {
            //设置播放时间间隔
            holder.mRollPagerView.setPlayDelay(2000);
            //设置透明度
            holder.mRollPagerView.setAnimationDurtion(500);
            //设置适配器
            holder.mRollPagerView.setAdapter(new TestNormalAdapter());

            holder.mRollPagerView.setHintView(new ColorPointHintView(mContext, Color.YELLOW, Color.WHITE));
        }
        if (viewType == TYPE_NORMAL) {
            NewsBean.DataBean dataBean = newsList.get(position);
            holder.tvTitle.setText(dataBean.getTitle());
            holder.tvDate.setText(dataBean.getPublishDateStr());
            if (dataBean.getImageUrls() != null) {
                //加载图片
                RequestOptions requestOptions = new RequestOptions()
                        .placeholder(R.drawable.icon_loading_picture)
                        .error(R.drawable.icon_loading_error)
                        .diskCacheStrategy(DiskCacheStrategy.ALL);
                Glide.with(mContext)
                        .load(dataBean.getImageUrls().get(0))
                        .apply(requestOptions)
                        .into(holder.ivImg);
            } else {
                RequestOptions requestOptions = new RequestOptions()
                        .placeholder(R.drawable.icon_loading_picture)
                        .error(R.drawable.icon_loading_error)
                        .diskCacheStrategy(DiskCacheStrategy.ALL);
                Glide.with(mContext)
                        .load(dataBean.getImageUrls())
                        .apply(requestOptions)
                        .into(holder.ivImg);
            }

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
        if (viewType == TYPE_FOOTER) {
            return;
        }

    }

    @Override
    public int getItemCount() {
        int count = (newsList == null ? 0 : newsList.size());
        if (VIEW_FOOTER != null) {
            count++;
        }

        if (VIEW_HEADER != null) {
            count++;
        }
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(position)) {
            return TYPE_HEADER;
        } else if (isFooterView(position)) {
            return TYPE_FOOTER;
        } else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try {
            if (mRecyclerView == null && mRecyclerView != recyclerView) {
                mRecyclerView = recyclerView;
            }
            ifGridLayoutManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void addHeaderView(View headerView) {
        if (haveHeaderView()) {
            throw new IllegalStateException("hearview has already exists!");
        } else {
            //避免出现宽度自适应
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            headerView.setLayoutParams(params);
            VIEW_HEADER = headerView;
            ifGridLayoutManager();
            notifyItemInserted(0);
        }

    }

    public void addFooterView(View footerView) {
        if (haveFooterView()) {
            throw new IllegalStateException("footerView has already exists!");
        } else {
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            footerView.setLayoutParams(params);
            VIEW_FOOTER = footerView;
            ifGridLayoutManager();
            notifyItemInserted(getItemCount() - 1);
        }
    }

    private void ifGridLayoutManager() {
        if (mRecyclerView == null) return;
        final RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager.SpanSizeLookup originalSpanSizeLookup =
                    ((GridLayoutManager) layoutManager).getSpanSizeLookup();
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (isHeaderView(position) || isFooterView(position)) ?
                            ((GridLayoutManager) layoutManager).getSpanCount() :
                            1;
                }
            });
        }
    }

    private boolean haveHeaderView() {
        return VIEW_HEADER != null;
    }

    public boolean haveFooterView() {
        return VIEW_FOOTER != null;
    }

    private boolean isHeaderView(int position) {
        return haveHeaderView() && position == 0;
    }

    private boolean isFooterView(int position) {
        return haveFooterView() && position == getItemCount() - 1;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        //        @BindView(R.id.tv_title)
        TextView tvTitle;
        //        @BindView(R.id.tv_date)
        TextView tvDate;
        //        @BindView(R.id.iv_img)
        ImageView ivImg;
        //        @BindView(R.id.ll_item)
        CardView LLItem;

        //        @BindView(R.id.roll_view_pager)
        RollPagerView mRollPagerView;

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mRollPagerView = itemView.findViewById(R.id.roll_view_pager);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDate = itemView.findViewById(R.id.tv_date);
            ivImg = itemView.findViewById(R.id.iv_img);
            LLItem = itemView.findViewById(R.id.ll_item);
        }
    }

    private class TestNormalAdapter extends StaticPagerAdapter {
        private int[] imgs = {
                R.drawable.image_feiji,
                R.drawable.image_xianshang,
                R.drawable.image_youyi,
        };

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setImageResource(imgs[position]);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }

        @Override
        public int getCount() {
            return imgs.length;
        }
    }

    /**
     * 自定义Item的点击事件接口
     */
    public interface OnItemClickListener {
        void onClick(View view, int position);
    }
}
