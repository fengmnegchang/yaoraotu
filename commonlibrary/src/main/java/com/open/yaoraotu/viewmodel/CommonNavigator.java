package com.open.yaoraotu.viewmodel;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fengguangjing
 * @createTime: 17/8/21
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public interface CommonNavigator<T> {
    /**
     * 取数据回调，刷新控件
     * @param result
     */
    void onCallback(T result);

//    /**
//     * 点击事件
//     * @param parent
//     * @param view
//     * @param position
//     * @param id
//     */
//    void onItemClick(AdapterView<?> parent, View view, int position, long id);
}
