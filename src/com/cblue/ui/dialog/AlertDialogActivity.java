package com.cblue.ui.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.os.Bundle;
import android.widget.Toast;

import com.cblue.android.R;

/**
 * 1、简单dialog
 * 2、多选框dialog
 * 3、单选框dialog
 * @author Administrator
 *
 */
public class AlertDialogActivity extends Activity {

	AlertDialog.Builder builder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		  //simpleAlertDialog();
		 multiChoiceAlertDialog();
		 //SingleChoiceAlertDialog();

	}

	// 简单的弹出对话框
	public void simpleAlertDialog() {
		// 创建Builder对象
		builder = new AlertDialog.Builder(AlertDialogActivity.this);
		// 设置标题
		builder.setTitle("提示");
		// 设置标题图片
		builder.setIcon(R.drawable.ic_launcher);
		// 设置提示内容
		builder.setMessage("你确定要删除吗？");
		// 添加确定(肯定)按钮	
		builder.setPositiveButton("确定", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(AlertDialogActivity.this, "点击了确定按钮",
						Toast.LENGTH_LONG).show();
			}
		});
		// 添加取消(否定)按钮
		builder.setNegativeButton("取消", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(AlertDialogActivity.this, "点击了取消按钮",
						Toast.LENGTH_LONG).show();
			}
		});
		builder.setNeutralButton("忽略", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(AlertDialogActivity.this, "点击了忽略按钮",
						Toast.LENGTH_LONG).show();
			}
		});
		// 显示对话框
		builder.show();
	}

	// 多选对话框
	public void multiChoiceAlertDialog() {
		builder = new AlertDialog.Builder(AlertDialogActivity.this);

		builder.setTitle("爱好");
		// TODO 必须定义为final
		final String[] hibby = { "吃饭", "睡觉", "打豆豆" };
		final boolean[] checkedItems = { true, false, true };
		builder.setMultiChoiceItems(hibby, checkedItems,
				new OnMultiChoiceClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							Toast.makeText(AlertDialogActivity.this,
									"被选中:" + hibby[which], Toast.LENGTH_LONG)
									.show();
						}
					}
				});
		builder.setPositiveButton("确定", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(AlertDialogActivity.this, "点击取消按钮", 1).show();
			}
		});
		builder.setNegativeButton("取消", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(AlertDialogActivity.this, "点击取消按钮", 1).show();
			}
		});

		builder.show();
	}

	// 单选对话框
	public void SingleChoiceAlertDialog() {
		builder = new AlertDialog.Builder(AlertDialogActivity.this);

		builder.setTitle("性别");
		final String sex[] = { "男", "女" };
		builder.setSingleChoiceItems(sex, 1, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(AlertDialogActivity.this, "被选中" + sex[which],
						Toast.LENGTH_LONG).show();
			}
		});
		builder.setPositiveButton("确定", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(AlertDialogActivity.this, "点击确定按钮", 1).show();
			}
		});
		builder.setNegativeButton("取消", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(AlertDialogActivity.this, "点击取消按钮", 1).show();
			}
		});

		builder.show();

	}
}
