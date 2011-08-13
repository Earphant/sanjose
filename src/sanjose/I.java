package sanjose;

import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Text;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
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

	private void init(String text,String plink,long classid,long rate,
		long ownerid,long ownersite){
		Date t=now();
		this.i=0L;
		this.j=9L;
		this.a=classid;
		this.b=ownerid;
		this.d=1L;
		this.h=1L;
		this.m=t;
		this.o=ownerid;
		this.w=ownersite;
		this.p=plink;
		this.r=rate;
		this.s=ownersite;
		this.x=text;
	}
	private static Date now(){
		long t=new Date().getTime()/1000;
		return new Date(t*1000);
	}

	static public I query(I id,PersistenceManager mgr){
		I ret=null;
		Query q=mgr.newQuery(I.class);
		q.setFilter("i==iParam && j==jParam");
		q.declareParameters("Long iParam,Long jParam");
		try{
			@SuppressWarnings("unchecked")
			List<I> r=(List<I>)q.execute(id.i,id.j);
			if(!r.isEmpty())
				ret=r.get(0);
		}
		finally{
			q.closeAll();
		}
		return ret;
	}
	static public I timed(String val){
		if(val==null)
			return null;
		I i=new I(val);
		i.o=i.i;
		i.w=i.j;
		i.m=i.z==null?now():new Date(Long.parseLong(i.z)*1000);
		i.t=i.m;
		return i;
	}

	public I(String path){
		if(path==null){
			this.i=0L;
			this.j=0L;
			return;
		}
		String[]t;
		if(path.charAt(0)=='/'){
			String[]s=path.split("/");
			t=s[1].split("\\.");
			this.o=Long.parseLong(t[0]);
			this.w=Long.parseLong(t[1]);
			if(s.length<2)
				return;
			t=s[2].split("\\.");
		}
		else
			t=path.split("\\.");
		this.i=Long.parseLong(t[0]);
		this.j=Long.parseLong(t[1]);
		if(t.length>2)
			this.z=t[2];
		this.m=now();
		this.t=now();
	}
	public I(long id,long site,String text,String plink,long classid,long rate,
		long ownerid,long ownersite){
		init(text,plink,classid,rate,ownerid,ownersite);
		this.i=id;
		this.j=site;
		this._key=KeyFactory.createKey(I.class.getSimpleName(),id+"."+site);
	}
	public I(long id,long site){
		this.i=id;
		this.j=site;
	}
	public I(String text,String plink,long classid,long rate,long ownerid,
		long ownersite){
		init(text,plink,classid,rate,ownerid,ownersite);
	}
	public I(String text,String plink,long classid,long rate,I owner){
		init(text,plink,classid,rate,owner.getId(),owner.getSite());
	}
	public I(String text,String plink,long classid,long rate,I owner,
		PersistenceManager mgr){
		init(text,plink,classid,rate,owner.getId(),owner.getSite());
		mgr.makePersistent(this);
		this.i=_key.getId();
		this.c=this.m;
		this.t=this.m;
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
	public long getCreateTick(){
	    return t.getTime()/1000;
	}
	public Date getCreateTime(){
	    return t;
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
	public I getOwner(){
	    return new I(o,w);
	}
	public long getOwnerId(){
	    return o;
	}
	public long getOwnerSite(){
	    return w;
	}
	public String getPath(){
		if(a==1)
			return i+"."+j;
	    return o+"."+w+"/"+(p==null?i+"."+j:p);
	}
	public String getPlink(){
	    return p;
	}
	public String getQuotation(){
	    return q==null?"":q.getValue();
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
	public String getTimed(){
		return i+"."+j+"."+t.getTime()/1000;
	}
	public String getTitle(){
		return x.equals("")?"<i>(untitled)</i>":x;
	}
	public String getText(){
	    return x;
	}
	public long getType(){
	    return a;
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
		this.m=val==null?now():val;
	}
	public void setPlink(String val){
	    this.p=val;
	}
	public void setQuotation(String val){
		this.q=new Text(val);
	}
	public void setText(String val){
	    this.x=val;
	}
	public String toString(){
		return i+"."+j;
	}
}