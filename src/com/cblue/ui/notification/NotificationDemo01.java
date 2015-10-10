package com.cblue.ui.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;

import com.cblue.android.R;

/**
 * 1.点击按钮发送一个简单通知 
 * 2.点击按钮发送一个大通知   4.1之后才能用
 * 3.通知点击后跳转到另外一个Activity 
 * 4.添加默认铃声，闪光灯，震动 没成功
 * 5.在通知中显示进度条 
 * 6.自定义通知 
 * 7.取消通知
 * 
 * @author Administrator
 * 
 */
public class NotificationDemo01 extends Activity {

	private Button notificationBtn1;
	private Button notificationBtn2;

	private NotificationManager manager;
	private NotificationCompat.Builder builder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notificatoindemo01);
		notificationBtn1 = (Button) findViewById(R.id.notificationbtn1);
		notificationBtn1.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				//获得通知的管理者
				manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
				builder = new NotificationCompat.Builder(NotificationDemo01.this);
				// TODO 这个图片不设置的话，通知出不来
				//通知的小图标  标题 内容 是必须的
				builder.setSmallIcon(R.drawable.icon);
				// 1设置一个简单通知
				builder = sendSimpleNotification(builder);
				// 2设置一个大通知
				//builder = sendMultipleNotification(builder);
				// 3添加一个跳转动作
				builder = setForwardAction(builder);
				// 4 添加默认铃声，闪光灯，震动
				//builder = setAlertAction(builder);
                // 6自定义通知
				//builder = selfDefindNotification(builder);
				//发送一个通知
				manager.notify((int) System.currentTimeMillis(),
						builder.build());
				// 5 在通知里显示一个进度条
				// builder = displayProgressInNotification();

			}
		});
		
		notificationBtn2 = (Button) findViewById(R.id.notificationbtn2);
		notificationBtn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//7 取消通知
				cancleNotification();
			}
		});

	}

	// 发送简单通知
	private NotificationCompat.Builder sendSimpleNotification(
			NotificationCompat.Builder builder) {
		builder.setTicker("通知来了");
		builder.setContentTitle("新消息");
		builder.setContentText("要放假了");
		
		return builder;
	}

	// 发送一个大通知
	private NotificationCompat.Builder sendMultipleNotification(
			NotificationCompat.Builder builder) {
		//4.1之前的使用这个，4.1之后的使用下面那个
		NotificationCompat.InboxStyle indexStyle = new NotificationCompat.InboxStyle();
		//Notification.InboxStyle indexStyle = new Notification.InboxStyle();a
		indexStyle.setBigContentTitle("大广播");
		String event[] = { "事件1", "事件2", "事件3" };
		for (int i = 0; i < event.length; i++) {
			indexStyle.addLine(event[i]);
		}
		builder.setStyle(indexStyle);
		return builder;
	}

	/**
	 * 设置一个跳转动作
	 * 当点击Notification时候，跳转的Activity
	 * @param builder
	 * @return
	 */
	private NotificationCompat.Builder setForwardAction(
			NotificationCompat.Builder builder) {
		// 设置跳转Activity的Intent
		Intent intent = new Intent(this, NotificationDemo02.class);
		// 设置跳转Activity的属性，当跳转到Activity的时候，新启动这个Activity
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		
		PendingIntent notificationIntent = PendingIntent.getActivity(this,
				1000, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		//设置通知跳转
		builder.setContentIntent(notificationIntent);
		
		//设置点击之后，通知取消，这个必须与跳转动作配合使用，如果只是设置了取消，但是没有跳转，无效
		builder.setAutoCancel(true);
		return builder;
	}
	/**
	 * 设置一个提醒动作
	 * @param builder
	 * @return
	 */
	private NotificationCompat.Builder setAlertAction(
			NotificationCompat.Builder builder) {
		//设置默认铃声和振动
		builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
		return builder;
	}

	/**
	 * 在notifiaction中显示进度条
	 * @return
	 */
	private NotificationCompat.Builder displayProgressInNotification() {
		// TODO 这个图片不设置的话，通知出不来
		builder.setSmallIcon(R.drawable.icon);
		builder.setContentTitle("下载文件");
		builder.setContentText("正在下载。。。");
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO <=100
				for (int i = 0; i <= 100; i += 5) {
					//将setProgress的第三个参数设为true即可显示为无明确进度的进度条样式,而不是没有进度条
					builder.setProgress(100, i, false);
					manager.notify(0, builder.build());

					try {
						Thread.sleep(5 * 100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				builder.setContentText("下载完成");
				builder.setProgress(0, 0, false);
		
				// id相同，使用新设置的属性的通知，代替之前的通知
				manager.notify(0, builder.build());

			}
		}).start();

		return builder;
	}
	
	
	/**
	 * 自定义通知
	 * @param builder
	 * @return
	 */
	private NotificationCompat.Builder selfDefindNotification(NotificationCompat.Builder builder){
		//得到自定义通知布局文件对象
		RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notificatoindemo03);
		//给布局文件对象属性设置值
		remoteViews.setImageViewResource(R.id.myselfnotificationIV, R.drawable.icon);
		remoteViews.setTextViewText(R.id.myselfnoticationTV1, "消息");
		remoteViews.setTextViewText(R.id.myselfnoticationTV2, "要放假了");
		//把布局对象放入到通知的内容中
	    builder.setContent(remoteViews);
	    return builder;

	}

	
	/**
	 * 取消通知
	 */
	private void cancleNotification() {
		// manager.cancel(id);
		manager.cancelAll();
	}
	
	
}
