/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-9-5上午10:38:11
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.yaoraotu.jsoup;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.util.Log;

import com.open.android.jsoup.CommonService;
import com.open.yaoraotu.bean.MasonryBean;
import com.open.yaoraotu.utils.UrlUtils;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-9-5上午10:38:11
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class YaoRaoTuJsoupService extends CommonService{
	
	public static List<MasonryBean> getPliList(String href, int pageNo){
		List<MasonryBean> list = new ArrayList<MasonryBean>();
		try {
			Document doc;
			doc = Jsoup.connect(href)
					.userAgent(UrlUtils.userAgent)
//					.header("Host","www.pximg.com")
//					.cookie("Cookie", "UM_distinctid=15d4f9ce60a4eb-050c6be969bcef-35414878-1aeaa0-15d4f9ce60b3bc; CNZZDATA1260136144=1243021942-1500278440-https%253A%252F%252Fwww.baidu.com%252F%7C1501116123; Hm_lvt_21e82dda40c2143d1c3187f1c80935ec=1500279272,1500968826,1501061381; Hm_lpvt_21e82dda40c2143d1c3187f1c80935ec=1501119552")
					.timeout(10000).get();
			Log.i(TAG, "url = " + href);
			
			/**
			 * <li class="Pli"> <a href="https://www.yaoraotu.com/taotu/tuigirl/10812.html" 
			 * title="推女郎超性感美女梦心月床上人体艺术写真" target="_blank"> 
			 * <img src="https://img.yaoraotu.com:8091/Thumb/2017/0303/d239fb2f313159ec12e3549db641bb99.jpg"
			 *  alt="推女郎超性感美女梦心月床上人体艺术写真"> </a> 
       <div class="imgabout about-title"> 
        <a href="https://www.yaoraotu.com/taotu/tuigirl/10812.html" 
        title="推女郎超性感美女梦心月床上人体艺术写真" target="_blank">推女郎超性感美女梦心月床上人体艺术写真</a> 
       </div> 
       <div class="imgabout">
        2017-03-03
       </div> </li> 
			 */
			try {
//				 Element globalnavElement = doc.select("ul.wp-tag-cloud").first();
				Elements moduleElements = doc.select("li.Pli");
				if (moduleElements != null && moduleElements.size() > 0) {
					for (int i = 0; i < moduleElements.size(); i++) {
						MasonryBean sbean = new MasonryBean();
							try {
								try {
									Element aElement = moduleElements.get(i).select("a").first();
									if (aElement != null) {
										String hrefa = aElement.attr("href");
										Log.i(TAG, "i==" + i + ";hrefa==" + hrefa);
										sbean.setHref(hrefa);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}


								try {
									Element imgElement = moduleElements.get(i).select("div.imgabout").get(1);
									if (imgElement != null) {
										String alt = imgElement.text();
										Log.i(TAG, "i==" + i + ";alt==" + alt);
										sbean.setAlt(alt);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
								
								try {
									Element imgElement = moduleElements.get(i).select("a").first();
									if (imgElement != null) {
										String title = imgElement.attr("title");
										Log.i(TAG, "i==" + i + ";title==" + title);
										sbean.setTitle(title);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
								
								try {
									Element imgElement = moduleElements.get(i).select("img").first();
									if (imgElement != null) {
										String src = imgElement.attr("src");
										Log.i(TAG, "i==" + i + ";src==" + src);
										sbean.setSrc(src);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

							} catch (Exception e) {
								e.printStackTrace();
							}

							list.add(sbean);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static List<MasonryBean> getHotList(String href, int pageNo){
		List<MasonryBean> list = new ArrayList<MasonryBean>();
		try {
			Document doc;
			doc = Jsoup.connect(href)
					.userAgent(UrlUtils.userAgent)
//					.header("Host","www.pximg.com")
//					.cookie("Cookie", "UM_distinctid=15d4f9ce60a4eb-050c6be969bcef-35414878-1aeaa0-15d4f9ce60b3bc; CNZZDATA1260136144=1243021942-1500278440-https%253A%252F%252Fwww.baidu.com%252F%7C1501116123; Hm_lvt_21e82dda40c2143d1c3187f1c80935ec=1500279272,1500968826,1501061381; Hm_lpvt_21e82dda40c2143d1c3187f1c80935ec=1501119552")
					.timeout(10000).get();
			Log.i(TAG, "url = " + href);
			try {
				 Element globalnavElement = doc.select("div.hotlist").first();
				Elements moduleElements = globalnavElement.select("li");
				if (moduleElements != null && moduleElements.size() > 0) {
					for (int i = 0; i < moduleElements.size(); i++) {
						MasonryBean sbean = new MasonryBean();
							try {
								try {
									Element aElement = moduleElements.get(i).select("a").first();
									if (aElement != null) {
										String hrefa = aElement.attr("href");
										Log.i(TAG, "i==" + i + ";hrefa==" + hrefa);
										sbean.setHref(hrefa);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

								try {
									Element imgElement = moduleElements.get(i).select("a").first();
									if (imgElement != null) {
										String title = imgElement.attr("title");
										Log.i(TAG, "i==" + i + ";title==" + title);
										sbean.setTitle(title);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
								
								try {
									Element imgElement = moduleElements.get(i).select("div.hdate").first();
									if (imgElement != null) {
										String alt = imgElement.text();
										Log.i(TAG, "i==" + i + ";alt==" + alt);
										sbean.setAlt(alt);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
								
								try {
									Element imgElement = moduleElements.get(i).select("img").first();
									if (imgElement != null) {
										String src = imgElement.attr("src");
										Log.i(TAG, "i==" + i + ";src==" + src);
										sbean.setSrc(src);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

							} catch (Exception e) {
								e.printStackTrace();
							}

							list.add(sbean);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static List<MasonryBean> getNewList(String href, int pageNo){
		List<MasonryBean> list = new ArrayList<MasonryBean>();
		try {
			Document doc;
			doc = Jsoup.connect(href)
					.userAgent(UrlUtils.userAgent)
//					.header("Host","www.pximg.com")
//					.cookie("Cookie", "UM_distinctid=15d4f9ce60a4eb-050c6be969bcef-35414878-1aeaa0-15d4f9ce60b3bc; CNZZDATA1260136144=1243021942-1500278440-https%253A%252F%252Fwww.baidu.com%252F%7C1501116123; Hm_lvt_21e82dda40c2143d1c3187f1c80935ec=1500279272,1500968826,1501061381; Hm_lpvt_21e82dda40c2143d1c3187f1c80935ec=1501119552")
					.timeout(10000).get();
			Log.i(TAG, "url = " + href);
			try {
				 Element globalnavElement = doc.select("ul.newlist").first();
				Elements moduleElements = globalnavElement.select("li");
				if (moduleElements != null && moduleElements.size() > 0) {
					for (int i = 0; i < moduleElements.size(); i++) {
						MasonryBean sbean = new MasonryBean();
							try {
								try {
									Element aElement = moduleElements.get(i).select("a").first();
									if (aElement != null) {
										String hrefa = aElement.attr("href");
										Log.i(TAG, "i==" + i + ";hrefa==" + hrefa);
										sbean.setHref(hrefa);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

								try {
									Element imgElement = moduleElements.get(i).select("a").first();
									if (imgElement != null) {
										String title = imgElement.attr("title");
										Log.i(TAG, "i==" + i + ";title==" + title);
										sbean.setTitle(title);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							} catch (Exception e) {
								e.printStackTrace();
							}

							list.add(sbean);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<MasonryBean> getTopTheme(String href, int pageNo){
		List<MasonryBean> list = new ArrayList<MasonryBean>();
		try {
			Document doc;
			doc = Jsoup.connect(href)
					.userAgent(UrlUtils.userAgent)
//					.header("Host","www.pximg.com")
//					.cookie("Cookie", "UM_distinctid=15d4f9ce60a4eb-050c6be969bcef-35414878-1aeaa0-15d4f9ce60b3bc; CNZZDATA1260136144=1243021942-1500278440-https%253A%252F%252Fwww.baidu.com%252F%7C1501116123; Hm_lvt_21e82dda40c2143d1c3187f1c80935ec=1500279272,1500968826,1501061381; Hm_lpvt_21e82dda40c2143d1c3187f1c80935ec=1501119552")
					.timeout(10000).get();
			Log.i(TAG, "url = " + href);
			try {
				 Element globalnavElement = doc.select("div.columnall").first();
				Elements moduleElements = globalnavElement.select("li");
				if (moduleElements != null && moduleElements.size() > 0) {
					for (int i = 0; i < moduleElements.size(); i++) {
						MasonryBean sbean = new MasonryBean();
							try {
								try {
									Element aElement = moduleElements.get(i).select("a").first();
									if (aElement != null) {
										String hrefa = aElement.attr("href");
										Log.i(TAG, "i==" + i + ";hrefa==" + hrefa);
										sbean.setHref(hrefa);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

								try {
									Element imgElement = moduleElements.get(i).select("a").first();
									if (imgElement != null) {
										String title = imgElement.attr("title");
										Log.i(TAG, "i==" + i + ";title==" + title);
										sbean.setTitle(title);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							} catch (Exception e) {
								e.printStackTrace();
							}

							list.add(sbean);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<MasonryBean> getMenuLi(String href, int pageNo){
		List<MasonryBean> list = new ArrayList<MasonryBean>();
		try {
			Document doc;
			doc = Jsoup.connect(href)
					.userAgent(UrlUtils.userAgent)
//					.header("Host","www.pximg.com")
//					.cookie("Cookie", "UM_distinctid=15d4f9ce60a4eb-050c6be969bcef-35414878-1aeaa0-15d4f9ce60b3bc; CNZZDATA1260136144=1243021942-1500278440-https%253A%252F%252Fwww.baidu.com%252F%7C1501116123; Hm_lvt_21e82dda40c2143d1c3187f1c80935ec=1500279272,1500968826,1501061381; Hm_lpvt_21e82dda40c2143d1c3187f1c80935ec=1501119552")
					.timeout(10000).get();
			Log.i(TAG, "url = " + href);
			try {
				 Element globalnavElement = doc.select("ul.navwarp").first();
				Elements moduleElements = globalnavElement.select("li.nLi");
				if (moduleElements != null && moduleElements.size() > 0) {
					for (int i = 0; i < moduleElements.size(); i++) {
						MasonryBean sbean = new MasonryBean();
							try {
								try {
									Element aElement = moduleElements.get(i).select("a").first();
									if (aElement != null) {
										String hrefa = aElement.attr("href");
										Log.i(TAG, "i==" + i + ";hrefa==" + hrefa);
										sbean.setHref(hrefa);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

								try {
									Element imgElement = moduleElements.get(i).select("a").first();
									if (imgElement != null) {
										String title = imgElement.attr("title");
										Log.i(TAG, "i==" + i + ";title==" + title);
										sbean.setTitle(title);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							} catch (Exception e) {
								e.printStackTrace();
							}

							list.add(sbean);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static List<MasonryBean> getSubMenuLi(String href, int pageNo){
		List<MasonryBean> list = new ArrayList<MasonryBean>();
		try {
			Document doc;
			doc = Jsoup.connect(href)
					.userAgent(UrlUtils.userAgent)
//					.header("Host","www.pximg.com")
//					.cookie("Cookie", "UM_distinctid=15d4f9ce60a4eb-050c6be969bcef-35414878-1aeaa0-15d4f9ce60b3bc; CNZZDATA1260136144=1243021942-1500278440-https%253A%252F%252Fwww.baidu.com%252F%7C1501116123; Hm_lvt_21e82dda40c2143d1c3187f1c80935ec=1500279272,1500968826,1501061381; Hm_lpvt_21e82dda40c2143d1c3187f1c80935ec=1501119552")
					.timeout(10000).get();
			Log.i(TAG, "url = " + href);
			try {
				Element globalnavElement = doc.select("ul.navwarp").first();
				Element moduleElement = globalnavElement.select("li.nLi").get(pageNo);
				Elements moduleElements = moduleElement.select("ul.sub").first().select("li");
				if (moduleElements != null && moduleElements.size() > 0) {
					for (int i = 0; i < moduleElements.size(); i++) {
						MasonryBean sbean = new MasonryBean();
							try {
								try {
									Element aElement = moduleElements.get(i).select("a").first();
									if (aElement != null) {
										String hrefa = aElement.attr("href");
										Log.i(TAG, "i==" + i + ";hrefa==" + hrefa);
										sbean.setHref(hrefa);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

								try {
									Element imgElement = moduleElements.get(i).select("a").first();
									if (imgElement != null) {
										String title = imgElement.attr("title");
										Log.i(TAG, "i==" + i + ";title==" + title);
										sbean.setTitle(title);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							} catch (Exception e) {
								e.printStackTrace();
							}

							list.add(sbean);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

}
