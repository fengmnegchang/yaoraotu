package com.open.yaoraotu.activity.mvvm;

import android.content.Context;
import android.content.Intent;

import com.open.android.activity.common.CommonCommonFragmentActivity;
import com.open.yaoraotu.mvvm.R;
import com.open.yaoraotu.fragment.mvvm.ImagePagerAdapterMvvmFragment;
import com.open.yaoraotu.utils.UrlUtils;
import com.open.yaoraotu.viewmodel.ImagePagerAdapterViewModel;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fengguangjing
 * @createTime: 17/9/20
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class ImagePagerAdapterFragmentMvvmActivity extends CommonCommonFragmentActivity {
    /*
     * (non-Javadoc)
     *
     * @see com.open.enrz.activity.CommonFragmentActivity#initValue()
     */
    @Override
    protected void initValue() {
        // TODO Auto-generated method stub
        if (getIntent().getStringExtra("URL") != null) {
            url = getIntent().getStringExtra("URL");
        } else {
            url = UrlUtils.YAORAOTU_IMAGE;
        }
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
        ImagePagerAdapterMvvmFragment fragment = ImagePagerAdapterMvvmFragment.newInstance(url, true);
        getSupportFragmentManager().beginTransaction().replace(R.id.id_common_fragment, fragment).commit();

        ImagePagerAdapterViewModel mViewModel = new ImagePagerAdapterViewModel(this);
        fragment.setViewModel(mViewModel);
    }

    public static void startImagePagerAdapterFragmentMvvmActivity(Context context, String url) {
        Intent intent = new Intent();
        intent.putExtra("URL", url);
        intent.setClass(context, ImagePagerAdapterFragmentMvvmActivity.class);
        context.startActivity(intent);
    }
}