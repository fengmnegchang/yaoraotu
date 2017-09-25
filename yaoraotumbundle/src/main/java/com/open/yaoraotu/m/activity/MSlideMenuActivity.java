/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-9-18上午9:59:15
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.yaoraotu.m.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.open.yaoraotu.m.R;
import com.open.yaoraotu.m.fragment.GroupHomeExpandableListMvvmFragment;
import com.open.yaoraotu.m.fragment.GroupMenuExpandableListMvvmFragment;
import com.open.yaoraotu.m.viewmodel.GroupHomeExpandableListViewModel;
import com.open.yaoraotu.m.viewmodel.GroupMenuExpandableListViewModel;
import com.open.yaoraotu.utils.UrlUtils;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-9-18上午9:59:15
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MSlideMenuActivity extends SlidingFragmentActivity {
	private String url = UrlUtils.YAORAOTU_TAOTU;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setStatusBarColor(getResources().getColor(R.color.status_bar_color));
		setContentView(R.layout.activity_m_slide);
		// 初始化SlideMenu
		initRightMenu();

		// url = "http://www.umei.cc/bizhitupian/diannaobizhi/7628.htm";
		// Fragment fragment = UmeiArticlePagerFragment.newInstance(url, true);
//		Fragment fragment = MIndexPagerFragment.newInstance(url,true);
		GroupHomeExpandableListMvvmFragment fragment = GroupHomeExpandableListMvvmFragment.newInstance(UrlUtils.YAORAOTU_M,true);
		getSupportFragmentManager().beginTransaction().replace(R.id.layout_viewpager, fragment).commit();

		GroupHomeExpandableListViewModel mViewModel = new GroupHomeExpandableListViewModel(this,"getGroupHome");
		fragment.setViewModel(mViewModel);

//		if (!ServiceUtils.isServiceExisted(this, "HotImagePushService")) {
//			ServiceUtils.startPollingService(this, 5, "com.open.yaoraotu.service.HotImagePushService", "com.open.yaoraotu.service.HotImagePushService");
//		}
	}

	private void initRightMenu() {

		setBehindContentView(R.layout.left_menu_mframe);
//		getSupportFragmentManager().beginTransaction().replace(R.id.id_left_menu_frame,new MenuLeftFragment()).commit();
		GroupMenuExpandableListMvvmFragment fragment = GroupMenuExpandableListMvvmFragment.newInstance(UrlUtils.YAORAOTU_TAOTU_TUIGIRL,true);
		getSupportFragmentManager().beginTransaction().replace(R.id.id_left_menu_frame,fragment).commit();

		GroupMenuExpandableListViewModel mViewModel = new GroupMenuExpandableListViewModel(this,"getGroupMenu");
		fragment.setViewModel(mViewModel);
		
		SlidingMenu menu = getSlidingMenu();
		menu.setMode(SlidingMenu.LEFT);
		// 设置触摸屏幕的模式
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.shadow);
		// 设置滑动菜单视图的宽度
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		// menu.setBehindWidth()
		// 设置渐入渐出效果的值
		menu.setFadeDegree(0.35f);
		// menu.setBehindScrollScale(1.0f);
		menu.setSecondaryShadowDrawable(R.drawable.shadow);
		// // 设置右边（二级）侧滑菜单
		// menu.setSecondaryMenu(R.layout.right_menu_frame);
		// Fragment rightMenuFragment = new MenuRightFragment();
		// getSupportFragmentManager().beginTransaction().replace(R.id.id_right_menu_frame,
		// rightMenuFragment).commit();
	}

	public void showLeftMenu(View view) {
		getSlidingMenu().showMenu();
	}
	
	public void toSearch(View view) {
//		MSearchEditFragmentActivity.startMSearchEditFragmentActivity(this, url);
//		MCommonTitleBarSearchEditFragmentActivity.startMCommonTitleBarSearchEditFragmentActivity(this, url);
	}

}