package com.cblue.savedata.sharedpreferences;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.cblue.android.R;

/**
 * SharedPreferences的简单应用2：实现登录(使用工具类) 简单的保存数据，并读取数据
 *  1 把文件写进去，点击按钮的时候写 
 *  2 Activity创建的时候，读取文件 
 *  3 只有在remember的时候，我们的用户和密码才能显示 
 *  4如果remember并且autolog的时候，自动跳转
 * 
 * @author Administrator
 * 
 */

public class SharedPreferencesActivity2 extends Activity {
	private Button btn1;
	private Button btn2;
	private EditText et1;
	private EditText et2;
	private CheckBox cb1;
	private CheckBox cb2;
	SharePreferencesTools mPreferencesTools;
	public static final String TAG = "SharedPreferencesActivity2";

	String username;
	String password;
	boolean saveinfo;
	boolean autologin;
	Map<String, ?> myinfoMap;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharepreference_login);
		et1 = (EditText) findViewById(R.id.sharepreference_login_et1);
		et2 = (EditText) findViewById(R.id.sharepreference_login_et2);
		cb1 = (CheckBox) findViewById(R.id.sharepreference_login_cb1);
		cb2 = (CheckBox) findViewById(R.id.sharepreference_login_cb2);
		btn1 = (Button) findViewById(R.id.sharepreference_login_btn1);
		btn2 = (Button) findViewById(R.id.sharepreference_login_btn2);
		// 首先读到数据
		mPreferencesTools = new SharePreferencesTools(this);
		myinfoMap = mPreferencesTools.readSharePreferences("userlogin");
		/*
		 * Log.i(TAG, "size="+myinfoMap.size()); for(Entry<String, ?>
		 * entry:myinfoMap.entrySet()){ Log.i(TAG,
		 * "key="+entry.getKey()+";value="+entry.getValue()); }
		 */
		if (myinfoMap.size() > 0) {
			username = myinfoMap.get("username").toString();
			password = myinfoMap.get("password").toString();
			saveinfo = (Boolean) myinfoMap.get("saveinfo");
			autologin = (Boolean) myinfoMap.get("autologin");

			// 如果自动保存信息，把信息填充的输入控件中
			if (saveinfo) {
				cb1.setChecked(saveinfo);
				et1.setText(username);
				et2.setText(password);
			}
			// 如果实现的自动登录，就直接进行跳转
			if (autologin) {
				cb2.setChecked(autologin);
				Toast.makeText(SharedPreferencesActivity2.this, "进入跳转", 1)
						.show();
			}

		}

		// 登录操作
		btn2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String name = et1.getText().toString().trim();
				String pass = et2.getText().toString().trim();
				if (name != null && pass != null) {
					// 如果验证成功
					// if(name.equals(username)&&pass.equals(password)){
					// 保存数据
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("username", et1.getText().toString().trim());
					map.put("password", et2.getText().toString().trim());
					map.put("saveinfo", cb1.isChecked());
					map.put("autologin", cb2.isChecked());
					// 保存文件
					mPreferencesTools.writeSharePreferences("userlogin", map);
					// 进行跳转
					Toast.makeText(SharedPreferencesActivity2.this, "进入跳转", 1)
							.show();
					// }
				}
			}
		});

		// 取消操作
		btn1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

	}

}