package com.bawei.goshopping.util;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

/**
 * Created by 赵倩 on 2017/4/14.
 * <p/>
 * 类的用途：使用接口回调的方式，将解析得到的json传传出
 *    之所以传出json串，避免url不同，jsonbean不同带来的麻烦
 */
public class Data {
    private InterGetData interGetData;
    public interface InterGetData{
        void intergetData(String json);
    }
    public void getData(String path){
        OkHttpClientManager.getAsyn(path, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String u)
            {
                interGetData.intergetData(u);
            }
        });

    }

    public void openInterGetDat(InterGetData interGetData){
        this.interGetData=interGetData;

    }

}
