package com.bawei.goshopping.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bawei.goshopping.R;
import com.bawei.goshopping.activity.LoginActivity;
import com.bawei.goshopping.activity.RegistActivity;

/**
 * Created by 赵倩 on 2017/4/12.
 * <p>
 * 类的用途：
 */
public class User extends Fragment {

    private View view;
    private Button login;
    private Button regin;
    private String userName;
    private SharedPreferences config;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.f_myself, null);
        initView();
        viewOnClick();


        return view;
    }

    private void initView() {
        login = (Button) view.findViewById(R.id.user_login);
       // regin = (Button) view.findViewById(R.id.user_regin);
        config = getContext().getSharedPreferences("config", Context.MODE_PRIVATE);

            if (config.getBoolean("isLogin",false)){
                login.setText(config.getString("userName","登录"));
            }

    }
    void viewOnClick(){
        //登录按钮点击
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), LoginActivity.class);
                startActivityForResult(intent,100);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100&&resultCode==101){
            String name=data.getStringExtra("name");//得到用户名
            //将用户名设置到控件上
            login.setText(name);
        }

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

    }
}
