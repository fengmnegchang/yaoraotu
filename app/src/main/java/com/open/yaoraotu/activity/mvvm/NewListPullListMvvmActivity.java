package com.open.yaoraotu.activity.mvvm;

import android.content.Context;
import android.content.Intent;

import com.open.android.activity.common.CommonTitleBarActivity;
import com.open.yaoraotu.R;
import com.open.yaoraotu.fragment.mvvm.NewListPullListMvvmFragment;
import com.open.yaoraotu.utils.UrlUtils;
import com.open.yaoraotu.viewmodel.NewListPullListViewModel;

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
public class NewListPullListMvvmActivity extends CommonTitleBarActivity {
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
            url = UrlUtils.YAORAOTU_TAOTU_TUIGIRL;
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
        NewListPullListMvvmFragment fragment =  NewListPullListMvvmFragment.newInstance(url,true);
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_content, fragment).commit();

        NewListPullListViewModel mViewModel = new NewListPullListViewModel(this,"getNewList");
        fragment.setViewModel(mViewModel);
    }

    public static void startNewListPullListMvvmActivity(Context context, String url) {
        Intent intent = new Intent();
        intent.putExtra("URL", url);
        intent.setClass(context, NewListPullListMvvmActivity.class);
        context.startActivity(intent);
    }
}