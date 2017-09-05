/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-9-5上午11:02:43
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.yaoraotu.presenter.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.open.android.bean.db.OpenDBBean;
import com.open.android.db.service.OpenDBService;
import com.open.android.mvp.base.CommonAsyncTaskPresenter2;
import com.open.android.mvp.presenter.CommonPresenter;
import com.open.android.mvp.view.CommonView;
import com.open.android.utils.NetWorkUtils;
import com.open.yaoraotu.json.MasonryJson;
import com.open.yaoraotu.jsoup.YaoRaoTuJsoupService;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-9-5上午11:02:43
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MasonryPullGridPresenterImpl extends CommonAsyncTaskPresenter2<MasonryJson,CommonPresenter,CommonView<MasonryJson,CommonPresenter>>  {
	public MasonryPullGridPresenterImpl(Context context, @NonNull CommonView view,String url) {
		mViewModel = checkNotNull(view, "CommonView cannot be null!");
		mViewModel.setPresenter(this);
		this.mContext = context;
		this.url = url;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.open.android.fragment.common.CommonPullToRefreshListFragment#call()
	 */
	@Override
	public MasonryJson call() throws Exception {
		// TODO Auto-generated method stub
		MasonryJson mMArticleJson = new MasonryJson();
		String href = url;
		if(pageNo>1){
			href = url+"list_"+pageNo+".html";
		}
		String typename = "YaoRaoTuJsoupService-getPliList-"+pageNo;
		if(NetWorkUtils.isNetworkAvailable(mContext)){
			mMArticleJson.setList(YaoRaoTuJsoupService.getPliList(href, pageNo));
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
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * com.open.android.fragment.common.CommonPullToRefreshListFragment#onCallback
//	 * (com.open.android.json.CommonJson)
//	 */
//	@Override
//	public void onCallback(MasonryJson result) {
//		mViewModel.onCallback(result);
//	}


}
