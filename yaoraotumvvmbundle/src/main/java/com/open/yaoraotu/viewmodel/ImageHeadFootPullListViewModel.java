package com.open.yaoraotu.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.HeaderViewListAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.open.yaoraotu.adapter.mvvm.MvvmImageAdapter;
import com.open.yaoraotu.bean.MasonryBean;

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
public class ImageHeadFootPullListViewModel extends CommonMvvmReflectJsoupViewModel {
    // These observable fields will update Views automatically
    public ImageHeadFootPullListViewModel(Context mContext, String methodName) {
        super(mContext, methodName);
    }

    @SuppressWarnings("unchecked")
    //@BindingAdapter("bind:src")
//    @BindingAdapter({"src"})
    @BindingAdapter("imagelist")
    public static void setItems(PullToRefreshListView listView, List<MasonryBean> items) {
        Log.d("setItems", "===========");
        ListView listView1 = listView.getRefreshableView();
        HeaderViewListAdapter listAdapter = (HeaderViewListAdapter) listView1.getAdapter();
        if (listAdapter.getWrappedAdapter() instanceof MvvmImageAdapter) {
            MvvmImageAdapter adapter = (MvvmImageAdapter) listAdapter.getWrappedAdapter();
            if (adapter != null) {
                Log.d("setItems", "=====adapter======");
                adapter.replaceData(items);
            }
        }
    }


}
