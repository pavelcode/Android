package com.cblue.control;


import com.cblue.android.R;

import android.app.Activity;  
import android.os.Bundle;  
import android.view.LayoutInflater;  
import android.view.View;  
import android.widget.TabHost;  
import android.widget.TextView;  

/**
 * 自定义TabHost的样式
 * 每块View的布局文件中的TexView都会引用一个selector作为背景，selector可以定义选中的变化
 * 
 * @author Administrator
 *
 */
public class TabHostCustomerStyle extends Activity {  
	
    /** Called when the activity is first created. */  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        
        setContentView(R.layout.customer_tabhost);  
          
        View niTab = (View) LayoutInflater.from(this).inflate(R.layout.customer_tabwidget, null);  
        TextView text0 = (TextView) niTab.findViewById(R.id.tab_label);  
        text0.setText("笑脸");  
          
        View woTab = (View) LayoutInflater.from(this).inflate(R.layout.customer_tabwidget, null);  
        TextView text1 = (TextView) woTab.findViewById(R.id.tab_label);  
        text1.setText("眨眼");  
          
        View taTab = (View) LayoutInflater.from(this).inflate(R.layout.customer_tabwidget, null);  
        TextView text2 = (TextView) taTab.findViewById(R.id.tab_label);  
        text2.setText("可爱");  
          
        View weTab = (View) LayoutInflater.from(this).inflate(R.layout.customer_tabwidget, null);  
        TextView text3 = (TextView) weTab.findViewById(R.id.tab_label);  
        text3.setText("卖萌");  
          /*
           * 这里我们用findViewById创建了TabHost，这样的话我们就需要在添加tab时调用TabHost的setup()方法；
           * 这里我们添加内容时添加的是布局，我们完全可以换成自己创建的Activity。
           */
        TabHost tabHost = (TabHost)findViewById(R.id.tabhost);  
        tabHost.setup();   
        //Call setup() before adding tabs if loading TabHost using findViewById().   
          
        tabHost.addTab(tabHost.newTabSpec("nitab").setIndicator(niTab).setContent(R.id.tab1));  
        tabHost.addTab(tabHost.newTabSpec("wotab").setIndicator(woTab).setContent(R.id.tab2));  
        tabHost.addTab(tabHost.newTabSpec("tatab").setIndicator(taTab).setContent(R.id.tab3));  
        tabHost.addTab(tabHost.newTabSpec("wetab").setIndicator(weTab).setContent(R.id.tab4));  
    }  
}  

