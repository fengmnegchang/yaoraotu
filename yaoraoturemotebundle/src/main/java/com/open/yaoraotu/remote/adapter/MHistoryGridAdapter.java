/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-6-9下午2:26:12
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.yaoraotu.remote.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.open.android.adapter.CommonAdapter;
import com.open.android.bean.OpenDBListBean;
import com.open.android.bean.db.OpenDBBean;
import com.open.yaoraotu.remote.R;
import com.open.yaoraotu.utils.ActivityUtils;

import java.util.List;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-6-9下午2:26:12
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class MHistoryGridAdapter extends CommonAdapter<OpenDBListBean> {
//	public AbstractAnimatedDrawable animatable;
	public MHistoryGridAdapter(Context mContext, List<OpenDBListBean> list) {
		super(mContext, list);
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.adapter_app_history,
					parent, false);
			viewHolder.lay_grid = (LinearLayout) convertView
					.findViewById(R.id.lay_grid);
			viewHolder.textdate = (TextView) convertView.findViewById(R.id.textdate);
			
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		final OpenDBListBean json = (OpenDBListBean) getItem(position);
		String date = json.getList().get(0).getTime().substring(0, 11);
		viewHolder.textdate.setText(date+"看"+json.getList().size()+"图集");
		viewHolder.lay_grid.removeAllViews();
		for(final OpenDBBean bean:json.getList()){
			View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_app_collection, null);
			SimpleDraweeView imageview = (SimpleDraweeView) view.findViewById(R.id.imageview);
			TextView texttitle = (TextView) view.findViewById(R.id.texttitle);
			 texttitle.setText(bean.getTitle());
			if (bean.getImgsrc()!= null && bean.getImgsrc().length() > 0) {
				Uri uri = Uri.parse(bean.getImgsrc());
				imageview.setImageURI(uri);
			}
			view.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					ActivityUtils.startBundleActivity(mContext,bean.getUrl(),"com.open.yaoraotu.activity.mvvm.MasonryImagePullListMvvmActivity");
				}
			});
			viewHolder.lay_grid.addView(view, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
		}
		return convertView;
	}

	class ViewHolder {
		LinearLayout lay_grid;
		TextView textdate;
	}
}
