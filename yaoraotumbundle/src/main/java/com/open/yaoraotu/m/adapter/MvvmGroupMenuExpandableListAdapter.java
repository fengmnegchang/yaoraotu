package com.open.yaoraotu.m.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.open.android.adapter.CommonExpandableListAdapter;
import com.open.android.view.ExpendGridView;
import com.open.yaoraotu.bean.MasonryBean;
import com.open.yaoraotu.bean.MasonryGroupBean;
import com.open.yaoraotu.m.R;
import com.open.yaoraotu.m.databinding.AdapterMvvmMenuChildViewBinding;
import com.open.yaoraotu.m.databinding.AdapterMvvmMenuGroupViewBinding;
import com.open.yaoraotu.m.viewmodel.ChildViewViewModel;
import com.open.yaoraotu.m.viewmodel.itemview.GroupViewItemViewModel;
import com.open.yaoraotu.utils.ActivityUtils;

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
public class MvvmGroupMenuExpandableListAdapter extends CommonExpandableListAdapter<MasonryGroupBean,MasonryBean>{

    public MvvmGroupMenuExpandableListAdapter(Context mContext, List<MasonryGroupBean> list) {
        super(mContext, list);
    }

    public void replaceData(List<MasonryGroupBean> list) {
        setList(list);
    }

    private void setList(List<MasonryGroupBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    @Override
    public View getGroupView(int groupPosition, boolean arg1, View convertView, ViewGroup parent) {
        AdapterMvvmMenuGroupViewBinding mBinding;
        final MasonryGroupBean bean = (MasonryGroupBean) getGroup(groupPosition);
        if (convertView == null) {
            // Inflate
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());

            // Create the binding
            mBinding = DataBindingUtil.inflate(inflater, R.layout.adapter_mvvm_menu_group_view, parent, false);
//            mBinding = AdapterMvvmMenuGroupViewBinding.inflate(inflater, parent, false);
        }else{
            // Recycling view
            mBinding = DataBindingUtil.getBinding(convertView);
        }

        GroupViewItemViewModel viewModel = new GroupViewItemViewModel();
        mBinding.setViewmodel(viewModel);
        viewModel.setMasonryGroupBeanObservable(bean);
        TextView text_moduleTitle = mBinding.textModuleTitle;
        text_moduleTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityUtils.startBundleActivity(view.getContext(),bean.getHref(),"com.open.yaoraotu.activity.SubMenuIndicatorFragmentActivity");
//                SubMenuIndicatorFragmentActivity.startSubMenuIndicatorFragmentActivity(mContext, bean.getHref(), position);
            }
        });
        return mBinding.getRoot();
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.open.tencenttv.adapter.CommonExpandableListAdapter#getChildrenCount
	 * (int)
	 */
    @Override
    public int getChildrenCount(int groupPosition) {
        // TODO Auto-generated method stub
        if (getGroup(groupPosition) != null
                && ((getGroup(groupPosition).getList() != null && getGroup(groupPosition)
                .getList().size() > 0))) {
            return 1;
        } else {
            return 0;
        }

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean arg2, View convertView, ViewGroup parent) {
        AdapterMvvmMenuChildViewBinding mBinding;
        if (convertView == null) {
            // Inflate
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());

            // Create the binding
            mBinding = DataBindingUtil.inflate(inflater, R.layout.adapter_mvvm_menu_child_view, parent, false);
//            mBinding = AdapterMvvmMenuChildViewBinding.inflate(inflater, parent, false);
        }else{
            // Recycling view
            mBinding = DataBindingUtil.getBinding(convertView);
        }

        ExpendGridView gridView = mBinding.gridView;
        final List<MasonryBean> clist = getGroup(groupPosition).getList();
        MvvmMenuGridAdapter mvvmMenuGridAdapter = new MvvmMenuGridAdapter(parent.getContext(),clist);
        gridView.setAdapter(mvvmMenuGridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(clist!=null && clist.size()>0 && clist.get((int)l)!=null){
                    MasonryBean cbean = clist.get((int)l);
                    ActivityUtils.startBundleActivity(view.getContext(),cbean.getHref(),"com.open.yaoraotu.activity.MasonryPullGridActivity");
                }
            }
        });

        ChildViewViewModel viewModel = new ChildViewViewModel(parent.getContext());
//        viewModel.setItems(gridView,clist);
        mBinding.setViewmodel(viewModel);
        return mBinding.getRoot();
    }
}
