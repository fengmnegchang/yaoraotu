/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-31下午4:18:02
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.yaoraotu.splash.presenter.impl;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.open.android.json.CommonJson;
import com.open.android.mvp.base.CommonAsyncTaskPresenter2;
import com.open.yaoraotu.application.CommonApplication;
import com.open.yaoraotu.bean.PatchBean;
import com.open.yaoraotu.splash.activity.SplashActivity;
import com.open.yaoraotu.splash.presenter.SplashPresenter;
import com.open.yaoraotu.splash.viewmodel.SplashViewModel;
import com.taobao.sophix.SophixManager;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-8-31下午4:18:02
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class SplashPresenterImpl extends CommonAsyncTaskPresenter2<CommonJson,SplashPresenter,SplashViewModel<CommonJson,SplashPresenter>> implements SplashPresenter {
	private static final int SHOW_TIME_MIN = 3000;// 最小显示时间
	private long mStartTime;// 开始时间
	private static final int REQUEST_EXTERNAL_STORAGE_PERMISSION = 0;

	public SplashPresenterImpl(Context context, @NonNull SplashViewModel viewModel) {
		mViewModel = checkNotNull(viewModel, "splashView cannot be null!");
		mViewModel.setPresenter(this);
		mContext = context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.mmxzg.mvp.base.BasePresenter#start()
	 */
	@Override
	public void start() {
		initData();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.mmxzg.model.SplashContract.Presenter#initData()
	 */
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		mStartTime = System.currentTimeMillis();// 记录开始时间，
		SophixManager.getInstance().queryAndLoadNewPatch();

		CommonApplication.msgDisplayListener = new CommonApplication.MsgDisplayListener() {
			@Override
			public void handle(final String msg) {
				((SplashActivity)mContext).runOnUiThread(new Runnable() {
					@Override
					public void run() {
						updateConsole(msg);
					}
				});
			}
		};

		new Thread() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Thread#run()
			 */
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(3000);
					// UserInfoDBService.userinfo(mContext);
					mHandler.sendEmptyMessage(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				super.run();
			}

		}.start();
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1000:// 如果城市列表加载完毕，就发送此消息
				long loadingTime = System.currentTimeMillis() - mStartTime;// 计算一下总共花费的时间
				if (loadingTime < SHOW_TIME_MIN) {// 如果比最小显示时间还短，就延时进入MainActivity，否则直接进入
					mHandler.postDelayed(goToMainActivity, SHOW_TIME_MIN - loadingTime);
				} else {
					mHandler.post(goToMainActivity);
				}
				break;
			default:
				break;
			}
		}
	};
	// 进入下一个Activity
	Runnable goToMainActivity = new Runnable() {
		@Override
		public void run() {
			mViewModel.goToMain();
		}
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.android.mvp.presenter.CommonPresenter#doAsync()
	 */
	@Override
	public void doAsync() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.android.mvp.presenter.CommonPresenter#setPageNo(int)
	 */
	@Override
	public void setPageNo(int pageNo) {
		// TODO Auto-generated method stub

	}

	/**
	 * 更新监控台的输出信息
	 * 
	 * @param content
	 *            更新内容
	 */
	private void updateConsole(String content) {
		try {
			Gson gson = new Gson();
			PatchBean bean = gson.fromJson(content, PatchBean.class);
			if (bean != null) {
				if (bean.getHandlePatchVersion() > 0) {
					alertNewPatch(content);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void alertNewPatch(String content) {
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		builder.setItems(new String[] { content + "检测到热更新包，重启生效" }, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				android.os.Process.killProcess(android.os.Process.myPid());
			}
		});
		builder.show();
	}

}
