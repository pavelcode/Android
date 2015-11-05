package com.cblue.business.shopcar;

import java.util.ArrayList;
import java.util.List;

import com.cblue.android.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * 简单的实现购物车的业务
 * @author pavel
 *
 */
public class ShopCarMainActivity extends Activity implements OnClickListener {
	
	private ListView lv;
	private LinearLayout bottomView;
	private Button deleteBt, jiesuanBt;
	private TextView allpriceTv;
	
	private List<Goods> list;
	
	ShopCarAdapter adapter;
	
	public static final String MAINACTION = "JIESUAN";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shopcar_listview);
		lv = (ListView) findViewById(R.id.listView);
		
		bottomView = (LinearLayout) findViewById(R.id.bottomView);
		// 设置透明度
		bottomView.getBackground().setAlpha(30);
		
		deleteBt = (Button) findViewById(R.id.deleteBt);
		allpriceTv = (TextView) findViewById(R.id.allPrice);
		jiesuanBt = (Button) findViewById(R.id.jiesuanBt);
		deleteBt.setOnClickListener(this);
		jiesuanBt.setOnClickListener(this);
		
		getData();
		adapter = new ShopCarAdapter(list, this);
		lv.setAdapter(adapter);
		
		registerReceiver(myBroadCast, new IntentFilter(MAINACTION));
	}

	
	private void getData() {
		// TODO Auto-generated method stub
		list = new ArrayList<Goods>();
		for (int i = 0; i < 10; i++) {
			list.add(new Goods(false, i + "", "商品" + i, ((double) i * 10 / 4)
					+ 0.1 + "", 1));
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(myBroadCast);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.deleteBt:
			delete();
			break;
		case R.id.jiesuanBt:
			//pay();
			Toast.makeText(getApplicationContext(),"跳转到订单界面", 1).show();
			break;
		default:
			break;
		}
	}


	private void pay() {
		// TODO Auto-generated method stub
		String sum = "0";
		int numSum = 0;

		for (int i = 0; i < list.size(); i++) {
			Goods goods = list.get(i);
			boolean state = goods.isState();
			if (state) {
				sum = MathUtils.add(sum + "",
						MathUtils.mul(goods.getNumber() + "", goods.getPrice()));
				numSum = numSum + goods.getNumber();
			}
		}
		allpriceTv.setText("总价:" + sum + "元" + ";商品总数量:" + numSum);
		
	}

	
	private void delete() {
		// TODO Auto-generated method stub
		List<Goods> newList = new ArrayList<Goods>();
		for (int i = 0; i < list.size(); i++) {
			Goods goods = list.get(i);
			boolean state = goods.isState();
			if (state)
				newList.add(goods);
		}
		list.removeAll(newList);
		adapter.notifyDataSetChanged();
	}

	BroadcastReceiver myBroadCast = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			if (MAINACTION.equals(intent.getAction())) {
				pay();
			}
		}
	};
}
