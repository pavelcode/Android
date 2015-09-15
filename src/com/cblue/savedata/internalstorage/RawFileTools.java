package com.cblue.savedata.internalstorage;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import android.content.Context;
import android.content.res.Resources;

public class RawFileTools {
	
	
private Context context;
	
	public RawFileTools(Context context){
		this.context= context;
	}
	
	public byte[] readRawFile(int fileID)throws Exception{
		//byte [] b =null;
		Resources rs =  context.getResources();
		InputStream inputStream = rs.openRawResource(fileID);
		ByteArrayOutputStream mArrayOutputStream = new ByteArrayOutputStream();
		byte [] b = new byte[1024];
		int length =0;
		while((length=inputStream.read(b, 0, b.length))!=-1){
			mArrayOutputStream.write(b, 0, length);
		}
		if(inputStream!=null){
			inputStream.close();
		}
		return mArrayOutputStream.toByteArray();
	}
	

}
