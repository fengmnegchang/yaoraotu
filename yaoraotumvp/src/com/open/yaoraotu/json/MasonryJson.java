/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-9-1上午10:56:35
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.yaoraotu.json;

import java.util.List;

import com.open.android.json.CommonJson;
import com.open.yaoraotu.bean.MasonryBean;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-9-1上午10:56:35
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MasonryJson extends CommonJson {
	private List<MasonryBean> list;

	public List<MasonryBean> getList() {
		return list;
	}

	public void setList(List<MasonryBean> list) {
		this.list = list;
	}
	

}
