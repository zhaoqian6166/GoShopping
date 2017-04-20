package com.bawei.goshopping.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bawei.goshopping.R;
import com.bawei.goshopping.adapter.ListAdapter;
import com.bawei.goshopping.bean.GoodsImg;
import com.bawei.goshopping.bean.Zq;
import com.bawei.goshopping.util.GsonUtil;
import com.bawei.goshopping.util.Image;
import com.bawei.goshopping.util.OkHttpClientManager;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 赵倩 on 2017/4/17.
 * <p/>
 * 类的用途：  首页中横向listview条目点击后跳转到的详情界面   自己绘制界面
 */
public class DetailActivity extends Activity {

    private String id;
    private ListView listview;
    private View titleView;
    private Banner banner;
    private String url;
    private TextView name;
    private TextView detail_shopPrice;
    private TextView detail_markPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detai_main);
        Intent intent = getIntent();
        id = intent.getStringExtra("url");
        url = "http://m.yunifang.com/yunifang/mobile/goods/detail?random=46389&encode=70ed2ed2facd7a812ef46717b37148d6&id="+id;
        initView();
        getData();
    }

    private void initView() {
        listview = (ListView) findViewById(R.id.detail_list);
        titleView = View.inflate(DetailActivity.this, R.layout.detail_title, null);
        banner = (Banner) titleView.findViewById(R.id.detail_banner);
        name = (TextView) titleView.findViewById(R.id.detail_name);
        detail_shopPrice = (TextView) titleView.findViewById(R.id.detail_shopPrice);
        detail_markPrice = (TextView) titleView.findViewById(R.id.detail_markPrice);
        ImageView detail_store= (ImageView) titleView.findViewById(R.id.detail_store);
        banner.setImageLoader(new Image());
//设置图片集合

    }
    void getData(){
        OkHttpClientManager.getAsyn(url, new OkHttpClientManager.ResultCallback<Zq>() {

            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(Zq response) {
                //得到了解析数据
                List<Zq.DataBean.GoodsBean.GalleryBean> gallery = response.data.goods.gallery;
                setView(response);//设置头布局中价格 商品名等控件
                setBanner(gallery);//设置轮播
                setList(response);//设置listview
            }
        });


    }
    void setList(Zq response){
        String goods_desc = response.data.goods.goods_desc;
        Gson gson = GsonUtil.getGsonInstance();
        GoodsImg[] goodsImgs = gson.fromJson(goods_desc, GoodsImg[].class);
        listview.addHeaderView(titleView);
        ListAdapter adapter = new ListAdapter(goodsImgs, DetailActivity.this);
        listview.setAdapter(adapter);
    }
    void setView(Zq response){
        name.setText(response.data.goods.goods_name);
        detail_markPrice.setText("￥ "+response.data.goods.market_price);
        detail_markPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//文字上设置横线
        detail_shopPrice.setText("￥ "+response.data.goods.shop_price+"   ");
    }

    private void setBanner(List<Zq.DataBean.GoodsBean.GalleryBean> gallery) {
        ArrayList<String> list=new ArrayList<String>();
        for(int i=0;i<gallery.size();i++){
           list.add(gallery.get(i).normal_url);

        }
        banner.setImages(list);
        banner.isAutoPlay(false);//设置不实现自动轮播
        banner.setViewPagerIsScroll(true);//设置允许手动滑动轮播
//banner设置方法全部调用完毕时最后调用
        banner.start();

    }
}
