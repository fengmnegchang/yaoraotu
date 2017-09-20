package com.open.yaoraotu.fragment.mvvm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.handmark.pulltorefresh.library.HeaderGridView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.open.yaoraotu.activity.mvvm.MasonryImagePullListMvvmActivity;
import com.open.yaoraotu.adapter.mvvm.MvvmNewListAdapter;
import com.open.yaoraotu.bean.MasonryBean;
import com.open.yaoraotu.json.MasonryJson;
import com.open.yaoraotu.mvvm.databinding.FragmentMvvmTopThemePullGridMvvmBinding;
import com.open.yaoraotu.viewmodel.TopThemePullGridViewModel;

import java.util.ArrayList;

//import com.open.yaoraotu.activity.MasonryImagePullListActivity;

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

//        mFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mvvm_top_theme_pull_grid_mvvm, container, false);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HeaderGridView gridView1 = mPullToRefreshHeadGridView.getRefreshableView();
        if(gridView1.getHeaderViewCount()>0){
            HeaderGridView.HeaderViewGridAdapter listAdapter = (HeaderGridView.HeaderViewGridAdapter) gridView1.getAdapter();
            if (listAdapter.getWrappedAdapter() instanceof MvvmNewListAdapter) {
                MvvmNewListAdapter adapter = (MvvmNewListAdapter) listAdapter.getWrappedAdapter();
                if (adapter != null) {
                    MasonryBean bean = (MasonryBean) adapter.getItem((int)id);
                     MasonryImagePullListMvvmActivity.startMasonryImagePullListMvvmActivity(view.getContext(),bean.getHref());
                }
            }
        }else{
            if (gridView1.getAdapter() instanceof MvvmNewListAdapter) {
                MvvmNewListAdapter adapter = (MvvmNewListAdapter) gridView1.getAdapter();
                if (adapter != null) {
                    MasonryBean bean = (MasonryBean) adapter.getItem((int)id);
                     MasonryImagePullListMvvmActivity.startMasonryImagePullListMvvmActivity(view.getContext(),bean.getHref());
                }
            }
        }
    }
}
