/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-9-5上午10:49:08
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.yaoraotu.adapter;

import java.util.List;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.open.android.adapter.CommonAdapter;
import com.open.yaoraotu.R;
import com.open.yaoraotu.activity.AppWebViewActivity;
import com.open.yaoraotu.bean.MasonryBean;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-9-5上午10:49:08
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class ImageAdapter extends CommonAdapter<MasonryBean> {

	public ImageAdapter(Context mContext, List<MasonryBean> list) {
		super(mContext, list);
	}
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.adapter_image_list, parent, false);
			viewHolder.text_title = (TextView) convertView.findViewById(R.id.text_title);
			viewHolder.text_camLiDes = (TextView) convertView.findViewById(R.id.text_camLiDes);
			viewHolder.imageview = (ImageView) convertView.findViewById(R.id.imageview);
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
//				DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.default_img).showImageForEmptyUri(R.drawable.default_img).showImageOnFail(R.drawable.default_img)
////						.cacheInMemory().cacheOnDisc().build();
//				.cacheInMemory().cacheOnDisc().imageScaleType(ImageScaleType.EXACTLY_STRETCHED).build();
//				ImageLoader.getInstance().displayImage(bean.getSrc(), viewHolder.imageview, options, getImageLoadingListener());
				Uri uri = Uri.parse(bean.getSrc());
				viewHolder.draweeview.setImageURI(uri);
			}

		}
		return convertView;
	}

	class ViewHolder {
		TextView text_title,text_camLiDes;
		ImageView imageview;
		SimpleDraweeView draweeview;
	}
}
