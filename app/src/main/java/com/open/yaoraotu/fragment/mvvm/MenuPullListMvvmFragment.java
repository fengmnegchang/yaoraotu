package com.open.yaoraotu.fragment.mvvm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.open.yaoraotu.adapter.mvvm.MvvmMenuAdapter;
import com.open.yaoraotu.bean.MasonryBean;
import com.open.yaoraotu.databinding.FragmentMvvmLeftmenuPullListviewBinding;
import com.open.yaoraotu.json.MasonryJson;
import com.open.yaoraotu.viewmodel.NewMenuPullListViewModel;

import java.util.ArrayList;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fengguangjing
 * @createTime: 17/9/18
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class MenuPullListMvvmFragment extends CommonPullListViewMVVMFragment<MasonryBean, MasonryJson,NewMenuPullListViewModel,
        FragmentMvvmLeftmenuPullListviewBinding,MvvmMenuAdapter>{
//    public NewMenuPullListViewModel mViewModel;
//    public FragmentMvvmLeftmenuPullListviewBinding mFragmentBinding;

    public MenuPullListMvvmFragment(){
        // Requires empty public constructor
    }

    public static MenuPullListMvvmFragment newInstance(String url, boolean isVisibleToUser) {
        MenuPullListMvvmFragment fragment = new MenuPullListMvvmFragment();
        fragment.setFragment(fragment);
        fragment.setUserVisibleHint(isVisibleToUser);
        fragment.url = url;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        mFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mvvm_leftmenu_pull_listview, container, false);
        mFragmentBinding = FragmentMvvmLeftmenuPullListviewBinding.inflate(inflater,container, false);
        mFragmentBinding.setView(this);
        mFragmentBinding.setViewmodel(mViewModel);
        setHasOptionsMenu(true);
        View root = mFragmentBinding.getRoot();
        return root;
    }

    @Override
    public void initValues() {
        super.initValues();
        mPullToRefreshListView =  mFragmentBinding.pullrefreshlist;
        mAdapter = new MvvmMenuAdapter(
                getActivity(),
                new ArrayList<MasonryBean>()
        );
        mPullToRefreshListView.setAdapter(mAdapter);
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
    }

    /* (non-Javadoc)
	 * @see com.open.mmxzg.model.MArticlePullListContract.View#bindEvent()
	 */
    @Override
    public void bindEvent() {
        // TODO Auto-generated method stub
        mPullToRefreshListView.setOnRefreshListener(this);
        mPullToRefreshListView.setOnItemClickListener(this);
    }

    @Override
    public void onCallback(MasonryJson result) {
        mPullToRefreshListView.onRefreshComplete();
    }
}
