package com.open.yaoraotu.json;

import com.open.android.json.CommonJson;
import com.open.yaoraotu.bean.MasonryGroupBean;

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
public class MasonryGroupJson extends CommonJson {
    private List<MasonryGroupBean> list;

    public List<MasonryGroupBean> getList() {
        return list;
    }

    public void setList(List<MasonryGroupBean> list) {
        this.list = list;
    }
}
