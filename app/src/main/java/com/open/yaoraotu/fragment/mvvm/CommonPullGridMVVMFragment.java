package com.open.yaoraotu.fragment.mvvm;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.handmark.pulltorefresh.library.HeaderGridView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshHeadGridView;
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
public class CommonPullGridMVVMFragment<B extends CommonBean,T extends CommonJson,M extends MVVMCommonViewModel<T>,FVDB extends ViewDataBinding,A extends CommonMvvmAdapter>
        extends BaseV4MVPFragment<B,CommonPullGridMVVMFragment> implements OnRefreshListener<HeaderGridView>,OnItemClickListener,CommonNavigator<T> {
    public M mViewModel;
    public FVDB mFragmentBinding;
    public A mAdapter;
//    private FragmentCommonMvvmPullGridviewBinding mMVVMArticleBinding;
//    private MMVVMArticleGridAdapter mMMVVMArticleGridAdapter;
    public PullToRefreshHeadGridView mPullToRefreshHeadGridView;

    public CommonPullGridMVVMFragment() {
        // Requires empty public constructor
    }

    public static CommonPullGridMVVMFragment newInstance(String url, boolean isVisibleToUser) {
        CommonPullGridMVVMFragment fragment = new CommonPullGridMVVMFragment();
        fragment.setFragment(fragment);
        fragment.setUserVisibleHint(isVisibleToUser);
        fragment.url = url;
        return fragment;
    }

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        mMVVMArticleBinding = FragmentCommonMvvmPullGridviewBinding.inflate(inflater, container, false);
//        mMVVMArticleBinding.setView(this);
//        mMVVMArticleBinding.setViewmodel(mMArticlePullGridViewModel);
//        setHasOptionsMenu(true);
//        View root = mMVVMArticleBinding.getRoot();
//        return root;
//    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initValues();
    }

    @Override
    public void initValues() {
        super.initValues();
//        mPullToRefreshHeadGridView =  mMVVMArticleBinding.pullrefreshgrid;
//        mMMVVMArticleGridAdapter = new MMVVMArticleGridAdapter(
//                getActivity(),
//                new ArrayList<MArticleBean>()
//
//        );
//        mPullToRefreshHeadGridView.setAdapter(mMMVVMArticleGridAdapter);
//        mPullToRefreshHeadGridView.setMode(PullToRefreshBase.Mode.BOTH);
    }

    /* (non-Javadoc)
	 * @see com.open.mmxzg.model.MArticlePullListContract.View#bindEvent()
	 */
    @Override
    public void bindEvent() {
        // TODO Auto-generated method stub
//        mPullToRefreshHeadGridView.setOnRefreshListener(this);
//        mPullToRefreshHeadGridView.setOnItemClickListener(this);
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
                try{
                    mViewModel.start();
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }



    public void setViewModel(M viewModel) {
        mViewModel = viewModel;
        mViewModel.setmNavigator(CommonPullGridMVVMFragment.this);
        mViewModel.url = url;
    }

    /* (non-Javadoc)
    * @see com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener#onRefresh(com.handmark.pulltorefresh.library.PullToRefreshBase)
    */
    @Override
    public void onRefresh(PullToRefreshBase<HeaderGridView> refreshView) {
        // TODO Auto-generated method stub
        String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
        // Update the LastUpdatedLabel
        refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
        // Do work to refresh the list here.
        mViewModel.setMode(mPullToRefreshHeadGridView.getCurrentMode());
        if (mPullToRefreshHeadGridView.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_START) {
            pageNo = 1;
            mViewModel.setPageNo(pageNo);
            weakReferenceHandler.sendEmptyMessage(MESSAGE_HANDLER);
        } else if (mPullToRefreshHeadGridView.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_END) {
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
//        mPullToRefreshHeadGridView.onRefreshComplete();
    }

}
