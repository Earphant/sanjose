package sanjose;

import java.util.Date;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Text;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class I{
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private Key key;
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
	@SuppressWarnings("unused")
	@Persistent
	private Long r;
	@Persistent
	private Long s;
	@SuppressWarnings("unused")
	@Persistent
    private Date c;
	@Persistent
    private Date m;
	@SuppressWarnings("unused")
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
	@Persistent
	private I135 i135;
	@Persistent
	private I136 i136;
	@Persistent
	private I138 i138;
	@Persistent
	private I139 i139;

	public I(Long i,Long j,String x,String p,Long a,Long r,Long o,Long w){
		Date c=new Date();
		this.key=KeyFactory.createKey(I.class.getSimpleName(),i+"."+j);
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
	public long getClassId(){
	    return a;
	}
	public long getb(){
	    return b;
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
	public long getSite(){
	    return j;
	}
	public String getp(){
	    return p;
	}
	public long gets(){
	    return s;
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
	public I135 geti135(){
	    return i135;
	}
	public I136 geti136(){
	    return i136;
	}
	public I138 geti138(){
	    return i138;
	}
	public I139 geti139(){
	    return i139;
	}
	public void sete(String e){
		this.e=new Text(e);
	}
	public void setExtra(String val){
	    this.xtr=val;
	}
	public void setId(){
		if(this.i==0L){
			this.i=key.getId();
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
	public void seti135(I135 i135){
	    this.i135=i135;
	}
	public void seti136(I136 i136){
	    this.i136=i136;
	}
	public void seti138(I138 i138){
	    this.i138=i138;
	}
	public void seti139(I139 i139){
	    this.i139=i139;
	}
}