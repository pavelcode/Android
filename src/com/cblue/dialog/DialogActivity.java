package com.cblue.dialog;

import com.cblue.android.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class DialogActivity extends Activity
{
	private static final String TAG = "DialogActivity";
	private static final int MAX_PROGRESS = 100;

	private final static int SIMPLEALERTDIALOG = 1;
	private final static int LISTALERTDIALOG = 2;
	private final static int SINGLECHOICEALERTDIALOG = 3;
	private final static int MULTICHOICEALTERTDIALOG = 4;
	private final static int PROGRESSTDIALOG = 5;
	private final static int CUSTOMALERTDIALOG = 6;

	private ProgressDialog progressDialog;
	private Handler progressHandler;
	private int progress;
	private AlertDialog alertDialog;

	private Button simpleAlertDialog;
	private Button listAlertDialog;
	private Button singleChoiceAlertDialog;
	private Button mutliChoiceAlertDialog;
	private Button progressAlertDialog;
	private Button customAlertDialog;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog);
		// 简单警告对话框
		simpleAlertDialog = (Button) findViewById(R.id.simpleAlertDialog);
		simpleAlertDialog.setOnClickListener(new OnClickListener()
		{

			public void onClick(View v)
			{
				showDialog(SIMPLEALERTDIALOG);
			}
		});

		// 列表对话框
		listAlertDialog = (Button) findViewById(R.id.listAlertDialog);
		listAlertDialog.setOnClickListener(new OnClickListener()
		{

			public void onClick(View v)
			{

				showDialog(LISTALERTDIALOG);
			}
		});

		// 单选对话框
		singleChoiceAlertDialog = (Button) findViewById(R.id.singleChoiceAlertDialog);
		singleChoiceAlertDialog.setOnClickListener(new OnClickListener()
		{

			public void onClick(View v)
			{
				showDialog(SINGLECHOICEALERTDIALOG);
			}
		});
		// 多选对话框
		mutliChoiceAlertDialog = (Button) findViewById(R.id.multiChoiceAlertDialog);
		mutliChoiceAlertDialog.setOnClickListener(new OnClickListener()
		{

			public void onClick(View v)
			{
                showDialog(MULTICHOICEALTERTDIALOG);
			}
		});
		// 进度条
		progressAlertDialog = (Button) findViewById(R.id.progressAlertDialog);
		progressAlertDialog.setOnClickListener(new OnClickListener()
		{

			public void onClick(View v)
			{
				showDialog(PROGRESSTDIALOG);
				progress = 0;
				progressDialog.setProgress(0);
				progressHandler.sendEmptyMessage(0);
			}
		});
		// 进度条Handler
		progressHandler = new Handler()
		{
			public void handleMessage(Message msg)
			{
				super.handleMessage(msg);
				if (progress >= MAX_PROGRESS)
				{
					progressDialog.dismiss();
				} else
				{
					progress++;
					progressDialog.setProgress(progress);
					progressHandler.sendEmptyMessageDelayed(0, 100);
				}
			}
		};
		// 自定义对话框
		customAlertDialog = (Button) findViewById(R.id.customAlertDialog);
		customAlertDialog.setOnClickListener(new OnClickListener()
		{

			public void onClick(View v)
			{
				showDialog(CUSTOMALERTDIALOG);
			}
		});

	}

	@Override
	protected Dialog onCreateDialog(int id)
	{
		Log.i(TAG, "@@@@@@@@@@@@@");
		final CharSequence[] items =
		{ "红色", "黄色", "蓝色" };
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		switch (id)
		{
		case SIMPLEALERTDIALOG:
			builder.setMessage("你确定要退出本软件吗?");
			builder.setCancelable(false);// 返回键是否可以关闭对话框
			builder.setPositiveButton("是", new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int id)
				{
					DialogActivity.this.finish();
				}
			});
			builder.setNegativeButton("否", new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int id)
				{
					dialog.cancel();
				}
			});
			return builder.create();
		case LISTALERTDIALOG:
			builder.setTitle("请选中一种颜色");
			builder.setItems(items, new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int item)
				{
					Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
				}
			});
			return builder.create();
		case SINGLECHOICEALERTDIALOG:
			builder.setTitle("请选中一种颜色");
			builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int item)
				{
					Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
					if (null != alertDialog)
					{
						alertDialog.dismiss();
					}
				}
			});
			alertDialog = builder.create();
			return alertDialog;
		case MULTICHOICEALTERTDIALOG:
			final boolean [] itemsBoolean = {false,true,false,true,false};
			final String [] itemsString = {"植物大战僵尸","愤怒的小鸟","泡泡龙","开心农场","超级玛丽"};
			builder.setTitle("你最新喜欢的游戏是什么？");
			builder.setMultiChoiceItems(itemsString,itemsBoolean,new OnMultiChoiceClickListener(){
				public void onClick(DialogInterface dialog, int which, boolean isChecked)
				{
					itemsBoolean[which]=isChecked;
				}
			});
			builder.setPositiveButton("确定",new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int which)
				{
                   //把boolean转化为string保存下来
					String result= "";
					for (int i=0;i<itemsBoolean.length;i++)
					{
						if(itemsBoolean[i])
						{
						   result += itemsString[i]+",";	
						}
					}
					if(!"".equals(result))
					{
						result = result.substring(0,result.length()-1);
						Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
					}
				}
			});
			return builder.create();
		case PROGRESSTDIALOG:
			progressDialog = new ProgressDialog(this);
			// progressDialog.setIconAttribute(android.R.attr.alertDialogIcon);
			progressDialog.setTitle("进度条");
			progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			progressDialog.setMax(MAX_PROGRESS);
			progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int whichButton)
				{

				}
			});
			progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int whichButton)
				{

				}
			});
			return progressDialog;
		case CUSTOMALERTDIALOG:
			LayoutInflater factory = LayoutInflater.from(this);
			final View textView = factory.inflate(R.layout.dialog_custom, null);
			builder.setIcon(R.drawable.qq);
			builder.setTitle("自定义对话框");
			builder.setView(textView);
			builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int whichButton)
				{

				}
			});
			builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int whichButton)
				{

				}
			});
			return builder.create();
		default:
			break;
		}
		return null;
	}
}