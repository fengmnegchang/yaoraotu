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

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshHeadGridView;
import com.open.android.fragment.common.CommonPullToRefreshGridMVPFragment2;
import com.open.android.mvp.presenter.CommonPresenter;
import com.open.android.mvp.view.CommonView;
import com.open.yaoraotu.mvp.R;
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
public class MasonryPullGridFragment extends CommonPullToRefreshGridMVPFragment2<MasonryBean, MasonryJson, CommonPresenter,MasonryAdapter> 
implements CommonView<MasonryJson, CommonPresenter> {
	public View headView;
	private SimpleDraweeView imageView;
	private TextView text_title;
	private TextView text_camLiDes;
	
	public static MasonryPullGridFragment newInstance(boolean isVisibleToUser) {
		MasonryPullGridFragment fragment = new MasonryPullGridFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		return fragment;
	}
	
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_common_pullgridview, container, false);
		mPullToRefreshHeadGridView = (PullToRefreshHeadGridView) root.findViewById(R.id.pull_refresh_grid);
		setHasOptionsMenu(true);
		headView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_grid_head, null);
		imageView = (SimpleDraweeView) headView.findViewById(R.id.draweeview);
		text_title = (TextView) headView.findViewById(R.id.text_title);
		text_camLiDes = (TextView) headView.findViewById(R.id.text_camLiDes);
		return root;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.mmxzg.model.MArticlePullListContract.View#initValues()
	 */
	@Override
	public void initValues() {
		mPullToRefreshHeadGridView.getRefreshableView().addHeaderView(headView);
		// TODO Auto-generated method stub
		mAdapter = new MasonryAdapter(getActivity(), list);
		mPullToRefreshHeadGridView.setAdapter(mAdapter);
		mPullToRefreshHeadGridView.setMode(Mode.BOTH);
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

		if(result.getHotlist()!=null && result.getHotlist().size()>0){
			text_title.setText(result.getHotlist().get(0).getTitle());
			text_camLiDes.setText(result.getHotlist().get(0).getAlt());
			imageView.setImageURI(Uri.parse(result.getHotlist().get(0).getSrc()));
		}
	}
}
