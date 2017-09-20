package com.open.yaoraotu.activity.mvvm;

import android.content.Context;
import android.content.Intent;

import com.open.android.activity.common.CommonTitleBarActivity;
import com.open.yaoraotu.mvvm.R;
import com.open.yaoraotu.fragment.mvvm.SubMenuIndicatorMvvmFragment;
import com.open.yaoraotu.utils.UrlUtils;
import com.open.yaoraotu.viewmodel.SubMenuIndicatorViewModel;

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
public class SubMenuIndicatorMvvmActivity extends CommonTitleBarActivity {
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
            url = UrlUtils.YAORAOTU_TAOTU;
        }
        setCenterTextValue(getResources().getString(R.string.app_name));
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
        SubMenuIndicatorMvvmFragment fragment =  SubMenuIndicatorMvvmFragment.newInstance(url,true,getIntent().getIntExtra("PAGE_NO",1));
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_content, fragment).commit();

        SubMenuIndicatorViewModel mViewModel = new SubMenuIndicatorViewModel(this,"getSubMenuLi");
        fragment.setViewModel(mViewModel);
    }

    public static void startSubMenuIndicatorMvvmActivity(Context context, String url,int pageNo) {
        Intent intent = new Intent();
        intent.putExtra("URL", url);
        intent.putExtra("PAGE_NO", pageNo);
        intent.setClass(context, SubMenuIndicatorMvvmActivity.class);
        context.startActivity(intent);
    }
}