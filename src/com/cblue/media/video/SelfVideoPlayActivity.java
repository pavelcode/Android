package com.cblue.media.video;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

/**
 * 调用自带播放器
 * @author pavel
 *
 */
public class SelfVideoPlayActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent intent  = new Intent(Intent.ACTION_VIEW);
		Uri uri = Uri.parse(Environment.getExternalStorageDirectory()+File.separator+"girl.mp4");
		intent.setDataAndType(uri,"video/mp4");
		startActivity(intent);
	}
}
