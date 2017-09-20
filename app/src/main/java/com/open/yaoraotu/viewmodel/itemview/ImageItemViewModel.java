package com.open.yaoraotu.viewmodel.itemview;

import android.view.View;

import com.open.yaoraotu.activity.ImagePagerAdapterFragmentActivity;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fengguangjing
 * @createTime: 17/9/20
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class ImageItemViewModel extends CommonItemViewModel {

    public ImageItemViewModel() {

    }

    /**
     * 点击事件
     *
     * @param view
     */
    public void onItemClick(View view) {
        ImagePagerAdapterFragmentActivity.startImagePagerAdapterFragmentActivity(view.getContext(), getHref());
    }

}