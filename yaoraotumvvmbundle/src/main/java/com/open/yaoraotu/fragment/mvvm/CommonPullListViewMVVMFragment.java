package com.open.yaoraotu.fragment.mvvm;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.open.android.bean.CommonBean;
import com.open.android.fragment.BaseV4MVPFragment;
import com.open.android.json.CommonJson;
import com.open.yaoraotu.adapter.mvvm.CommonMvvmAdapter;
import com.open.yaoraotu.viewmodel.CommonNavigator;
import com.open.yaoraotu.viewmodel.MVVMCommonViewModel;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fengguangjing
 * @createTime: 17/8/22
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
//,FVDB extends ViewDataBinding,
    //M extends MVVMCommonViewModel<T>
public class CommonPullListViewMVVMFragment<B extends CommonBean,T extends CommonJson,M extends MVVMCommonViewModel<T>,FVDB extends ViewDataBinding,A extends CommonMvvmAdapter>
        extends BaseV4MVPFragment<B,CommonPullListViewMVVMFragment> implements OnRefreshListener<ListView>,OnItemClickListener,CommonNavigator<T> {
    public M mViewModel;
    public FVDB mFragmentBinding;
    public A mAdapter;
    public PullToRefreshListView mPullToRefreshListView;
//    public List<MArticleBean> list = new ArrayList<MArticleBean>();

    public CommonPullListViewMVVMFragment() {
        // Requires empty public constructor
    }

    public static CommonPullListViewMVVMFragment newInstance(String url, boolean isVisibleToUser) {
        CommonPullListViewMVVMFragment fragment = new CommonPullListViewMVVMFragment();
        fragment.setFragment(fragment);
        fragment.setUserVisibleHint(isVisibleToUser);
        fragment.url = url;
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
//        mMVVMArticleViewModel.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        mMVVMArticleBinding = FragmentCommonMvvmPullListviewBinding.inflate(inflater, container, false);
//        mMVVMArticleBinding.setView(this);
////        mMVVMArticleBinding.setViewmodel(mViewModel);
//        setHasOptionsMenu(true);
//        View root = mMVVMArticleBinding.getRoot();
        return null;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initValues();
    }

    @Override
    public void initValues() {
        super.initValues();
//        mPullToRefreshListView =  mMVVMArticleBinding.pullrefreshlist;
//        mMMVVMArticleListAdapter = new MMVVMArticleListAdapter2(
//                getActivity(),
//                new ArrayList<MArticleBean>()
//
//        );
//        mPullToRefreshListView.setAdapter(mMMVVMArticleListAdapter);
//        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
    }

    /* (non-Javadoc)
	 * @see com.open.mmxzg.model.MArticlePullListContract.View#bindEvent()
	 */
    @Override
    public void bindEvent() {
        // TODO Auto-generated method stub
//        mPullToRefreshListView.setOnRefreshListener(this);
//        mPullToRefreshListView.setOnItemClickListener(this);
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
                mViewModel.start();
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroy() {
//        mMMVVMArticleListAdapter.onDestroy();
        super.onDestroy();
    }


    public void setViewModel(M viewModel) {
        mViewModel = viewModel;
        mViewModel.setmNavigator(CommonPullListViewMVVMFragment.this);
        mViewModel.url = url;
    }

    /* (non-Javadoc)
	 * @see com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener#onRefresh(com.handmark.pulltorefresh.library.PullToRefreshBase)
	 */
    @Override
    public void onRefresh(PullToRefreshBase<ListView> refreshView) {
        // TODO Auto-generated method stub
        String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
        // Update the LastUpdatedLabel
        refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
        // Do work to refresh the list here.
        mViewModel.setMode(mPullToRefreshListView.getCurrentMode());
        if (mPullToRefreshListView.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_START) {
            pageNo = 1;
            mViewModel.setPageNo(pageNo);
            weakReferenceHandler.sendEmptyMessage(MESSAGE_HANDLER);
        } else if (mPullToRefreshListView.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_END) {
            pageNo++;
            mViewModel.setPageNo(pageNo);
            weakReferenceHandler.sendEmptyMessage(MESSAGE_HANDLER);
        }
    }

    /* (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onCallback(T result) {
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
//        mPullToRefreshListView.onRefreshComplete();
    }
}
