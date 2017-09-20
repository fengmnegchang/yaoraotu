package com.open.yaoraotu.adapter.mvvm;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.open.yaoraotu.bean.MasonryBean;
import com.open.yaoraotu.mvvm.databinding.AdapterMvvmImageListBinding;
import com.open.yaoraotu.viewmodel.itemview.ImageItemViewModel;

import java.util.List;

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
public class MvvmImageAdapter extends CommonMvvmAdapter<MasonryBean,AdapterMvvmImageListBinding> {

    public MvvmImageAdapter(Context mContext, List<MasonryBean> list) {
        super(mContext, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MasonryBean bean = (MasonryBean) getItem(position);
        if (convertView == null) {
            // Inflate
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());

            // Create the binding
//            mBinding = DataBindingUtil.inflate(inflater, R.layout.adapter_mvvm_hot_top, parent, false);
            mBinding = AdapterMvvmImageListBinding.inflate(inflater, parent, false);
        }else{
            // Recycling view
            mBinding = DataBindingUtil.getBinding(convertView);
        }

        ImageItemViewModel viewModel = new ImageItemViewModel();
        mBinding.setViewmodel(viewModel);
        viewModel.setMasonryBeanObservable(bean);
        return mBinding.getRoot();
    }

}
