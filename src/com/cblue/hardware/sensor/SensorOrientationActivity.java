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
import android.util.Log;
import android.widget.TextView;

import com.cblue.android.R;

/**
 * 方向传感器：不好解释给学生
 * @author Administrator
 *
 */
public class SensorOrientationActivity extends Activity {

	
	SensorManager mSensorManager;
	Sensor mSensor;
	TextView currentmazimuth;
	private static final String TAG="SensorOrientationActivity";
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sensor_show);
		currentmazimuth = (TextView)findViewById(R.id.sensorvalue);
		mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
		//这种方式过时了，但是用新的方式会说到矩阵
		mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		
		
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
			float azimuth = event.values[0];
			//0表示正北，90表示正东，180表示正南，270表示正西
			Log.i(TAG, "azimuth="+azimuth);
			//先打印出来看看
			currentmazimuth.setText("偏转度="+azimuth);
			if(azimuth>=0&&azimuth<45){
				currentmazimuth.setText("北偏东"+azimuth+"度");
			}else if(azimuth>=45&&azimuth<90){
				currentmazimuth.setText("东偏北"+(azimuth-45)+"度");
			}else if(azimuth>=90&&azimuth<135){
				currentmazimuth.setText("东偏南"+(azimuth-90)+"度");
			}else if(azimuth>=135&&azimuth<180){
				currentmazimuth.setText("南偏东"+(azimuth-135)+"度");
			}else if(azimuth>=180&&azimuth<225){
				currentmazimuth.setText("南偏西"+(azimuth-180)+"度");
			}else if(azimuth>=225&&azimuth<265){
				currentmazimuth.setText("西偏南"+(azimuth-225)+"度");
			}else if(azimuth>=265&&azimuth<315){
				currentmazimuth.setText("西偏北"+(azimuth-275)+"度");
			}else if(azimuth>=315&&azimuth<359){
				currentmazimuth.setText("北偏西"+(azimuth-315)+"度");
			}
			
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
