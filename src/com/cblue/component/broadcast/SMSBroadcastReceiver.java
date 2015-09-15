package com.cblue.component.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * 系统自定义广播
 * 1 接收短信的广播
 * @author Administrator
 *
 */
public class SMSBroadcastReceiver extends BroadcastReceiver {

	private static final String TAG="SMSBroadcastReceiver";
	@Override
	public void onReceive(Context context, Intent intent) {
       Log.i(TAG, "-->onReceive");
      // intent 存放的有接收到的短信的内容
       Object[] pdus = (Object[]) intent.getExtras().get("pdus");
       for (Object pdu : pdus) {
        SmsMessage message = SmsMessage.createFromPdu((byte[]) pdu);
       // 获取短信的正文内容
        String content = message.getMessageBody();
       //获取短信的发送者
        String address = message.getOriginatingAddress();
        Log.i(TAG,"信息内容:"+content);
        Log.i(TAG,"发送者"+address);
       }
	}

}
