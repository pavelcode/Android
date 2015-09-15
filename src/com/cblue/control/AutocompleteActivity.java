package com.cblue.control;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.cblue.android.R;

public class AutocompleteActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.control_autocompletetextview);

		final AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autocomplete_country);
		String[] CITYS = { "Afghanistan", "Albania", "Algeria",
				"American Samoa", "Andorra", "British Virgin Islands",
				"Brunei", "Bulgaria", "Burkina Faso", "Burundi" };
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, CITYS);
		autoCompleteTextView.setAdapter(adapter);

		Button btn = (Button) findViewById(R.id.autocompletebtn);
		
		btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(AutocompleteActivity.this,
						"你搜索了" +autoCompleteTextView.getText().toString(), Toast.LENGTH_SHORT)
						.show();
			}
		});

	}
}