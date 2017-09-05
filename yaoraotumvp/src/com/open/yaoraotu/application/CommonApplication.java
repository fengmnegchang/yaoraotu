/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-31下午3:55:54
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.yaoraotu.application;

import java.io.File;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.multidex.MultiDex;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.util.ByteConstants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.open.yaoraotu.ElnImageDownloaderFetcher;
import com.open.yaoraotu.bean.PatchBean;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-31下午3:55:54
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class CommonApplication extends Application{
	// 此TAG在adb logcat中检索自己所需要的信息， 只需在命令行终端输入 adb logcat | grep
    // com.xiaomi.mipushdemo
    public static final String TAG = "com.open.yaoraotu";
    private static DemoHandler sHandler = null;
	
	public interface MsgDisplayListener {
        void handle(String msg);
    }

    public static MsgDisplayListener msgDisplayListener = null;
    public static StringBuilder cacheMsg = new StringBuilder();

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		MultiDex.install(this);
	}
	@Override
    public void onCreate() {
        super.onCreate();
        initHotfix();
        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(this)
                .setBaseDirectoryPath(new File(Environment.getExternalStorageDirectory() +"/"+ getPackageName()+"/"))
                .setBaseDirectoryName("image_cache")
                .setMaxCacheSize(50 * ByteConstants.MB)
                .setMaxCacheSizeOnLowDiskSpace(10 * ByteConstants.MB)
                .setMaxCacheSizeOnVeryLowDiskSpace(2 * ByteConstants.MB)
                .build();
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setNetworkFetcher(new ElnImageDownloaderFetcher())
                .setMainDiskCacheConfig(diskCacheConfig).build();
        Fresco.initialize(this, config);
//      //创建默认的ImageLoader配置参数
//        ImageLoaderConfiguration configuration =   new ImageLoaderConfiguration.Builder(this).threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
//                .diskCacheFileNameGenerator(new Md5FileNameGenerator()).diskCacheSize(50 * 1024 * 1024) // 50 Mb
//                .tasksProcessingOrder(QueueProcessingType.LIFO).writeDebugLogs() // Remove for release app
//                .build();
//        //Initialize ImageLoader with configuration.
//        ImageLoader.getInstance().init(configuration);
    }
    
 

    public static DemoHandler getHandler() {
        return sHandler;
    }
    
    public static class DemoHandler extends Handler {

        private Context context;

        public DemoHandler(Context context) {
            this.context = context;
        }

        @Override
        public void handleMessage(Message msg) {
            String s = (String) msg.obj;
//            if (sMainActivity != null) {
//                sMainActivity.refreshLogInfo();
//            }
            if (!TextUtils.isEmpty(s)) {
                Toast.makeText(context, s, Toast.LENGTH_LONG).show();
            }
        }
    }
    private void initHotfix() {
        String appVersion;
        try {
            appVersion = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
        } catch (Exception e) {
            appVersion = "1.0";
        }

        SophixManager.getInstance().setContext(this)
                .setAppVersion(appVersion)
                .setAesKey(null)
                //.setAesKey("0123456789123456")
                .setEnableDebug(true)
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        String msg = new StringBuilder("").append("Mode:").append(mode)
                                .append(" Code:").append(code)
                                .append(" Info:").append(info)
                                .append(" HandlePatchVersion:").append(handlePatchVersion).toString();
                        Log.d("", msg);
                        PatchBean bean = new PatchBean();
                        bean.setCode(code);
                        bean.setHandlePatchVersion(handlePatchVersion);
                        bean.setInfo(info);
                        bean.setMode(mode);
                        Gson gson = new Gson();
                        if (msgDisplayListener != null) {
                            msgDisplayListener.handle(gson.toJson(bean));
                        } else {
                            cacheMsg.append("\n").append(gson.toJson(bean));
                        }
                    }
                }).initialize();
    }
}
