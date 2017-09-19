package com.open.yaoraotu.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.util.Log;

import com.handmark.pulltorefresh.library.HeaderGridView;
import com.handmark.pulltorefresh.library.PullToRefreshHeadGridView;
import com.open.yaoraotu.adapter.mvvm.MvvmNewListAdapter;
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
public class TopThemePullGridViewModel extends  CommonReflectJsoupViewModel{
    public TopThemePullGridViewModel(Context mContext, String methodName) {
        super(mContext, methodName);
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter("items")
    public static void setItems(PullToRefreshHeadGridView gridView, List<MasonryBean> items) {
        Log.d("setItems", "===========");
        HeaderGridView gridView1 = gridView.getRefreshableView();
        if(gridView1.getHeaderViewCount()>0){
            HeaderGridView.HeaderViewGridAdapter listAdapter = (HeaderGridView.HeaderViewGridAdapter) gridView1.getAdapter();
            if (listAdapter.getWrappedAdapter() instanceof MvvmNewListAdapter) {
                MvvmNewListAdapter adapter = (MvvmNewListAdapter) listAdapter.getWrappedAdapter();
                if (adapter != null) {
                    Log.d("setItems", "=====adapter======");
                    adapter.replaceData(items);
                }
            }
        }else{
            if (gridView1.getAdapter() instanceof MvvmNewListAdapter) {
                MvvmNewListAdapter adapter = (MvvmNewListAdapter) gridView1.getAdapter();
                if (adapter != null) {
                    Log.d("setItems", "=====adapter======");
                    adapter.replaceData(items);
                }
            }
        }

    }
}
