package com.open.yaoraotu.m.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.open.yaoraotu.bean.MasonryBean;
import com.open.yaoraotu.m.R;
import com.open.yaoraotu.m.databinding.AdapterMvvmHomeGridBinding;
import com.open.yaoraotu.m.viewmodel.itemview.HomeGridItemViewModel;

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
public class MvvmHomeGridAdapter extends  CommonMvvmAdapter<MasonryBean,AdapterMvvmHomeGridBinding>{

    public MvvmHomeGridAdapter(Context mContext, List<MasonryBean> list) {
        super(mContext, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MasonryBean bean = (MasonryBean) getItem(position);
        if (convertView == null) {
            // Inflate
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());

            // Create the binding
            mBinding = DataBindingUtil.inflate(inflater, R.layout.adapter_mvvm_home_grid, parent, false);
//            mBinding = AdapterMvvmHomeGridBinding.inflate(inflater, parent, false);
        }else{
            // Recycling view
            mBinding = DataBindingUtil.getBinding(convertView);
        }

        HomeGridItemViewModel viewModel = new HomeGridItemViewModel();
        mBinding.setViewmodel(viewModel);
        viewModel.setMasonryBeanObservable(bean);
        return mBinding.getRoot();
    }
}
