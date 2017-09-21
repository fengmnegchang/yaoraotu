package com.open.yaoraotu.service;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.open.yaoraotu.bean.MasonryBean;
import com.open.yaoraotu.jsoup.YaoRaoTuJsoupService;
import com.open.yaoraotu.utils.UrlUtils;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fengguangjing
 * @createTime: 17/9/21
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class HotImagePushService extends Service {
    public static final String TAG = "HotImagePushService";
    public static final String ACTION = "com.open.yaoraotu.service.HotImagePushService";
    private Notification mNotification;
    private NotificationManager mManager;
    private ExecutorService executorService = Executors.newFixedThreadPool(5);

    private Handler mHandler = new Handler(){
        /* (non-Javadoc)
         * @see android.os.Handler#handleMessage(android.os.Message)
         */
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            if(msg.what==1000){
//				MArticleBean bean = (MArticleBean) msg.obj;
//				dialog(bean.getAlt(),bean.getHref());
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        initNotifiManager();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    /*
     * (non-Javadoc)
     *
     * @see android.app.Service#onStart(android.content.Intent, int)
     */
    @Override
    @Deprecated
    public void onStart(Intent intent, int startId) {
        // TODO Auto-generated method stub
        super.onStart(intent, startId);
        executorService.submit(new PollingRunnable());
//		new PollingThread().start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // 初始化通知栏配置
    private void initNotifiManager() {
        mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//		int icon = R.drawable.ic_launcher;
//		mNotification = new Notification();
//		mNotification.icon = icon;
//		mNotification.tickerText = "New Message";
//		mNotification.defaults |= Notification.DEFAULT_SOUND;
//		mNotification.flags = Notification.FLAG_AUTO_CANCEL;


    }

    // 弹出Notification
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void showNotification(String msg, String url) {
//		mNotification.when = System.currentTimeMillis();
        // Navigator to the new activity when click the notification title
        Intent i = new Intent();
        i.setClassName(HotImagePushService.this, "com.open.yaoraotu.activity.mvvm.MasonryImagePullListMvvmActivity");
        i.setAction(System.currentTimeMillis()+"");
        i.putExtra("URL", url);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i,PendingIntent.FLAG_UPDATE_CURRENT );
//		mNotification.setLatestEventInfo(this, getResources().getString(R.string.app_name)+" 养眼美女", msg, pendingIntent);
//		mNotification.tickerText = getResources().getString(R.string.app_name)+" 养眼美女"+msg;
//		mNotification.contentIntent = pendingIntent;
//		mManager.notify(count, mNotification);

        int icon = R.drawable.icon;
        Notification.Builder builder1 = new Notification.Builder(this);
        builder1.setSmallIcon(icon); //设置图标
        builder1.setTicker("New Message");
        builder1.setContentTitle("妖娆图 养眼美女"); //设置标题
        builder1.setContentText(msg); //消息内容
        builder1.setWhen(System.currentTimeMillis()); //发送时间
        builder1.setDefaults(Notification.DEFAULT_ALL); //设置默认的提示音，振动方式，灯光
        builder1.setAutoCancel(true);//打开程序后图标消失

//		Intent intent = new Intent (MainActivity.this,Center.class);
//		PendingIntent pendingIntent =PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
        builder1.setContentIntent(pendingIntent);
        Notification notification1 = builder1.build();
        mManager.notify(count, notification1); // 通过通知管理器发送通知
    }

    /**
     * Polling thread 模拟向Server轮询的异步线程
     *
     * @Author Ryan
     * @Create 2013-7-13 上午10:18:34
     */
    int count = 0;
//	class PollingThread extends Thread {
//		@Override
//		public void run() {
//			countData();
//		}
//	}

    class PollingRunnable implements Runnable{
        /* (non-Javadoc)
         * @see java.lang.Runnable#run()
         */
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void run() {
            countData();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void countData(){
        System.out.println(TAG+"... count==="+count);
        count++;
        // 当计数能被2整除时弹出通知
        if (count % 2 == 0) {
            List<MasonryBean> list = YaoRaoTuJsoupService.getPliList(UrlUtils.YAORAOTU_TAOTU_TUIGIRL,0);
            if(list!=null && list.size()>0){
                java.util.Random random=new java.util.Random();// 定义随机类
                int size=random.nextInt(list.size());// 返回[0,10)集合中的整数，注意不包括10
                MasonryBean bean = list.get(size);
                showNotification(bean.getAlt(),bean.getHref());

                Message msg = mHandler.obtainMessage();
                msg.what=1000;
                msg.obj = bean;
                mHandler.sendMessage(msg);

                Gson gson = new Gson();
                System.out.println(gson.toJson(bean));
            }
        }
    }

    public void dialog(String alt,final String href){
        Builder builder = new Builder(this);
        builder.setMessage(alt);
        builder.setNegativeButton("取消", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // to do
            }
        });
        builder.setPositiveButton("确定", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // to do
                Intent intent = new Intent();
                intent.setClassName(HotImagePushService.this, "com.open.yaoraotu.activity.mvvm.MasonryImagePullListMvvmActivity");
                intent.putExtra("URL", href);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        final AlertDialog dialog = builder.create();
        //在dialog show前添加此代码，表示该dialog属于系统dialog。
        dialog.getWindow().setType((WindowManager.LayoutParams.TYPE_SYSTEM_ALERT));
        dialog.show();
    }

}