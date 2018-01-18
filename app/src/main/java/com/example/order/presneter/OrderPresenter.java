package com.example.order.presneter;


import com.example.order.bean.OrderBean;
import com.example.order.model.OrderModel;
import com.example.order.model.imodel.IOrderModel;
import com.example.order.net.OnNetListener;
import com.example.order.view.IOrderView;

/**
 * Created by dell on 2018-01-18  14:14
 */

public class OrderPresenter {
    private IOrderView iOrderView;
    private final IOrderModel iOrderModel;

    public OrderPresenter(IOrderView iOrderView) {
        this.iOrderView = iOrderView;
        iOrderModel = new OrderModel();
    }

    public void getOrder(String status) {
        iOrderModel.getOrder("71", status, new OnNetListener<OrderBean>() {
            @Override
            public void onSuccess(OrderBean orderBean) {
                iOrderView.getOrder(orderBean);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    public void getOrders() {
        iOrderModel.getOrders("71", new OnNetListener<OrderBean>() {
            @Override
            public void onSuccess(OrderBean orderBean) {
                iOrderView.getOrder(orderBean);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
