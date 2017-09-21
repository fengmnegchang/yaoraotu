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
package com.open.yaoraotu.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.open.android.utils.ServiceUtils;
import com.open.yaoraotu.fragment.MenuPullListFragment;
import com.open.yaoraotu.fragment.ThemePullListHeadFootFragment;
import com.open.yaoraotu.mvp.R;
import com.open.yaoraotu.presenter.impl.MasonryPullGridPresenterImpl;
import com.open.yaoraotu.presenter.impl.ReflectJsoupPresenterImpl;
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
public class MainSlideMenuActivity extends SlidingFragmentActivity {
	private String url = UrlUtils.YAORAOTU_TAOTU;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setStatusBarColor(getResources().getColor(R.color.status_bar_color));
		setContentView(R.layout.activity_m_main_slide);
		// 初始化SlideMenu
		initRightMenu();

		// url = "http://www.umei.cc/bizhitupian/diannaobizhi/7628.htm";
		// Fragment fragment = UmeiArticlePagerFragment.newInstance(url, true);
//		Fragment fragment = MIndexPagerFragment.newInstance(url,true);
		ThemePullListHeadFootFragment fragment = ThemePullListHeadFootFragment.newInstance(true);
		getSupportFragmentManager().beginTransaction().replace(R.id.layout_viewpager, fragment).commit();
		new MasonryPullGridPresenterImpl(this, fragment,url);

		if (!ServiceUtils.isServiceExisted(this, "HotImagePushService")) {
			ServiceUtils.startPollingService(this, 5, "com.open.yaoraotu.service.HotImagePushService", "com.open.yaoraotu.service.HotImagePushService");
		}
	}

	private void initRightMenu() {

		setBehindContentView(R.layout.left_menu_frame);
//		getSupportFragmentManager().beginTransaction().replace(R.id.id_left_menu_frame,new MenuLeftFragment()).commit();
		MenuPullListFragment fragment = MenuPullListFragment.newInstance(true);
		getSupportFragmentManager().beginTransaction().replace(R.id.id_left_menu_frame,fragment).commit();
		new ReflectJsoupPresenterImpl(this, fragment,url,"getMenuLi");
		
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