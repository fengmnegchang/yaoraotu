package com.open.yaoraotu.viewmodel.itemview;

import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.view.View;

import com.open.yaoraotu.activity.SubMenuIndicatorFragmentActivity;

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
public class MenuItemViewModel extends CommonItemViewModel{
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


    /**
     * 点击事件
     * @param view
     */
    public void onItemClick(View view) {
        SubMenuIndicatorFragmentActivity.startSubMenuIndicatorFragmentActivity(view.getContext(), getHref(), getPosition());
    }


    @Bindable
    public Integer getPosition(){
        return positionObservable.get();
    }

}
