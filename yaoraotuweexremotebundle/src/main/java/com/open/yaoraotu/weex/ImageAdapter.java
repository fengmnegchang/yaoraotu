package com.open.yaoraotu.weex;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.taobao.weex.adapter.IWXImgLoaderAdapter;
import com.taobao.weex.common.WXImageStrategy;
import com.taobao.weex.dom.WXImageQuality;

/**
 */

public class ImageAdapter implements IWXImgLoaderAdapter {
    @Override
    public void setImage(String url, ImageView view, WXImageQuality quality, WXImageStrategy strategy) {
        //实现你自己的图片下载，否则图片无法显示。
//        Glide.with(view.getContext())
//                .load(url)
//                .into(view);
        Picasso.with(view.getContext()).load(url).into(view);
    }
}
