/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-9-18上午11:06:59
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.yaoraotu.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.open.android.fragment.BaseV4MVPPFragment;
import com.open.yaoraotu.R;
import com.open.yaoraotu.adapter.ImagePagerAdapter;
import com.open.yaoraotu.bean.MasonryBean;
import com.open.yaoraotu.json.MasonryJson;
import com.open.yaoraotu.presenter.ImagePagerAdapterPresenter;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-9-18上午11:06:59
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class ImagePagerAdapterMVPFragment extends BaseV4MVPPFragment<MasonryJson, ImagePagerAdapterMVPFragment,ImagePagerAdapterPresenter>  {
	public ViewPager viewpager;
	public ImagePagerAdapter mMImagePagerAdapter;
	public List<MasonryBean> list = new ArrayList<MasonryBean>();
	// public String url = UrlUtils.PXING_IMAGE;
	// public int position;
	public TextView text_page_foot;
	public boolean isHasData;

	public static ImagePagerAdapterMVPFragment newInstance(String url, boolean isVisibleToUser) {
		ImagePagerAdapterMVPFragment fragment = new ImagePagerAdapterMVPFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.url = url;
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_m_image_viewpager, container, false);
		viewpager = (ViewPager) view.findViewById(R.id.viewpager);
		text_page_foot = (TextView) view.findViewById(R.id.text_page_foot);
		return view;
	}
	
	/* (non-Javadoc)
	 * @see com.open.android.fragment.BaseV4MVPPFragment#setPresenter(com.open.android.mvp.presenter.CommonPresenter)
	 */
	@Override
	public void setPresenter(ImagePagerAdapterPresenter presenter) {
		// TODO Auto-generated method stub
		mPresenter = presenter;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.qianbailu.fragment.BaseV4Fragment#initValues()
	 */
	@Override
	public void initValues() {
		// TODO Auto-generated method stub
		mMImagePagerAdapter = new ImagePagerAdapter(getActivity(), list);
		viewpager.setAdapter(mMImagePagerAdapter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.open.enrz.fragment.BaseV4Fragment#handlerMessage(android.os.Message)
	 */
	@Override
	public void handlerMessage(Message msg) {
		// TODO Auto-generated method stub
		switch (msg.what) {
		case MESSAGE_HANDLER:
			mPresenter.doAsync();
			break;
		default:
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.qianbailu.fragment.BaseV4Fragment#bindEvent()
	 */
	@Override
	public void bindEvent() {
		// TODO Auto-generated method stub
		super.bindEvent();
		viewpager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				text_page_foot.setText((position + 1) + " / " + list.size());
				ImagePagerAdapterMVPFragment.this.position = position;
				pageNo++;
				mPresenter.setPageNo(pageNo);
				mPresenter.setPosition(position);
				mPresenter.setUrl(list.get(position).getHref());
				mPresenter.doAsync();
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.open.umei.activity.BaseFragmentActivity#onCallback(java.lang.Object)
	 */
	@Override
	public void onCallback(MasonryJson result) {
		// TODO Auto-generated method stub
		// super.onCallback(result);
		if (result == null) {
			return;
		}
		if (list.size() == 0) {
			// list.clear();
			list.addAll(result.getList());
		} else {
			if (isHasData) {
				list.clear();
				list.addAll(result.getList());
				isHasData = false;
			} else {
				list.set(result.getCurrentPosition(), result.getList().get(0));
			}
		}

		mMImagePagerAdapter.notifyDataSetChanged();
		// text_page_foot.setText((position+1)+" / "+list.size());
	}

}