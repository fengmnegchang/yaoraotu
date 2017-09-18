/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-9-5上午11:19:26
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.yaoraotu.activity;

import android.content.Context;
import android.content.Intent;

import com.open.android.activity.common.CommonTitleBarActivity;
import com.open.yaoraotu.R;
import com.open.yaoraotu.fragment.ImagePullListHeadFootFragment;
import com.open.yaoraotu.presenter.impl.ReflectJsoupPresenterImpl;
import com.open.yaoraotu.utils.UrlUtils;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-9-5上午11:19:26
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MasonryImagePullListActivity extends CommonTitleBarActivity{
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
			url = UrlUtils.YAORAOTU_IMAGE;
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
//		MasonryPullGridFragment fragment =  MasonryPullGridFragment.newInstance(true);
		ImagePullListHeadFootFragment fragment =  ImagePullListHeadFootFragment.newInstance(true);
		getSupportFragmentManager().beginTransaction().replace(R.id.layout_content, fragment).commit();
		
		// Create the presenter
		new ReflectJsoupPresenterImpl(this, fragment,url,"getImageDetailList");
//		Fragment fragment = MArticlePullGridMVPFragment.newInstance(url, true);
//		getSupportFragmentManager().beginTransaction().replace(R.id.layout_content, fragment).commit();
	}

	public static void startMasonryImagePullListActivity(Context context, String url) {
		Intent intent = new Intent();
		intent.putExtra("URL", url);
		intent.setClass(context, MasonryImagePullListActivity.class);
		context.startActivity(intent);
	}
}
