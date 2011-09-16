package sanjose;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class I8{
	@SuppressWarnings("unused")
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private String _key;
	@Persistent
	private Long i;
	@Persistent
	private Long j;
	@Persistent
	private Long v;
	@Persistent
	private Date t;
	@Persistent
	private String htm;

	public I8(I i,String htm,long ver,Date time){
		this.i=i.getId();
		this.j=i.getSite();
		this.htm=htm;
		this.v=ver;
		this.t=time;
		this._key=this.i+"."+this.j+"."+this.v;
	}
	public Long getId(){
	    return i;
	}
	public Long getSite(){
	    return j;
	}
	public Date getTime(){
	    return t;
	}
	public String getHtml() {
		return htm;
	}
	public Long getVersion(){
	    return v;
	}
	public void setHtml(String val){
	    this.htm=val;
	}
}