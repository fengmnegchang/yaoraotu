package com.open.yaoraotu.m.activity;

import android.content.Context;
import android.content.Intent;

import com.open.android.activity.common.CommonTitleBarActivity;
import com.open.yaoraotu.m.R;
import com.open.yaoraotu.m.fragment.HomeDotPagerMvvmFragment;
import com.open.yaoraotu.m.viewmodel.HomeDotViewPagerViewModel;
import com.open.yaoraotu.utils.UrlUtils;

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
public class HomeDotPagerMvvmActivity extends CommonTitleBarActivity {
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
            url = UrlUtils.YAORAOTU_TAOTU;
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
        HomeDotPagerMvvmFragment fragment =  HomeDotPagerMvvmFragment.newInstance(url,true);
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_content, fragment).commit();

        HomeDotViewPagerViewModel mViewModel = new HomeDotViewPagerViewModel(this,"getTopPager");
        fragment.setViewModel(mViewModel);
    }

    public static void startHomeDotPagerMvvmActivity(Context context, String url) {
        Intent intent = new Intent();
        intent.putExtra("URL", url);
        intent.setClass(context, HomeDotPagerMvvmActivity.class);
        context.startActivity(intent);
    }
}