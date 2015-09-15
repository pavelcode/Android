package com.cblue.savedata.contentprovider;

import android.test.AndroidTestCase;

public class TestReadSDCardResource extends AndroidTestCase {
	
	public void testReadSDCardResourceByContentProvider(){
		
		ReadSDCardResource mReadSDCardResource = new ReadSDCardResource();
		mReadSDCardResource.readSDCardImageResourceByContentProvider(getContext());
	}

}
