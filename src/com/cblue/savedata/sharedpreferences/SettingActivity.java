package com.cblue.savedata.sharedpreferences;

import android.content.Intent;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.util.Log;

import com.cblue.android.R;

/**
 * 
 * 1.创建设置的配置文件,在 xml目录下新建文件， PreferenceScreen PreferenceCategory CheckBoxPreference,
 * EditTextPreference, ListPreference， RingtonePreference  Preference
 * 2.创建设置的类，继承PreferenceActivity,onCreate()加载配置文件
 * 3.得到我们配置文件中控件的java对象，给它添加事件OnPreferenceClickListener，OnPreferenceChangeListener
 * 4.
 * OnPreferenceChangeListener 简单的修改监听器
 * OnPreferenceClickListener 简单的修改监听器 ，当这个方法返回false的时候，会调用onPreferenceTreeClick(),
 * 否则，在该方法调用之后，不会调用onPreferenceTreeClick
 * onPreferenceTreeClick  处理子设置界面的时候，使用这个方法。
 * 
 */
public class SettingActivity extends PreferenceActivity implements
		OnPreferenceChangeListener, OnPreferenceClickListener {

	public static final String TAG = "SettingActivity";
	PreferenceManager mPreferenceManager;
	CheckBoxPreference mCheckBoxPreference;
	EditTextPreference mEditTextPreference;
	ListPreference mListPreference;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 加载布局文件
		addPreferencesFromResource(R.xml.sharepreference_setting);
		mPreferenceManager = getPreferenceManager();
		// 找到控件对象
		/*mCheckBoxPreference = (CheckBoxPreference) findPreference("setuserinfo");
		mEditTextPreference = (EditTextPreference) findPreference("username");*/
		mCheckBoxPreference = (CheckBoxPreference) mPreferenceManager.findPreference("setuserinfo");
		mEditTextPreference = (EditTextPreference) mPreferenceManager.findPreference("username");
		mListPreference = (ListPreference)mPreferenceManager.findPreference("favorite");
	
		// ClickListener
		mCheckBoxPreference.setOnPreferenceClickListener(this);
		// ChangeListener
		mCheckBoxPreference.setOnPreferenceChangeListener(this);
		mEditTextPreference.setEnabled(mCheckBoxPreference.isChecked());

	}

	/**
	 * 
	 * onPreferenceChange的方法独立与其他两种方法的运行。也就是说，它总是会运行
	 * 当Preference的值发生改变时触发该事件，true则以新值更新控件的状态，false则do noting,也就是说方法没有处理
	 */
	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		// TODO Auto-generated method stub
		Log.i(TAG, "---onPreferenceChange");
		Log.i(TAG, "onPreferenceChange=" + preference.getKey());
		Log.i(TAG, "onPreferenceChange=" + newValue.toString());
		Log.i(TAG, "onPreferenceChange="+mCheckBoxPreference.isChecked());
		Log.i(TAG, "onPreferenceChange=" + mEditTextPreference.getText());
		Log.i(TAG,"onListPreference-----entry="+mListPreference.getEntry()+"---value="+mListPreference.getValue());
		return true;
	}

	/**
	 * 先调用onPreferenceClick()方法，如果该方法返回false，则不再调用onPreferenceTreeClick方法
	 * 如果onPreferenceClick方法返回true，则继续调用onPreferenceTreeClick方法
	 */
	@Override
	public boolean onPreferenceClick(Preference preference) {
		// TODO Auto-generated method stub
		Log.i(TAG, "---onPreferenceClick");
		if (preference == mCheckBoxPreference) {
			Log.i(TAG, "onPreferenceClick() setuserinfo is checked="
					+ mCheckBoxPreference.isChecked());
			//让checkbox是否可用，影响editText是否可用
			mEditTextPreference.setEnabled(mCheckBoxPreference.isChecked());
		}
		if (preference == mEditTextPreference) {
			Log.i(TAG,
					"onPreferenceClick()username="
							+ mEditTextPreference.getText());
		}
		return true;
	}

	/**
	 * 这个方法过时 当Preference控件被点击时，触发该方法 返回值： true
	 * 代表点击事件已成功捕捉，无须执行默认动作或者返回上层调用链。例如，不跳转至默认Intent。 false
	 * 代表执行默认动作并且返回上层调用链。例如，跳转至默认Intent
	 */
	@Override
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
			Preference preference) {
		// TODO Auto-generated method stub
		   Log.i(TAG, "---onPreferenceTreeClick");
		if("button_voicemail_category_key".equals(preferenceScreen.getKey())){
		   if("voicemail_key1".equals(preference.getKey())){
			   intent = new Intent(SettingActivity.this,IntroduceActivity.class);
			   startActivity(intent);
		   }
		   if("voicemail_key2".equals(preference.getKey())){
			   intent = new Intent(SettingActivity.this,IntroduceActivity.class);
			   startActivity(intent);
		   }
		}
		
		//return super.onPreferenceTreeClick(preferenceScreen, preference);
		return true;
	}

}
