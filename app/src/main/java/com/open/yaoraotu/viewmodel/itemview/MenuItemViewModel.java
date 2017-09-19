package com.open.yaoraotu.viewmodel.itemview;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.view.View;

import com.open.yaoraotu.activity.SubMenuIndicatorFragmentActivity;
import com.open.yaoraotu.bean.MasonryBean;

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
public class MenuItemViewModel extends BaseObservable {
    private final ObservableField<MasonryBean> mMasonryBeanObservable = new ObservableField<>();
    private final ObservableField<Integer> positionObservable = new ObservableField<>();

    public MenuItemViewModel(){

    }


    /**
     * item position位置
     * @param positionObservable
     */
    public void setPositionObservable(Integer positionObservable) {
        this.positionObservable.set(positionObservable);
    }

    public void setMasonryBeanObservable(MasonryBean mMasonryBeanObservable) {
        this.mMasonryBeanObservable.set(mMasonryBeanObservable);
    }

    /**
     * 点击事件
     * @param view
     */
    public void onItemClick(View view) {
        SubMenuIndicatorFragmentActivity.startSubMenuIndicatorFragmentActivity(view.getContext(), getHref(), getPosition());
    }


    @Bindable
    public String getTitle(){
        return mMasonryBeanObservable.get().getTitle();
    }

    @Bindable
    public String getHref(){
        return mMasonryBeanObservable.get().getHref();
    }

    @Bindable
    public Integer getPosition(){
        return positionObservable.get();
    }

}
