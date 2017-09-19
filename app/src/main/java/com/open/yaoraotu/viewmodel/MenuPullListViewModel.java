package com.open.yaoraotu.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.HeaderViewListAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.open.yaoraotu.adapter.mvvm.MvvmMenuAdapter;
import com.open.yaoraotu.bean.MasonryBean;

import java.util.List;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fengguangjing
 * @createTime: 17/9/18
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class MenuPullListViewModel extends CommonReflectJsoupViewModel {
    // These observable fields will update Views automatically

    public MenuPullListViewModel(Context mContext, String methodName) {
        super(mContext, methodName);
    }


    @SuppressWarnings("unchecked")
    //@BindingAdapter("bind:src")
//    @BindingAdapter({"src"})
    @BindingAdapter("items")
    public static void setItems(PullToRefreshListView listView, List<MasonryBean> items) {
        Log.d("setItems", "===========");
        Log.d("setItems", "=====111111======");
        ListView listView1 = listView.getRefreshableView();
        HeaderViewListAdapter listAdapter = (HeaderViewListAdapter) listView1.getAdapter();
        Log.d("setItems", "=====22222222======");
        if (listAdapter.getWrappedAdapter() instanceof MvvmMenuAdapter) {
            MvvmMenuAdapter adapter = (MvvmMenuAdapter) listAdapter.getWrappedAdapter();
            if (adapter != null) {
                Log.d("setItems", "=====adapter======");
                adapter.replaceData(items);
            }
        }
    }

}
