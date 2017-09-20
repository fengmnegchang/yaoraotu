package com.open.yaoraotu.activity.mvvm;

import android.content.Context;
import android.content.Intent;

import com.open.android.activity.common.CommonTitleBarActivity;
import com.open.yaoraotu.mvvm.R;
import com.open.yaoraotu.fragment.mvvm.HotTopPullListMvvmFragment;
import com.open.yaoraotu.utils.UrlUtils;
import com.open.yaoraotu.viewmodel.NewHotTopPullListViewModel;

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
public class HotTopPullListMvvmActivity extends CommonTitleBarActivity {
    /*
     * (non-Javadoc)
     *
     * @see com.open.enrz.activity.CommonFragmentActivity#initValue()
     */
    @Override
    protected void initValue() {
        // TODO Auto-generated method stub
//		super.initValue();
        if (getIntent().getStringExtra("URL") != null) {
            url = getIntent().getStringExtra("URL");
        } else {
            url = UrlUtils.YAORAOTU_TAOTU_XIURENWANG;
        }
        setCenterTextValue(getResources().getString(R.string.app_name));
        addfragment();
    }
    /*
     * (non-Javadoc)
     *
     * @see com.open.android.activity.CommonFragmentActivity#addfragment()
     */
    @Override
    public void addfragment() {
        // TODO Auto-generated method stub
        HotTopPullListMvvmFragment fragment =  HotTopPullListMvvmFragment.newInstance(url,true);
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_content, fragment).commit();

        NewHotTopPullListViewModel mViewModel = new NewHotTopPullListViewModel(this,"getHotList");
        fragment.setViewModel(mViewModel);
    }

    public static void startHotTopPullListMvvmActivity(Context context, String url) {
        Intent intent = new Intent();
        intent.putExtra("URL", url);
        intent.setClass(context, HotTopPullListMvvmActivity.class);
        context.startActivity(intent);
    }
}