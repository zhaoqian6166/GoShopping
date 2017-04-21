package com.bawei.goshopping.bean;

import java.util.ArrayList;

/**
 * Created by 赵倩 on 2017/4/20.
 * <p/>
 * 类的用途：
 */
public class ClassifyBean {
    public Data data;
    public class Data{
        public ArrayList<GoodsBrief> goodsBrief;

    }
    public class GoodsBrief{
        public String goods_name;
        public String goods_img;
        public String market_price;
        public String shop_price;

    }
}
