package com.app.microyang.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.microyang.R;
import com.app.microyang.utils.DensityUtils;


/**
 * Created by zjz on 2017/5/5.
 */

/**
 * 使用方法：
 * 1、默认只有一个标题
 * 2、可以设置两个左右按钮，直接调用setButtonBackground方法，传入一个drawable资源（R.drawable.xx的形式），如果传入0则不显示此按钮
 * 3、button的大小在xml中设置。统一使用@dimen/title_button_size
 * 3、点击事件则用setOnTopbarClickListener注册监听器，实现点击事件的方法。
 * 4、topbar背景，标题，标题大小、颜色都在xml中设置
 * 5、标题也可以用代码设置
 */

public class TopBar extends RelativeLayout {

    private int mTitleTextColor;
    private String mTitle;
    private float mTitleSize, mLeftButtonSize, mRightButtonSize;

    private ImageView mLeftButton;

    public ImageView getRightButton() {
        return mRightButton;
    }

    public void setRightButton(ImageView rightButton) {
        mRightButton = rightButton;
    }

    private ImageView mRightButton;
    private TextView mTitleView;

    private Resources resources;
    private LayoutParams mLeftParams, mRightParams, mTitleParams;

    private topbarClickListner mTopbarClickListner;
    //  private HashMap mTopbarClickListnerMap ;//用来储存每一个Fragment的监听器
    // private final float TITLE_SIZE = 7;


    public TopBar(Context context) {
        super(context);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        mTitleSize = ta.getDimension(R.styleable.TopBar_titleTextSize, 0);
        mTitleTextColor = ta.getColor(R.styleable.TopBar_titleTextColor, 0);
        mTitle = ta.getString(R.styleable.TopBar_title);
        mLeftButtonSize = ta.getDimension(R.styleable.TopBar_leftButtonSize, 0);
        mRightButtonSize = ta.getDimension(R.styleable.TopBar_rightButtonSize, 0);
        if(mLeftButtonSize == 0){
            mLeftButtonSize = 40;
        }
        if(mRightButtonSize == 0){
            mRightButtonSize = 40;
        }
        ta.recycle();
        //资源
        resources = context.getResources();
        //创建map
        //    mTopbarClickListnerMap = new HashMap();

        //创建组件控件
        mLeftButton = new ImageView(context);
        mRightButton = new ImageView(context);
        mTitleView = new TextView(context);
        //为组件元素赋值
        mTitleView.setText(mTitle);
        mTitleView.setTextSize(DensityUtils.px2dip(context, mTitleSize));
        mTitleView.setTextColor(mTitleTextColor);
        mTitleView.setGravity(Gravity.CENTER);

        mLeftButton.setVisibility(INVISIBLE);
        mRightButton.setVisibility(INVISIBLE);


        //为组件设置布局元素

        mLeftParams = new LayoutParams((int) mLeftButtonSize, (int) mLeftButtonSize);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        mLeftParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE);
        mLeftParams.setMargins(DensityUtils.dip2px(context, 16), 0, 0, 0);
        addView(mLeftButton, mLeftParams);

        mRightParams = new LayoutParams((int) mRightButtonSize, (int) mRightButtonSize);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        mRightParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE);
        mRightParams.setMargins(0, 0, DensityUtils.dip2px(context, 16), 0);
        addView(mRightButton, mRightParams);

        mTitleParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        mTitleParams.setMargins(0, 0, 0, 0);
        addView(mTitleView, mTitleParams);

        //按钮的点击事件
        mLeftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTopbarClickListner != null) {
                    mTopbarClickListner.leftClick();
                }

            }
        });
        mRightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTopbarClickListner != null) {
                    mTopbarClickListner.rightClick();
                }
            }
        });
    }

    //回调接口
    public interface topbarClickListner {
        void leftClick();

        void rightClick();
    }

    //暴露一个方法给调用者来注册接口回调
    public void setOnTopbarClickListener(topbarClickListner listener) {
        mTopbarClickListner = listener;
    }

    //设置Button的可见及图片，传入参数0即为没有此按钮
    public void setButtonBackground(int left, int right) {
        if (left != 0) {
//            mLeftButton.setVisibility(VISIBLE);
//            Drawable drawable = resources.getDrawable(left);
//            mLeftButton.setImageDrawable(drawable);
//            mLeftButton.setScaleType(ImageButton.ScaleType.CENTER_CROP);
//            mLeftButton.setBackgroundColor(resources.getColor(R.color.transparent));

            mLeftButton.setVisibility(VISIBLE);
            Drawable drawable = resources.getDrawable(left);
            mLeftButton.setImageDrawable(drawable);
            mLeftButton.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mLeftButton.setBackgroundColor(resources.getColor(R.color.transparent));
        } else {
            mLeftButton.setVisibility(INVISIBLE);
        }
        if (right != 0) {
            mRightButton.setVisibility(VISIBLE);
            Drawable drawable = resources.getDrawable(right);
            mRightButton.setImageDrawable(drawable);
            mRightButton.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mRightButton.setBackgroundColor(resources.getColor(R.color.transparent));
        } else {
            mRightButton.setVisibility(INVISIBLE);
        }

    }

    //设置标题
    public void setTitle(String title) {
        mTitle = title;
        mTitleView.setText(mTitle);
    }

}

//暴露一个方法给Fragment调用者来注册接口回调
//    public void setFragmentClickListener(int position , topbarClickListner listner){
//        mTopbarClickListnerMap.put(position,listner);
//    }

//设置当前的fragment的监听器
//    private void setFragmentClickListenerToListener(int position){
//        if(mTopbarClickListnerMap.containsKey(position)){
//            mTopbarClickListner =(topbarClickListner) mTopbarClickListnerMap.get(position);
//        }
//    }