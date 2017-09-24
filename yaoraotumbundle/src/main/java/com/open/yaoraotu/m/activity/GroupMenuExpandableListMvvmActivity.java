package com.open.yaoraotu.m.activity;

import android.content.Context;
import android.content.Intent;

import com.open.android.activity.common.CommonCommonFragmentActivity;
import com.open.yaoraotu.m.R;
import com.open.yaoraotu.m.fragment.GroupMenuExpandableListMvvmFragment;
import com.open.yaoraotu.m.viewmodel.GroupMenuExpandableListViewModel;
import com.open.yaoraotu.utils.UrlUtils;

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
public class GroupMenuExpandableListMvvmActivity extends CommonCommonFragmentActivity {
    /*
     * (non-Javadoc)
     *
     * @see com.open.enrz.activity.CommonFragmentActivity#initValue()
     */
    @Override
    protected void initValue() {
        // TODO Auto-generated method stub
//		super.initValue();
        if (getIntent().getStringExtra("URL") != null) {
            url = getIntent().getStringExtra("URL");
        } else {
            url = UrlUtils.YAORAOTU_TAOTU_TUIGIRL;
        }
//        setCenterTextValue(getResources().getString(R.string.app_name));
        addfragment();
    }
    /*
     * (non-Javadoc)
     *
     * @see com.open.android.activity.CommonFragmentActivity#addfragment()
     */
    @Override
    public void addfragment() {
        // TODO Auto-generated method stub
        GroupMenuExpandableListMvvmFragment fragment =  GroupMenuExpandableListMvvmFragment.newInstance(url,true);
        getSupportFragmentManager().beginTransaction().replace(R.id.id_common_fragment, fragment).commit();

        GroupMenuExpandableListViewModel mViewModel = new GroupMenuExpandableListViewModel(this,"getGroupMenu");
        fragment.setViewModel(mViewModel);
    }

    public static void startGroupMenuExpandableListMvvmActivity(Context context, String url) {
        Intent intent = new Intent();
        intent.putExtra("URL", url);
        intent.setClass(context, GroupMenuExpandableListMvvmActivity.class);
        context.startActivity(intent);
    }
}