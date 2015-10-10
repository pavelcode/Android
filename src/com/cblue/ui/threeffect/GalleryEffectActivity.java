package com.cblue.ui.threeffect;

  


import com.cblue.android.R;

import android.app.Activity;  
import android.graphics.Bitmap;  
import android.graphics.drawable.BitmapDrawable;  
import android.os.Bundle;  
import android.view.View;  
import android.view.ViewGroup;  
import android.widget.BaseAdapter;  
import android.widget.Gallery.LayoutParams;  
import android.widget.ImageView;  
  
/**
 * 3D画廊效果
 * @author Administrator
 *
 */
public class GalleryEffectActivity extends Activity {  
  
    /** 图片资源数组 */  
    private int[] imageResIDs;  
  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.threeffect_gallery);  
        imageResIDs = new int[]{//  
        R.drawable.pic1, //  
                R.drawable.pic2, //  
                R.drawable.pic3, //  
                R.drawable.pic4, //  
                R.drawable.pic5, //  
                R.drawable.pic6 //  
                 //  
        };  
        GalleryCustom galleryCustom = (GalleryCustom) findViewById(R.id.customgallery);  
        ImageAdapter adapter = new ImageAdapter();  
        galleryCustom.setAdapter(adapter);  
    }  
  
    public class ImageAdapter extends BaseAdapter {  
  
        @Override  
        public int getCount() {  
            // TODO Auto-generated method stub  
            return imageResIDs.length;  
        }  
  
        @Override  
        public Object getItem(int position) {  
            // TODO Auto-generated method stub  
            return imageResIDs[position];  
        }  
  
        @Override  
        public long getItemId(int position) {  
            // TODO Auto-generated method stub  
            return position;  
        }  
  
        @Override  
        public View getView(int position, View convertView, ViewGroup parent) {  
            // TODO Auto-generated method stub  
            ImageView imageView;  
            if (convertView != null) {  
                imageView = (ImageView) convertView;  
            } else {  
                imageView = new ImageView(GalleryEffectActivity.this);  
            }  
            Bitmap bitmap = GalleryImageUtil.getImageBitmap(getResources(),  
                    imageResIDs[position]);  
            BitmapDrawable drawable = new BitmapDrawable(bitmap);  
            drawable.setAntiAlias(true); // 消除锯齿  
            imageView.setImageDrawable(drawable);  
            LayoutParams params = new LayoutParams(240, 320);  
            imageView.setLayoutParams(params);  
            return imageView;  
        }  
    }  
}  