package com.app.microyang.view.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.microyang.R;
import com.app.microyang.base.BaseFragment;
import com.app.microyang.bean.UserBean;
import com.app.microyang.network.ServerApi;
import com.app.microyang.utils.LogUtil;
import com.app.microyang.utils.RetrofitHelper;
import com.app.microyang.view.activity.AboutActivity;
import com.app.microyang.view.activity.LoginActivity;
import com.app.microyang.view.activity.UserDetailsActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MyFragment extends BaseFragment {

    @BindView(R.id.ll_user_details)
    LinearLayout ll_user_details;

    @BindView(R.id.ll_user_activities)
    LinearLayout ll_user_activities;

    @BindView(R.id.ll_user_about)
    LinearLayout ll_user_about;

    @BindView(R.id.ll_user_logout)
    LinearLayout ll_user_logout;

    @BindView(R.id.iv_user_sex)
    ImageView iv_user_sex;

    @BindView(R.id.tv_user_school)
    TextView tv_user_school;

    @BindView(R.id.tv_user_name)
    TextView tv_user_name;


    @Override
    protected View initView(Bundle savedInstanceState) {
        View view = View.inflate(mActivity, R.layout.fragment_my, null);
        EventBus.getDefault().register(this);
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void eventBus(UserBean.DataBean dataBean) {
        tv_user_name.setText(dataBean.getUsername());
        if (dataBean.getSex().equals("男")) {
            iv_user_sex.setImageDrawable(getResources().getDrawable(R.drawable.ic_male));
        } else if (dataBean.getSex().equals("女")) {
            iv_user_sex.setImageDrawable(getResources().getDrawable(R.drawable.ic_female));
        }
        tv_user_school.setText(dataBean.getSchool());
    }

    @OnClick({R.id.ll_user_details, R.id.ll_user_activities, R.id.ll_user_about, R.id.ll_user_logout})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_user_details:
                startUserDetailsActivity();
                break;
            case R.id.ll_user_activities:
                break;
            case R.id.ll_user_about:
                startUserAboutActivity();
                break;
            case R.id.ll_user_logout:
                showLogoutDialog();
                break;
            default:
                break;
        }
    }

    private void startUserDetailsActivity() {
        startActivity(new Intent(mActivity, UserDetailsActivity.class));
    }

    private void startUserAboutActivity() {
        startActivity(new Intent(mActivity, AboutActivity.class));
    }

    private void showLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("退出登录");
        builder.setIcon(R.drawable.ic_logout);
        builder.setMessage("您确定要退出登录吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO: 2018/12/13 退出登录
                dialog.dismiss();
                logout();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_my;
    }

    private void logout() {
        ServerApi serverApi = RetrofitHelper.getServerApi();
        serverApi.logout()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e("logoutError", e.getMessage());
                    }

                    @Override
                    public void onNext(UserBean userBean) {
                        Intent loginIntent = new Intent(mActivity, LoginActivity.class);
                        startActivity(loginIntent);
                        mActivity.finish();
                    }
                });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
