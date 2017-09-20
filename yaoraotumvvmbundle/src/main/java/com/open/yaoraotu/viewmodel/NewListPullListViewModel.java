package com.open.yaoraotu.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.HeaderViewListAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.open.yaoraotu.adapter.mvvm.MvvmNewListAdapter;
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
public class NewListPullListViewModel extends CommonMvvmReflectJsoupViewModel {
    // These observable fields will update Views automatically
    public NewListPullListViewModel(Context mContext, String methodName) {
        super(mContext, methodName);
    }


    @SuppressWarnings("unchecked")
    //@BindingAdapter("bind:src")
//    @BindingAdapter({"src"})
    @BindingAdapter("newlistpull")
    public static void setItems(PullToRefreshListView listView, List<MasonryBean> items) {
        Log.d("setItems", "===========");
        Log.d("setItems", "====222=======");
        ListView listView1 = listView.getRefreshableView();
        HeaderViewListAdapter listAdapter = (HeaderViewListAdapter) listView1.getAdapter();
        if (listAdapter.getWrappedAdapter() instanceof MvvmNewListAdapter) {
            MvvmNewListAdapter adapter = (MvvmNewListAdapter) listAdapter.getWrappedAdapter();
            if (adapter != null) {
                Log.d("setItems", "=====adapter======");
                adapter.replaceData(items);
            }
        }
    }


}
