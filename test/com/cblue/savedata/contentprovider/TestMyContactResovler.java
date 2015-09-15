package com.cblue.savedata.contentprovider;

import com.cblue.savedata.contentprovider.MyContactResovler;

import android.test.AndroidTestCase;

public class TestMyContactResovler extends AndroidTestCase {
	
	public void testDisplayContact(){
		MyContactResovler myContactResovler = new MyContactResovler(getContext());
		myContactResovler.displayContact();
		
		
	}

}
