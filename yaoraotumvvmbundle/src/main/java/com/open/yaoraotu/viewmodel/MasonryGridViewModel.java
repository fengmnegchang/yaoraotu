package com.open.yaoraotu.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.util.Log;

import com.handmark.pulltorefresh.library.HeaderGridView;
import com.handmark.pulltorefresh.library.PullToRefreshHeadGridView;
import com.open.yaoraotu.adapter.mvvm.MvvmMasonryGridAdapter;
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
public class MasonryGridViewModel extends  CommonReflectJsoupViewModel{
    // These observable fields will update Views automatically


    public MasonryGridViewModel(Context mContext, String methodName) {
        super(mContext, methodName);
    }



    @SuppressWarnings("unchecked")
    @BindingAdapter("masonrygrid")
    public static void setItems(PullToRefreshHeadGridView gridView, List<MasonryBean> items) {
        Log.d("setItems", "===========");
        HeaderGridView gridView1 = gridView.getRefreshableView();
        if(gridView1.getHeaderViewCount()>0){
            HeaderGridView.HeaderViewGridAdapter listAdapter = (HeaderGridView.HeaderViewGridAdapter) gridView1.getAdapter();
            if (listAdapter.getWrappedAdapter() instanceof MvvmMasonryGridAdapter) {
                MvvmMasonryGridAdapter adapter = (MvvmMasonryGridAdapter) listAdapter.getWrappedAdapter();
                if (adapter != null) {
                    Log.d("setItems", "=====adapter======");
                    adapter.replaceData(items);
                }
            }
        }else{
            if (gridView1.getAdapter() instanceof MvvmMasonryGridAdapter) {
                MvvmMasonryGridAdapter adapter = (MvvmMasonryGridAdapter) gridView1.getAdapter();
                if (adapter != null) {
                    Log.d("setItems", "=====adapter======");
                    adapter.replaceData(items);
                }
            }
        }

    }

}
