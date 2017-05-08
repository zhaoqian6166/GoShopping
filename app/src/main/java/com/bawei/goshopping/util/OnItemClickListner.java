package com.bawei.goshopping.util;

import android.view.View;

/**
 * Created by 赵倩 on 2017/4/23.
 * <p>
 * 类的用途：
 */
public interface OnItemClickListner {
    /**
     * item点击回调
     *
     * @param view
     * @param position
     */
    void onItemClick(View view, int position);

    /**
     * 删除按钮回调
     *
     * @param position
     */
    void onDeleteClick(int position);
}
