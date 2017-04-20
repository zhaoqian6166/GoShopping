package com.bawei.goshopping.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bawei.goshopping.R;
import com.bawei.goshopping.bean.GoodsImg;
import com.bawei.goshopping.bean.Zq;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 赵倩 on 2017/4/19.
 * <p/>
 * 类的用途：
 */
public class ListAdapter extends BaseAdapter {
    GoodsImg[] list;
    Context context;

    public ListAdapter( GoodsImg[] list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public Object getItem(int position) {
        return list[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView=convertView.inflate(context, R.layout.detail_item,null);
            holder=new ViewHolder();
            holder.img= (ImageView) convertView.findViewById(R.id.detail_item_img);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(list[position].url).into(holder.img);
        return convertView;
    }
    class ViewHolder{
        ImageView img;
    }
}
