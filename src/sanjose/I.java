package sanjose;

import java.util.Date;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Text;
import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class I{
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private Key _key;
	@Persistent
	private Long i;
	@Persistent
	private Long j;
	@Persistent
	private Long a;
	@Persistent
	private Long b;
	@Persistent
    private Date c;
	@Persistent
	private Long d;
	@Persistent
	private String e;
	@Persistent
	private Long h;
	@Persistent
    private Date m;
	@Persistent
	private Long o;
	@Persistent
	private String p;
	@Persistent
	private Text q;
	@Persistent
	private Long r;
	@Persistent
	private Long s;
	@Persistent
    private Date t;
	@Persistent
	private Long w;
	@Persistent
	private String x;
	@Persistent
	private String z;

	public I(Long id,Long site,String text,String plink,long classid,long rate,
		long ownerid,long ownersite){
		Date current=new Date();
		this._key=KeyFactory.createKey(I.class.getSimpleName(),id+"."+site);
		this.i=id;
		this.j=site;
		this.a=classid;
		this.b=ownerid;
		this.s=ownersite;
		this.d=1L;
		this.h=1L;
		this.o=ownerid;
		this.w=ownersite;
		this.p=plink;
		this.c=current;
		this.m=current;
		this.r=rate;
		this.t=current;
		this.x=text;
	}
	public I(String text,String plink,long classid,long rate,long ownerid,long ownersite){
		Date current=new Date();
		this.i=0L;
		this.j=9L;
		this.a=classid;
		this.b=ownerid;
		this.d=1L;
		this.h=1L;
		this.m=current;
		this.o=ownerid;
		this.w=ownersite;
		this.p=plink;
		this.r=rate;
		this.s=ownersite;
		this.x=text;
	}
	public long getAccessTick(){
	    return c.getTime()/1000;
	}
	public long getBaseId(){
	    return b;
	}
	public long getBaseSite(){
	    return s;
	}
	public long getClassId(){
	    return a;
	}
	public long getCreateTick(){
	    return t.getTime()/1000;
	}
	public String getExtension(){
	    return e;
	}
	public String getExtra(){
	    return z;
	}
	public long getId(){
	    return i;
	}
	public long getModifyTick(){
	    return m.getTime()/1000;
	}
	public Date getModifyTime(){
	    return m;
	}
	public String getQuotation(){
	    return q.getValue();
	}
	public long getRate(){
	    return r;
	}
	public long getRefId(){
	    return d;
	}
	public long getRefSite(){
	    return h;
	}
	public long getSite(){
	    return j;
	}
	public String getp(){
	    return p;
	}
	public long getOwnerId(){
	    return o;
	}
	public long getOwnerSite(){
	    return w;
	}
	public String getText(){
	    return x;
	}
	public void setExtra(String val){
	    this.z=val;
	}
	public void setId(){
		if(this.i==0L){
			this.i=_key.getId();
			this.c=this.m;
			this.t=this.m;
		}
	}
	public void setId(PersistenceManager mgr){
		setId();
		mgr.makePersistent(this);
	}
	public void setModifyTime(Date val){
		this.m=val;
	}
	public void setQuotation(String val){
		this.q=new Text(val);
	}
	public void setText(String val){
	    this.x=val;
	}
}