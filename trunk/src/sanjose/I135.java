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

	public I135(I i,Long fat,Long wat,Date time){
		this._key=o+"."+w+"."+time;
		this.o=i.getId();
		this.w=i.getSite();
		this.fat=fat;
		this.wat=wat;
		this.t=time;
	}
	public I135(Long o,Long w,Long fat,Long wat,Date time){
		this._key=o+"."+w+"."+time;
		this.o=o;
		this.w=w;
		this.fat=fat;
		this.wat=wat;
		this.t=time;
	}
	public long geto(){
	    return o;
	}
	public long getw(){
		return w;
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