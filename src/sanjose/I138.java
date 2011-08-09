package sanjose;

import java.util.Date;
import javax.jdo.annotations.*;

@PersistenceCapable
public class I138 extends Single{
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
	private Long vol;
	
	public I138(I i,Long vol,Date time){
		this.key=i+"."+j+"."+time;
		this.i=i.getId();
		this.j=i.getSite();
		this.t=time;
		this.vol=vol;
	}
	public I138(Long i,Long j,Long vol,Date time){
		this.key=i+"."+j+"."+time;
		this.i=i;
		this.j=j;
		this.vol=vol;
		this.t=time;
	}
	public Long geti(){
	    return i;
	}	
	public Long getj(){
		return j;
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