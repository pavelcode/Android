package com.cblue.time;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.WindowManager;

import com.cblue.media.music.MediaPlayerService1;

/**
 * 声音服务启动没有成功
 * @author Administrator
 *
 */
public class AlarmBroadCast extends BroadcastReceiver {

	
	Intent mIntent;
	Context mContext;
	public static final String TAG="AlarmBroadCast";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		Log.i(TAG, "onReceive");
		mContext = context;
		
		//设定一个铃声服务
		mIntent = new Intent(context,MediaPlayerService1.class);
		mContext.startService(intent);
		
		
		AlertDialog.Builder builder = new AlertDialog.Builder(context.getApplicationContext());
		builder.setTitle("闹铃来了");
		builder.setMessage("起床？");
		builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				mContext.stopService(mIntent);
			    dialog.cancel();
			}
		});
		builder.setNegativeButton("取消",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});
   
		//设置系统级别的对话框（不能被关闭）这里需要一个权限：SYSTEM_ALERT_WINDOW
		AlertDialog alertDialog = builder.create();
		alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		alertDialog.show();
	}

}
