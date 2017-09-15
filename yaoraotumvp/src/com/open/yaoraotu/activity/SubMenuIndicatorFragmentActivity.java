/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-9-14下午5:54:01
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
import com.open.yaoraotu.fragment.SubMenuIndicatorFragment;
import com.open.yaoraotu.presenter.impl.ReflectJsoupPresenterImpl;
import com.open.yaoraotu.utils.UrlUtils;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-9-14下午5:54:01
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class SubMenuIndicatorFragmentActivity extends CommonTitleBarActivity{
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
			url = UrlUtils.YAORAOTU;
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
		SubMenuIndicatorFragment fragment = SubMenuIndicatorFragment.newInstance(url, true,getIntent().getIntExtra("PAGE_NO",0));
		getSupportFragmentManager().beginTransaction().replace(R.id.layout_content, fragment).commit();
		
		new ReflectJsoupPresenterImpl(this, fragment, url,"getSubMenuLi");
	}

	public static void startSubMenuIndicatorFragmentActivity(Context context, String url,int pageNo) {
		Intent intent = new Intent();
		intent.putExtra("URL", url);
		intent.putExtra("PAGE_NO", pageNo);
		intent.setClass(context, SubMenuIndicatorFragmentActivity.class);
		context.startActivity(intent);
	}
}