package com.open.yaoraotu.viewmodel.itemview;

import android.view.View;

import com.open.yaoraotu.activity.MasonryImagePullListActivity;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fengguangjing
 * @createTime: 17/9/18
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class MvvmHotTopItemViewModel extends CommonItemViewModel {

    public MvvmHotTopItemViewModel(){

    }

    /**
     * 点击事件
     * @param view
     */
    public void onItemClick(View view) {
        MasonryImagePullListActivity.startMasonryImagePullListActivity(view.getContext(),getHref());
    }

}
