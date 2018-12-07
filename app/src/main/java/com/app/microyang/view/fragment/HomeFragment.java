package com.app.microyang.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.app.microyang.R;
import com.app.microyang.base.BaseFragment;
import com.app.microyang.bean.News;
import com.app.microyang.bean.NewsBean;
import com.app.microyang.network.ServerApi;
import com.app.microyang.utils.LogUtil;
import com.app.microyang.utils.RetrofitHelper;
import com.app.microyang.view.adapter.MyAdapter;
import com.app.microyang.view.adapter.NewsAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.list)
    RecyclerView main_fragment_list;

    @BindView(R.id.pb)
    ProgressBar pb;

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    private List<NewsBean.DataBean> dataBeans;

    private List<News> list;

    private NewsBean.DataBean newsBeans;

    private NewsAdapter adapter;

    //    private String HTTPURL = "http://litchiapi.jstv.com/api/GetFeeds?column=3&PageSize=20&pageIndex=1&val=100511D3BE5301280E0992C73A9DEC41";
    private String HTTPURL = "http://api01.idataapi.cn:8000/news/qihoo?kw=%E6%A0%A1%E5%9B%AD&site=qq.com&apikey=rVdPWU1LZzfjfQBn3iKQzzdUHum1nNao9qO0TqYsKITRbyvUYjeI11VpUmZdj07J";

//    private Handler mHandler = new Handler() {
//
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case 0:
//                    NewsAdapter adapter = new NewsAdapter(dataBeans,mActivity);
//                    main_fragment_list.setAdapter(adapter);
//                    break;
//
//                default:
//                    break;
//            }
//        }
//    };


    @Override
    protected View initView(Bundle savedInstanceState) {
        View view = View.inflate(mActivity, R.layout.fragment_home, null);
        View header = LayoutInflater.from(mActivity).inflate(R.layout.fragment_home_head, main_fragment_list, false);
//        main_fragment_list.addHeaderView(header);
        return view;
    }

//    @Override
//    protected void initData() {
//        dataBeans = new ArrayList<>();
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder().url(HTTPURL).build();
//        Call call = client.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                try {
//                    JSONObject jo1 = new JSONObject(response.body().string());
//                    JSONObject jo2 = jo1.getJSONObject("paramz");
//                    JSONArray ja = jo2.getJSONArray("feeds");
//                    News news = null;
//                    for (int i = 0; i < ja.length(); i++) {
//                        JSONObject data = ja.getJSONObject(i).getJSONObject(
//                                "data");
//                        String imageUrl = "http://litchiapi.jstv.com"
//                                + data.getString("cover");
//                        String title = data.getString("subject");
//                        String summary = data.getString("summary");
//                        news = new News(imageUrl, title, summary);
//                        list.add(news);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                mHandler.obtainMessage(0).sendToTarget();
//            }
//        });
//    }

    @Override
    protected void initEvent() {
        dataBeans = new ArrayList<>();
        ServerApi serverApi = RetrofitHelper.getServerNews();
        serverApi.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NewsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(NewsBean newsBean) {
                        dataBeans = newsBean.getData();
                        for (int i = 0; i < dataBeans.size(); i++) {
                            String title = dataBeans.get(i).getTitle();
                            String content = dataBeans.get(i).getContent();
                            String imageUrl = dataBeans.get(i).getUrl();
                            LogUtil.d("NEWS",title);
                            LogUtil.d("NEWS",content);
                            LogUtil.d("NEWS",imageUrl);
//                            newsBeans = new NewsBean.DataBean(title,content,imageUrl);
//                            dataBeans.add(newsBeans);
//                            adapter = new NewsAdapter(dataBeans,mActivity);
//                            main_fragment_list.setAdapter(adapter);
                        }
                    }
                });
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

}
