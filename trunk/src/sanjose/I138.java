package sanjose;

import java.util.Date;
import javax.jdo.annotations.*;

@PersistenceCapable
public class I138 extends Single{
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
	private Long vol;

	public I138(I owner,long vol,Date time){
		this.o=owner.getId();
		this.w=owner.getSite();
		this.t=time;
		this.vol=vol;
		this._key=this.o+"."+this.w+"."+time.getTime();
	}
	public I138(long ownerId,long ownerSite,long vol,Date time){
		this.o=ownerId;
		this.w=ownerSite;
		this.t=time;
		this.vol=vol;
		this._key=this.o+"."+this.w+"."+time.getTime();
	}
	public long getOwnerId(){
	    return o;
	}	
	public long getOwnerSite(){
		return w;
	}
	public Date getTime(){
		return t;
	}
	public long getVol(){
		return vol;
	}
	public void setVol(long vol){
		this.vol=vol;
	}
}