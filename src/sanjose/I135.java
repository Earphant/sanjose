package sanjose;

import java.util.Date;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class I135 extends Single{
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
	private Long fat;
	@Persistent
	private Long wat;

	public I135(I owner,long fat,long wat,Date time){
		this.o=owner.getId();
		this.w=owner.getSite();
		this.t=time;
		this.fat=fat;
		this.wat=wat;
		this._key=this.o+"."+this.w+"."+time.getTime();
	}
	public I135(long ownerId,long ownerSite,long fat,long wat,Date time){
		this.o=ownerId;
		this.w=ownerSite;
		this.t=time;
		this.fat=fat;
		this.wat=wat;
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
	public long getFat(){
	    return fat;
	}
	public long getWater(){
	    return wat;
	}
	public void setFat(long fat){
		this.fat=fat;
	}
	public void setWater(long wat){
		this.wat=wat;
	}
	public void setTime(Date t){
		this.t=t;
	}
}