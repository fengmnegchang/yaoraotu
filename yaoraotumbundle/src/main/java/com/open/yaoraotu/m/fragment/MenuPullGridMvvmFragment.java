package com.open.yaoraotu.m.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.HeaderViewListAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.open.yaoraotu.bean.MasonryBean;
import com.open.yaoraotu.json.MasonryJson;
import com.open.yaoraotu.m.R;
import com.open.yaoraotu.m.adapter.MvvmMenuGridAdapter;
import com.open.yaoraotu.m.databinding.FragmentMvvmMenuPullGridviewBinding;
import com.open.yaoraotu.m.viewmodel.MenuPullGridViewModel;

import java.util.ArrayList;

//import com.open.yaoraotu.activity.SubMenuIndicatorFragmentActivity;

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
public class MenuPullGridMvvmFragment extends CommonPullListViewMVVMFragment<MasonryBean, MasonryJson,MenuPullGridViewModel,
        FragmentMvvmMenuPullGridviewBinding,MvvmMenuGridAdapter>{
//    public NewMenuPullListViewModel mViewModel;
//    public FragmentMvvmLeftmenuPullListviewBinding mFragmentBinding;

    public MenuPullGridMvvmFragment(){
        // Requires empty public constructor
    }

    public static MenuPullGridMvvmFragment newInstance(String url, boolean isVisibleToUser) {
        MenuPullGridMvvmFragment fragment = new MenuPullGridMvvmFragment();
        fragment.setFragment(fragment);
        fragment.setUserVisibleHint(isVisibleToUser);
        fragment.url = url;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mvvm_menu_pull_gridview, container, false);
//        mFragmentBinding = FragmentMvvmMenuPullGridviewBinding.inflate(inflater,container, false);
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
        mAdapter = new MvvmMenuGridAdapter(
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
        if (listAdapter.getWrappedAdapter() instanceof MvvmMenuGridAdapter) {
            MvvmMenuGridAdapter adapter = (MvvmMenuGridAdapter) listAdapter.getWrappedAdapter();
            if (adapter != null) {
                MasonryBean bean = (MasonryBean) adapter.getItem((int)id);
//                SubMenuIndicatorMvvmActivity.startSubMenuIndicatorMvvmActivity(view.getContext(), bean.getHref(), (int)id);

            }
        }
    }
}
