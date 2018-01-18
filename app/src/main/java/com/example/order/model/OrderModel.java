package com.example.order.model;



import com.example.order.bean.OrderBean;
import com.example.order.model.imodel.IOrderModel;
import com.example.order.net.OnNetListener;
import com.example.order.net.RetrofitHelper;
import com.example.order.net.ServiceApi;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dell on 2018-01-18  14:12
 */

public class OrderModel implements IOrderModel {

    @Override
    public void getOrder(String uid, String status, final OnNetListener<OrderBean> onNetListener) {
        ServiceApi serviceApi = RetrofitHelper.getAPI();
        serviceApi.getOrders(uid, status)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<OrderBean>() {
                    @Override
                    public void accept(OrderBean orderBean) throws Exception {
                        onNetListener.onSuccess(orderBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        onNetListener.onFailure((Exception) throwable);
                    }
                });
    }

    @Override
    public void getOrders(String uid, final OnNetListener<OrderBean> onNetListener) {
        ServiceApi serviceApi = RetrofitHelper.getAPI();
        serviceApi.getOrders2(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<OrderBean>() {
                    @Override
                    public void accept(OrderBean orderBean) throws Exception {
                        onNetListener.onSuccess(orderBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        onNetListener.onFailure((Exception) throwable);
                    }
                });
    }
}
