/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-9-14下午1:47:22
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.yaoraotu.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.open.android.adapter.CommonAdapter;
import com.open.yaoraotu.R;
import com.open.yaoraotu.activity.AppWebViewActivity;
import com.open.yaoraotu.activity.SubMenuIndicatorFragmentActivity;
import com.open.yaoraotu.bean.MasonryBean;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-9-14下午1:47:22
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class MenuAdapter extends CommonAdapter<MasonryBean> {

	public MenuAdapter(Context mContext, List<MasonryBean> list) {
		super(mContext, list);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.android.adapter.CommonAdapter#getView(int,
	 * android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.adapter_menu_list, parent, false);
			viewHolder.text_title = (TextView) convertView.findViewById(R.id.text_title);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		final MasonryBean bean = (MasonryBean) getItem(position);
		if (bean != null) {
			viewHolder.text_title.setText(bean.getTitle());
			viewHolder.text_title.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
//					AppWebViewActivity.startAppWebViewActivity(mContext, bean.getHref());
					SubMenuIndicatorFragmentActivity.startSubMenuIndicatorFragmentActivity(mContext, bean.getHref(), position);
				}
			});
		}
		return convertView;
	}

	class ViewHolder {
		TextView text_title;
	}

}
