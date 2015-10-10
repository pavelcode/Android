package com.cblue.ui.waterfall;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cblue.android.R;
import com.cblue.ui.waterfall.Vertical02_LazyScrollView.OnScrollListener;


/**
 * 实现垂直瀑布流的第二种方式
 * 与第一种方式比较，代码比较简洁，但是分类不够清晰
 * @author Administrator
 *
 */
public class Vertical02_WaterFall_Activity extends Activity {

	Vertical02_LazyScrollView waterfall_scroll;
	LinearLayout mLinearLayout;
	//用于存放线性布局
	List<LinearLayout> itemlist=new ArrayList<LinearLayout>();
	Display display;
	//单项的宽度
	int itemWidth;
	//列数
	int column_count = 3;
	//用户一页显示的项数
	int page_count = 15;
	//第一次运行的页数初始参数
	int current_page = 0;
	//assets文件
	List<String> image_filenames=new ArrayList<String>();
	AssetManager assetManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vertical02_waterfall);
        //获得屏幕
        display = this.getWindowManager().getDefaultDisplay();
        //获得屏幕宽度
        itemWidth = display.getWidth() / 3;
        //获得assets文件的管理器
        assetManager=getAssets();
        init();
    }
    
    
    public void init(){//滑动事件
		waterfall_scroll = (Vertical02_LazyScrollView) findViewById(R.id.myscroll);
		waterfall_scroll.getView();
		waterfall_scroll.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onTop() {  
			}

			@Override
			public void onScroll() {
			}
//向下滚动时到低端时调用
			@Override
			public void onBottom() {
				loadImager(++current_page, page_count);
			}
		});
    	
    	
    	//获得主页面布局控件
    	mLinearLayout=(LinearLayout) findViewById(R.id.scroll_linear);
    	//向线性布局存入list当中
    	for(int i=0;i<3;i++){
    		LinearLayout itemLayout = new LinearLayout(this);
			LinearLayout.LayoutParams itemParam = new LinearLayout.LayoutParams(
					itemWidth, LayoutParams.WRAP_CONTENT);
			itemLayout.setPadding(2, 2, 2, 2);
			itemLayout.setOrientation(LinearLayout.VERTICAL);
			itemLayout.setLayoutParams(itemParam);
			mLinearLayout.addView(itemLayout);
			itemlist.add(itemLayout);
    	}
    	
    	try {
			image_filenames=Arrays.asList(assetManager.list("images"));
		} catch (IOException e) {
		}
    	loadImager(current_page, page_count);
    	
    }
    
    
    public void loadImager(int pageindex, int pagecount){
    	int imagecount1 = image_filenames.size();
    	
    	int j=0;
    	for (int i = pageindex * pagecount; i < pagecount * (pageindex + 1)
				; i++) {
    		int imagecount2 =(int) (Math.random()*imagecount1);
    		j = j >= column_count ? j = 0 : j;
			AddImage(image_filenames.get(imagecount2), j++);

		}
    }
    private void AddImage(String filename, int columnIndex) {
		ImageView item = (ImageView) LayoutInflater.from(this).inflate(
				R.layout.vertical02_waterfall_item, null);
		itemlist.get(columnIndex).addView(item);
		
		Vertical02_TaskParam param = new Vertical02_TaskParam();
		param.setAssetManager(assetManager);
		param.setFilename("images" + "/" + filename);
		param.setItemWidth(itemWidth);
		Vertical02_ImageLoaderTask task = new Vertical02_ImageLoaderTask(item);
		task.execute(param);
	}
}
