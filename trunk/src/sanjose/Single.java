package sanjose;

import java.util.Date;

//import java.util.Date;
//import javax.jdo.annotations.Persistent;
//import javax.jdo.annotations.PrimaryKey;

public class Single{
	private long tick;
	private long val;
	public boolean real=false;
	public long getTick(){
		return tick;
	}
	public Date getTime(){
		return null;
	}
	public long getVal(){
		return val;
	}
	public void setTick(long tick){
		this.tick=val;
	}
	public void setVal(long val){
		this.val=val;
	}
}
