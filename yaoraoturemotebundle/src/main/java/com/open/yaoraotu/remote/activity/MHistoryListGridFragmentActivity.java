package com.open.yaoraotu.remote.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.open.android.activity.common.CommonTitleBarActivity;
import com.open.yaoraotu.remote.R;
import com.open.yaoraotu.remote.fragment.MHistoryListGridMVPFragment;
import com.open.yaoraotu.remote.presenter.impl.MHistoryListGridPresenterImpl;


public class MHistoryListGridFragmentActivity extends CommonTitleBarActivity {
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
			url = "";
		}
		addfragment();
		
		setCenterTextValue("浏览历史");
		setRightTextValue("清空");
		setRightTextVisivable(true);
		setLeftImageResId(R.drawable.left01);
		setLeftTextVisivable(false);
		Toast.makeText(this,"浏览历史",Toast.LENGTH_LONG).show();
	}
	
	/* (non-Javadoc)
	 * @see com.open.mm.activity.m.MCommonTitleBarActivity#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int i = v.getId();
		if (i == R.id.txt_right) {//清空
//			DialogUitls.commondialog(this, "提示", "您看过的内容会被记录下来，方便您再次快速浏览。确认要清空历史记录吗？", "残忍清除", "容我看看", new OnClickListener() {
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					MHistoryListGridMVPFragment fragment = (MHistoryListGridMVPFragment) getSupportFragmentManager()
//							.findFragmentById(R.id.layout_content);
//					fragment.cleanHistory();
//					dialog.dismiss();
//				}
//			}, new OnClickListener() {
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					dialog.dismiss();
//				}
//			});


		} else if (i == R.id.id_iv_left) {
			finish();

		} else {
			super.onClick(v);

		}
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.android.activity.CommonFragmentActivity#addfragment()
	 */
	@Override
	public void addfragment() {
		// TODO Auto-generated method stub
		MHistoryListGridMVPFragment fragment = MHistoryListGridMVPFragment.newInstance(true);
		getSupportFragmentManager().beginTransaction().replace(R.id.layout_content, fragment).commit();
		new MHistoryListGridPresenterImpl(this, fragment);
	}

	public static void startMHistoryListGridFragmentActivity(Context context, String url) {
		Intent intent = new Intent();
		intent.putExtra("URL", url);
		intent.setClass(context, MHistoryListGridFragmentActivity.class);
		context.startActivity(intent);
	}
}

