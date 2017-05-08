package com.bawei.goshopping.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/4/20.
 */

public class MyGoodsBean implements Serializable{

    /**
     * cartItemList : [{"colorID":0,"count":10,"id":5,"name":"黑泥","pic":"http://image.hmeili.com/yunifang/images/goods/189/goods_img/160819091183119066095185335.jpg","price":99.9,"productID":1,"repertory":899,"sizeID":0,"userID":123},{"colorID":0,"count":10,"id":6,"name":"美白","pic":"http://image.hmeili.com/yunifang/images/goods/189/goods_img/160819091183119066095185335.jpg","price":99.9,"productID":1,"repertory":899,"sizeID":0,"userID":123},{"colorID":0,"count":10,"id":7,"name":"面膜\u0097","pic":"http://image.hmeili.com/yunifang/images/goods/189/goods_img/160819091183119066095185335.jpg","price":99.9,"productID":1,"repertory":899,"sizeID":0,"userID":123}]
     * response : cart
     */

    public String response;
    public List<CartItemListBean> cartItemList;


    public static class CartItemListBean implements Serializable{
        /**
         * colorID : 0
         * count : 10
         * id : 5
         * name : 黑泥
         * pic : http://image.hmeili.com/yunifang/images/goods/189/goods_img/160819091183119066095185335.jpg
         * price : 99.9
         * productID : 1
         * repertory : 899
         * sizeID : 0
         * userID : 123
         */

        public int colorID;
        public int count;
        public int id;
        public String name;
        public String pic;
        public double price;
        public int productID;
        public int repertory;
        public int sizeID;
        public int userID;
        public boolean isChecked=false;


    }
}
