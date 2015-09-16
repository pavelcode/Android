package com.cblue.file.download.singlethread;

public class ThreadInfo {

	
	private int id;
	private String url;
	private long startPosition;  //开始位置
	private long endPosition;    //结束位置
	@Override
	public String toString() {
		return "ThreadInfo [id=" + id + ", url=" + url + ", startPosition="
				+ startPosition + ", endPosition=" + endPosition
				+ ", finishedPosition=" + finishedPosition + "]";
	}
	public ThreadInfo() {
		super();
	}
	public ThreadInfo(int id, String url, long startPosition, long endPosition,
			long finishedPosition) {
		super();
		this.id = id;
		this.url = url;
		this.startPosition = startPosition;
		this.endPosition = endPosition;
		this.finishedPosition = finishedPosition;
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
	public long getStartPosition() {
		return startPosition;
	}
	public void setStartPosition(long startPosition) {
		this.startPosition = startPosition;
	}
	public long getEndPosition() {
		return endPosition;
	}
	public void setEndPosition(long endPosition) {
		this.endPosition = endPosition;
	}
	public long getFinishedPosition() {
		return finishedPosition;
	}
	public void setFinishedPosition(long finishedPosition) {
		this.finishedPosition = finishedPosition;
	}
	private long finishedPosition;  //完成位置
}
