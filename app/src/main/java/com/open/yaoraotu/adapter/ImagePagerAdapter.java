/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-9-18上午11:01:45
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.yaoraotu.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.open.android.adapter.CommonPagerAdapter;
import com.open.android.bean.db.OpenDBBean;
import com.open.android.db.service.OpenDBService;
import com.open.android.utils.ImageAsyncTask;
import com.open.yaoraotu.R;
import com.open.yaoraotu.activity.AppWebViewActivity;
import com.open.yaoraotu.bean.MasonryBean;

import java.util.List;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-9-18上午11:01:45
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class ImagePagerAdapter extends CommonPagerAdapter<MasonryBean> {
	public ImagePagerAdapter(Context mContext, List<MasonryBean> list) {
		super(mContext, list);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		final MasonryBean bean = (MasonryBean) getItem(position);
		final ViewHolder mViewHolder = new ViewHolder();
		View convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_m_image_viewpager, null);
//		mViewHolder.imageview = (ZoomImageView) convertView.findViewById(R.id.imageview);
		mViewHolder.txt_save = (TextView) convertView.findViewById(R.id.txt_save);
		mViewHolder.draweeview = (SimpleDraweeView) convertView.findViewById(R.id.draweeview);
		if (bean != null) {
			if (bean.getSrc() != null && bean.getSrc().length() > 0) {
				mViewHolder.draweeview.setImageURI(Uri.parse(bean.getSrc()));
			}
			convertView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					AppWebViewActivity.startAppWebViewActivity(mContext, bean.getHref());
				}
			});
			mViewHolder.txt_save.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
					builder.setItems(new String[] { mContext.getResources().getString(R.string.save_picture) }, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// 保存收藏
							// MiStatInterface.recordCountEvent("美图", "保存图片");
							String href = "";
							if (bean.getHref() != null) {
								if (bean.getHref().contains("_")) {
									href = bean.getHref().split("_")[0] + ".html";
								} else {
									href = bean.getHref();
								}
							}
							OpenDBBean openbean = new OpenDBBean();
							openbean.setImgsrc(bean.getSrc());
							openbean.setUrl(href);
							openbean.setType(0);
							openbean.setTitle(bean.getAlt());
							openbean.setTypename(0 + "");
							OpenDBService.insert(mContext, openbean);

							mViewHolder.draweeview.setDrawingCacheEnabled(true);
							Bitmap imageBitmap = mViewHolder.draweeview.getDrawingCache();
							if (imageBitmap != null) {
								new ImageAsyncTask(mContext, mViewHolder.draweeview, bean.getSrc()).execute(imageBitmap);
							}
						}
					});
					builder.show();
				}
			});
		}
		container.addView(convertView);
		return convertView;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	@Override
	public void startUpdate(ViewGroup container) {
		// TODO Auto-generated method stub
		super.startUpdate(container);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	@Override
	public int getItemPosition(Object object) {
		// TODO Auto-generated method stub
		return POSITION_NONE;
	}

	private class ViewHolder {
		TextView txt_save;
		SimpleDraweeView draweeview;
	}
}
