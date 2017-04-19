package com.bawei.goshopping.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.goshopping.R;
import com.bawei.goshopping.activity.DetailActivity;
import com.bawei.goshopping.bean.Zqbean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 赵倩 on 2017/4/17.
 * <p/>
 * 类的用途：
 */
public class RecycleAdapter2 extends RecyclerView.Adapter<RecycleAdapter2.MyViewHolder> {
   private  List<Zqbean.DataBean.SubjectsBean.GoodsListBean> list;
    private  List<String> goodsIdsList;
    private LayoutInflater inflater;
    private Context context;

    public RecycleAdapter2(List<Zqbean.DataBean.SubjectsBean.GoodsListBean> list, Context context, List<String> goodsIdsList) {
        this.list = list;
        this.context = context;
        this.goodsIdsList=goodsIdsList;
        inflater=LayoutInflater.from(context);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.home_item_h_recycle, parent, false);
        final MyViewHolder holder = new MyViewHolder(view);
       /* //点击后跳转到详情界面
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getLayoutPosition();
                Intent intent=new Intent(context,DetailActivity.class);
                intent.putExtra("url",goodsIdsList.get(position));
                context.startActivity(intent);
            }
        });*/
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        holder.markPrice.setText();getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
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

    class MyViewHolder extends RecyclerView.ViewHolder{

        private final ImageView img;
        private final TextView markPrice;
        private final TextView name;
        private final TextView shopPrice;

        public MyViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.home_item_img);
            markPrice = (TextView) itemView.findViewById(R.id.home_item_markPrice);
            name = (TextView) itemView.findViewById(R.id.home_item_name);
            shopPrice = (TextView) itemView.findViewById(R.id.home_item_shopPrice);
        }
    }
}
