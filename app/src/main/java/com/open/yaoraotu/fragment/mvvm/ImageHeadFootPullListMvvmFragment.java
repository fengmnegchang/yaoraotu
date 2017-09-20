package com.open.yaoraotu.fragment.mvvm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.HeaderViewListAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.open.yaoraotu.activity.ImagePagerAdapterFragmentActivity;
import com.open.yaoraotu.adapter.mvvm.MvvmImageAdapter;
import com.open.yaoraotu.bean.MasonryBean;
import com.open.yaoraotu.databinding.FragmentMvvmImageHeadfootPullListviewBinding;
import com.open.yaoraotu.json.MasonryJson;
import com.open.yaoraotu.viewmodel.ImageHeadFootPullListViewModel;

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
public class ImageHeadFootPullListMvvmFragment extends CommonPullListViewMVVMFragment<MasonryBean, MasonryJson,ImageHeadFootPullListViewModel,
        FragmentMvvmImageHeadfootPullListviewBinding,MvvmImageAdapter>{
//    public FragmentMvvmHotTopPullListviewBinding mFragmentBinding;
//    public NewHotTopPullListViewModel mViewModel;
    public ImageHeadFootPullListMvvmFragment(){
        // Requires empty public constructor
    }

    public static ImageHeadFootPullListMvvmFragment newInstance(String url, boolean isVisibleToUser) {
        ImageHeadFootPullListMvvmFragment fragment = new ImageHeadFootPullListMvvmFragment();
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
        mFragmentBinding = FragmentMvvmImageHeadfootPullListviewBinding.inflate(inflater,container, false);
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
        mAdapter = new MvvmImageAdapter(
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


    /* (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ListView listView1 = mPullToRefreshListView.getRefreshableView();
        HeaderViewListAdapter listAdapter = (HeaderViewListAdapter) listView1.getAdapter();
        if (listAdapter.getWrappedAdapter() instanceof MvvmImageAdapter) {
            MvvmImageAdapter adapter = (MvvmImageAdapter) listAdapter.getWrappedAdapter();
            if (adapter != null) {
                MasonryBean bean = (MasonryBean) adapter.getItem((int)id);
                ImagePagerAdapterFragmentActivity.startImagePagerAdapterFragmentActivity(view.getContext(),bean.getHref());
            }
        }

    }

    @Override
    public void onCallback(MasonryJson result) {
        mPullToRefreshListView.onRefreshComplete();
    }
}
