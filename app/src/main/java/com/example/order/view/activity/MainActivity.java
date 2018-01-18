package com.example.order.view.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.order.R;
import com.example.order.fragment.FourFragment;
import com.example.order.fragment.OneFragment;
import com.example.order.fragment.ThreeFragment;
import com.example.order.fragment.TwoFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            private String[] mTitles = new String[]{"全部", "待支付", "已支付", "已取消"};

            @Override
            public Fragment getItem(int position) {
                if (position == 1) {
                    return new TwoFragment();
                } else if (position == 2) {
                    return new ThreeFragment();
                } else if (position == 3) {
                    return new FourFragment();
                }
                return new OneFragment();
            }

            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {

                return mTitles[position];
            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }

    @OnClick(R.id.imageView)
    public void onViewClicked() {
        MyPopupWindow.showPopupWindow(viewPager, MainActivity.this, imageView);
    }
}
