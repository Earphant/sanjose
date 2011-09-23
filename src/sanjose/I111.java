package sanjose;

import java.util.Date;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class I111 extends Single{
	@PrimaryKey
	@SuppressWarnings("unused")
	private String _key;
	@SuppressWarnings("unused")
	@Persistent
	private Long i;
	@SuppressWarnings("unused")
	@Persistent
	private Long j;
	@Persistent
	private Date t;
	@Persistent
	private Long ord;
	@Persistent
	private Long qty;
	@Persistent
	private String val;
	@Persistent
	private String ref;
	@Persistent
	private String z;

	public I111(I i,long ord,Date time,String val,long qty,String ref,String xtr){
		this.i=i.getId();
		this.j=i.getSite();
		this.t=time;
		this.ord=ord;
		this.qty=qty;
		this.ref=ref;
		this.val=val;
		this.z=xtr;
		this._key=i+"."+ord;
	}
	public String getExtra(){
	    return z;
	}
	public long getOrder(){
	    return ord;
	}
	public long getQuantity(){
	    return qty;
	}
	public String getReference(){
	    return ref;
	}
	public long getTick(){
		return t.getTime()/1000;
	}
	public Date getTime(){
		return t;
	}
	public String getValue(){
	    return val;
	}
	public void setQuantity(long val){
		this.qty=val;
	}
	public void setValue(String val){
		this.val=val;
	}
	public void setTime(Date t){
		this.t=t;
	}
}