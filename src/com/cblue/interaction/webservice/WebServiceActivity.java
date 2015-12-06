package com.cblue.interaction.webservice;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.cblue.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 
 * 访问webservice得到手机号码归属地 使用handler把得到的线程得到数据，更新UI
 * 
 * @author Administrator
 * 
 *  webService步骤：
 *   1.指定WebService的命名空间和调用的方法名 
 *   2.设置调用方法的参数值
 *   3.生成调用WebService方法的SOAP请求信息：请求的SOAP版本，SOAP对象
 *   4.创建HttpTransportSE对象。可以指定WebService的WSDL文档的URL
 *   5.使用call方法调用WebService方法
 *   6.使用getResponse方法获得WebService方法的返回结果
 * 
 */
public class WebServiceActivity extends Activity {

	private EditText phoneSecEditText;
	private TextView resultView;
	private Button queryButton;
	String phoneSec;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webservice);
		phoneSecEditText = (EditText) findViewById(R.id.phone_sec);
		resultView = (TextView) findViewById(R.id.result_text);
		queryButton = (Button) findViewById(R.id.query_btn);
		queryButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 手机号码（段）
				phoneSec = phoneSecEditText.getText().toString().trim();
				// 简单判断用户输入的手机号码（段）是否合法
				if ("".equals(phoneSec) || phoneSec.length() < 7) {
					// 给出错误提示
					phoneSecEditText.setError("您输入的手机号码（段）有误！");
					phoneSecEditText.requestFocus();
					// 将显示查询结果的TextView清空
					resultView.setText("");
					return;
				}
				// 查询手机号码（段）信息
				new Thread(runnable).start();

			}
		});
	}

	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {
			String result = (String) msg.obj;
			// 将WebService返回的结果显示在TextView中
			resultView.setText(result);
		}
	};

	Runnable runnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			getRemoteInfo(phoneSec);
		}
	};

	public void getRemoteInfo(String phoneSec) {
		// 命名空间
		String nameSpace = "http://WebXml.com.cn/";
		// 调用的方法名称
		String methodName = "getMobileCodeInfo";
		// WSDL文档的URL
		String WSDLUrl = "http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx";
		// SOAP Action 命名空间+调用的方法名称
		String soapAction = "http://WebXml.com.cn/getMobileCodeInfo";
		
		/**
		 * 设置SOAP的属性
		 */
		// 设定SoapObject的命名空间和调用的方法名
		SoapObject soapObject = new SoapObject(nameSpace, methodName);
		// 设定SoapObject需要传入的两个参数mobileCode、userId
		Log.i("aaa", "mobileCode=" + phoneSec);
		soapObject.addProperty("mobileCode", phoneSec);
		soapObject.addProperty("userID", "");

		// 设定SOAP的版本
		// Envelope封皮，包装
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		// 等价于 envelope.setOutputSoapObject(soapObject);
		envelope.bodyOut = soapObject;
		// 设定是否调用的是dotNet开发的WebServiceH
		envelope.dotNet = true;

		// 设定WebService的WSDL文档的URL
		HttpTransportSE transport = new HttpTransportSE(WSDLUrl);
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
			// 获取返回的数据
			SoapObject object = (SoapObject) envelope.bodyIn;

			// Log.i("aaa", "-----"+object);
			// 获取返回的结果
			//SoapObject resultSoapObject = (SoapObject) envelope.getResponse();  
			//object.getProperty("qqCheckOnlinenResult");
			String result = object.getProperty(0).toString();
			Log.i("aaa", "-----" + result);
			Message message = Message.obtain();
			message.obj = result;
			myHandler.sendMessage(message);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
