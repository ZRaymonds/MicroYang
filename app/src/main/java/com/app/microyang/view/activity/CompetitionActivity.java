package com.app.microyang.view.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.app.microyang.R;
import com.app.microyang.base.BaseActivity;
import com.app.microyang.bean.MatchBean;
import com.app.microyang.network.ServerApi;
import com.app.microyang.utils.LogUtil;
import com.app.microyang.utils.RetrofitHelper;
import com.app.microyang.view.adapter.ItemViewAdapter;
import com.app.microyang.widget.TopBar;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.header.PhoenixHeader;
import com.scwang.smartrefresh.header.WaveSwipeHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;

import java.util.List;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CompetitionActivity extends BaseActivity implements TopBar.topbarClickListner {

    @BindView(R.id.tb_competition)
    TopBar tb_competition;

    @BindView(R.id.rv_competition_activity)
    RecyclerView rv_competition_activity;

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    private MatchBean.DataBean dataBean;

    private MatchBean.DataBean.ListBean listBean;

    private List<MatchBean.DataBean.ListBean> listBeanList;

    private ItemViewAdapter adapter;

    @Override
    protected void initData(Bundle savedInstanceState) {
        initView();
        initEvent();
    }

    private void initEvent() {
        dataBean = null;
        ServerApi serverApi = RetrofitHelper.getServerApi();
        serverApi.getMatch()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MatchBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e("competitionError", e.getMessage());
                    }

                    @Override
                    public void onNext(MatchBean matchBean) {
                        dataBean = matchBean.getData();
                        listBeanList = dataBean.getList();
                        for (int i = 0; i < listBeanList.size(); i++) {
                            String title = listBeanList.get(i).getTheme();
                            String contnet = listBeanList.get(i).getContent();
                            listBean = new MatchBean.DataBean.ListBean(title,contnet);
                            listBeanList.add(listBean);
                            adapter = new ItemViewAdapter(listBeanList);
                            rv_competition_activity.setAdapter(adapter);
                            LogUtil.d("competition",title);
                            LogUtil.d("competition",contnet);
                        }
                    }
                });
    }

    private void initView() {
        tb_competition.setButtonBackground(R.drawable.icon_back, 0);
        tb_competition.setOnTopbarClickListener(this);
        refreshLayout.setRefreshHeader(new MaterialHeader(this));
        refreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_competition;
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {

    }
}
