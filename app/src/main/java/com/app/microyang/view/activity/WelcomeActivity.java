package com.app.microyang.view.activity;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.app.microyang.R;
import com.app.microyang.base.BaseActivity;

public class WelcomeActivity extends BaseActivity {
    @Override
    protected void initData(Bundle savedInstanceState) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SharedPreferences preferences = getSharedPreferences("count", 0);
                    int count = preferences.getInt("count", 0);
                    if (count == 0) {
                        startActivity(GuideActivity.class);
                    } else {
                        startActivity(SplashActivity.class);
                    }
                    finish();
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("count", 1);
                    editor.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_splash;
    }
}
