package sanjose;

import java.util.Date;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
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

	public I21(I i,I owner,Date time){
		this.i=i.getId();
		this.j=i.getSite();
		this.o=owner.getId();
		this.w=owner.getSite();
		t=time;
		_key=i+"."+owner;
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