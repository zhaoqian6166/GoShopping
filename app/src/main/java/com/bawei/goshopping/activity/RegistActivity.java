package com.bawei.goshopping.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei.goshopping.R;
import com.bawei.goshopping.bean.LoginBean;
import com.bawei.goshopping.util.Myokhttp;
import com.bawei.goshopping.util.OkHttpClientManager;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by 赵倩 on 2017/4/21.
 * <p>
 * 类的用途：
 */
public class RegistActivity extends Activity{

    private Button regin;
    private EditText name;
    private EditText pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist_main);
        initView();
        viewOnClick();
    }

    private void initView() {
        name = (EditText) findViewById(R.id.regin_name);
        pwd = (EditText) findViewById(R.id.regin_pwd);
        regin = (Button) findViewById(R.id.regin_regin);


    }
    void viewOnClick(){
        regin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击注册按钮之后  上传至后台
                String sname = name.getText().toString();
                String spwd = pwd.getText().toString();
                Log.i("测试得到的注册信息",sname+"--"+spwd);
                getData(sname,spwd);
            }
        });
    }
    void getData(final String sname, final String spwd){
        String url="http://169.254.217.5:8080/bullking1/register?name="+sname+"&pwd="+spwd;
        //创建okHttpClient对象
     /*   Log.i("测试url",url);
        Myokhttp myokhttp = Myokhttp.getInstance();
        myokhttp.getData(url);
        myokhttp.getMyData(new Myokhttp.MyData() {

            private int id;
            private String dataStr;

            @Override
            public void getdata(String json) {
                Gson gson=new Gson();
                LoginBean jieGuoBean = gson.fromJson(json, LoginBean.class);
                dataStr = jieGuoBean.dataStr;
                id = jieGuoBean.id;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (dataStr.equals("register succeed")){
                            Log.i("测试","成功");
                            Toast.makeText(RegistActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            *//*Intent intent=new Intent();
                            intent.putExtra("zhaoghao",zhaohao);
                            intent.putExtra("mima",mima);
                            intent.putExtra("id",id);
                            ZhuceActivity.this.setResult(100,intent);
                            finish();*//*
                        }else {
                            Log.i("测试","失败");
                            Toast.makeText(RegistActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });*/

      /*  OkHttpClient mOkHttpClient = new OkHttpClient();
//创建一个Request
        final Request request = new Request.Builder()
                .url(url)
                .build();
//new call
        Call call = mOkHttpClient.newCall(request);
//请求加入调度
        call.enqueue(new Callback()
        {
            @Override
            public void onFailure(Request request, IOException e)
            {
                Log.i("测试","失败");
            }

            @Override
            public void onResponse(final Response response) throws IOException
            {
                //String htmlStr =  response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Log.i("测试","注册成功"+response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });*/
      /*  OkHttpClientManager.getAsyn(url, new OkHttpClientManager.ResultCallback<String>()
        {
            @Override
            public void onError(Request request, Exception e)
            {
                Log.i("测试","失败");
                e.printStackTrace();
            }

            @Override
            public void onResponse(String u)
            {
                Log.i("测试","注册成功"+u);
               // mTv.setText(u);//注意这里是UI线程
            }
        });
*/
        OkHttpClientManager.getAsyn(url, new OkHttpClientManager.ResultCallback<LoginBean>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(LoginBean response) {
                Log.i("测试","注册成功"+response);

                if (response.dataStr.equals("register succeed")){
                    Toast.makeText(RegistActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    //表示注册成功 成功之后将值回传
                    Intent intent=new Intent();
                    intent.putExtra("name",sname);
                    intent.putExtra("pwd",spwd);
                    setResult(201,intent);
                    finish();

                }else{
                    Toast.makeText(RegistActivity.this, "注册失败，请重新注册", Toast.LENGTH_SHORT).show();
                    Log.i("测试","注册失败");

                }
                name.setText("");
                pwd.setText("");

            }
        });
    }

}
