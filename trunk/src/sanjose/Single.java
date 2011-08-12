package sanjose;

import java.util.Date;

//import java.util.Date;
//import javax.jdo.annotations.Persistent;
//import javax.jdo.annotations.PrimaryKey;

public class Single{
	private long t;
	private long val;
	public boolean real=false;

	public long getTick(){
		return this.t;
	}
	public Date getTime(){
		return null;
	}
	public long getVal(){
		return this.val;
	}
	public void setTick(long val){
		this.t=val;
	}
	public void setVal(long val){
		this.val=val;
	}
}
