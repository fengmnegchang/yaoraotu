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

import com.open.android.fragment.common.CommonExpendGridMVPFragment;
import com.open.android.mvp.presenter.CommonPresenter;
import com.open.yaoraotu.adapter.MasonryAdapter;
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
public class MasonryExpendGridFragment extends CommonExpendGridMVPFragment<MasonryBean, MasonryJson, CommonPresenter, MasonryAdapter> {

	public static MasonryExpendGridFragment newInstance(boolean isVisibleToUser) {
		MasonryExpendGridFragment fragment = new MasonryExpendGridFragment();
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
		mAdapter = new MasonryAdapter(getActivity(), list);
		mExpendGridView.setAdapter(mAdapter);
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
		list.clear();
		list.addAll(result.getList());
		pageNo = 1;
		mPresenter.setPageNo(1);
		mAdapter.notifyDataSetChanged();
		// Call onRefreshComplete when the list has been refreshed.
	}
}
