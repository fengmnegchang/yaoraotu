package com.open.yaoraotu.adapter.mvvm;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.View;
import android.view.ViewGroup;

import com.open.android.adapter.CommonPagerAdapter;

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
public class CommonMvvmPagerAdapter<T,VDB extends ViewDataBinding> extends CommonPagerAdapter<T> {
    public VDB mBinding;

    public CommonMvvmPagerAdapter(Context mContext, List<T> list) {
        super(mContext, list);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public void startUpdate(ViewGroup container) {
        // TODO Auto-generated method stub
        super.startUpdate(container);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return arg0 == arg1;
    }

    @Override
    public int getItemPosition(Object object) {
        // TODO Auto-generated method stub
        return POSITION_NONE;
    }

    public void replaceData(List<T> list) {
        setList(list);
    }

    private void setList(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
