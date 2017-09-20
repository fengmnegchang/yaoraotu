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
import com.open.yaoraotu.activity.MasonryImagePullListActivity;
import com.open.yaoraotu.adapter.mvvm.MvvmNewListAdapter;
import com.open.yaoraotu.bean.MasonryBean;
import com.open.yaoraotu.databinding.FragmentMvvmNewListPullListviewBinding;
import com.open.yaoraotu.json.MasonryJson;
import com.open.yaoraotu.viewmodel.NewListPullListViewModel;

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
public class NewListPullListMvvmFragment extends CommonPullListViewMVVMFragment<MasonryBean, MasonryJson,NewListPullListViewModel,
        FragmentMvvmNewListPullListviewBinding, MvvmNewListAdapter>{

//    public NewListPullListViewModel mViewModel;
//    public FragmentMvvmNewListPullListviewBinding mFragmentBinding;
    public NewListPullListMvvmFragment(){
        // Requires empty public constructor
    }

    public static NewListPullListMvvmFragment newInstance(String url, boolean isVisibleToUser) {
        NewListPullListMvvmFragment fragment = new NewListPullListMvvmFragment();
        fragment.setFragment(fragment);
        fragment.setUserVisibleHint(isVisibleToUser);
        fragment.url = url;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        mFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mvvm_new_list_pull_listview, container, false);
        mFragmentBinding = FragmentMvvmNewListPullListviewBinding.inflate(inflater,container, false);
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
        mAdapter = new MvvmNewListAdapter(
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ListView listView1 = mPullToRefreshListView.getRefreshableView();
        HeaderViewListAdapter listAdapter = (HeaderViewListAdapter) listView1.getAdapter();
        if (listAdapter.getWrappedAdapter() instanceof MvvmNewListAdapter) {
            MvvmNewListAdapter adapter = (MvvmNewListAdapter) listAdapter.getWrappedAdapter();
            if (adapter != null) {
                MasonryBean bean = (MasonryBean) adapter.getItem((int)id);
                MasonryImagePullListActivity.startMasonryImagePullListActivity(view.getContext(),bean.getHref());
            }
        }
    }
}
