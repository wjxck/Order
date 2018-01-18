package com.example.order.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.order.R;
import com.example.order.bean.OrderBean;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter{
    Context context;
    List<OrderBean.DataBean> data;

    public OrderAdapter(Context context, List<OrderBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_adapter_item,null);
        return new ViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder1 holder1 = (ViewHolder1) holder;
        //设置数据
        holder1.textView.setText(data.get(position).getTitle());
        holder1.price.setText(data.get(position).getPrice()+"");
        holder1.time.setText(data.get(position).getCreatetime()+"");
        //根据不同的状态动态的设置
        int status = data.get(position).getStatus();
        if(status==0){
            holder1.textView1.setText("待支付");
            holder1.btn.setText("待支付");
        }else if(status==1){
            holder1.textView1.setText("已支付");
            holder1.btn.setText("已支付");
        }else{
            holder1.textView1.setText("已取消");
            holder1.btn.setText("已取消");
        }
        //点击改变按钮事件
        holder1.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String btn = holder1.btn.getText().toString();
                String text = holder1.textView1.getText().toString();
                if(btn.equals("待支付")){
                    holder1.btn.setText("已取消");
                    holder1.textView1.setText("已取消");

                }else if(btn.equals("已取消")){
                    Toast.makeText(context,"已取消",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context,"已支付",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //点击删除按钮事件
        holder1.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出窗
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("警告");
                alertDialog.setMessage("确定删除"+data.get(position).getTitle()+"吗?");
                alertDialog.setNegativeButton("取消", new AlertDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.setPositiveButton("确定", new AlertDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data.remove(position);
                        notifyDataSetChanged();
                    }
                });
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class ViewHolder1 extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView1;
        RelativeLayout relativeLayout;
        Button btn;
        TextView price;
        TextView time;
        public ViewHolder1(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.titleView);
            textView1 = itemView.findViewById(R.id.textView);
            price = itemView.findViewById(R.id.price);
            time = itemView.findViewById(R.id.time);
            btn = itemView.findViewById(R.id.btn);
            relativeLayout = itemView.findViewById(R.id.relative);

        }
    }
}