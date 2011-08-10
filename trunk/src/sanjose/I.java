package sanjose;

import java.util.Date;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Text;
import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdGeneratorStrategy;
//import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

//@PersistenceCapable(identityType = IdentityType.APPLICATION)
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
	private Long o;
	@Persistent
	private Long w;
	@Persistent
	private Long r;
	@Persistent
	private Long s;
	@Persistent
    private Date c;
	@Persistent
    private Date m;
	@Persistent
    private Date t;
	@Persistent
	private String p;
	@Persistent
	private String x;
	@Persistent
	private String xtr;
	@Persistent
	private Text e;

	public I(Long i,Long j,String x,String p,Long a,Long r,Long o,Long w){
		Date c=new Date();
		this._key=KeyFactory.createKey(I.class.getSimpleName(),i+"."+j);
		this.i=i;
		this.j=j;
		this.a=a;
		this.b=o;
		this.s=w;
		this.o=o;
		this.w=w;
		this.c=c;
		this.m=c;
		this.r=r;
		this.t=c;
		this.p=p;
		this.x=x;
		this.e=new Text("");
	}
	public I(String text,String guid,long classid,long rate,long ownerid,long ownersite){
		Date current=new Date();
		this.i=0L;
		this.j=9L;
		this.a=classid;
		this.b=ownerid;
		this.m=current;
		this.o=ownerid;
		this.w=ownersite;
		this.p=guid;
		this.r=rate;
		this.s=ownersite;
		this.x=text;
		this.e=new Text("");
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
	public String gete(){
	    return e.getValue();
	}
	public String getExtra(){
	    return xtr;
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
	public long getRate(){
	    return r;
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
	public String getx(){
	    return x;
	}
	public void sete(String e){
		this.e=new Text(e);
	}
	public void setExtra(String val){
	    this.xtr=val;
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
	public void setx(String val){
	    this.x=val;
	}
}