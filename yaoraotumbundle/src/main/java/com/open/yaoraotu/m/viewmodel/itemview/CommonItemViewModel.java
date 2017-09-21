package com.open.yaoraotu.m.viewmodel.itemview;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.net.Uri;

import com.facebook.drawee.view.SimpleDraweeView;
import com.open.yaoraotu.bean.MasonryBean;

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
public class CommonItemViewModel extends BaseObservable {
    private final ObservableField<MasonryBean> mMasonryBeanObservable = new ObservableField<>();

    public CommonItemViewModel(){

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

//    /**
//     * 点击事件
//     * @param view
//     */
//    public void onItemClick(View view) {
//        ImagePagerAdapterFragmentActivity.startImagePagerAdapterFragmentActivity(view.getContext(),getHref());
//    }

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