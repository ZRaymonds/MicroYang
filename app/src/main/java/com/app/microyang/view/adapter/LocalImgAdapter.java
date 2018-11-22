package com.app.microyang.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.allure.lbanners.LMBanners;
import com.allure.lbanners.adapter.LBaseAdapter;
import com.app.microyang.R;

/**
 * Created by luomin on 16/7/12.
 */
public class LocalImgAdapter implements LBaseAdapter<Integer> {
    private Context mContext;

    public LocalImgAdapter(Context context) {
        mContext = context;
    }

    @Override
    public View getView(final LMBanners lBanners, final Context context, int position, Integer data) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.guide_item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.id_image);
        imageView.setImageResource(data);
        return view;
    }


}
