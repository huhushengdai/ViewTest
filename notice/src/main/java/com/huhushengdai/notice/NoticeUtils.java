package com.huhushengdai.notice;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.NotificationCompat;
import android.widget.RemoteViews;

import java.util.List;

/**
 * Date： 2017/4/6
 * Description:
 *
 * @author WangLizhi
 * @version 1.0
 */
public class NoticeUtils {

    //    public static void sendNotice() {
//        Context context = BaseApp.getInstance();
//        Notification.Builder builder = new Notification.Builder(context);
//        builder.setTicker("ticker");
//        builder.setContentTitle("content title");
//        builder.setSmallIcon(R.mipmap.ic_launcher);
//        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        manager.notify(1, builder.getNotification());
//    }
    public static void sendNotice() {
        Context context = BaseApp.getInstance();

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
        if (context.getPackageName().equals(list.get(0).topActivity.getPackageName())) {
            return;
        }

        String sender = "";
        String content = "";
        Bitmap head = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        String action = "";
        int id = 1;

        //为了版本兼容  选择V7包下的NotificationCompat进行构造
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.item_notice);
        builder.setContent(views);
        //Ticker是状态栏显示的提示
        builder.setTicker(content);
        //第一行内容  通常作为通知栏标题
        builder.setContentTitle(sender);
        //第二行内容 通常是通知正文
        builder.setContentText(content);
        //可以点击通知栏的删除按钮删除
        builder.setAutoCancel(true);
        //系统状态栏显示的小图标
        builder.setSmallIcon(R.mipmap.ic_launcher);
        //下拉显示的大图标
        builder.setLargeIcon(head);
        Intent intent = new Intent(context, MainActivity.class);
        intent.setAction(action);
        PendingIntent pIntent = PendingIntent.getActivity(context, 1, intent, 0);
        //点击跳转的intent
        builder.setContentIntent(pIntent);
        //通知默认的声音 震动 呼吸灯
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        Notification notification = builder.build();

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, notification);
    }
}
