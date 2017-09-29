/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-9-5上午10:58:59
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.yaoraotu.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.open.android.fragment.common.CommonPullToRefreashListMVPFragment2;
import com.open.android.mvp.presenter.CommonPresenter;
import com.open.android.mvp.view.CommonView;
import com.open.yaoraotu.adapter.MasonryAdapter;
import com.open.yaoraotu.bean.MasonryBean;
import com.open.yaoraotu.json.MasonryJson;
import com.open.yaoraotu.mvp.R;
import com.open.yaoraotu.presenter.impl.ReflectJsoupPresenterImpl;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-9-5上午10:58:59
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class ThemePullListHeadFootFragment extends CommonPullToRefreashListMVPFragment2<MasonryBean, MasonryJson, CommonPresenter,MasonryAdapter> 
implements CommonView<MasonryJson, CommonPresenter> {
	public View headview,footview;
	
	public static ThemePullListHeadFootFragment newInstance(boolean isVisibleToUser,String url) {
		ThemePullListHeadFootFragment fragment = new ThemePullListHeadFootFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.url = url;
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_common_pulllistview, container, false);
		mPullToRefreshListView = (PullToRefreshListView) root.findViewById(R.id.pull_refresh_list);
		setHasOptionsMenu(true);
		headview = LayoutInflater.from(getActivity()).inflate(R.layout.layout_m_head,null);
		footview = LayoutInflater.from(getActivity()).inflate(R.layout.layout_m_foot,null);
		return root;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.mmxzg.model.MArticlePullListContract.View#initValues()
	 */
	@Override
	public void initValues() {
		// TODO Auto-generated method stub
		//head
		mPullToRefreshListView.getRefreshableView().addHeaderView(headview);
		//foot
		mPullToRefreshListView.getRefreshableView().addFooterView(footview);
		HotTopExpendListFragment fragment =  HotTopExpendListFragment.newInstance(true);
		getChildFragmentManager().beginTransaction().replace(R.id.id_m_head, fragment).commit();
		// Create the presenter
		new ReflectJsoupPresenterImpl(getActivity(), fragment,url,"getHotList");


		NewListExpendListFragment ffragment =  NewListExpendListFragment.newInstance(true);
		getChildFragmentManager().beginTransaction().replace(R.id.id_m_foot, ffragment).commit();
		// Create the presenter
		new ReflectJsoupPresenterImpl(getActivity(), ffragment,url,"getNewList");


		mAdapter = new MasonryAdapter(getActivity(), list);
		mPullToRefreshListView.setAdapter(mAdapter);
		mPullToRefreshListView.setMode(Mode.BOTH);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.open.mmxzg.model.MArticlePullListContract.View#onCallback(com.open
	 * .mmxzg.json.m.MArticleJson)
	 */
	@Override
	public void onCallback(MasonryJson result) {
		if (result == null) {
			return;
		}
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Log.i(TAG, "getMode ===" + mPullToRefreshListView.getCurrentMode());
		if (mPullToRefreshListView.getCurrentMode() == Mode.PULL_FROM_START) {
			list.clear();
			list.addAll(result.getList());
			pageNo = 1;
			mPresenter.setPageNo(1);
		} else {
			if (result.getList() != null && result.getList().size() > 0) {
				list.addAll(result.getList());
			}
		}
		mAdapter.notifyDataSetChanged();
		// Call onRefreshComplete when the list has been refreshed.
		mPullToRefreshListView.onRefreshComplete();
		 
	}
}
