package com.open.yaoraotu.adapter.mvvm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.open.yaoraotu.bean.MasonryBean;
import com.open.yaoraotu.mvvm.databinding.AdapterMvvmImageViewpagerBinding;
import com.open.yaoraotu.viewmodel.itemview.ImagePagerItemViewModel;

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
public class MvvmImagePagerAdapter extends  CommonMvvmPagerAdapter<MasonryBean,AdapterMvvmImageViewpagerBinding>{

    public MvvmImagePagerAdapter(Context mContext, List<MasonryBean> list) {
        super(mContext, list);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final MasonryBean bean = (MasonryBean) getItem(position);
        View convertView = null;
//        if (convertView == null) {
        // Inflate
        LayoutInflater inflater = LayoutInflater.from(mContext);

        // Create the binding
        mBinding = AdapterMvvmImageViewpagerBinding.inflate(inflater, container, false);
//        } else {
//            // Recycling view
//            binding = DataBindingUtil.getBinding(convertView);
//        }

        ImagePagerItemViewModel viewModel = new ImagePagerItemViewModel();
        mBinding.setViewmodel(viewModel);
        viewModel.setMasonryBeanObservable(bean);
        container.addView(mBinding.getRoot());
        return mBinding.getRoot();

    }
}
