package com.cblue.callback;

public class A {
	
	
	private String s ;
	
	public void downloadFile(String fileUrl,final CallBack callBack){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//线程中得到执行结果之后，并把执行的结果传递给接口方法
				String result = "aaa";
				//s = result;
				callBack.getResult(result);
			}
		}).start();
	}

	public interface CallBack{
		public void getResult(String s);
	}
}
