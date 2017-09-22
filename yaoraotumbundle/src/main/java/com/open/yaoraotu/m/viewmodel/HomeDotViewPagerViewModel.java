package com.open.yaoraotu.m.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.open.yaoraotu.bean.MasonryBean;
import com.open.yaoraotu.m.adapter.MvvmHomeDotViewPagerAdapter;

import java.util.List;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fengguangjing
 * @createTime: 17/9/22
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class HomeDotViewPagerViewModel extends CommonMvvmReflectJsoupViewModel{

    public HomeDotViewPagerViewModel(Context mContext, String methodName) {
        super(mContext, methodName);
    }

    @SuppressWarnings("unchecked")
    //@BindingAdapter("bind:src")
//    @BindingAdapter({"src"})
    @BindingAdapter("itemsdot")
    public static void setItems(ViewPager viewpager, List<MasonryBean> items) {
        Log.d("setItems", "===========");
        if (viewpager.getAdapter() instanceof MvvmHomeDotViewPagerAdapter) {
            MvvmHomeDotViewPagerAdapter adapter = (MvvmHomeDotViewPagerAdapter) viewpager.getAdapter();
            if (adapter != null && items!=null && items.size()>0) {
                Log.d("setItems", "=====adapter======");
                adapter.replaceData(items);
            }
        }
    }
}
