package com.cblue.savedata.internalstorage;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;

public class AssetsFileTools {

private Context context;
	
	public AssetsFileTools(Context context){
		this.context= context;
	}
	
	public byte[] readAssetFile(String fileName)throws Exception{
		AssetManager assetManager = context.getResources().getAssets();
		//AssetManager assetManager = context.getAssets();
		InputStream inputStream = assetManager.open(fileName);
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
