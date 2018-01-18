package com.example.order.net;

/**
 * Created by dell on 2018-01-09  11:39
 */

public interface OnNetListener<T> {
    public void onSuccess(T t);

    public void onFailure(Exception e);
}
