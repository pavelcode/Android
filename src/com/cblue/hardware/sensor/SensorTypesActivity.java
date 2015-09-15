package com.cblue.hardware.sensor;


import java.util.ArrayList;
import java.util.List;

import android.R.anim;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cblue.android.R;

/**
 * 查看当前手机的传感器类型
 * 1 首先得到传感器的管理者
 * 2 根据管理者得到手机上所有的传感器
 * 3 得到传感器的名字，在ListView中显示出来
 * @author Administrator
 *
 */
public class SensorTypesActivity extends Activity{
	
	
	ListView mListView;
	SensorManager mSensorManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sensor_list);
		mListView = (ListView)findViewById(R.id.sensorlist);
		
		
		//首先得到传感器的管理者
		mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
		//根据管理者得到手机上所有的传感器
		List<Sensor> sensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
		//得到所有传感器的名字
		List<String> sensorsName = new ArrayList<String>();
		for(Sensor sensor:sensors){
			sensorsName.add(sensor.getName());
		}
		//放入适配器中显示出来
		ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, sensorsName);
		mListView.setAdapter(mAdapter);
		
		
		
	}
	
	

}
