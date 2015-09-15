package com.cblue.popwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.cblue.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.Toast;

/**
 * Activity中有一个按钮，点击之后，弹出PopWindow,PopWindow是一个ListView。
 * @author Administrator
 *
 */
public class PopWindowListActivity extends Activity {

	private class ViewHolder {
		int position;
		RadioButton radioButton;
	}

	private Button button;
	private PopupWindow popupWindow;
	private ListView listView;
	private ViewHolder viewHolder = null;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popwindow_list_activity);
		listView = new ListView(this);

		SimpleAdapter adapter = new SimpleAdapter(this, getData(),
				R.layout.popwindow_list_item, new String[] { "listview_imageview",
						"listview_textview" }, new int[] {
						R.id.listview_imageview, R.id.listview_textview });
		listView.setAdapter(adapter);
		listView.setFocusable(true);
		listView.setItemsCanFocus(true);
		
		
		button = (Button) findViewById(R.id.popupwindow);
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//不可见
				button.setVisibility(Button.INVISIBLE);
				
				popupWindow = new PopupWindow(listView, 350,
						LayoutParams.WRAP_CONTENT, true);
				//popupWindow.setBackgroundDrawable(new BitmapDrawable());
				popupWindow.setOnDismissListener(new OnDismissListener() {
					@Override
					public void onDismiss() {
						button.setVisibility(Button.VISIBLE);
					}
				});
				popupWindow.setFocusable(true);
				popupWindow.setTouchable(true);
				popupWindow.setOutsideTouchable(true);

				listView.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub
						ViewGroup vp = (ViewGroup) view;
						RadioButton radioButton = (RadioButton) vp
								.getChildAt(2);
						if (viewHolder == null) {
							viewHolder = new ViewHolder();
							viewHolder.position = position;
							viewHolder.radioButton = radioButton;
							viewHolder.radioButton.setChecked(true);
						} else if (viewHolder.position != position
								&& viewHolder.radioButton != null) {
							viewHolder.radioButton.setChecked(false);
							viewHolder.position = position;
							viewHolder.radioButton = radioButton;
							radioButton.setChecked(true);
						}
						List<Map<String,Object>> data = getData();
						Map<String,Object> map = data.get(viewHolder.position);
						String value = (String)map.get("listview_textview");
						Toast.makeText(PopWindowListActivity.this,value, 1).show();

						popupWindow.dismiss();
						button.setVisibility(Button.VISIBLE);

					}
				});

				popupWindow.showAsDropDown(button, 0, -button.getHeight());
				popupWindow.update();
			}
		});
	}

	private List<Map<String, Object>> getData() {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("listview_imageview", R.drawable.a);
		map.put("listview_textview", "选项1");
		// map.put("listview_radiobutton", R.id.listview_radiobutton);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("listview_imageview", R.drawable.c);
		map.put("listview_textview", "选项2");
		// map.put("listview_radiobutton", R.id.listview_radiobutton);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("listview_imageview", R.drawable.d);
		map.put("listview_textview", "选项3");
		// map.put("listview_radiobutton", R.id.listview_radiobutton);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("listview_imageview", R.drawable.e);
		map.put("listview_textview", "选项4");
		// map.put("listview_radiobutton", R.id.listview_radiobutton);
		list.add(map);

		return list;
	}

}