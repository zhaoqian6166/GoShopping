package com.bawei.goshopping.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.goshopping.R;
import com.bawei.goshopping.activity.PayActivity;
import com.bawei.goshopping.adapter.CarAdapter;
import com.bawei.goshopping.adapter.RecycleAdapter;
import com.bawei.goshopping.bean.DeleteGoods;
import com.bawei.goshopping.bean.MyGoodsBean;
import com.bawei.goshopping.util.ItemRemoveRecyclerView;
import com.bawei.goshopping.util.OkHttpClientManager;
import com.bawei.goshopping.util.OnItemClickListner;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 赵倩 on 2017/4/12.
 * <p/>
 * 类的用途：
 */
public class ShoppingCar extends Fragment {

    private View view;
    private Button count;
    private SharedPreferences config;
    private ItemRemoveRecyclerView list;
    private TextView price;
    private CheckBox selectAll;
    private PopupWindow popupWindow;
    private CarAdapter adapter;
    private int money;
    private ArrayList<Integer> listInt;
    private List<MyGoodsBean.CartItemListBean> cartItemList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.f_shoppingcar, null);
        Log.i("走了", "");
        initView();
        getData();
        return view;
    }

    private void initView() {
        list = (ItemRemoveRecyclerView) view.findViewById(R.id.shoppingcar_list);
        count = (Button) view.findViewById(R.id.shopping_count);
        price = (TextView) view.findViewById(R.id.shopping_priceAll);
        selectAll = (CheckBox) view.findViewById(R.id.shopping_selectAll);
        config = getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        listInt = new ArrayList<Integer>();


    }

    //请求后台  得到购物车的信息
    void getData() {
        if (config.getBoolean("isLogin", false)) {
            int userId = config.getInt("userId", 1);
            String url = "http://169.254.217.5:8080/bullking1/cart?userID=" + userId;
            //String url="http://20.1.2.247 :8080/bullking1/cart?userID="+userId;
            OkHttpClientManager.getAsyn(url, new OkHttpClientManager.ResultCallback<MyGoodsBean>() {
                @Override
                public void onError(Request request, Exception e) {
                    Log.e("购物车", "失败");

                }

                @Override
                public void onResponse(MyGoodsBean response) {
                    //请求成功
                    Log.i("查询商品", response.response);
                    //得到信息  解析到适配器中
                    cartItemList = response.cartItemList;
                    setData(cartItemList);
                    // initPop(cartItemList);
                    viewClick(cartItemList, response);
                }
            });
        }


    }

    //将查询得到的数据设置到适配器上
    void setData(final List<MyGoodsBean.CartItemListBean> cartItemList) {
        final LinearLayoutManager list_layout = new LinearLayoutManager(getContext());
        list_layout.setOrientation(LinearLayoutManager.VERTICAL);
        list.setLayoutManager(list_layout);
        // list.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.HORIZONTAL));
        adapter = new CarAdapter(cartItemList, getContext());
        list.setAdapter(adapter);
        list.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        list.setOnItemClickListener(new OnItemClickListner() {
            @Override
            public void onItemClick(View view, int position) {
            }

            @Override
            public void onDeleteClick(int position) {
                Toast.makeText(getContext(), "点击删除", Toast.LENGTH_SHORT).show();
                Log.d("购物车条目", "点击删除");
                deleteGood(position);
                cartItemList.remove(position);
                adapter.notifyDataSetChanged();

            }
        });
    }

    void viewClick(final List<MyGoodsBean.CartItemListBean> cartItemList, final MyGoodsBean response) {
        //接口回调得到条目中的点击状态的改变  刷新适配器
        adapter.setOnSelecte(new CarAdapter.SetSelect() {
            @Override
            public void onSelect(int position, boolean isSelect) {
                adapter.notifyDataSetChanged();
                int count = 0;
                for (int i = 0; i < cartItemList.size(); i++) {
                    if (cartItemList.get(i).isChecked) {
                        count++;
                    }
                }
                if (count == cartItemList.size()) {
                    selectAll.setChecked(true);
                } else {
                    selectAll.setChecked(false);
                }
                setView(cartItemList);
            }
        });

        count.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                //弹出一个对话框dialog

                //  popupWindow.showAtLocation(view, Gravity.CENTER,0,0);
                ArrayList<MyGoodsBean.CartItemListBean> list = new ArrayList<MyGoodsBean.CartItemListBean>();
                listInt.clear();
                for (int i = 0; i < cartItemList.size(); i++) {
                    boolean isChecked = cartItemList.get(i).isChecked;//如果是选中状态  取出来存到集合中，传递到下个页面
                    if (isChecked) {
                        list.add(cartItemList.get(i));
                        listInt.add(i);
                    }

                }
                Intent intent = new Intent(getContext(), PayActivity.class);
                intent.putExtra("data", list);
                intent.putExtra("count", money);
                startActivity(intent);


            }
        });
        selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = selectAll.isChecked();
                // Log.d("点击前状态",checked+"");

                for (int i = 0; i < cartItemList.size(); i++) {
                    cartItemList.get(i).isChecked = checked;

                }
                Log.d("点击状态", selectAll.isChecked() + "");
                adapter.notifyDataSetChanged();
                setView(cartItemList);
            }
        });

    }

    void initPop(final List<MyGoodsBean.CartItemListBean> cartItemList) {
        View inflate = View.inflate(getContext(), R.layout.pop, null);
        popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        Button popCancle = (Button) inflate.findViewById(R.id.pop_cancle);
        Button popOk = (Button) inflate.findViewById(R.id.pop_ok);
       // pop_price = (TextView) inflate.findViewById(R.id.pop_price);
        final TextView price = (TextView) inflate.findViewById(R.id.pop_price);
     /*   //确认支付按钮点击  点击后文字显示为支付成功  对话框消失,请求后台删除此商品
        popOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price.setText("支付成功！");

                ArrayList<Integer> list=new ArrayList<Integer>();//存取需要移除的角标
                for (int i=0;i<cartItemList.size();i++){

                    if (cartItemList.get(i).isChecked){
                        Log.d("需要移除的角标1:",i+"");
                       list.add(i);
                        deleteGood(cartItemList.get(i).productID);
                    }
                }
                //遍历角标集合，倒叙移除
                for (int i=list.size()-1;i>0;i--){
                    Log.d("需要移除的角标2:",i+"");
                    cartItemList.remove(i);
                    adapter.notifyDataSetChanged();
                }

                popupWindow.dismiss();

            }
        });
        popCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });*/
    }

    //请求后台删除数据库中的先关产品
    void deleteGood(final int position) {
        int userId = config.getInt("userId", 1);
        int productID = cartItemList.get(position).productID;
        //20.1.2.247
        String url = "http://169.254.217.5:8080/bullking1/deletepro?productID=" + productID + "&userID=" + userId;
        Log.i("???", url);
        OkHttpClientManager.getAsyn(url, new OkHttpClientManager.ResultCallback<DeleteGoods>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("？？？？", "失败");
            }

            @Override
            public void onResponse(DeleteGoods response) {
                //请求成功
                if (response.count == 1) {
                    Log.i("？？？？", "删除成功");
                    //   adapter.notifyDataSetChanged();
                    cartItemList.remove(position);
                    Toast.makeText(getContext(), "删除成功", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    void setView(List<MyGoodsBean.CartItemListBean> cartItemList) {
        //得到所有的价格
        money = 0;

        for (int i = 0; i < cartItemList.size(); i++) {
            if (cartItemList.get(i).isChecked) {
                //表示被选中了  计算价格
                money += cartItemList.get(i).price;

            }
        }
        //设置价格
        price.setText("总计：￥" + money);
        //   pop_price.setText("总金额：￥"+ money);
    }

}
