package sanjose;

import java.util.Date;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class I135{
	@PrimaryKey
	@SuppressWarnings("unused")
	private String key;
	@Persistent
	private Long n;
	@Persistent
	private Long o;
	@Persistent
	private Date t;
	@Persistent
	private Long fat;
	@Persistent
	private Long wat;


	public I135(long n,long o,long fat,long wat,Date t){
		this.key=n+"."+o+"."+t;
		this.n=n;
		this.o=o;
		this.t=t;
		this.fat=fat;
		this.wat=wat;
	}
	
	public long getn(){
		return n;
	}
	public long geto(){
	    return o;
	}
	public Date gett(){
		return t;
	}
	public long getfat(){
	    return fat;
	}
	public long getwat(){
	    return wat;
	}
	public void setfat(long fat){
		this.fat=fat;
	}
	public void setwat(long wat){
		this.wat=wat;
	}
	public void sett(Date t){
		this.t=t;
	}
}
