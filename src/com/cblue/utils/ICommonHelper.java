package com.cblue.utils;

public interface ICommonHelper {
	
	public String readTextFile(String filePath);
	public byte[] readBinaryFile(String filePath);
	public boolean writeTextFile(String content , String filePath);
	public boolean writeBinaryFile(byte[] data , String filePath);
	public boolean copyTextFile(String filePath , String destFilePath);
	public boolean copyBinaryFile(String filePath , String destFilePath);
	public boolean deleteFile(String filePath);
	public boolean isExistFile(String filePath);
	public String getFileExtension(String filePath);
	
}