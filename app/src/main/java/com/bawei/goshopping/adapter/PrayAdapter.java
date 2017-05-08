package com.bawei.goshopping.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.goshopping.R;
import com.bawei.goshopping.bean.MyGoodsBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 赵倩 on 2017/4/24.
 * <p/>
 * 类的用途：
 */
public class PrayAdapter extends RecyclerView.Adapter<PrayAdapter.MyPrayViewHolder> {
    Context context;
    List<MyGoodsBean.CartItemListBean> list;
    private LayoutInflater inflater;

    public PrayAdapter(Context context, List<MyGoodsBean.CartItemListBean> list) {
        this.context = context;
        this.list = list;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public MyPrayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.pray_item, parent, false);
        final MyPrayViewHolder holder = new MyPrayViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyPrayViewHolder holder, int position) {
        holder.price.setText("￥ "+list.get(position).price);
        holder.name.setText(list.get(position).name);
        Glide.with(context).load(list.get(position).pic).error(R.mipmap.bottle_no_lottery).placeholder(R.mipmap.bottle_no_lottery).into(holder.img);
        holder.priceCount.setText("总计：￥ "+list.get(position).price);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyPrayViewHolder extends RecyclerView.ViewHolder{

        private final ImageView img;
        private final TextView name;
        private final TextView price;
        private final TextView priceCount;

        public MyPrayViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.pay_item_img);
            name = (TextView) itemView.findViewById(R.id.pray_name);
            price = (TextView) itemView.findViewById(R.id.pray_price);
            priceCount = (TextView) itemView.findViewById(R.id.pay_priceCount);
        }
    }
}
