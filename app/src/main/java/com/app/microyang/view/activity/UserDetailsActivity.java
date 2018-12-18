package com.app.microyang.view.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.app.microyang.R;
import com.app.microyang.base.BaseActivity;
import com.app.microyang.bean.UserBean;
import com.app.microyang.widget.TopBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

public class UserDetailsActivity extends BaseActivity implements TopBar.topbarClickListner {

    @BindView(R.id.tv_user_name)
    TextView tv_user_name;

    @BindView(R.id.tv_user_sex)
    TextView tv_user_sex;

    @BindView(R.id.tv_user_school)
    TextView tv_user_school;

    @BindView(R.id.tv_user_department)
    TextView tv_user_department;

    @BindView(R.id.tv_user_classes)
    TextView tv_user_classes;

    @BindView(R.id.tb_user_details)
    TopBar tb_user_details;

    @Override
    protected void initData(Bundle savedInstanceState) {
        tb_user_details.setButtonBackground(R.drawable.icon_back, 0);
        tb_user_details.setOnTopbarClickListener(this);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void eventBus(UserBean.DataBean dataBean) {
        tv_user_name.setText(dataBean.getUsername());
        tv_user_sex.setText(dataBean.getSex());
        tv_user_school.setText(dataBean.getSchool());
        tv_user_department.setText(dataBean.getSystem_type());
        tv_user_classes.setText(dataBean.getClasses());
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_user_detail;
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
