package com.cblue.file.download.singlethread;

import java.util.List;

public interface DownLoadDBDao {
	
	public void insert(ThreadInfo threadInfo);
	
	public void delete(String url,int thread_id);
	
	public void update(String url,int thread_id,long finishedPosition);
	
	public List<ThreadInfo> getThreads(String url);
	
	public boolean isExist(String url,int thread_id);

}
