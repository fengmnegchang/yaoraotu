/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-6-9下午2:08:56
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.yaoraotu.remote.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.open.android.activity.common.CommonTitleBarActivity;
import com.open.yaoraotu.remote.R;
import com.open.yaoraotu.remote.fragment.MCollectionGridMVPFragment;
import com.open.yaoraotu.remote.presenter.impl.MCollectionGridPresenterImpl;


/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-6-9下午2:08:56
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MCollectionGridFragmentActivity extends CommonTitleBarActivity {
	public boolean editable;
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.enrz.activity.CommonFragmentActivity#initValue()
	 */
	@Override
	protected void initValue() {
		// TODO Auto-generated method stub
		super.initValue();
		if (getIntent().getStringExtra("URL") != null) {
			url = getIntent().getStringExtra("URL");
		} else {
			url = "";
		}
		addfragment();
		
		setCenterTextValue("我的收藏");
		setRightTextValue("编辑");
		setRightTextVisivable(true);
		setLeftImageResId(R.drawable.left01);
		setLeftTextVisivable(false);
		Toast.makeText(this,"我的收藏",Toast.LENGTH_LONG).show();
	}
	
	
	/* (non-Javadoc)
	 * @see com.open.mm.activity.m.MCommonTitleBarActivity#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int i = v.getId();
		if (i == R.id.txt_right) {
			if (editable) {
				setRightTextValue("编辑");
			} else {
				setRightTextValue("完成");
			}
			MCollectionGridMVPFragment fragment = (MCollectionGridMVPFragment) getSupportFragmentManager()
					.findFragmentById(R.id.layout_content);
			fragment.setEditable(editable);
			editable = !editable;

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
		MCollectionGridMVPFragment fragment = MCollectionGridMVPFragment.newInstance(true);
		getSupportFragmentManager().beginTransaction().replace(R.id.layout_content, fragment).commit();
		
		new MCollectionGridPresenterImpl(this, fragment);
	}

	public static void startMCollectionGridFragmentActivity(Context context, String url) {
		Intent intent = new Intent();
		intent.putExtra("URL", url);
		intent.setClass(context, MCollectionGridFragmentActivity.class);
		context.startActivity(intent);
	}
}

