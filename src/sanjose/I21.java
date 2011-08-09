package sanjose;

import java.util.Date;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class I21{
	@PrimaryKey
	@SuppressWarnings("unused")
	private String _key;
	@Persistent
	private Long i;
	@Persistent
	private Long j;
	@Persistent
	private Long r;
	@Persistent
	private Date t;
	@Persistent
	private Long o;
	@Persistent
	private Long w;;

	public I21(I i,Long oi,Long os,Date t){
		this.o=i.geto();
		this.w=i.getw();
		this.i=oi;
		this.j=os;
		this.t=t;
		this._key=this.i+"."+this.j+"."+this.o+"."+this.w;
	}

	public Long geti(){
	    return i;
	}
	public Long getj(){
	    return j;
	}
	public Long getr(){
	    return r;
	}
	public Date gett(){
	    return t;
	}
	public Long getw(){
	    return w;
	}
	public Long geto(){
	    return o;
	}

}