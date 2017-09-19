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

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.open.android.adapter.CommonAdapter;
import com.open.yaoraotu.R;
import com.open.yaoraotu.activity.AppWebViewActivity;
import com.open.yaoraotu.activity.MasonryImagePullListActivity;
import com.open.yaoraotu.bean.MasonryBean;

import java.util.List;

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
public class HotTopAdapter extends CommonAdapter<MasonryBean> {

	public HotTopAdapter(Context mContext, List<MasonryBean> list) {
		super(mContext, list);
	}
	
	/* (non-Javadoc)
	 * @see com.open.android.adapter.CommonAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.layout_grid_head, parent, false);
			viewHolder.text_title = (TextView) convertView.findViewById(R.id.text_title);
			viewHolder.text_camLiDes = (TextView) convertView.findViewById(R.id.text_camLiDes);
			viewHolder.draweeview = (SimpleDraweeView) convertView.findViewById(R.id.draweeview);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		final MasonryBean bean = (MasonryBean) getItem(position);
		if (bean != null) {
			viewHolder.text_camLiDes.setText(bean.getAlt());
			viewHolder.text_title.setText(bean.getTitle());
			viewHolder.text_title.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					AppWebViewActivity.startAppWebViewActivity(mContext, bean.getHref());
				}
			});
			if (bean.getSrc()!= null && bean.getSrc().length() > 0) {
				viewHolder.draweeview.setImageURI(Uri.parse(bean.getSrc()));
			}
			convertView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					MasonryImagePullListActivity.startMasonryImagePullListActivity(mContext,bean.getHref());
				}
			});

		}
		return convertView;
	}
	
	class ViewHolder {
		TextView text_title,text_camLiDes;
		SimpleDraweeView draweeview;
	}

}
