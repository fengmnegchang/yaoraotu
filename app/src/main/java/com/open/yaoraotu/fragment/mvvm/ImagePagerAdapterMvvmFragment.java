package com.open.yaoraotu.fragment.mvvm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.open.android.fragment.BaseV4MVPFragment;
import com.open.yaoraotu.adapter.mvvm.MvvmImagePagerAdapter;
import com.open.yaoraotu.bean.MasonryBean;
import com.open.yaoraotu.databinding.FragmentMvvmImageViewpagerBinding;
import com.open.yaoraotu.json.MasonryJson;
import com.open.yaoraotu.viewmodel.CommonNavigator;
import com.open.yaoraotu.viewmodel.ImagePagerAdapterViewModel;

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
public class ImagePagerAdapterMvvmFragment extends BaseV4MVPFragment<MasonryBean,ImagePagerAdapterMvvmFragment> implements CommonNavigator<MasonryJson> {
    public ViewPager viewpager;
    public MvvmImagePagerAdapter mMImagePagerAdapter;
    public List<MasonryBean> list = new ArrayList<MasonryBean>();
    // public String url = UrlUtils.PXING_IMAGE;
    // public int position;
    public TextView text_page_foot;
    public boolean isHasData;
    private ImagePagerAdapterViewModel mViewModel;
    private FragmentMvvmImageViewpagerBinding mBinding;


    public static ImagePagerAdapterMvvmFragment newInstance(String url, boolean isVisibleToUser) {
        ImagePagerAdapterMvvmFragment fragment = new ImagePagerAdapterMvvmFragment();
        fragment.setFragment(fragment);
        fragment.setUserVisibleHint(isVisibleToUser);
        fragment.url = url;
        return fragment;
    }

    public ImagePagerAdapterMvvmFragment() {
        // Requires empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentMvvmImageViewpagerBinding.inflate(inflater, container, false);
        mBinding.setView(this);
        mBinding.setViewmodel(mViewModel);
        setHasOptionsMenu(true);
        View root = mBinding.getRoot();
        return root;
    }

    @Override
    public void initValues() {
        viewpager =  mBinding.viewpager;
        text_page_foot = mBinding.textPageFoot;
        if(isHasData){
//            mViewModel.items = list;
        }
        mMImagePagerAdapter = new MvvmImagePagerAdapter(
                getActivity(),
                new ArrayList<MasonryBean>()
        );
        viewpager.setAdapter(mMImagePagerAdapter);
//        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mViewModel.start();
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see com.open.qianbailu.fragment.BaseV4Fragment#bindEvent()
	 */
    @Override
    public void bindEvent() {
        // TODO Auto-generated method stub
        super.bindEvent();
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                text_page_foot.setText((position + 1) + " / " + list.size());
                ImagePagerAdapterMvvmFragment.this.position = position;
                pageNo++;
                mViewModel.setPageNo(position);
                mViewModel.setPosition(position);
                mViewModel.setUrl(mViewModel.getPositionItem(viewpager,position).getHref());
                mViewModel.start();
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

    public void setViewModel(ImagePagerAdapterViewModel viewModel) {
        mViewModel = viewModel;
        mViewModel.setmNavigator(ImagePagerAdapterMvvmFragment.this);
        mViewModel.url = url;
    }


    @Override
    public void onCallback(MasonryJson result) {

    }
}
