package com.open.yaoraotu.m.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.open.android.fragment.BaseV4MVPFragment;
import com.open.yaoraotu.bean.MasonryBean;
import com.open.yaoraotu.json.MasonryJson;
import com.open.yaoraotu.m.R;
import com.open.yaoraotu.m.adapter.MvvmHomeDotViewPagerAdapter;
import com.open.yaoraotu.m.databinding.FragmentMvvmHomeDotViewpagerBinding;
import com.open.yaoraotu.m.viewmodel.CommonNavigator;
import com.open.yaoraotu.m.viewmodel.HomeDotViewPagerViewModel;

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
public class HomeDotPagerMvvmFragment extends BaseV4MVPFragment<MasonryBean,HomeDotPagerMvvmFragment> implements CommonNavigator<MasonryJson>,ViewPager.OnPageChangeListener {
    public ViewPager viewpager;
//    public List<MasonryBean> list = new ArrayList<MasonryBean>();
    public ImageView[] dots;
    public int currentIndex;
    public int size;
    public LinearLayout layout_dot;
    public HomeDotViewPagerViewModel mViewModel;
    public FragmentMvvmHomeDotViewpagerBinding mFragmentBinding;
    public MvvmHomeDotViewPagerAdapter mAdapter;

    public HomeDotPagerMvvmFragment() {
    }

    public static HomeDotPagerMvvmFragment newInstance(String url, boolean isVisibleToUser) {
        HomeDotPagerMvvmFragment fragment = new HomeDotPagerMvvmFragment();
        fragment.setFragment(fragment);
        fragment.setUserVisibleHint(isVisibleToUser);
        fragment.url = url;
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mvvm_home_dot_viewpager, container, false);
//        mFragmentBinding = FragmentMvvmHomeDotViewpagerBinding.inflate(inflater,container, false);
        mFragmentBinding.setView(this);
        mFragmentBinding.setViewmodel(mViewModel);
        setHasOptionsMenu(true);
        View root = mFragmentBinding.getRoot();
        return root;
    }

    @Override
    public void initValues() {
        super.initValues();
        viewpager =  mFragmentBinding.viewpager;
        layout_dot = mFragmentBinding.layoutDot;
        mAdapter = new MvvmHomeDotViewPagerAdapter(
                getActivity(),
                new ArrayList<MasonryBean>()
        );
        viewpager.setAdapter(mAdapter);
    }

    /* (non-Javadoc)
	 * @see com.open.enrz.fragment.BaseV4Fragment#bindEvent()
	 */
    @Override
    public void bindEvent() {
        // TODO Auto-generated method stub
        super.bindEvent();
        viewpager.setOnPageChangeListener(this);
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

    public void setViewModel(HomeDotViewPagerViewModel viewModel) {
        mViewModel = viewModel;
        mViewModel.setmNavigator(HomeDotPagerMvvmFragment.this);
        mViewModel.url = url;
    }

    @Override
    public void onCallback(MasonryJson result) {
//        list.clear();
//        list.addAll(result.getList());
//        mAdapter.notifyDataSetChanged();

        size = result.getList().size();
        if(size>0){
            dots = new ImageView[size];
            for (int i = 0; i < size; i++) {
                ImageView img = new ImageView(getActivity());
                img.setImageResource(R.drawable.dot);
                img.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                img.setPadding(15, 15, 15, 15);
                img.setClickable(true);
                dots[i] = img;
                dots[i].setEnabled(true);
                dots[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = (Integer) v.getTag();
                        setCurView(position);
                        setCurDot(position);
                    }
                });
                dots[i].setTag(i);

                layout_dot.addView(dots[i]);
            }

            currentIndex = 0;
            dots[currentIndex].setEnabled(false);
            viewpager.setCurrentItem(0);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setCurDot(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 设置当前的引导页
     */
    public void setCurView(int position) {
        if (position < 0 || position >= size) {
            return;
        }

        viewpager.setCurrentItem(position);
    }

    /**
     * 这只当前引导小点的选中
     */
    public void setCurDot(int positon) {
        if (positon < 0 || positon > size - 1
                || currentIndex == positon) {
            return;
        }

        dots[positon].setEnabled(false);
        dots[currentIndex].setEnabled(true);

        currentIndex = positon;
    }
}
