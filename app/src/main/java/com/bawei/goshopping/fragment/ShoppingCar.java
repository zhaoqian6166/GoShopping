package com.bawei.goshopping.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bawei.goshopping.R;
import com.bawei.goshopping.adapter.CarAdapter;
import com.bawei.goshopping.adapter.RecycleAdapter;
import com.bawei.goshopping.bean.MyGoodsBean;
import com.bawei.goshopping.util.OkHttpClientManager;
import com.squareup.okhttp.Request;

import java.util.List;

/**
 * Created by 赵倩 on 2017/4/12.
 * <p>
 * 类的用途：
 */
public class ShoppingCar extends Fragment {

    private View view;
    private Button count;
    private SharedPreferences config;
    private RecyclerView list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.f_shoppingcar, null);
        initView();

        return view;
    }

    private void initView() {
        list = (RecyclerView) view.findViewById(R.id.shoppingcar_list);
        count = (Button) view.findViewById(R.id.shopping_count);
        config = getContext().getSharedPreferences("config", Context.MODE_PRIVATE);


    }
    //请求后台  得到购物车的信息
    void getData(){
        int userId = config.getInt("userId", 1);
        String url="http://169.254.217.5:8080/bullking1/cart?userID="+userId;
        OkHttpClientManager.getAsyn(url, new OkHttpClientManager.ResultCallback<MyGoodsBean>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(MyGoodsBean response) {
                //请求成功
                Log.i("查询商品",response.response);
                //得到信息  解析到适配器中
                List<MyGoodsBean.CartItemListBean> cartItemList = response.cartItemList;
                setData(cartItemList);
            }
        });


    }
    //将查询得到的数据设置到适配器上
    void setData(final List<MyGoodsBean.CartItemListBean> cartItemList){
        LinearLayoutManager list_layout = new LinearLayoutManager(getContext());
        list_layout.setOrientation(LinearLayoutManager.HORIZONTAL);
        list.setLayoutManager(list_layout);
        CarAdapter adapter = new CarAdapter(cartItemList, getContext());
        list.setAdapter(adapter);
        list.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.HORIZONTAL));
        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到所有的价格
                int money=0;
                for (int i=0;i<cartItemList.size();i++){
                    if (cartItemList.get(i).isChecked){
                        //表示被选中了  计算价格
                        money+=cartItemList.get(i).price;
                    }
                }
                //设置价格

            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            getData();
        }

    }
}
