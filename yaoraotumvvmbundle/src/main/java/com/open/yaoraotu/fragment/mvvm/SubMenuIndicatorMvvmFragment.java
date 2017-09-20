package com.open.yaoraotu.fragment.mvvm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.open.android.adapter.CommonFragmentPagerAdapter;
import com.open.android.fragment.BaseV4MVPFragment;
import com.open.indicator.TabPageIndicator;
import com.open.yaoraotu.bean.MasonryBean;
import com.open.yaoraotu.json.MasonryJson;
import com.open.yaoraotu.mvvm.R;
import com.open.yaoraotu.mvvm.databinding.FragmentMvvmSubIndicatorViewpagerBinding;
import com.open.yaoraotu.viewmodel.CommonNavigator;
import com.open.yaoraotu.viewmodel.MasonryGridViewModel;
import com.open.yaoraotu.viewmodel.SubMenuIndicatorViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fengguangjing
 * @createTime: 17/9/20
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class SubMenuIndicatorMvvmFragment  extends BaseV4MVPFragment<MasonryBean,SubMenuIndicatorMvvmFragment> implements CommonNavigator<MasonryJson> {
    public SubMenuIndicatorViewModel mViewModel;
    public FragmentMvvmSubIndicatorViewpagerBinding mFragmentBinding;

    public ArrayList<MasonryBean> list = new ArrayList<MasonryBean>();
    public ViewPager viewpager;
    public TabPageIndicator indicator;
    public List<String> titleList = new ArrayList<String>();
    public List<Fragment> listRankFragment = new ArrayList<Fragment>();// view数组
    public CommonFragmentPagerAdapter mRankPagerAdapter;
    public List<MasonryGridViewModel> listPresenterImpl = new ArrayList<MasonryGridViewModel>();//

    public SubMenuIndicatorMvvmFragment() {
    }

    public static SubMenuIndicatorMvvmFragment newInstance(String url, boolean isVisibleToUser,int pageNo) {
        SubMenuIndicatorMvvmFragment fragment = new SubMenuIndicatorMvvmFragment();
        fragment.setFragment(fragment);
        fragment.setUserVisibleHint(isVisibleToUser);
        fragment.url = url;
        fragment.pageNo = pageNo;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mvvm_sub_indicator_viewpager, container, false);
//        mFragmentBinding = FragmentMvvmSubIndicatorViewpagerBinding.inflate(inflater, container, false);
        mFragmentBinding.setView(this);
        mFragmentBinding.setViewmodel(mViewModel);
        setHasOptionsMenu(true);
        View root = mFragmentBinding.getRoot();
        return root;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see com.open.qianbailu.fragment.BaseV4Fragment#initValues()
	 */
    @Override
    public void initValues() {
        // TODO Auto-generated method stub
        super.initValues();
        viewpager =  mFragmentBinding.viewpager;
        indicator =  mFragmentBinding.indicator;
        mViewModel.setPageNo(pageNo);

        mRankPagerAdapter = new CommonFragmentPagerAdapter(getChildFragmentManager(), listRankFragment, titleList);
        viewpager.setAdapter(mRankPagerAdapter);
        indicator.setViewPager(viewpager);
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

    public void setViewModel(SubMenuIndicatorViewModel viewModel) {
        mViewModel = viewModel;
        mViewModel.setmNavigator(SubMenuIndicatorMvvmFragment.this);
        mViewModel.url = url;
    }

    @Override
    public void onCallback(MasonryJson result) {
        if(result==null){
            return;
        }
        list.clear();
        list.addAll(result.getList());
        titleList.clear();

        MasonryPullGridMvvmFragment fragment;
        for (int i=0;i< result.getList().size();i++) {
            MasonryBean bean = result.getList().get(i);
            titleList.add(bean.getTitle());
            if(i==0){
                fragment = MasonryPullGridMvvmFragment.newInstance(bean.getHref(),true);
            }else{
                fragment = MasonryPullGridMvvmFragment.newInstance(bean.getHref(),false);
            }
            MasonryGridViewModel mViewModel = new MasonryGridViewModel(getActivity(),"getPliList");
            fragment.setViewModel(mViewModel);
            listPresenterImpl.add(mViewModel);
            listRankFragment.add(fragment);
        }
        mRankPagerAdapter.notifyDataSetChanged();
        indicator.notifyDataSetChanged();
    }
}
