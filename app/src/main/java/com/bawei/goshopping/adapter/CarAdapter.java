package com.bawei.goshopping.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.goshopping.R;
import com.bawei.goshopping.activity.DetailActivity;
import com.bawei.goshopping.bean.MyGoodsBean;
import com.bawei.goshopping.bean.Zqbean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 赵倩 on 2017/4/17.
 * <p/>
 * 类的用途：
 */
public class CarAdapter extends RecyclerView.Adapter<CarAdapter.MyViewHolder> {
   private  List<MyGoodsBean.CartItemListBean>  list;
    private LayoutInflater inflater;
    private Context context;

    public CarAdapter(List<MyGoodsBean.CartItemListBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater=LayoutInflater.from(context);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.car_item, parent, false);
        final MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        holder.markPrice.setText();getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.shopPrice.setText("  ￥ "+list.get(position).price+"   ");
        holder.name.setText(list.get(position).name);
        holder.count.setText("  数量："+list.get(position).count);
        Glide.with(context).load(list.get(position).pic).error(R.mipmap.bottle_no_lottery).placeholder(R.mipmap.bottle_no_lottery).into(holder.img);
       //设置check的监听


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private final ImageView img;
        private final TextView name;
        private final TextView shopPrice;
        private final TextView count;
        private final CheckBox check;


        public MyViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.car_item_img);
            name = (TextView) itemView.findViewById(R.id.car_item_name);
            shopPrice = (TextView) itemView.findViewById(R.id.car_item_price);
            count = (TextView) itemView.findViewById(R.id.car_item_count);
            check = (CheckBox) itemView.findViewById(R.id.car_item_check);

        }
    }
}
