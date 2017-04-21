package com.bawei.goshopping.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.goshopping.R;

import java.util.ArrayList;

/**
 * Created by 赵倩 on 2017/4/20.
 * <p>
 * 类的用途：
 */
public class ClassifyAdapter extends RecyclerView.Adapter<ClassifyAdapter.MyHolder> {
   Context context;
    private ArrayList<String> list;
    private LayoutInflater inflater;
    public ClassifyAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
        inflater= LayoutInflater.from(context);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.classify_item, parent, false);
        final MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.text.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        private final TextView text;

        public MyHolder(View itemView) {
            super(itemView);
            text=  (TextView) itemView.findViewById(R.id.item_text);
        }
    }
}
