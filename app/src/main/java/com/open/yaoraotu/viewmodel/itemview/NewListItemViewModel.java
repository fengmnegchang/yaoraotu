package com.open.yaoraotu.viewmodel.itemview;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.view.View;

import com.open.yaoraotu.activity.MasonryImagePullListActivity;
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
public class NewListItemViewModel extends BaseObservable {
    private final ObservableField<MasonryBean> mMasonryBeanObservable = new ObservableField<>();

    public NewListItemViewModel(){

    }

    public void setMasonryBeanObservable(MasonryBean mMasonryBeanObservable) {
        this.mMasonryBeanObservable.set(mMasonryBeanObservable);
    }

    /**
     * 点击事件
     * @param view
     */
    public void onItemClick(View view) {
        MasonryImagePullListActivity.startMasonryImagePullListActivity(view.getContext(),getHref());
    }


    @Bindable
    public String getTitle(){
        return mMasonryBeanObservable.get().getTitle();
    }

    @Bindable
    public String getHref(){
        return mMasonryBeanObservable.get().getHref();
    }

}
