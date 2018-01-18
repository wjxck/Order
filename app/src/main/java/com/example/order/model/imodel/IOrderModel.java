package com.example.order.model.imodel;


import com.example.order.bean.OrderBean;
import com.example.order.net.OnNetListener;

/**
 * Created by dell on 2018-01-18  14:12
 */

public interface IOrderModel {
    public void getOrder(String uid, String status, OnNetListener<OrderBean> onNetListener);

    public void getOrders(String uid, OnNetListener<OrderBean> onNetListener);
}
