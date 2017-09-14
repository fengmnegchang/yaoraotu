/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-9-14下午1:53:01
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.yaoraotu.fragment;

import com.open.android.fragment.common.CommonExpendListMVPFragment;
import com.open.android.mvp.presenter.CommonPresenter;
import com.open.yaoraotu.adapter.HotTopAdapter;
import com.open.yaoraotu.bean.MasonryBean;
import com.open.yaoraotu.json.MasonryJson;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-9-14下午1:53:01
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class HotTopExpendListFragment extends CommonExpendListMVPFragment<MasonryBean, MasonryJson, CommonPresenter, HotTopAdapter> {
	public static HotTopExpendListFragment newInstance(boolean isVisibleToUser) {
		HotTopExpendListFragment fragment = new HotTopExpendListFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		return fragment;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.mmxzg.model.MArticlePullListContract.View#initValues()
	 */
	@Override
	public void initValues() {
		// TODO Auto-generated method stub
		mAdapter = new HotTopAdapter(getActivity(), list);
		mExpendListView.setAdapter(mAdapter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.open.android.fragment.common.CommonPullToRefreashListMVPFragment#
	 * onCallback(com.open.android.json.CommonJson)
	 */
	@Override
	public void onCallback(MasonryJson result) {
		// TODO Auto-generated method stub
		if (result == null) {
			return;
		}
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		list.clear();
		list.addAll(result.getList());
		pageNo = 1;
		mPresenter.setPageNo(1);
		mAdapter.notifyDataSetChanged();
		// Call onRefreshComplete when the list has been refreshed.
	}

}
