package com.example.order.view.activity;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.order.R;


public class MyPopupWindow {
    private static PopupWindow mPopWindow;

    public static void showPopupWindow(final ViewPager mViewPager, Context context, ImageView mImageView) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.popupwindow, null);
        mPopWindow = new PopupWindow(contentView);
        mPopWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView tv1 = (TextView)contentView.findViewById(R.id.pop_computer);
        TextView tv2 = (TextView)contentView.findViewById(R.id.pop_financial);
        TextView tv3 = (TextView)contentView.findViewById(R.id.pop_manage);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(1,true);
                mPopWindow.dismiss();
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(2,true);
                mPopWindow.dismiss();
            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(3,true);
                mPopWindow.dismiss();
            }
        });
        mPopWindow.showAsDropDown(mImageView);


    }
}
