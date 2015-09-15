package com.cblue.hardware.sensor;


import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.cblue.android.R;

/**
 * 光线传感器
 * @author Administrator
 *
 */
public class SensorLightActivity extends Activity {

	
	SensorManager mSensorManager;
	Sensor mSensor;
	TextView currentValue;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sensor_show);
		currentValue = (TextView)findViewById(R.id.sensorvalue);
		mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
		//传感器
		mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
		
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	/*	List<Sensor> sensors = mSensorManager.getSensorList(Sensor.TYPE_ORIENTATION);
        if (sensors.size() > 0){ 
           Sensor sensor = sensors.get(0);
        // 第一个参数是Listener，第二个参数是所得传感器类型，第三个参数值获取传感器信息的频率
           mSensorManager.registerListener(mSensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } */
		mSensorManager.registerListener(mSensorEventListener, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		if (mSensorManager != null) {// 取消监听器
			mSensorManager.unregisterListener(mSensorEventListener);
		}
	}
	
	SensorEventListener mSensorEventListener = new SensorEventListener() {

		@Override
		public void onSensorChanged(SensorEvent event) {
			// TODO Auto-generated method stub
			//偏角度
			float light = event.values[0];
			currentValue.setText("环境光强度值="+light);
			
			
		}

		
		/**
         * Android设备可能带有多种传感器，每种传感器的精度不同
         * 当我们指定了传感器的类型，就指定了新的精度，这时候onAccuracyChanged被触发
		 */
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub
			
		}
		
	
	};
}
