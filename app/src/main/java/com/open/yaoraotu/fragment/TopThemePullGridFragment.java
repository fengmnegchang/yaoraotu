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

import android.util.Log;

import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.open.android.fragment.common.CommonPullToRefreshGridMVPFragment2;
import com.open.android.mvp.presenter.CommonPresenter;
import com.open.android.mvp.view.CommonView;
import com.open.yaoraotu.adapter.NewListAdapter;
import com.open.yaoraotu.bean.MasonryBean;
import com.open.yaoraotu.json.MasonryJson;

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
public class TopThemePullGridFragment extends CommonPullToRefreshGridMVPFragment2<MasonryBean, MasonryJson, CommonPresenter,NewListAdapter> 
implements CommonView<MasonryJson, CommonPresenter> {
	
	public static TopThemePullGridFragment newInstance(boolean isVisibleToUser) {
		TopThemePullGridFragment fragment = new TopThemePullGridFragment();
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
		mAdapter = new NewListAdapter(getActivity(), list);
		mPullToRefreshHeadGridView.setAdapter(mAdapter);
		mPullToRefreshHeadGridView.setMode(Mode.PULL_FROM_START);
		mPullToRefreshHeadGridView.getRefreshableView().setNumColumns(3);
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
		Log.i(TAG, "getMode ===" + mPullToRefreshHeadGridView.getCurrentMode());
		if (mPullToRefreshHeadGridView.getCurrentMode() == Mode.PULL_FROM_START) {
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
		mPullToRefreshHeadGridView.onRefreshComplete();
 
	}
}
