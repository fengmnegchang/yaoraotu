/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-9-14下午5:49:09
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.yaoraotu.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.open.android.adapter.CommonFragmentPagerAdapter;
import com.open.android.fragment.BaseV4MVPPFragment;
import com.open.android.mvp.presenter.CommonPresenter;
import com.open.indicator.TabPageIndicator;
import com.open.yaoraotu.mvp.R;
import com.open.yaoraotu.bean.MasonryBean;
import com.open.yaoraotu.json.MasonryJson;
import com.open.yaoraotu.presenter.impl.MasonryPullGridPresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-9-14下午5:49:09
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class SubMenuIndicatorFragment extends BaseV4MVPPFragment<MasonryJson, SubMenuIndicatorFragment, CommonPresenter>{
	public ArrayList<MasonryBean> list = new ArrayList<MasonryBean>();
	public ViewPager viewpager;
	public TabPageIndicator indicator;
	public List<String> titleList = new ArrayList<String>();
	public List<Fragment> listRankFragment = new ArrayList<Fragment>();// view数组
	public CommonFragmentPagerAdapter mRankPagerAdapter;
	public List<MasonryPullGridPresenterImpl> listPresenterImpl = new ArrayList<MasonryPullGridPresenterImpl>();// 
	
	public static SubMenuIndicatorFragment newInstance(String url, boolean isVisibleToUser,int pageNo) {
		SubMenuIndicatorFragment fragment = new SubMenuIndicatorFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.url = url;
		fragment.pageNo = pageNo;
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_common_indicator_viewpager, container, false);
		viewpager = (ViewPager) view.findViewById(R.id.viewpager);
		indicator = (TabPageIndicator) view.findViewById(R.id.indicator);
		return view;
	}
	
	/* (non-Javadoc)
	 * @see com.open.android.fragment.BaseV4MVPPFragment#setPresenter(com.open.android.mvp.presenter.CommonPresenter)
	 */
	@Override
	public void setPresenter(CommonPresenter presenter) {
		// TODO Auto-generated method stub
//		super.setPresenter(presenter);
		mPresenter = presenter;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		mPresenter.start();
		mPresenter.setPageNo(pageNo);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.qianbailu.fragment.BaseV4Fragment#initValues()
	 */
	@Override
	public void initValues() {
		// TODO Auto-generated method stub
		super.initValues();
		mRankPagerAdapter = new CommonFragmentPagerAdapter(getChildFragmentManager(), listRankFragment, titleList);
		viewpager.setAdapter(mRankPagerAdapter);
		indicator.setViewPager(viewpager);
	}
	
	
	@Override
	public void onCallback(MasonryJson result) {
		// TODO Auto-generated method stub
		if(result==null){
			return;
		}
		list.clear();
		list.addAll(result.getList());
		titleList.clear();

		MasonryPullGridFragment fragment;
		for (int i=0;i< result.getList().size();i++) {
			MasonryBean bean = result.getList().get(i);
			titleList.add(bean.getTitle());
			if(i==0){
				fragment = MasonryPullGridFragment.newInstance(true);
			}else{
				fragment = MasonryPullGridFragment.newInstance(false);
			}
			listPresenterImpl.add(new MasonryPullGridPresenterImpl(getActivity(), fragment, bean.getHref()));
			listRankFragment.add(fragment);
		}
		mRankPagerAdapter.notifyDataSetChanged();
		indicator.notifyDataSetChanged();
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
}
