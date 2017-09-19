package com.open.yaoraotu.viewmodel.itemview;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.net.Uri;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
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
public class MasonryGridItemViewModel extends BaseObservable {
    private final ObservableField<MasonryBean> mMasonryBeanObservable = new ObservableField<>();

    public MasonryGridItemViewModel(){

    }

    public void setMasonryBeanObservable(MasonryBean mMasonryBeanObservable) {
        this.mMasonryBeanObservable.set(mMasonryBeanObservable);
    }

    /**
     * 加载src图片
     */
    //@BindingAdapter("bind:src")
    @BindingAdapter({"src"})
    public static void loadInternetImage(SimpleDraweeView draweeview, String src) {
        if (src!= null && src.length() > 0) {
            draweeview.setImageURI(Uri.parse(src));
        }
    }

    /**
     * 点击事件
     * @param view
     */
    public void onItemClick(View view) {
        MasonryImagePullListActivity.startMasonryImagePullListActivity(view.getContext(),getHref());
    }

    @Bindable
    public String getSrc(){
        return mMasonryBeanObservable.get().getSrc();
    }

    @Bindable
    public String getAlt(){
        return mMasonryBeanObservable.get().getAlt();
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