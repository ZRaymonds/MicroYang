package com.app.microyang.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.app.microyang.R;
import com.app.microyang.base.BaseActivity;
import com.app.microyang.view.fragment.HomeFragment;
import com.app.microyang.view.fragment.MyFragment;
import com.app.microyang.view.fragment.NoticeFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.mainContent)
    FrameLayout mainContent;

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    private FragmentManager fragmentManager;

    HomeFragment homeFragment = new HomeFragment();
    NoticeFragment noticeFragment = new NoticeFragment();
    MyFragment myFragment = new MyFragment();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    showFragment(homeFragment);
                    hideFragment(noticeFragment);
                    hideFragment(myFragment);
                    return true;
                case R.id.notice:
                    showFragment(noticeFragment);
                    hideFragment(homeFragment);
                    hideFragment(myFragment);
                    return true;
                case R.id.my:
                    showFragment(myFragment);
                    hideFragment(noticeFragment);
                    hideFragment(homeFragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void initData(Bundle savedInstanceState) {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        fragmentManager = getSupportFragmentManager();

        addFragment(noticeFragment);
        hideFragment(noticeFragment);
        addFragment(myFragment);
        hideFragment(myFragment);
        addFragment(homeFragment);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    private void addFragment(Fragment fragment) {
        fragmentManager.beginTransaction().add(R.id.mainContent, fragment).commit();
    }

    private void showFragment(Fragment fragment) {
        fragmentManager.beginTransaction().show(fragment).commit();
    }

    private void hideFragment(Fragment fragment) {
        fragmentManager.beginTransaction().hide(fragment).commit();
    }

}
