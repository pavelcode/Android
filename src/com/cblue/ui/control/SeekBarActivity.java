package com.cblue.ui.control;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.cblue.android.R;

public class SeekBarActivity extends Activity
{

	private SeekBar seekBar;
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.control_seekbar);
		seekBar = (SeekBar) findViewById(R.id.seekbarid);
		textView = (TextView) findViewById(R.id.description);
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
		{
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
			{
				textView.setText("当前进度：" + progress);
			}

			public void onStartTrackingTouch(SeekBar seekBar)
			{
				textView.setText("开始拖动");
			}

			public void onStopTrackingTouch(SeekBar seekBar)
			{
				textView.setText("停止拖动");
			}

		});

	}

}
