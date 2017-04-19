package com.bawei.goshopping.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.goshopping.R;
import com.bawei.goshopping.activity.WebActivity;
import com.bawei.goshopping.adapter.GridAdapter;
import com.bawei.goshopping.adapter.MoreTypeAdapter;
import com.bawei.goshopping.adapter.RecycleAdapter;
import com.bawei.goshopping.bean.Zqbean;
import com.bawei.goshopping.util.Data;
import com.bawei.goshopping.util.GsonUtil;
import com.bawei.goshopping.util.Image;
import com.bawei.goshopping.util.OkHttpClientManager;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 赵倩 on 2017/4/12.
 * <p>
 * 类的用途：首页
 */
public class Home extends Fragment {

    private View view;
    private Banner banner;
    private List<Zqbean.DataBean.Ad1Bean> banner_list;
    private TextView daydaycom;
    private TextView jifen;
    private TextView duihuan;
    private TextView zhenwei;
    private ImageView dayImg;
    private ImageView jifenImg;
    private ImageView dhImg;
    private ImageView tfImg;
    private RecyclerView recycl_horizontal;
    private RecyclerView recycl_list;
    private RecyclerView recycl_grid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.f_shouye, null);
        initView();
        getData();
        onClickListner();
        return view;
    }

    private void initView() {
        banner = (Banner) view.findViewById(R.id.f_home_viewpager);
        daydaycom = (TextView) view.findViewById(R.id.f_home_daydaycom);
        jifen = (TextView) view.findViewById(R.id.f_home_djifen);
        duihuan = (TextView) view.findViewById(R.id.f_home_duihuan);
        zhenwei = (TextView) view.findViewById(R.id.f_home_t_f);
        dayImg = (ImageView) view.findViewById(R.id.f_home_dayImg);
        jifenImg = (ImageView) view.findViewById(R.id.f_home_jifenImg);
        dhImg = (ImageView) view.findViewById(R.id.f_home_dhImg);
        tfImg = (ImageView) view.findViewById(R.id.f_home_tfImg);
        recycl_grid = (RecyclerView) view.findViewById(R.id.f_home_recycle_grid);
        recycl_horizontal = (RecyclerView) view.findViewById(R.id.f_home_recycle_horizontal);
        recycl_list = (RecyclerView) view.findViewById(R.id.f_home_recycle_list);

    }
    //解析数据

    public void getData(){
        String url="http://m.yunifang.com/yunifang/mobile/home?random=84831&encode=9dd34239798e8cb22bf99a75d1882447";
        OkHttpClientManager.getAsyn(url, new OkHttpClientManager.ResultCallback<Zqbean>() {
            @Override
            public void onError(Request request, Exception e) {
            }

            @Override
            public void onResponse(Zqbean zqbean) {
            //图片轮播
                banner_list = zqbean.data.ad1;
                initBanner(banner_list);
                //积分兑换等
                List<Zqbean.DataBean.Ad5Bean> listText = zqbean.data.ad5;
                initListText(listText);
                //水平ListView  使用RecyCleView实现  春季热销
                List<Zqbean.DataBean.BestSellersBean.GoodsListBeanX> goodsList = zqbean.data.bestSellers.get(0).goodsList;
                List<String> goodsIdsList = zqbean.data.bestSellers.get(0).goodsIdsList;
                initHorizontalRecle( goodsList,goodsIdsList);
                //多条目展示数据
                List<Zqbean.DataBean.SubjectsBean> subjects = zqbean.data.subjects;
                initRecycleMoreTypr(subjects);
                //瀑布流展示商品信息
                List<Zqbean.DataBean.DefaultGoodsListBean> goodsList1 = zqbean.data.defaultGoodsList;
                initStaggered(goodsList1);
            }
        });

    }
    void initStaggered(List<Zqbean.DataBean.DefaultGoodsListBean> goodsList1){
        StaggeredGridLayoutManager stg = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recycl_grid.setLayoutManager(stg);
        GridAdapter adapter = new GridAdapter(goodsList1, getContext());
        recycl_grid.setAdapter(adapter);
    }

    private void initRecycleMoreTypr(List<Zqbean.DataBean.SubjectsBean> subjects) {
        LinearLayoutManager list_layout = new LinearLayoutManager(getContext());
        list_layout.setOrientation(LinearLayoutManager.VERTICAL);
        recycl_list.setLayoutManager(list_layout);
        MoreTypeAdapter adapter = new MoreTypeAdapter(getContext(),subjects);
        recycl_list.setAdapter(adapter);

    }

    void initBanner( List<Zqbean.DataBean.Ad1Bean> banner_list){
        ArrayList<String> list=new ArrayList<String>();
        for (int i=0;i<banner_list.size();i++){
            list.add(banner_list.get(i).image);
        }
        //设置图片加载器
        banner.setImageLoader(new Image());
//设置图片集合
         banner.setImages(list);
//banner设置方法全部调用完毕时最后调用
        banner.start();

    }

    void  onClickListner(){
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent=new Intent(getContext(), WebActivity.class);
                intent.putExtra("url",banner_list.get(position).ad_type_dynamic_data);
                startActivity(intent);

            }
        });
        //每日签到
        dayImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), WebActivity.class);
                intent.putExtra("url",banner_list.get(0).ad_type_dynamic_data);
                startActivity(intent);
            }
        });
        //积分兑换
        jifenImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), WebActivity.class);
                intent.putExtra("url",banner_list.get(1).ad_type_dynamic_data);
                startActivity(intent);
            }
        });
        //兑换
        dhImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), WebActivity.class);
                intent.putExtra("url",banner_list.get(2).ad_type_dynamic_data);
                startActivity(intent);
            }
        });
        //真伪查询
        tfImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), WebActivity.class);
                intent.putExtra("url",banner_list.get(3).ad_type_dynamic_data);
                startActivity(intent);
            }
        });


    }
    void initListText(List<Zqbean.DataBean.Ad5Bean> listText){
        daydaycom.setText(listText.get(0).title);
        jifen.setText(listText.get(1).title);
        duihuan.setText(listText.get(2).title);
        zhenwei.setText(listText.get(3).title);
        Glide.with(getContext()).load(listText.get(0).image).error(R.mipmap.bottle_no_lottery).placeholder(R.mipmap.bottle_no_lottery).into(dayImg);
        Glide.with(getContext()).load(listText.get(1).image).error(R.mipmap.bottle_no_lottery).placeholder(R.mipmap.bottle_no_lottery).into(jifenImg);
        Glide.with(getContext()).load(listText.get(2).image).error(R.mipmap.bottle_no_lottery).placeholder(R.mipmap.bottle_no_lottery).into(dhImg);
        Glide.with(getContext()).load(listText.get(3).image).error(R.mipmap.bottle_no_lottery).placeholder(R.mipmap.bottle_no_lottery).into(tfImg);

    }
    void initHorizontalRecle(List<Zqbean.DataBean.BestSellersBean.GoodsListBeanX> goodsList,List<String> goodsIdsList){
        LinearLayoutManager list_layout = new LinearLayoutManager(getContext());
        list_layout.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycl_horizontal.setLayoutManager(list_layout);
        RecycleAdapter adapter = new RecycleAdapter(goodsList, getContext(), goodsIdsList);
        recycl_horizontal.setAdapter(adapter);
        recycl_horizontal.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.HORIZONTAL));

    }


}
