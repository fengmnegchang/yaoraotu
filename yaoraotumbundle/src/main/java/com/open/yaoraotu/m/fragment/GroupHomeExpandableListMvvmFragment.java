package com.open.yaoraotu.m.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.open.android.fragment.BaseV4MVPFragment;
import com.open.yaoraotu.bean.MasonryGroupBean;
import com.open.yaoraotu.json.MasonryGroupJson;
import com.open.yaoraotu.m.R;
import com.open.yaoraotu.m.adapter.MvvmGroupHomeExpandableListAdapter;
import com.open.yaoraotu.m.databinding.FragmentMvvmGroupHomeExpandableListviewBinding;
import com.open.yaoraotu.m.viewmodel.CommonNavigator;
import com.open.yaoraotu.m.viewmodel.GroupHomeExpandableListViewModel;
import com.open.yaoraotu.m.viewmodel.HomeDotViewPagerViewModel;
import com.open.yaoraotu.utils.UrlUtils;

import java.util.ArrayList;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fengguangjing
 * @createTime: 17/9/22
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class GroupHomeExpandableListMvvmFragment extends BaseV4MVPFragment<MasonryGroupBean,GroupHomeExpandableListMvvmFragment> implements CommonNavigator<MasonryGroupJson> {

    public GroupHomeExpandableListViewModel mViewModel;
    public FragmentMvvmGroupHomeExpandableListviewBinding mFragmentBinding;
    public MvvmGroupHomeExpandableListAdapter mAdapter;
    public ExpandableListView expendablelistview;
    public View headView;

    public GroupHomeExpandableListMvvmFragment() {
        // Requires empty public constructor
    }

    public static GroupHomeExpandableListMvvmFragment newInstance(String url, boolean isVisibleToUser) {
        GroupHomeExpandableListMvvmFragment fragment = new GroupHomeExpandableListMvvmFragment();
        fragment.setFragment(fragment);
        fragment.setUserVisibleHint(isVisibleToUser);
        fragment.url = url;
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mvvm_group_home_expandable_listview, container, false);
//        mFragmentBinding = FragmentMvvmGroupHomeExpandableListviewBinding.inflate(inflater, container, false);
        mFragmentBinding.setView(this);
        mFragmentBinding.setViewmodel(mViewModel);
        setHasOptionsMenu(true);
        View root = mFragmentBinding.getRoot();
        headView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_home_head,null);
        return root;
    }

    @Override
    public void initValues() {
        expendablelistview =  mFragmentBinding.expendablelistview;
        expendablelistview.addHeaderView(headView);
        //head
        HomeDotPagerMvvmFragment fragment =  HomeDotPagerMvvmFragment.newInstance(UrlUtils.YAORAOTU_TAOTU,true);
        getChildFragmentManager().beginTransaction().replace(R.id.id_home_head, fragment).commit();

        HomeDotViewPagerViewModel mDotViewModel = new HomeDotViewPagerViewModel(getActivity(),"getTopPager");
        fragment.setViewModel(mDotViewModel);

        expendablelistview.setGroupIndicator(null);
        mAdapter = new MvvmGroupHomeExpandableListAdapter(
                getActivity(),
                new ArrayList<MasonryGroupBean>()
        );
        expendablelistview.setAdapter(mAdapter);

    }

    /*
         * (non-Javadoc)
         *
         * @see com.open.umei.fragment.BaseV4Fragment#bindEvent()
         */
    @Override
    public void bindEvent() {
        // TODO Auto-generated method stub
        super.bindEvent();
        expendablelistview.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return true;
            }
        });
        expendablelistview
                .setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                    @Override
                    public void onGroupExpand(int groupPosition) {

                    }
                });
        expendablelistview
                .setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
                    @Override
                    public void onGroupCollapse(int groupPosition) {

                    }
                });
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


    public void setViewModel(GroupHomeExpandableListViewModel viewModel) {
        mViewModel = viewModel;
        mViewModel.setmNavigator(GroupHomeExpandableListMvvmFragment.this);
        mViewModel.url = url;
    }

    @Override
    public void onCallback(MasonryGroupJson result) {
        for (int i = 0; i < mAdapter.getGroupCount(); i++) {
            expendablelistview.expandGroup(i);
        }
    }
}
