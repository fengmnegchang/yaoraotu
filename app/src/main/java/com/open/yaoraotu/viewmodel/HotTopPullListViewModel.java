package com.open.yaoraotu.viewmodel;

import android.content.Context;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.util.Log;
import android.widget.HeaderViewListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.open.android.bean.db.OpenDBBean;
import com.open.android.db.service.OpenDBService;
import com.open.android.utils.NetWorkUtils;
import com.open.yaoraotu.BR;
import com.open.yaoraotu.adapter.mvvm.MvvmHotTopAdapter;
import com.open.yaoraotu.bean.MasonryBean;
import com.open.yaoraotu.json.MasonryJson;
import com.open.yaoraotu.jsoup.YaoRaoTuJsoupService;

import java.util.List;

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
public class HotTopPullListViewModel extends MVVMCommonViewModel<MasonryJson>{
    // These observable fields will update Views automatically
    public ObservableList<MasonryBean> items = new ObservableArrayList<MasonryBean>();

    public HotTopPullListViewModel(Context mContext) {
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
	 * @see
	 * com.open.android.fragment.common.CommonPullToRefreshListFragment#call()
	 */
    @Override
    public MasonryJson call() throws Exception {
        // TODO Auto-generated method stub
        MasonryJson mMArticleJson = new MasonryJson();
        String href = url;
//        if(pageNo>1){
//            href = url+"list_"+pageNo+".html";
//        }
        String typename = "YaoRaoTuJsoupService-getHotList-"+pageNo;
        if(NetWorkUtils.isNetworkAvailable(mContext)){
            mMArticleJson.setList(YaoRaoTuJsoupService.getHotList(href, pageNo));
//            mMArticleJson.setHotlist(YaoRaoTuJsoupService.getHotList(href, pageNo));
            try {
                //数据存储
                Gson gson = new Gson();
                OpenDBBean openbean = new OpenDBBean();
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
        if (getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_START) {
            items.clear();
            items.addAll(result.getList());
            pageNo = 1;
        } else {
            if (result.getList() != null && result.getList().size() > 0) {
                items.addAll(result.getList());
            }
        }
        notifyPropertyChanged(BR.empty); // It's a @Bindable so update manually
//        mMMVVMArticleListAdapter.notifyDataSetChanged();
        // Call onRefreshComplete when the list has been refreshed.
//        mPullToRefreshListView.onRefreshComplete();
        Log.d("mNavigator===","onCallback");
        mNavigator.onCallback(result);

    }

    @SuppressWarnings("unchecked")
    //@BindingAdapter("bind:src")
//    @BindingAdapter({"src"})
    @BindingAdapter("items")
    public static  void setItems(PullToRefreshListView listView, List<MasonryBean> items) {
        Log.d("setItems", "===========");
        ListView listView1 = listView.getRefreshableView();
        HeaderViewListAdapter listAdapter = (HeaderViewListAdapter) listView1.getAdapter();
        if (listAdapter.getWrappedAdapter() instanceof MvvmHotTopAdapter) {
            MvvmHotTopAdapter adapter = (MvvmHotTopAdapter) listAdapter.getWrappedAdapter();
            if (adapter != null) {
                Log.d("setItems", "=====adapter======");
                adapter.replaceData(items);
            }
        }
    }

    @Bindable
    public boolean isEmpty() {
        return items.isEmpty();
    }
}
