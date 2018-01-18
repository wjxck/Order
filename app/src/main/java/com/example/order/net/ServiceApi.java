package com.example.order.net;


import com.example.order.bean.OrderBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by dell on 2018-01-09  10:31
 */

public interface ServiceApi {

    //订单
    @GET(Urls.GETORDERS)
    Observable<OrderBean> getOrders(@Query("uid") String uid, @Query("status") String status);

    //订单
    @GET(Urls.GETORDERS)
    Observable<OrderBean> getOrders2(@Query("uid") String uid);
}
