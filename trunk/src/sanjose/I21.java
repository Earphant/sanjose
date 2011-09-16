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
	private Long a;
	@Persistent
	private Long r;
	@Persistent
	private Date t;
	@Persistent
	private Long o;
	@Persistent
	private Long w;

	public I21(I i,I owner,Date time){
		this.i=i.getId();
		this.j=i.getSite();
		this.a=i.getType();
		this.o=owner.getId();
		this.w=owner.getSite();
		t=time;
		_key=i+"."+owner;
	}
	public long geti(){
	    return i;
	}
	public long getj(){
	    return j;
	}
	public long getr(){
	    return r;
	}
	public Date gett(){
	    return t;
	}
	public long geta(){
	    return a;
	}
	public long getw(){
	    return w;
	}
	public long geto(){
	    return o;
	}
}