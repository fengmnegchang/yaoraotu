package com.open.yaoraotu.m.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.open.yaoraotu.bean.MasonryGroupBean;
import com.open.yaoraotu.m.adapter.MvvmGroupHomeExpandableListAdapter;

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
public class GroupHomeExpandableListViewModel extends CommonMvvmReflectJsoupExpandableListViewModel {

    public GroupHomeExpandableListViewModel(Context mContext, String methodName) {
        super(mContext,methodName);
    }

    @SuppressWarnings("unchecked")
    //@BindingAdapter("bind:src")
//    @BindingAdapter({"src"})
    @BindingAdapter("itemshome")
    public static void setItems(ExpandableListView listView, List<MasonryGroupBean> items) {
        Log.d("setItems", "===========");
        ExpandableListAdapter listAdapter = (ExpandableListAdapter) listView.getExpandableListAdapter();
        if (listAdapter  instanceof MvvmGroupHomeExpandableListAdapter) {
            MvvmGroupHomeExpandableListAdapter adapter = (MvvmGroupHomeExpandableListAdapter) listAdapter;
            if (adapter != null) {
                Log.d("setItems", "=====adapter======");
                adapter.replaceData(items);
            }
        }
    }


}
