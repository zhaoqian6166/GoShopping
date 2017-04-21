package com.bawei.goshopping.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.goshopping.R;
import com.bawei.goshopping.adapter.ClassifyAdapter;
import com.bawei.goshopping.adapter.GridAdapterStar;
import com.bawei.goshopping.bean.ClassifyBean;
import com.bawei.goshopping.util.OkHttpClientManager;
import com.squareup.okhttp.Request;

import java.util.ArrayList;

/**
 * Created by 赵倩 on 2017/4/12.
 * <p>
 * 类的用途：
 */
public class Classify extends Fragment {

    private View view;
    private RecyclerView star;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fr_fenlei, null);
        initView();
        getData();//网络请求数据
        return view;
    }

   void getData(){
       String url="http://m.yunifang.com/yunifang/mobile/category/list?random=96333&encode=bf3386e14fe5bb0bcef234baebca2414";
       OkHttpClientManager.getAsyn(url, new OkHttpClientManager.ResultCallback<ClassifyBean>() {

           @Override
           public void onError(Request request, Exception e) {

           }

           @Override
           public void onResponse(ClassifyBean response) {
               ArrayList<ClassifyBean.GoodsBrief> goodsBrief = response.data.goodsBrief;
               setStar(goodsBrief);

           }
       });

    }


    private void initView() {
        star = (RecyclerView) view.findViewById(R.id.classify_star);
    }

    void setStar(ArrayList<ClassifyBean.GoodsBrief> goodsBrief){
        StaggeredGridLayoutManager stg = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        star.setLayoutManager(stg);
        GridAdapterStar adapter = new GridAdapterStar(goodsBrief,getContext());
        star.setAdapter(adapter);
    }
}
