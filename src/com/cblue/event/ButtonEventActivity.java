package com.cblue.event;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

public class ButtonEventActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    }
    
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		//禁用back按键
 	   if(keyCode==KeyEvent.KEYCODE_BACK)
	   {
 		   Toast.makeText(ButtonEventActivity.this,"回退按钮被禁用",Toast.LENGTH_LONG).show();
		   return true;
	   }
 	   //音量键增加
 	   if(keyCode==KeyEvent.KEYCODE_VOLUME_UP)
 	   {
 		   Toast.makeText(ButtonEventActivity.this,"音量增加", Toast.LENGTH_LONG).show();
 		   //注意返回false
 		   return false;
 	   }
		return super.onKeyDown(keyCode, event);
	}
	
}
