package sanjose;

import java.util.Date;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class I136 extends Single{
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
	
	public I136(I i,Long vol,Date time){
		this._key=o+"."+w+"."+time;
		this.o=i.getId();
		this.w=i.getSite();
		this.t=time;
		this.vol=vol;
	}
	public I136(Long o,Long w,Long vol,Date time){
		this._key=o+"."+w+"."+time;
		this.o=o;
		this.w=w;
		this.vol=vol;
		this.t=time;
	}
	public Long geto(){
	    return o;
	}	
	public Long getw(){
		return w;
	}
	public Date gettime(){
		return t;
	}
	public Long getvol(){
		return vol;
	}
	public void setvol(long vol){
		this.vol=vol;
	}
}