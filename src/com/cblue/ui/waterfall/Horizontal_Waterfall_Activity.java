package com.cblue.ui.waterfall;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cblue.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * 横向瀑布流
 * 自定义ListView
 * @author Administrator
 *
 */
public class Horizontal_Waterfall_Activity extends Activity {
	private Horizontal_Waterfall_ListView mHorizontal_Waterfall_ListView;
	private List<String[]> mListChild = new ArrayList();

	public static final String TAG = Horizontal_Waterfall_Activity.class
			.getSimpleName();

	// 得到asserts文件夹中的所有图片地址
	private String[] imagePaths;
    //准备随机读取读片
	private Random random;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.horizontal_waterfall_activity);
		random = new Random();
		try {
			imagePaths = getResources().getAssets().list("images");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
        //准备显示数据
		String item[];
		mListChild = new ArrayList();
		//有5屏
		for (int i = 0; i < 5; i++) {
			//每屏显示5张图片
			item = new String[5];
			for (int j = 0; j < item.length; j++) {
				item[j] = "images/"
						+ imagePaths[random.nextInt(imagePaths.length)];
			}
			mListChild.add(item);
			//每屏显示4张图片
			item = new String[4];
			for (int j = 0; j < item.length; j++) {
				item[j] = "images/"
						+ imagePaths[random.nextInt(imagePaths.length)];
			}
			mListChild.add(item);

		}
		//自定义一个ListView,给他设置一个适配器
		mHorizontal_Waterfall_ListView = (Horizontal_Waterfall_ListView) findViewById(R.id.listview_child);
		mHorizontal_Waterfall_ListView.setAdapter(new Horizontal_Waterfall_Adapter(this,
				mListChild, mHorizontal_Waterfall_ListView));
	}
}