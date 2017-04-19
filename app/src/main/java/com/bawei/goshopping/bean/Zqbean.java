package com.bawei.goshopping.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/13.
 */

public class Zqbean {

    public int code;
    public String msg;
    public DataBean data;

    public static class DataBean {

        public ActivityInfoBean activityInfo;
        public boolean creditRecived;
        public List<SubjectsBean> subjects;
        public List<BestSellersBean> bestSellers;
        public List<Ad1Bean> ad1;
        public List<Ad5Bean> ad5;
        public List<DefaultGoodsListBean> defaultGoodsList;


        public static class ActivityInfoBean {

            public String activityAreaDisplay;
            public List<ActivityInfoListBean> activityInfoList;

            public static class ActivityInfoListBean {

                public String id;
                public String activityImg;
                public String activityType;
                public String activityData;
                public String activityDataDetail;
                public String startSeconds;
                public String endSeconds;
                public String activityStatus;
                public String activityAreaDisplay;
                public String countDownEnable;
                public String remark;
                public String starttime;
                public String endtime;
                public int sort;
            }
        }

        public static class SubjectsBean {

            public String id;
            public String title;
            public String detail;
            public String image;
            public String start_time;
            public String end_time;
            public int show_number;
            public String state;
            public int sort;
            public List<GoodsListBean> goodsList;
            public List<String> goodsIdsList;

            public static class GoodsListBean {

                public String id;
                public String goods_name;
                public double shop_price;
                public double market_price;
                public String goods_img;
                public boolean reservable;
                public String efficacy;
                public int stock_number;
                public int restrict_purchase_num;
            }
        }

        public static class BestSellersBean {

            public String id;
            public String name;
            public String descript;
            public String state;
            public int show_number;
            public String begin_date;
            public String end_date;
            public List<GoodsListBeanX> goodsList;
            public List<String> goodsIdsList;

            public static class GoodsListBeanX {

                public String id;
                public String goods_name;
                public double shop_price;
                public double market_price;
                public String goods_img;
                public boolean reservable;
                public String efficacy;
                public int stock_number;
                public int restrict_purchase_num;
            }
        }

        public static class Ad1Bean {

            public String id;
            public String image;
            public int ad_type;
            public int sort;
            public int position;
            public int enabled;
            public String createtime;
            public String createuser;
            public String ad_type_dynamic;
            public String ad_type_dynamic_data;
            public String ad_type_dynamic_detail;
            public String title;
            public String channelType;
            public String show_channel;
            public String lastupdateuser;
        }

        public static class Ad5Bean {

            public String id;
            public String image;
            public int ad_type;
            public int sort;
            public int position;
            public int enabled;
            public String ad_type_dynamic;
            public String ad_type_dynamic_data;
            public String ad_type_dynamic_detail;
            public String show_channel;
            public String title;
            public String url;
        }

        public static class DefaultGoodsListBean {

            public String id;
            public String goods_name;
            public double shop_price;
            public double market_price;
            public String goods_img;
            public boolean reservable;
            public String efficacy;
            public int stock_number;
            public int restrict_purchase_num;
        }
    }
}
