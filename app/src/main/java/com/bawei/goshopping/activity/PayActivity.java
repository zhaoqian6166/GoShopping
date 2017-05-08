package com.bawei.goshopping.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.goshopping.R;
import com.bawei.goshopping.adapter.PrayAdapter;
import com.bawei.goshopping.bean.DeleteGoods;
import com.bawei.goshopping.bean.MyGoodsBean;
import com.bawei.goshopping.bean.OrderBean;
import com.bawei.goshopping.util.OkHttpClientManager;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.squareup.okhttp.Request;

import java.util.ArrayList;

/**
 * Created by 赵倩 on 2017/4/24.
 * <p/>
 * 类的用途：
 */
public class PayActivity extends Activity {

    private ArrayList<MyGoodsBean.CartItemListBean> cartItemList;
    private int countPrice;
    private SharedPreferences config;
    private Button count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prepay_main);
        Intent intent = getIntent();
        countPrice = intent.getIntExtra("count", 0);
        cartItemList  = (ArrayList<MyGoodsBean.CartItemListBean>) intent.getSerializableExtra("data");
       // cartItemList = response.cartItemList;

        initView();
        config = this.getSharedPreferences("config", Context.MODE_PRIVATE);
        viewClick();
    }

    private void initView() {
       // TextView name= (TextView) findViewById(R.id.pray_name);MyGoodsBean response
        RecyclerView recycle= (RecyclerView) findViewById(R.id.pray_recycle);
        TextView price= (TextView) findViewById(R.id.pay_priceAll);
        count = (Button) findViewById(R.id.pay_count);
        price.setText("总计：￥"+countPrice);
        final LinearLayoutManager list_layout = new LinearLayoutManager(PayActivity.this);
        list_layout.setOrientation(LinearLayoutManager.VERTICAL);
        recycle.setLayoutManager(list_layout);
        // list.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.HORIZONTAL));
     //   PrayAdapter  adapter = new PrayAdapter(PayActivity.this,cartItemList);
        PrayAdapter prayAdapter = new PrayAdapter(PayActivity.this, cartItemList);
        recycle.setAdapter(prayAdapter);
        recycle.addItemDecoration(new DividerItemDecoration(PayActivity.this,DividerItemDecoration.HORIZONTAL));


    }
    void viewClick(){
        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击以后   点击后跳转到选择支付方式界面
                for (int i=0;i<cartItemList.size();i++){
                    deleteGood(cartItemList.get(i).productID);
                }

                /**
                 * 跳转到选择支付方式界面  执行两步：
                 * 1,与后台交互，post请求，生成预支付订单信息返回，信息中包含有订单号。
                 * 2,将得到的订单号传递到选择支付方式界面
                 *
                 */
                String url="http://lexue365.51dangao.cn/api/order/add_order";
                getBill(url);

              /*  Intent intent = new Intent(PayActivity.this,ChoosePayStyleActivity.class);

                startActivity(intent);*/

            }
        });

    }

    private void getBill(String url) {
        //使用post请求，参数为：除了contact和mobile,remark/随意填写,其他的值为固定的
        AsyncHttpClient  client = new AsyncHttpClient();
        /**cookie和header,复制即可*/
        int userId1 = config.getInt("userId", 0);

        client.addHeader("userid",465+"");
        client.addHeader("cltid", "1");
        client.addHeader("token", "bbb6e99c4cd22930ea4c945d9932f98a");
        client.addHeader("mobile", "13051711111");

        RequestParams params = new RequestParams();
        params.put("activity_id",4);
        params.put("time_id",2927);
        params.put("child_num",1);
        params.put("contact","zq");
        params.put("mobile","13051711111");
        params.put("remark",1);

        client.post(getApplicationContext(), url, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                 Log.i("Tag","订单详情界面请求失败");
            }

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString) {
                  //成功之后  得到预支付订单信息； 并且将信息传递到下一个界面
                Log.i("Tag","订单详情界面请求成功");
                    Gson gson = new Gson();
                    OrderBean order = gson.fromJson(responseString, OrderBean.class);
                Intent intent = new Intent(PayActivity.this,ChoosePayStyleActivity.class);
                 //将商品价格和订单信息传递到
                intent.putExtra("order", order);
                startActivity(intent);
            }
        });


    }

    //请求后台删除数据库中的先关产品
    void deleteGood(int productID){
        int userId = config.getInt("userId", 1);
        //20.1.2.247
        String url="http://169.254.217.5:8080/bullking1/deletepro?productID="+productID+"&userID="+userId;
        Log.i("???",url);
        OkHttpClientManager.getAsyn(url, new OkHttpClientManager.ResultCallback<DeleteGoods>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("？？？？","失败");
            }

            @Override
            public void onResponse(DeleteGoods response) {
                //请求成功
                if (response.count==1){
                    Log.i("？？？？","删除成功");
                    //   adapter.notifyDataSetChanged();
                //    Toast.makeText(PayActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
