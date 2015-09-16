package com.cblue.file.download.singlethread;

import java.io.Serializable;

public class FileInfo implements Serializable {

	private int id;
	private String url;
	private String fileName;
	private long fileLength;  //文件长度
	private long finishedLength; //下载完成文件长度
	@Override
	public String toString() {
		return "FileInfo [id=" + id + ", url=" + url + ", fileName=" + fileName
				+ ", fileLength=" + fileLength + ", finishedLength="
				+ finishedLength + "]";
	}
	public FileInfo() {
		super();
	}
	public FileInfo(int id, String url, String fileName, int fileLength,
			int finishedLength) {
		super();
		this.id = id;
		this.url = url;
		this.fileName = fileName;
		this.fileLength = fileLength;
		this.finishedLength = finishedLength;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public long getFileLength() {
		return fileLength;
	}
	public void setFileLength(long fileLength) {
		this.fileLength = fileLength;
	}
	public long getFinishedLength() {
		return finishedLength;
	}
	public void setFinishedLength(long finishedLength) {
		this.finishedLength = finishedLength;
	}
}
