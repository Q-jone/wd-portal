package com.wonders.stpt.todoItem.util;

import java.util.List;

public class FutureData implements Data{
	private RealData realData = null;
	private boolean ready = false;
	
	public synchronized void setData(RealData realData){
		if(ready) return;
		this.realData = realData;
		notifyAll();
	}
	
	public synchronized String getContent(){
		while(!ready){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return this.realData.getContent();
	}
	
	public synchronized List getList(){
		while(!ready){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return this.realData.getList();
	}
}
