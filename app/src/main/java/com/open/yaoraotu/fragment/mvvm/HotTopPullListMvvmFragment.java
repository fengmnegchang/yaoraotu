package com.open.yaoraotu.fragment.mvvm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.open.yaoraotu.adapter.mvvm.MvvmHotTopAdapter;
import com.open.yaoraotu.bean.MasonryBean;
import com.open.yaoraotu.databinding.FragmentMvvmHotTopPullListviewBinding;
import com.open.yaoraotu.json.MasonryJson;
import com.open.yaoraotu.viewmodel.NewHotTopPullListViewModel;

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
public class HotTopPullListMvvmFragment extends CommonPullListViewMVVMFragment<MasonryBean, MasonryJson,NewHotTopPullListViewModel,
        MvvmHotTopAdapter>{
    public FragmentMvvmHotTopPullListviewBinding mFragmentBinding;
//    public NewHotTopPullListViewModel mViewModel;
    public HotTopPullListMvvmFragment(){
        // Requires empty public constructor
    }

    public static HotTopPullListMvvmFragment newInstance(String url,boolean isVisibleToUser) {
        HotTopPullListMvvmFragment fragment = new HotTopPullListMvvmFragment();
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
        mFragmentBinding = FragmentMvvmHotTopPullListviewBinding.inflate(inflater,container, false);
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
        mAdapter = new MvvmHotTopAdapter(
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

//    /*
//   * (non-Javadoc)
//   *
//   * @see
//   * com.open.enrz.fragment.BaseV4Fragment#handlerMessage(android.os.Message)
//   */
//    @Override
//    public void handlerMessage(Message msg) {
//        switch (msg.what) {
//            case MESSAGE_HANDLER:
//                mViewModel.start();
//                break;
//            default:
//                break;
//        }
//    }
//
//    public void setViewModel(HotTopPullListViewModel viewModel) {
//        mViewModel = viewModel;
//        mViewModel.setmNavigator(HotTopPullListMvvmFragment.this);
//        mViewModel.url = url;
//    }
//
//    /* (non-Javadoc)
//	 * @see com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener#onRefresh(com.handmark.pulltorefresh.library.PullToRefreshBase)
//	 */
//    @Override
//    public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//        // TODO Auto-generated method stub
//        String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
//        // Update the LastUpdatedLabel
//        refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
//        // Do work to refresh the list here.
//        mViewModel.setMode(mPullToRefreshListView.getCurrentMode());
//        if (mPullToRefreshListView.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_START) {
//            pageNo = 1;
//            mViewModel.setPageNo(pageNo);
//            weakReferenceHandler.sendEmptyMessage(MESSAGE_HANDLER);
//        } else if (mPullToRefreshListView.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_END) {
//            pageNo++;
//            mViewModel.setPageNo(pageNo);
//            weakReferenceHandler.sendEmptyMessage(MESSAGE_HANDLER);
//        }
//    }

    /* (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onCallback(MasonryJson result) {
        // TODO Auto-generated method stub
        // TODO Auto-generated method stub
//        Log.i(TAG, "getMode ===" + mPullToRefreshListView.getCurrentMode());
//        if (mPullToRefreshListView.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_START) {
//            list.clear();
//            list.addAll(result.getList());
//            pageNo = 1;
//        } else {
//            if (result.getList() != null && result.getList().size() > 0) {
//                list.addAll(result.getList());
//            }
//        }
        // Call onRefreshComplete when the list has been refreshed.
//        mMMVVMArticleListAdapter.notifyDataSetChanged();
        mPullToRefreshListView.onRefreshComplete();
    }
}
