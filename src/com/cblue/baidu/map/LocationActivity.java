package com.cblue.baidu.map;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import com.cblue.android.R;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class LocationActivity extends Activity {

	public static final String TAG = LocationActivity.class.getSimpleName();
	ListView listView ;
	LocationManager lManager;
	Location myLocation;
	String bestProvider;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_location);
		listView = (ListView)findViewById(R.id.locationListview);
		lManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		//getAllProvider();
		//getLocationByCondition();
		addListener(LocationManager.GPS_PROVIDER);
	}
	
	
	/**
	 * 得到全部提供者
	 */
	private void getAllProvider(){
		
		List<String> allProviders = lManager.getAllProviders();
		for(String provider:allProviders){
			Log.i(TAG, "provider="+provider);
		}
	}
	
	/**
	 * 根据条件得到最好的定位对象,得到经纬度
	 */
	private void getLocationByCondition(){
		//标准，条件
		Criteria criteria = new Criteria();
		//是否收费
		criteria.setCostAllowed(true);
		//提供海拔信息
		//criteria.setAltitudeRequired(true);
		//提供方向信息  关系；方位；举止
		//criteria.setBearingRequired(true);
		//设置粗略精确度  Criteria.ACCURACY_COARSE 低精度 Criteria.ACCURACY_FINE 高精度
		//criteria.setAccuracy(Criteria.ACCURACY_COARSE);
		//是否返回速度信息
		 criteria.setSpeedRequired(false);
		//低能耗
		 criteria.setPowerRequirement(criteria.POWER_LOW);
		//后面的那个参数，当为true的时候，对于启动的定位方式中查找合适的，当为false的时候，所有的定位方式中查找合适的，不管可不可用
		bestProvider = lManager.getBestProvider(criteria, false);
		Log.i(TAG, "bestProvider="+bestProvider);
		
		
		/*currentLocation= lManager.getLastKnownLocation(bestProvider);
		//得到经纬度
		double latitude = currentLocation.getLatitude();
		double longitude = currentLocation.getLongitude();
		Log.i(TAG, "latitude="+latitude);*/
	}
	
	
	/**
	 * 添加一个定位监听器
	 * 单独写一遍
	 */
	private void addListener(String mProvider){
		
			//第二个参数执行的间隔时间，第三个参数 执行的间隔距离
			lManager.requestLocationUpdates(mProvider, 1, 1, new LocationListener() {
				
				//变化不同的提供者的时候调用
				@Override
				public void onStatusChanged(String provider, int status, Bundle extras) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onProviderEnabled(String provider) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onProviderDisabled(String provider) {
					// TODO Auto-generated method stub
					
				}
				//当定位发生了变化
				@Override
				public void onLocationChanged(Location location) {
					// TODO Auto-generated method stub
					//经度
					Log.i(TAG,"当前的经度："+location.getLatitude());
					Log.i(TAG, "当前的纬度："+location.getLongitude());
					GeoCoderTOAddress(location);
				}
			});
		
	}
	
	/**
	 * 使用异步操作
	 * 经纬度转化为地址
	 * http://104zz.iteye.com/blog/1681845
	 * 把当前的经纬度转化成地址
	 */
	
	private void GeoCoderTOAddress(Location currentLocation) {

		
			try {
				Geocoder geocoder = new Geocoder(LocationActivity.this,Locale.CHINA);
				Log.i(TAG, "当前的经纬度："+currentLocation.getLatitude()+"---"+currentLocation.getLongitude());
				//根据经纬度得到地址
				List<Address> addresses = geocoder.getFromLocation(39.915,116.404, 1);
				for(int i=0;i<addresses.size();i++){
			    	Address address = addresses.get(i);
			    	Log.i(TAG, address.getCountryName()+"-"+address.getAdminArea()+"-"+address.getFeatureName());
			    }
				//根据地址得到经纬度
				List<Address> addresses2 = geocoder.getFromLocationName("百度商务大厦",1);
				for(int i=0;i<addresses2.size();i++){
					  Address address2 = addresses2.get(i);
					  Log.i(TAG, address2.getLatitude()+"--"+address2.getLongitude());
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		
	}	
		
		
	/***
	 * 有问题
	 * 使用GPS定位
	 */
	private void GPSLocation(){
		String currentProvider = lManager.getProvider(LocationManager.GPS_PROVIDER).getName();
		//最后一次的位置信息 TODO这里出现空指针
		//Location currentLoaction = lManager.getLastKnownLocation(currentProvider);
		//显示当前位置
		//fixAddress(currentLoaction);
		//添加GPS状态监听
		lManager.addGpsStatusListener(new GpsStatus.Listener() {
			
			@Override
			public void onGpsStatusChanged(int event) {
				// TODO Auto-generated method stub
				GpsStatus gpsStatus = lManager.getGpsStatus(null);
				switch (event) {
				//第一次定位的事件
				case GpsStatus.GPS_EVENT_FIRST_FIX:
					break;
				//开始定位的事件
				case GpsStatus.GPS_EVENT_STARTED:
					break;
				//发送GPS卫星状态事件
				case GpsStatus.GPS_EVENT_SATELLITE_STATUS:
					Log.i("GpsStatus", "GPS_EVENT_SATELLITE_STATUS");
					Iterable<GpsSatellite> gpsSatellites = gpsStatus.getSatellites();
					Iterator<GpsSatellite> it = gpsSatellites.iterator();
					int count =0;
					while(it.hasNext()){
						count++;
					}
					Toast.makeText(LocationActivity.this, "共有"+count+"颗卫星", 1).show();
					break;

				}
				
			}
		});
		
	}

	

}
