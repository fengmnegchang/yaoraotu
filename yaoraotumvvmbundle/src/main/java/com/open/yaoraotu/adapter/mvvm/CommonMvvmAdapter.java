package com.open.yaoraotu.adapter.mvvm;

import android.content.Context;
import android.databinding.ViewDataBinding;

import com.open.android.adapter.CommonAdapter;

import java.util.List;

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
public class CommonMvvmAdapter<T,VDB extends ViewDataBinding> extends CommonAdapter<T> {
    public VDB mBinding;

    public CommonMvvmAdapter(Context mContext, List<T> list) {
        super(mContext, list);
    }

    public void replaceData(List<T> list) {
        setList(list);
    }

    private void setList(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
