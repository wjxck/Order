package com.example.order.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.order.R;
import com.example.order.adapter.OrderAdapter;
import com.example.order.bean.OrderBean;
import com.example.order.presneter.OrderPresenter;
import com.example.order.view.IOrderView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by dell on 2018-01-18  14:00
 */

public class TwoFragment extends Fragment implements IOrderView {
    @BindView(R.id.recyclerFragment)
    RecyclerView recyclerFragment;
    Unbinder unbinder;
    private int status;
    private OrderPresenter orderPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_twor, null);
        unbinder = ButterKnife.bind(this, view);
        orderPresenter = new OrderPresenter(this);
        orderPresenter.getOrder("0");
        recyclerFragment.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void getOrder(OrderBean orderBean) {
        List<OrderBean.DataBean> data = orderBean.getData();
        for (int i = 0; i < data.size(); i++) {
            status = data.get(i).getStatus();
        }
        if (status == 0) {
            OrderAdapter orderAdapter = new OrderAdapter(getActivity(), data);
            recyclerFragment.setAdapter(orderAdapter);
        }
    }
}
