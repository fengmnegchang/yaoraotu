package com.open.yaoraotu.bean;

import com.open.android.bean.CommonBean;

import java.util.List;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fengguangjing
 * @createTime: 17/9/22
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class MasonryGroupBean extends CommonBean {
    private List<MasonryBean> list;
    private String title;
    private String href;

    public List<MasonryBean> getList() {
        return list;
    }

    public void setList(List<MasonryBean> list) {
        this.list = list;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
