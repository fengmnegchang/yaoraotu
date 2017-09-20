/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-9-18上午11:15:25
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.yaoraotu.activity;

import android.content.Context;
import android.content.Intent;

import com.open.android.activity.common.CommonCommonFragmentActivity;
import com.open.yaoraotu.mvp.R;
import com.open.yaoraotu.fragment.ImagePagerAdapterMVPFragment;
import com.open.yaoraotu.presenter.impl.ImagePagerAdapterPresenterImpl;
import com.open.yaoraotu.utils.UrlUtils;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-9-18上午11:15:25
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class ImagePagerAdapterFragmentActivity extends CommonCommonFragmentActivity {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.qianbailu.activity.CommonFragmentActivity#initValue()
	 */
	@Override
	protected void initValue() {
		if (getIntent().getStringExtra("URL") != null) {
			url = getIntent().getStringExtra("URL");
		} else {
			url = UrlUtils.YAORAOTU_IMAGE;
		}
		// TODO Auto-generated method stub
		ImagePagerAdapterMVPFragment fragment = ImagePagerAdapterMVPFragment.newInstance(url, true);
		getSupportFragmentManager().beginTransaction().replace(R.id.id_common_fragment, fragment).commit();

		new ImagePagerAdapterPresenterImpl(this, fragment, url);
	}

	public static void startImagePagerAdapterFragmentActivity(Context context, String url) {
		Intent intent = new Intent();
		intent.putExtra("URL", url);
		intent.setClass(context, ImagePagerAdapterFragmentActivity.class);
		context.startActivity(intent);
	}
}