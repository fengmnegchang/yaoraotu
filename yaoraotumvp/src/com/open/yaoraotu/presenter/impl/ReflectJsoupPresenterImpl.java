/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-9-14下午3:50:38
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
import com.open.android.utils.ReflectJsoupUtils;
import com.open.yaoraotu.bean.MasonryBean;
import com.open.yaoraotu.json.MasonryJson;
import com.open.yaoraotu.jsoup.YaoRaoTuJsoupService;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-9-14下午3:50:38
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class ReflectJsoupPresenterImpl extends CommonAsyncTaskPresenter2<MasonryJson,CommonPresenter,CommonView<MasonryJson,CommonPresenter>>  {
	
	public ReflectJsoupPresenterImpl(Context context, @NonNull CommonView view,String url,String methodName) {
		this(context, view, url, null, methodName, null, null);
	}
	
	public ReflectJsoupPresenterImpl(Context context, @NonNull CommonView view,String url,String className,String methodName,Class[] parameterTypes,Object[] args) {
		mViewModel = checkNotNull(view, "CommonView cannot be null!");
		mViewModel.setPresenter(this);
		this.mContext = context;
		this.url = url;
		this.className = className;
		this.methodName = methodName;
		this.parameterTypes = parameterTypes;
		this.args = args;
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
		if(className==null){
			className = YaoRaoTuJsoupService.class.getName();
		}
		if(parameterTypes==null){
			parameterTypes = new Class[] { String.class,int.class };
		}
		
		if(args==null){
			args = new Object[] { href, pageNo};
		}
		
		String typename = className+"-"+methodName+"-"+pageNo;
		if(NetWorkUtils.isNetworkAvailable(mContext)){
//			mMArticleJson.setList(YaoRaoTuJsoupService.getPliList(href, pageNo));
			try {
//				Class clazz = Class.forName("com.open.yaoraotu.jsoup.YaoRaoTuJsoupService");
//		        Constructor c = clazz.getConstructor();
//		        Method method = clazz.getMethod("getNewList", new Class[] { String.class,int.class });
//		        List<MasonryBean> list = (List<MasonryBean>) method.invoke(c.newInstance(), new Object[] { href, pageNo});
				
//				List<MasonryBean> list = (List<MasonryBean>) ReflectJsoupUtils.invoke(YaoRaoTuJsoupService.class.getName(), "getNewList",
//						 new Class[] { String.class,int.class }, 
//						 new Object[] { href, pageNo});
				
				List<MasonryBean> list = (List<MasonryBean>) ReflectJsoupUtils.invoke(className, methodName,
						parameterTypes, args);
		        mMArticleJson.setList(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
//			mMArticleJson.setList(YaoRaoTuJsoupService.getNewList(href, pageNo));
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
}