package com.open.yaoraotu.fragment.mvvm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.open.yaoraotu.adapter.mvvm.MvvmNewListAdapter;
import com.open.yaoraotu.bean.MasonryBean;
import com.open.yaoraotu.databinding.FragmentMvvmTopThemePullGridMvvmBinding;
import com.open.yaoraotu.json.MasonryJson;
import com.open.yaoraotu.viewmodel.TopThemePullGridViewModel;

import java.util.ArrayList;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fengguangjing
 * @createTime: 17/9/19
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class TopThemePullGridMvvmFragment extends  CommonPullGridMVVMFragment<MasonryBean, MasonryJson,TopThemePullGridViewModel,
        FragmentMvvmTopThemePullGridMvvmBinding,MvvmNewListAdapter>{
    public TopThemePullGridMvvmFragment(){
        // Requires empty public constructor
    }

    public static TopThemePullGridMvvmFragment newInstance(String url,boolean isVisibleToUser) {
        TopThemePullGridMvvmFragment fragment = new TopThemePullGridMvvmFragment();
        fragment.setFragment(fragment);
        fragment.setUserVisibleHint(isVisibleToUser);
        fragment.url = url;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        mFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mvvm_hot_top_pull_listview, container, false);
        mFragmentBinding = FragmentMvvmTopThemePullGridMvvmBinding.inflate(inflater,container, false);
        mFragmentBinding.setView(this);
        mFragmentBinding.setViewmodel(mViewModel);
        setHasOptionsMenu(true);
        View root = mFragmentBinding.getRoot();
        return root;
    }

    @Override
    public void initValues() {
        super.initValues();
        mPullToRefreshHeadGridView =  mFragmentBinding.pullrefreshgrid;
        mAdapter = new MvvmNewListAdapter(
                getActivity(),
                new ArrayList<MasonryBean>()
        );
        mPullToRefreshHeadGridView.setAdapter(mAdapter);
        mPullToRefreshHeadGridView.setMode(PullToRefreshBase.Mode.BOTH);
    }

    /* (non-Javadoc)
	 * @see com.open.mmxzg.model.MArticlePullListContract.View#bindEvent()
	 */
    @Override
    public void bindEvent() {
        // TODO Auto-generated method stub
        mPullToRefreshHeadGridView.setOnRefreshListener(this);
        mPullToRefreshHeadGridView.setOnItemClickListener(this);
    }
    @Override
    public void onCallback(MasonryJson result) {
        mPullToRefreshHeadGridView.onRefreshComplete();
    }
}
