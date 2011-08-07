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
	
	public I138(Long ownerid,Long ownersite,Date time,Long vol){
		this._key=ownerid+"."+ownersite+"."+time;
		this.o=ownerid;
		this.w=ownersite;
		this.t=time;
		this.vol=vol;
	}
	public Long getid(){
	    return o;
	}	
	public Long getsite(){
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