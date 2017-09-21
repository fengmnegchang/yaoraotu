package com.open.yaoraotu.m.viewmodel;

import android.content.Context;

import com.open.yaoraotu.json.MasonryJson;

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
public class CommonMvvmReflectJsoupViewModel extends CommonReflectJsoupViewModel{

    public CommonMvvmReflectJsoupViewModel(Context mContext, String methodName) {
        super(mContext, methodName);
    }

    @Override
    public void onCallback(MasonryJson result) {
        super.onCallback(result);
        notifyPropertyChanged(com.open.yaoraotu.m.BR.empty); // It's a @Bindable so update manually
    }
}
