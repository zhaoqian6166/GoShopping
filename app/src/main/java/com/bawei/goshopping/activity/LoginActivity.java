package com.bawei.goshopping.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.goshopping.R;
import com.bawei.goshopping.bean.LoginBean;
import com.bawei.goshopping.util.OkHttpClientManager;
import com.squareup.okhttp.Request;

/**
 * Created by 赵倩 on 2017/4/13.
 * <p/>
 * 类的用途：
 */
public class LoginActivity extends Activity{

    private Button login;
    private SharedPreferences.Editor edit;
    private EditText name;
    private EditText pwd;
    private TextView regist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        initView();
        viewOnClick();
    }

    private void initView() {
        name = (EditText) findViewById(R.id.login_edit1);
        pwd = (EditText) findViewById(R.id.login_edit2);
        login = (Button) findViewById(R.id.login_login);
        ImageView qq= (ImageView) findViewById(R.id.login_qq);
        ImageView wx= (ImageView) findViewById(R.id.login_wx);
        ImageView sina= (ImageView) findViewById(R.id.login_sina);
        regist = (TextView) findViewById(R.id.login_regist);
        SharedPreferences config = getSharedPreferences("config", MODE_PRIVATE);
        edit = config.edit();


    }
    void viewOnClick(){

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击登录以后  请求后台
                String s_name = name.getText().toString();
                String s_pwd = pwd.getText().toString();
                if (!TextUtils.isEmpty(s_name)&&!TextUtils.isEmpty(s_pwd)){
                    getData(s_name,s_pwd);
                }

            }
        });
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, RegistActivity.class);
                startActivityForResult(intent,200);
            }
        });
    }

    void getData(final String s_name, String s_pwd){
        String url="http://169.254.217.5:8080/bullking1/login";
        OkHttpClientManager.postAsyn(url, new OkHttpClientManager.ResultCallback<LoginBean>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(LoginBean response) {
                if (response.dataStr.equals("login succeed")){
                    edit.putInt("userId",response.id);
                    edit.putBoolean("isLogin",true);
                    edit.putString("userName",s_name);
                    edit.commit();
                    Intent intent=new Intent();
                    intent.putExtra("name",s_name);
                    setResult(101,intent);
                    finish();

                }else{
                    Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }

            }
        },new OkHttpClientManager.Param[]{
                new OkHttpClientManager.Param("name",s_name),
                new OkHttpClientManager.Param("pwd",s_pwd)
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==200&&resultCode==201){
            String sname = data.getStringExtra("name");
            String spwd = data.getStringExtra("pwd");
            name.setText(sname);
            pwd.setText(spwd);
        }
    }
}
