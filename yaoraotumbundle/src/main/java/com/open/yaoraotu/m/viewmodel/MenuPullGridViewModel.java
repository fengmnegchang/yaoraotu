package com.open.yaoraotu.m.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.HeaderViewListAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.open.yaoraotu.bean.MasonryBean;
import com.open.yaoraotu.m.adapter.MvvmMenuGridAdapter;

import java.util.List;

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
public class MenuPullGridViewModel extends CommonMvvmReflectJsoupViewModel {
    // These observable fields will update Views automatically

    public MenuPullGridViewModel(Context mContext, String methodName) {
        super(mContext, methodName);
    }


    @SuppressWarnings("unchecked")
    //@BindingAdapter("bind:src")
//    @BindingAdapter({"src"})
    @BindingAdapter("menupull")
    public static void setItems(PullToRefreshListView listView, List<MasonryBean> items) {
        Log.d("setItems", "===========");
        Log.d("setItems", "=====111111======");
        ListView listView1 = listView.getRefreshableView();
        HeaderViewListAdapter listAdapter = (HeaderViewListAdapter) listView1.getAdapter();
        Log.d("setItems", "=====22222222======");
        if (listAdapter.getWrappedAdapter() instanceof MvvmMenuGridAdapter) {
            MvvmMenuGridAdapter adapter = (MvvmMenuGridAdapter) listAdapter.getWrappedAdapter();
            if (adapter != null) {
                Log.d("setItems", "=====adapter======");
                adapter.replaceData(items);
            }
        }
    }

}