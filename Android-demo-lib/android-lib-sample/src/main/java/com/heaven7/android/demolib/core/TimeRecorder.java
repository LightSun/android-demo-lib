package com.heaven7.android.demolib.core;


/**
 * the time recorder, 
 * @author heaven7
 */
public class TimeRecorder {

	private long startTime;
	private long endTime;
	
	public void begin(){
		startTime = System.currentTimeMillis();
	}
	
	public void end(){
		endTime = System.currentTimeMillis();
	}
	/** @return the cost time in mills*/
	public long getCostTime(){
		if(startTime==0 || endTime==0 || startTime > endTime)
			throw new IllegalStateException("wrong startTime or endTime");
		return endTime - startTime;
	}

	public void reset(){
		startTime = endTime = 0;
	}
	
	public String toCostTimeString(){
		return "cost time: "+ getCostTime();
	}
}
