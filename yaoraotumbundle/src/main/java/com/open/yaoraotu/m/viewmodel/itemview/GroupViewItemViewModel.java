package com.open.yaoraotu.m.viewmodel.itemview;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;

import com.open.yaoraotu.bean.MasonryGroupBean;

//import com.open.yaoraotu.activity.SubMenuIndicatorFragmentActivity;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fengguangjing
 * @createTime: 17/9/19
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class GroupViewItemViewModel extends BaseObservable {
    private final ObservableField<MasonryGroupBean> masonryGroupBeanObservable = new ObservableField<>();

    public GroupViewItemViewModel(){

    }

    public void setMasonryGroupBeanObservable(MasonryGroupBean masonryGroupBeanObservable) {
        this.masonryGroupBeanObservable.set(masonryGroupBeanObservable);
    }

    @Bindable
    public String getTitle(){
        return masonryGroupBeanObservable.get().getTitle();
    }

    @Bindable
    public String getHref(){
        return masonryGroupBeanObservable.get().getHref();
    }
}
