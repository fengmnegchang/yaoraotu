package com.open.yaoraotu.viewmodel.itemview;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;

import com.open.android.bean.db.OpenDBBean;
import com.open.android.db.service.OpenDBService;
import com.open.android.utils.DownLoadAsyncTask;
import com.open.yaoraotu.mvvm.R;
import com.open.yaoraotu.activity.AppWebViewActivity;

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
public class ImagePagerItemViewModel extends CommonItemViewModel {

    public ImagePagerItemViewModel() {
    }

    public void onItemClick(View view) {
        AppWebViewActivity.startAppWebViewActivity(view.getContext(),
                getHref());
    }

    public void saveImage(final  View imageView){
        AlertDialog.Builder builder = new AlertDialog.Builder(imageView.getContext());
        builder.setItems(new String[]{imageView.getContext().getResources().getString(R.string.save_picture)}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //保存收藏
//	                	   MiStatInterface.recordCountEvent("美图", "保存图片");
                String href="";
                if(getHref()!=null){
                    if(getHref().contains("_")){
                        href =getHref().split("_")[0]+".html";
                    }else{
                        href = getHref();
                    }
                }
                OpenDBBean openbean = new OpenDBBean();
                openbean.setImgsrc(getSrc());
                openbean.setUrl(href);
                openbean.setType(0);
                openbean.setTitle(getAlt());
                openbean.setTypename(0+"");
                OpenDBService.insert(imageView.getContext(), openbean);

//                imageView.setDrawingCacheEnabled(true);
//                Bitmap imageBitmap = imageView.getDrawingCache();
//                if (imageBitmap != null) {
                new DownLoadAsyncTask(imageView.getContext(), getSrc()).execute();
//                }
            }
        });
        builder.show();
    }

}
