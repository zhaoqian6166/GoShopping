package com.bawei.goshopping.util;

import android.util.Log;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Administrator on 2017/4/17.
 */

public class Myokhttp {
    private static Myokhttp mInstance;
    private OkHttpClient mOkHttpClient;
    private MyData myData;
   public interface MyData{
        void getdata(String json);
    }
    public void getMyData(MyData myData){
        this.myData=myData;

    }
    public void getData(String url){
         mOkHttpClient = new OkHttpClient();
//创建一个Request
         Request request = new Request.Builder()
                .url(url)
                .build();
//new call
        Call call = mOkHttpClient.newCall(request);
//请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.i("请求","失败了");

            }

            @Override
            public void onResponse(Response response) throws IOException {
                myData.getdata(response.body().string());
            }
        });
        }
    //单利自己的 单例模式
   public static Myokhttp getInstance()
    {
        if (mInstance == null)
        {
            synchronized (Myokhttp.class)
            {
                if (mInstance == null)
                {
                    mInstance = new Myokhttp();
                }
            }
        }
        return mInstance;
    }
}
