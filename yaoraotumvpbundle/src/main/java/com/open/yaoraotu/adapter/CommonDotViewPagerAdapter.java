/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-9-15上午10:09:06
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.yaoraotu.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.open.android.adapter.CommonPagerAdapter;
import com.open.yaoraotu.mvp.R;
import com.open.yaoraotu.activity.AppWebViewActivity;
import com.open.yaoraotu.bean.MasonryBean;

import java.util.List;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-9-15上午10:09:06
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class CommonDotViewPagerAdapter extends CommonPagerAdapter<MasonryBean> {

	public CommonDotViewPagerAdapter(Context mContext, List<MasonryBean> list) {
		super(mContext, list);
	}
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		final MasonryBean bean = (MasonryBean) getItem(position);
		final ViewHolder mViewHolder = new ViewHolder();
		View convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_m_viewpager, null);
		mViewHolder.draweeview = (SimpleDraweeView) convertView.findViewById(R.id.draweeview);
		mViewHolder.txt_alt = (TextView) convertView.findViewById(R.id.txt_alt);
		if (bean != null) {
			if (bean.getSrc() != null && bean.getSrc().length() > 0) {
//				DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.default_img).showImageForEmptyUri(R.drawable.default_img).showImageOnFail(R.drawable.default_img)
//						.cacheInMemory().cacheOnDisc().build();
//				ImageLoader.getInstance().displayImage(bean.getSrc(), mViewHolder.imageview, options, null);
				mViewHolder.draweeview.setImageURI(Uri.parse(bean.getSrc()));
			}
			mViewHolder.txt_alt.setText(bean.getTitle());
			convertView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					AppWebViewActivity.startAppWebViewActivity(mContext, bean.getHref());
				}
			});
		}
		container.addView(convertView);
		return convertView;
	}

	private class ViewHolder {
		SimpleDraweeView draweeview;
		TextView txt_alt;
	}
}