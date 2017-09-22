package com.open.yaoraotu.m.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.open.android.adapter.CommonPagerAdapter;
import com.open.yaoraotu.bean.MasonryBean;
import com.open.yaoraotu.m.R;
import com.open.yaoraotu.m.databinding.AdapterMvvmDotViewpagerBinding;
import com.open.yaoraotu.m.viewmodel.itemview.HomeDotItemViewModel;

import java.util.List;

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
public class MvvmHomeDotViewPagerAdapter extends CommonPagerAdapter<MasonryBean> {
    public MvvmHomeDotViewPagerAdapter(Context mContext, List<MasonryBean> list) {
        super(mContext, list);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        AdapterMvvmDotViewpagerBinding mBinding;
        MasonryBean bean = (MasonryBean) getItem(position);
//        View convertView = null;
//        if (convertView == null) {
        // Inflate
        LayoutInflater inflater = LayoutInflater.from(container.getContext());

        // Create the binding
        mBinding = DataBindingUtil.inflate(inflater, R.layout.adapter_mvvm_dot_viewpager, container, false);
//            mBinding = AdapterMvvmHomeGridBinding.inflate(inflater, parent, false);
//        }else{
//            // Recycling view
//            mBinding = DataBindingUtil.getBinding(convertView);
//        }

        HomeDotItemViewModel viewModel = new HomeDotItemViewModel();
        mBinding.setViewmodel(viewModel);
        viewModel.setMasonryBeanObservable(bean);
        container.addView(mBinding.getRoot());
        return mBinding.getRoot();
    }

    public void replaceData(List<MasonryBean> list) {
        setList(list);
    }

    private void setList(List<MasonryBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
