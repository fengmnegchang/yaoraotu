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
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.open.android.adapter.CommonAdapter;
import com.open.android.bean.db.OpenDBBean;
import com.open.yaoraotu.remote.R;

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
public class MCollectionGridAdapter extends CommonAdapter<OpenDBBean> {
	public MCollectionGridAdapter(Context mContext, List<OpenDBBean> list) {
		super(mContext, list);
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.adapter_app_collection, parent, false);
			viewHolder.image_select = (ImageView) convertView.findViewById(R.id.image_select);
			viewHolder.imageview = (SimpleDraweeView) convertView.findViewById(R.id.imageview);
			viewHolder.texttitle = (TextView) convertView.findViewById(R.id.texttitle);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		final OpenDBBean bean = (OpenDBBean) getItem(position);
		if (bean != null) {
			viewHolder.texttitle.setText(bean.getTitle());
			if (bean.getImgsrc()!= null && bean.getImgsrc().length() > 0) {
//				DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.default_img).showImageForEmptyUri(R.drawable.default_img).showImageOnFail(R.drawable.default_img)
////						.cacheInMemory().cacheOnDisc().build();
//				.cacheInMemory().cacheOnDisc().imageScaleType(ImageScaleType.EXACTLY_STRETCHED).build();
//				ImageLoader.getInstance().displayImage(bean.getImgsrc(), viewHolder.imageview, options, getImageLoadingListener());
//				Picasso.with(mContext).load(bean.getImgsrc()).into(viewHolder.imageview);
				Uri uri = Uri.parse(bean.getImgsrc());
				viewHolder.imageview.setImageURI(uri);
			}
			if(bean.isEditable()){
				viewHolder.image_select.setVisibility(View.VISIBLE);
				if(bean.isSelectstate()){
					viewHolder.image_select.setImageResource(R.drawable.icon_selected);
				}else{
					viewHolder.image_select.setImageResource(R.drawable.icon_select_normal);
				}
			}else{
				viewHolder.image_select.setVisibility(View.GONE);
			}
			final ImageView image_select = viewHolder.image_select;
			viewHolder.image_select.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if(bean.isSelectstate()){
						image_select.setImageResource(R.drawable.icon_select_normal);
					}else{
						image_select.setImageResource(R.drawable.icon_selected);
					}
					bean.setSelectstate(!bean.isSelectstate());
				}
			});

//			convertView.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View v) {
//				}
//			});

		}
		return convertView;
	}

	class ViewHolder {
		SimpleDraweeView imageview;
		ImageView image_select;
		TextView texttitle;
	}
}
