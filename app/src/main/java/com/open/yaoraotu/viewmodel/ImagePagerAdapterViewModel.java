package com.open.yaoraotu.viewmodel;

import android.content.Context;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.google.gson.Gson;
import com.open.android.bean.db.OpenDBBean;
import com.open.android.db.service.OpenDBService;
import com.open.android.utils.NetWorkUtils;
import com.open.yaoraotu.adapter.mvvm.MvvmImagePagerAdapter;
import com.open.yaoraotu.bean.MasonryBean;
import com.open.yaoraotu.json.MasonryJson;
import com.open.yaoraotu.jsoup.YaoRaoTuJsoupService;

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
public class ImagePagerAdapterViewModel extends MVVMCommonViewModel<MasonryJson> {
    public ObservableList<MasonryBean> items = new ObservableArrayList<MasonryBean>();
    private int position;
    private String href;
    private boolean isHasData;

    public ImagePagerAdapterViewModel(Context mContext){
        super(mContext);
    }

    @Override
    public void start() {
        super.start();
        doAsync(this,this,this);
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
     * @see com.open.mmxzg.model.MArticlePullListContract.View#onCallback(com.open.mmxzg.json.m.MArticleJson)
     */
    @Override
    public void onCallback(MasonryJson result) {
        if(result==null){
            return;
        }
        if (items.size()==0) {
//            items.clear();
            items.addAll(result.getList());
            pageNo = 1;
        } else {
            if (isHasData) {
                items.clear();
                items.addAll(result.getList());
                isHasData = false;
            } else {
                items.set(result.getCurrentPosition(), result.getList().get(0));
            }
        }
        notifyPropertyChanged(com.open.yaoraotu.BR.empty); // It's a @Bindable so update manually
//        mMMVVMArticleListAdapter.notifyDataSetChanged();
        // Call onRefreshComplete when the list has been refreshed.
//        mPullToRefreshListView.onRefreshComplete();
        Log.d("mNavigator===","onCallback");
        mNavigator.onCallback(result);

    }

    /* (non-Javadoc)
     * @see com.open.mmxzg.presenter.MImagePagerAdapterPresenter#setPosition(int)
     */
    public void setPosition(int position) {
        // TODO Auto-generated method stub
        this.position = position;
    }

    /* (non-Javadoc)
     * @see com.open.mmxzg.presenter.MImagePagerAdapterPresenter#setUrl(java.lang.String)
     */
    public void setUrl(String url) {
        // TODO Auto-generated method stub
        this.href = url;
    }

    /* (non-Javadoc)
     * @see com.open.mmxzg.presenter.MImagePagerAdapterPresenter#getUrl()
     */
    public String getUrl() {
        // TODO Auto-generated method stub
        return href;
    }


    @Bindable
    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void setHasData(boolean hasData) {
        isHasData = hasData;
    }

    public MasonryBean getPositionItem(ViewPager listView, int position){
        if (listView.getAdapter() instanceof MvvmImagePagerAdapter) {
            MvvmImagePagerAdapter adapter = (MvvmImagePagerAdapter) listView.getAdapter();
            if (adapter != null) {
                Log.d("setItems2", "=====adapter======");
                return  adapter.getItem(position);
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    //@BindingAdapter("bind:src")
//    @BindingAdapter({"src"})
    @BindingAdapter("items2")
    public static  void setItems2(ViewPager listView, List<MasonryBean> items) {
        Log.d("setItems2", "===========");
        if (listView.getAdapter() instanceof MvvmImagePagerAdapter) {
            MvvmImagePagerAdapter adapter = (MvvmImagePagerAdapter) listView.getAdapter();
            if (adapter != null) {
                Log.d("setItems2", "=====adapter======");
                adapter.replaceData(items);
            }
        }
    }

}
