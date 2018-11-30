package com.app.microyang.view.activity;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.allure.lbanners.LMBanners;
import com.allure.lbanners.transformer.TransitionEffect;
import com.app.microyang.R;
import com.app.microyang.base.BaseActivity;
import com.app.microyang.common.ParallaxTransformer;
import com.app.microyang.view.adapter.LocalImgAdapter;

import java.util.ArrayList;

import butterknife.BindView;

public class GuideActivity extends BaseActivity {

    @BindView(R.id.banners)
    LMBanners lmBanners;

    //本地图片
    private ArrayList<Integer> localImages = new ArrayList<Integer>();

    @Override
    protected void initData(Bundle savedInstanceState) {
        addLocalImg();
        initGuide();
    }

    private void addLocalImg() {
        localImages.add(R.drawable.guide1);
        localImages.add(R.drawable.guide2);
        localImages.add(R.drawable.guide3);
    }

    private void initGuide() {
        lmBanners.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        lmBanners.isGuide(true);
//        lmBanners.setVertical(true);
        lmBanners.setAutoPlay(false);
        lmBanners.setCanLoop(false);
        lmBanners.setScrollDurtion(1200);
        lmBanners.setIndicatorBottomPadding(30);
        lmBanners.setIndicatorWidth(10);
        //滚动样式
        lmBanners.setHoriZontalTransitionEffect(TransitionEffect.Default);
        lmBanners.setHoriZontalCustomTransformer(new ParallaxTransformer(R.id.id_image));
        lmBanners.setIndicatorPosition(LMBanners.IndicaTorPosition.BOTTOM_MID);
        //本地用法
        lmBanners.setAdapter(new LocalImgAdapter(GuideActivity.this), localImages);

        /**
         * 若btnBgColor为-1，则表示不需要任何背景色
         * textColor 文字颜色
         * 点击事件
         */
        lmBanners.setOnStartListener(R.drawable.button_shape, -1, new LMBanners.onStartListener() {
            @Override
            public void startOpen() {
                //回调跳转的逻辑
                startActivity(LoginActivity.class);
            }
        });
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_guide;
    }
}
