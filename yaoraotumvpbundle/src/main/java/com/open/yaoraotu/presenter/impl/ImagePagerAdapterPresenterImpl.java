/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-9-18上午11:17:53
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.yaoraotu.presenter.impl;

import android.content.Context;

import com.google.gson.Gson;
import com.open.android.bean.db.OpenDBBean;
import com.open.android.db.service.OpenDBService;
import com.open.android.mvp.base.CommonAsyncTaskPresenter2;
import com.open.android.mvp.view.CommonView;
import com.open.android.utils.NetWorkUtils;
import com.open.yaoraotu.json.MasonryJson;
import com.open.yaoraotu.jsoup.YaoRaoTuJsoupService;
import com.open.yaoraotu.presenter.ImagePagerAdapterPresenter;

import java.util.List;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-9-18上午11:17:53
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class ImagePagerAdapterPresenterImpl extends CommonAsyncTaskPresenter2<MasonryJson,ImagePagerAdapterPresenter,CommonView<MasonryJson,ImagePagerAdapterPresenter>> implements ImagePagerAdapterPresenter {
	private int position;
	private String href;
	
	public ImagePagerAdapterPresenterImpl(Context mContext,CommonView view,String url){
		this.mContext = mContext;
		this.url = url;
		this.mViewModel = view;
		this.mViewModel.setPresenter(this);
	}
	
 
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.umei.activity.BaseFragmentActivity#call()
	 */
	@Override
	public MasonryJson call() throws Exception {
		// TODO Auto-generated method stub
		MasonryJson mMArticleJson =null;
		String typename = "YaoRaoTuJsoupService-parseImagePagerList-"+pageNo;
		if(NetWorkUtils.isNetworkAvailable(mContext)){
			if(pageNo==1){
				mMArticleJson = YaoRaoTuJsoupService.parseImagePagerList(url,position);
			}else{
				mMArticleJson = new MasonryJson();
				mMArticleJson.setList(YaoRaoTuJsoupService.getImageList(getUrl(),position));
				mMArticleJson.setCurrentPosition(position);
			}
			try {
				//数据存储
				Gson gson = new Gson();
				OpenDBBean  openbean = new OpenDBBean();
	    	    openbean.setUrl(url);
	    	    openbean.setTypename(typename);
			    openbean.setTitle(gson.toJson(mMArticleJson));
			    OpenDBService.insert(mContext, openbean);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			List<OpenDBBean> beanlist =  OpenDBService.queryListType(mContext, url,typename);
			String result = beanlist.get(0).getTitle();
			Gson gson = new Gson();
			mMArticleJson = gson.fromJson(result, MasonryJson.class);
		}
		return mMArticleJson;
	}

	/* (non-Javadoc)
	 * @see com.open.mmxzg.presenter.MImagePagerAdapterPresenter#setPosition(int)
	 */
	@Override
	public void setPosition(int position) {
		// TODO Auto-generated method stub
		this.position = position;
	}

	/* (non-Javadoc)
	 * @see com.open.mmxzg.presenter.MImagePagerAdapterPresenter#setUrl(java.lang.String)
	 */
	@Override
	public void setUrl(String url) {
		// TODO Auto-generated method stub
		this.href = url;
	}

	/* (non-Javadoc)
	 * @see com.open.mmxzg.presenter.MImagePagerAdapterPresenter#getUrl()
	 */
	@Override
	public String getUrl() {
		// TODO Auto-generated method stub
		return href;
	}

}