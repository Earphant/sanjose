package sanjose;

import java.util.Date;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;


@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class I8{
	@Persistent
	private Long i;
	@Persistent
	private Long j;
	@Persistent
	private Date t;
	@Persistent
	private String v;
	@Persistent
	private String htm;

	public I8(I i, String htm ,Date time){
		this.i=i.getId();
		this.j=i.getSite();
		this.htm=htm;
		this.t=time;
	}
	public Long geti(){
	    return i;
	}
	public Long getj(){
	    return j;
	}
	public Date gett(){
	    return t;
	}
	public String gethtm() {
		return htm;
	}
	public String getv(){
	    return v;
	}
	public void sethtm(String htm){
	    this.htm=htm;
	}
}