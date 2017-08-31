/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-31下午4:04:20
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.yaoraotu.activity;

import android.content.Context;
import android.content.Intent;

import com.open.android.activity.common.CommonWebViewActivity;
import com.open.yaoraotu.utils.UrlUtils;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-31下午4:04:20
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class AppWebViewActivity extends CommonWebViewActivity {
	/* (non-Javadoc)
	 * @see com.open.android.activity.common.CommonWebViewActivity#initValue()
	 */
	@Override
	protected void initValue() {
		// TODO Auto-generated method stub
		super.initValue();
	}
	/* (non-Javadoc)
	 * @see com.open.android.activity.common.CommonWebViewActivity#loadUrl()
	 */
	@Override
	public void loadUrl() {
		// TODO Auto-generated method stub
		if(url==null || url.length()==0){
			url = UrlUtils.YAORAOTU;
		}
		webview.loadUrl(url);
	}
	
 
	
	public static void startAppWebViewActivity(Context context, String url) {
		Intent intent = new Intent();
		intent.putExtra("URL", url);
		intent.setClass(context, AppWebViewActivity.class);
		context.startActivity(intent);
	}
}