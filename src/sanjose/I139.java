package sanjose;

import java.util.Date;
import javax.jdo.annotations.*;

@PersistenceCapable
public class I139 extends Single{
	@PrimaryKey
	@SuppressWarnings("unused")
	private String _key;
	@Persistent
	private Long o;
	@Persistent
	private Long w;
	@Persistent
	private Date t;
	@Persistent
	private Long val;
	
	public I139(I owner,long val,Date time){
		this.o=owner.getId();
		this.w=owner.getSite();
		this.t=time;
		this.val=val;
		this._key=this.o+"."+this.w+"."+this.getTick();
	}
	public I139(long ownerId,long ownerSite,long val,Date time){
		this.o=ownerId;
		this.w=ownerSite;
		this.t=time;
		this.val=val;
		this._key=this.o+"."+this.w+"."+this.getTick();
	}
	public long getOwnerId(){
	    return o;
	}	
	public long getOwnerSite(){
		return w;
	}
	public long getTick(){
		return t.getTime()/1000;
	}
	public Date getTime(){
		return t;
	}
	public long getVal(){
		return val;
	}
	public void setVal(Long val){
		this.val=val;
	}
}