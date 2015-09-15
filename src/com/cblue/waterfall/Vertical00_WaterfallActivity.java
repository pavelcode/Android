package com.cblue.waterfall;

import java.io.InputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cblue.android.R;


/**
 * 瀑布流的最简单的实现，图片的瀑布流的基本原理，没有加图片缓存
 * @author Administrator
 *
 */
public class Vertical00_WaterfallActivity extends Activity implements View.OnClickListener
{
	/**瀑布流三列**/
    private  LinearLayout linearLayout1 = null;
    private  LinearLayout linearLayout2 = null;
    private  LinearLayout linearLayout3 = null;
    //添加图片的LinearLayout的位置
    private int add_image_linearlayout = 0;
    //瀑布流的宽度
    private int linearlayoutWidth = 0;
	
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vertical00_main);
        linearLayout1 = (LinearLayout)findViewById(R.id.main_linearlayout1);
        linearLayout2 = (LinearLayout)findViewById(R.id.main_linearlayout2);
        linearLayout3 = (LinearLayout)findViewById(R.id.main_linearlayout3);
        linearlayoutWidth =  getWindowManager().getDefaultDisplay().getWidth()/3;
        addBitmaps();
    }
    
    private void addBitmaps()
    {
    	int index =0;
    	try {
    		String filepaths[] = getResources().getAssets().list("images");
    		for(String string:filepaths)
    		{
    			try {
    				InputStream inputStream = getResources().getAssets().open("images/"+string);
    				Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
    				Bitmap bitmap2 = Vertical00_BitmapZoom.bitmapZoomByWidth(bitmap, linearlayoutWidth);
    				ImageView imageView = new ImageView(this);
    				imageView.setImageBitmap(bitmap);
    				LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(bitmap2.getWidth(), bitmap2.getHeight());
    				imageView.setLayoutParams(layoutParams);
    				imageView.setOnClickListener(this);
    				imageView.setTag(new Integer(index));
    				switch (add_image_linearlayout) 
    				{
						case 0:
							linearLayout1.addView(imageView);
							break;
						case 1:
							linearLayout2.addView(imageView);
							break;
						case 2:
							linearLayout3.addView(imageView);
							break;
						default:
							break;
					}
    				index++;
    				add_image_linearlayout++;
    				//是否能被3整除，判断要添加的图片到那个LinearLayout中
    				add_image_linearlayout= add_image_linearlayout%3;
    				inputStream.close();
    				
				} catch (Exception e) {
				}
    			
    			
    		}
    		

		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	
    }
    
    @Override
    public void onClick(View v) 
    {
    	int index  =  (Integer)v.getTag();
    	Toast.makeText(this, "click index= "+index, 1).show();
    }
    
    
}