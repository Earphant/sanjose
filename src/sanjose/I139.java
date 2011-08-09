package sanjose;

import java.util.Date;
import javax.jdo.annotations.*;

@PersistenceCapable
public class I139 extends Single{
	@PrimaryKey
	@SuppressWarnings("unused")
	private String _key;
	@Persistent
	private Long i;
	@Persistent
	private Long j;
	@Persistent
	private Date t;
	@Persistent
	private Long vol;

	public I139(I i,Long vol,Date time){
		this._key=i+"."+j+"."+time;
		this.i=i.geti();
		this.j=i.getj();
		this.t=time;
		this.vol=vol;
	}
	public I139(Long i,Long j,Long vol,Date time){
		this._key=i+"."+j+"."+time;
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
	public void setvol(Long vol){
		this.vol=vol;
	}
}