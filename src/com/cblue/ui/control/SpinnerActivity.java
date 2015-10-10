package com.cblue.ui.control;

import com.cblue.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class SpinnerActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spinner);
		
		Spinner spinner = (Spinner) findViewById(R.id.spinner);
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.planets_array, android.R.layout.simple_spinner_item);
		//设置下拉菜单的显示方式
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
	}
}

class MyOnItemSelectedListener implements OnItemSelectedListener
{

	
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
	{
		//TODO 得到对象的方法要注意
		Toast.makeText(parent.getContext(), "The planet is " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
	}

	
	public void onNothingSelected(AdapterView<?> parent)
	{

	}

}