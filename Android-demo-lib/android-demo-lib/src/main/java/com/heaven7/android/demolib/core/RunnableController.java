package com.heaven7.android.demolib.core;

import java.util.ArrayList;
import java.util.List;

/**
 * runnable controller
 * @author Administrator
 */
public class RunnableController {

	private final List<Runner> runners ;
	
	public RunnableController(){
		runners = new ArrayList<Runner>();
	}

	public RunnableController(Runnable... rs){
		runners = new ArrayList<Runner>();
		final List<Runner> runners = this.runners;
		for(int i=0,size = rs.length ;i<size ;i++){
			runners.add(new Runner(rs[i], i));
		}
	}
	/**
	 * @param flags 应该为1-2-4-8-16-32...等 ，其中之一或者其中几个的和
	 * eg:  1|2|4|8...
	 */
	public void runByFlags(int flags){
		final List<Runner> runners = this.runners;
		Runner runner;
		for(int i=0,size = runners.size(); i<size;i++){
			runner = runners.get(i);
			if((flags & runner.flag) != 0)
				runner.r.run();
	    }
	}
	
	public void runByFlags(int flags,Runnable...rs){
		final List<Runner> runners = this.runners;
		runners.clear();
		for(int i=0,size = rs.length ;i<size ;i++){
			runners.add(new Runner(rs[i], i));
		}
		runByFlags(flags);
	}
	
	private class Runner{
		public final Runnable r;
		public final int flag;

		/**index >=0 */
		public Runner(Runnable r,int index) {
			this.r = r;
			if(index<0 || index >29){ //2^30  (log2 Integer.MAX_VALUE >=30 && <31)
				throw new IllegalArgumentException("must: index >=0 && index<=29");
			}
			flag = (int) Math.pow(2, index);//2^index
		}
	}

	
}
