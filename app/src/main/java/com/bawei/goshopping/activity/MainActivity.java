package com.bawei.goshopping.activity;



import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bawei.goshopping.R;
import com.bawei.goshopping.fragment.Classify;
import com.bawei.goshopping.fragment.User;
import com.bawei.goshopping.fragment.ShoppingCar;
import com.bawei.goshopping.fragment.Home;

public class MainActivity extends FragmentActivity implements View.OnClickListener{

    private ImageView img;
    private Home home;
    private Classify classify;
    private ShoppingCar shoppingCar;
    private User user;
    private int index=1;//当前Fragment的下标位置
    private android.support.v4.app.FragmentManager manager;
    private android.support.v4.app.FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        FrameLayout frameLayout= (FrameLayout) findViewById(R.id.main_framlayout);
        RadioGroup rg = (RadioGroup) findViewById(R.id.main_rg);
        rg.check(R.id.main_rb_home);//默认第一个为选中状态
        RadioButton rb_home = (RadioButton) findViewById(R.id.main_rb_home);
        RadioButton rb_classify = (RadioButton) findViewById(R.id.main_rb_classify);
        RadioButton rb_shopping = (RadioButton) findViewById(R.id.main_rb_shopping);
        RadioButton rb_user = (RadioButton) findViewById(R.id.main_rb_user);
        rb_home.setOnClickListener(this);
        rb_classify.setOnClickListener(this);
        rb_shopping.setOnClickListener(this);
        rb_user.setOnClickListener(this);
        //实例化Fragment
        home = new Home();
        classify = new Classify();
        shoppingCar = new ShoppingCar();
        user = new User();

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        this.transaction.add(R.id.main_framlayout, home,"home");
        this.transaction.show(home);
        this.transaction.commit();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_rb_home:
                int positon1=1;
                Log.i("测试index","当前"+positon1+"---标识："+index);
                android.support.v4.app.FragmentTransaction transaction1 = manager.beginTransaction();
                if (index>positon1){
                    Log.i("position1","<----");
                    //从右向左点击过来的
                    transaction1.setCustomAnimations(R.anim.fragment_slide_right_enter,R.anim.fragment_slide_left_exit);
                }else{
                    Log.i("position1","---->");
                    transaction1.setCustomAnimations(R.anim.fragment_slide_left_enter,R.anim.fragment_slide_right_exit);
                }

                transaction1.replace(R.id.main_framlayout,home);
                index=positon1;
                transaction1.commit();

                break;
            case R.id.main_rb_classify:
                int positon2=2;
                Log.i("测试index","当前"+positon2+"---标识："+index);
                android.support.v4.app.FragmentTransaction transaction2 = manager.beginTransaction();
                if (index>positon2){
                    Log.i("position2","<----");
                    //从右向左点击过来的
                    transaction2.setCustomAnimations(R.anim.fragment_slide_right_enter,R.anim.fragment_slide_left_exit);
                }else{
                    Log.i("position2","--->");
                    transaction2.setCustomAnimations(R.anim.fragment_slide_left_enter,R.anim.fragment_slide_right_exit);
                }
                transaction2.replace(R.id.main_framlayout,classify);
                index=positon2;
                transaction2.commit();
                break;
            case R.id.main_rb_shopping:
                int positon3=3;
                Log.i("测试index","当前"+positon3+"---标识："+index);
                android.support.v4.app.FragmentTransaction transaction3 = manager.beginTransaction();
                if (index>positon3){
                    Log.i("position3","<----");
                    //从右向左点击过来的
                    transaction3.setCustomAnimations(R.anim.fragment_slide_right_enter,R.anim.fragment_slide_left_exit);
                }else{
                    Log.i("position3","---->");
                    transaction3.setCustomAnimations(R.anim.fragment_slide_left_enter,R.anim.fragment_slide_right_exit);
                }
                transaction3.replace(R.id.main_framlayout,shoppingCar);
                index=positon3;
                transaction3.commit();
                break;
            case R.id.main_rb_user:
                int positon4=4;
                Log.i("测试index","当前"+positon4+"---标识："+index);
                android.support.v4.app.FragmentTransaction transaction4 = manager.beginTransaction();
                if (index>positon4){
                    Log.i("position4","<----");
                    //从右向左点击过来的
                    transaction4.setCustomAnimations(R.anim.fragment_slide_right_enter,R.anim.fragment_slide_left_exit);
                }else{
                    Log.i("position4","---->");
                    transaction4.setCustomAnimations(R.anim.fragment_slide_left_enter,R.anim.fragment_slide_right_exit);
                }
                transaction4.replace(R.id.main_framlayout,user);
                index=positon4;
                transaction4.commit();
                break;
        }

    }
}