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
	private Long i;
	@Persistent
	private Long j;
	@Persistent
	private Date t;
	@Persistent
	private Long fat;
	@Persistent
	private Long wat;

	public I135(I i,Long fat,Long wat,Date time){
		this.key=i+"."+j+"."+time;
		this.i=i.geti();
		this.j=i.getj();
		this.fat=fat;
		this.wat=wat;
		this.t=time;
	}
	public I135(Long i,Long j,Long fat,Long wat,Date time){
		this.key=i+"."+j+"."+time;
		this.i=i;
		this.j=j;
		this.fat=fat;
		this.wat=wat;
		this.t=time;
	}
	public long geti(){
	    return i;
	}
	public long getj(){
		return j;
	}
	public long getfat(){
	    return fat;
	}
	public long getwat(){
	    return wat;
	}
	public Date gettime(){
		return t;
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
