package com.bawei.goshopping.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bawei.goshopping.R;
import com.bawei.goshopping.activity.DetailActivity;
import com.bawei.goshopping.bean.Zqbean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 赵倩 on 2017/4/19.
 * <p>
 * 类的用途：
 */
public class GridAdapter extends RecyclerView.Adapter<GridAdapter.MyHolder> {
    private List<Zqbean.DataBean.DefaultGoodsListBean> list;
    private LayoutInflater inflater;
    private Context context;
    ArrayList<Integer> mHeights;


    public GridAdapter(List<Zqbean.DataBean.DefaultGoodsListBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater=LayoutInflater.from(context);
        //这是一个随机数的集合,用于动态生成item的 宽和高
       /* mHeights = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++)
        {
            mHeights.add( (int) (100 + Math.random() * 300));
        }*/
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.home_item_h_recycle, parent, false);
        final MyHolder holder = new MyHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        //每个条目的高度设置成随机数
        /*if (holder instanceof MyHolder){
            MyHolder holder1 = (MyHolder) holder;

            //每个条目的高度设置成随机数
            ViewGroup.LayoutParams lp = holder1.img.getLayoutParams();
            ViewGroup.LayoutParams nameParams = holder1.name.getLayoutParams();
            ViewGroup.LayoutParams llParams = holder1.ll.getLayoutParams();


            lp.height = mHeights.get(position);

            holder1.img.setLayoutParams(lp);
            holder1.name.setLayoutParams(nameParams);
         //   holder1.img.setText(list.get(position));

        }*/
        holder.markPrice.setText("￥ "+list.get(position).market_price);
        holder.markPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//文字上设置横线
        holder.shopPrice.setText("￥ "+list.get(position).shop_price+"   ");
        holder.name.setText(list.get(position).goods_name);
        Glide.with(context).load(list.get(position).goods_img).error(R.mipmap.bottle_no_lottery).placeholder(R.mipmap.bottle_no_lottery).into(holder.img);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{

        private final ImageView img;
        private final TextView markPrice;
        private final TextView name;
        private final TextView shopPrice;
        public MyHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.home_item_img);
            markPrice = (TextView) itemView.findViewById(R.id.home_item_markPrice);
            name = (TextView) itemView.findViewById(R.id.home_item_name);
            shopPrice = (TextView) itemView.findViewById(R.id.home_item_shopPrice);
        }
    }

}
