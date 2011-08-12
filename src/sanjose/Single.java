package sanjose;

import java.util.Date;

public class Single{
	private Date t;
	private long val;
	public boolean real=false;

	public long getTick(){
		return this.t.getTime()/1000;
	}
	public Date getTime(){
		return t;
	}
	public long getVal(){
		return this.val;
	}
	public void setTick(long val){
		this.t=new Date(val*1000);
	}
	public void setVal(long val){
		this.val=val;
	}
}
