package com.wonders.stpt.todoItem.util;

import com.wonders.stpt.todoItem.model.bo.TodoItem;

public class MultiMain {
	private class Inner{
		public void test(){
			test2();
			System.out.println(a);
			MultiMain.this.test2();
		}
	}
	
	private int a=1;
	private Data test2(){
		final FutureData futureData = new FutureData();
		new Thread(){
			public void run(){
				RealData realData = new RealData();
				futureData.setData(realData);
			}
		}.start();
		return futureData;
	}
	
	public static void main(String[] args){
		
	}
	
}
