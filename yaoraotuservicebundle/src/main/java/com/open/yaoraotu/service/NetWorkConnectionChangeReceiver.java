package com.open.yaoraotu.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.open.android.utils.ServiceUtils;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fengguangjing
 * @createTime: 17/9/21
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class NetWorkConnectionChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        NetworkInfo mobNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (activeNetInfo != null) {
            // Toast.makeText( context, "Active Network Type : " +
            // activeNetInfo.getTypeName(), Toast.LENGTH_SHORT ).show();
            Log.d("NetWork", "Active  : " +activeNetInfo.getTypeName());
            if(!ServiceUtils.isServiceExisted(context, HotImagePushService.class.getSimpleName())){
                ServiceUtils.startPollingService(context, 5, HotImagePushService.class, HotImagePushService.ACTION);
            }
        }
        if (mobNetInfo != null) {
            // Toast.makeText( context, "Mobile Network Type : " +
            // mobNetInfo.getTypeName(), Toast.LENGTH_SHORT ).show();
            Log.d("NetWork", "Mobile  : " +mobNetInfo.getTypeName());
        }
    }
}