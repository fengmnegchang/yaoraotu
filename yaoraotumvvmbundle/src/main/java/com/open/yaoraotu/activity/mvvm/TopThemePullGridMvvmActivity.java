package com.open.yaoraotu.activity.mvvm;

import android.content.Context;
import android.content.Intent;

import com.open.android.activity.common.CommonTitleBarActivity;
import com.open.yaoraotu.mvvm.R;
import com.open.yaoraotu.fragment.mvvm.TopThemePullGridMvvmFragment;
import com.open.yaoraotu.utils.UrlUtils;
import com.open.yaoraotu.viewmodel.TopThemePullGridViewModel;

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
public class TopThemePullGridMvvmActivity extends CommonTitleBarActivity {
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
        TopThemePullGridMvvmFragment fragment =  TopThemePullGridMvvmFragment.newInstance(url,true);
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_content, fragment).commit();

        TopThemePullGridViewModel mViewModel = new TopThemePullGridViewModel(this,"getTopTheme");
        fragment.setViewModel(mViewModel);
    }

    public static void startTopThemePullGridMvvmActivity(Context context, String url) {
        Intent intent = new Intent();
        intent.putExtra("URL", url);
        intent.setClass(context, TopThemePullGridMvvmActivity.class);
        context.startActivity(intent);
    }
}