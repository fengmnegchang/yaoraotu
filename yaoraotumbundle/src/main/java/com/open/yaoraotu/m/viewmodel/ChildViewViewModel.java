package com.open.yaoraotu.m.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.util.Log;

import com.open.android.view.ExpendGridView;
import com.open.yaoraotu.bean.MasonryBean;
import com.open.yaoraotu.json.MasonryJson;
import com.open.yaoraotu.m.adapter.MvvmMenuGridAdapter;

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
public class ChildViewViewModel extends MVVMCommonViewModel<MasonryJson> {

    public ChildViewViewModel(Context mContext) {
        super(mContext);
    }

    @SuppressWarnings("unchecked")
    //@BindingAdapter("bind:src")
//    @BindingAdapter({"src"})
    @BindingAdapter("itemschild")
    public static void setItems(ExpendGridView gridView, List<MasonryBean> items) {
        Log.d("setItems", "===========");
        Log.d("setItems", "=====111111======");
        Log.d("setItems", "===========");

        if (gridView.getAdapter() instanceof MvvmMenuGridAdapter) {
            MvvmMenuGridAdapter adapter = (MvvmMenuGridAdapter) gridView.getAdapter();
            if (adapter != null) {
                Log.d("setItems", "=====adapter======");
                adapter.replaceData(items);
            }
        }

    }

}
