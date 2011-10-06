package sanjose;

import java.util.Date;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class I24{
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
	private Long v;
	@Persistent
	private Long ord;
	@SuppressWarnings("unused")
	@Persistent
	private Long pri;
	@Persistent
	@SuppressWarnings("unused")
	private Long prj;
	@Persistent
	@SuppressWarnings("unused")
	private Long prs;
	@Persistent
	private Long qty;
	@Persistent
	@SuppressWarnings("unused")
	private Long stt;
	@Persistent
	private String val;
	@Persistent
	private Long o;
	@Persistent
	private Long w;
	@Persistent
	private String ref;
	@Persistent
	private String z;

	public I24(I i,long ord,Date time,String val,long qty,String ref,String xtr){
		this.i=i.getId();
		this.j=i.getSite();
		this.t=time;
		this.ord=ord;
		this.pri=1L;
		this.prj=1L;
		this.prs=0L;
		this.qty=qty;
		this.ref=ref;
		this.v=0L;
		this.val=val;
		this.o=1L;
		this.w=1L;
		this.z=xtr;
		this._key=i+"."+v+"."+ord;
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
	public I getVendor(){
		return new I(o,w);
	}
	public void setQuantity(long val){
		this.qty=val;
	}
	public void setTime(Date t){
		this.t=t;
	}
	public void setValue(String val){
		this.val=val;
	}
	public void setVendor(I val){
		o=val.getId();
		w=val.getSite();
	}
}