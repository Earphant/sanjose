package sanjose;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Text;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class I{
	private static final Logger log = Logger.getLogger(I.class.getName());
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
	private Long u;
	@Persistent
	private Long v;
	@Persistent
	private Long w;
	@Persistent
	private String x;
	@Persistent
	private Date y;
	@Persistent
	private String z;

	private void init(String text,String plink,long type,long rate,
		long ownerid,long ownersite){
		Date t=now();
		this.i=0L;
		this.j=9L;
		this.a=type;
		this.b=ownerid;
		this.s=ownersite;
		this.d=1L;
		this.h=1L;
		this.m=t;
		this.o=ownerid;
		this.w=ownersite;
		this.p=plink;
		this.r=rate;
		this.u=0L;
		this.v=1L;
		this.x=text;
		this.y=t;
	}
	private static Date now(){
		long t=new Date().getTime()/1000;
		return new Date(t*1000);
	}

	@SuppressWarnings("unchecked")
	static public long list(Object rs,Page page)throws IOException{
		long ret=0;
		if(!((List<I>)rs).isEmpty()){
			for(I o:(List<I>)rs){
				String b=o.getBase().toString();
				String i=o.toString();
				String t="<a href=/"+b+"/"+i+" class=c2 t="+o.getModifyTick()+
					"></a>";
				String w=o.getOwner().toString();
				String x=o.getTitle(true);
				switch((int)o.getType()){
				case 1:
				case 2:
					page.out("<div class=post><a href=/"+i+
						"><img class=icon src=/icons/"+i+
						"></a><div class=text><a href=/"+i+
						">"+x+"</a><div class=c2 t="+
						o.getModifyTick()+"></div></div></div>");
					break;
				case 12:
					page.out("<div class=post><a href=/"+w+"/"+i+"><img class=thmb src=/thumbnails/"+i+"></a><a href=/"+w+"/><img src=/icons/"+w+" class=icon></a><div class=text><a href=/"+w+"/"+i+">"+x+"</a><div class=c2 t="+o.getModifyTick()+"></div><a href=/post?re="+i+"&jmp=%2F>Re</a></div></div>");
					break;
				case 136:
					page.out("<div class=post><a href=/"+w+"><img src=/icons/"+w+" class=icon></a><div class=text><a href=/"+w+"/heart-rate>"+x+"</a><div class=grf1>"+o.getQuotation()+"</div><div class=c2 t="+o.getModifyTick()+"></div><a href=/post?re="+i+"&jmp=%2F>Re</a></div></div>");
					break;
				case 138:
					page.out("<div class=post><a href=/"+w+"><img src=/icons/"+w+" class=icon></a><div class=text><a href=/"+w+"/weight>"+x+"</a><div class=grf1>"+o.getQuotation()+"</div><div class=c2 t="+o.getModifyTick()+"></div><a href=/post?re="+i+"&jmp=%2F>Re</a></div></div>");
					break;
				case 139:
					page.out("<div class=post><a href=/"+w+"><img src=/icons/"+w+" class=icon></a><div class=text><a href=/"+w+"/steps>"+x+"</a><div class=grf1>"+o.getQuotation()+"</div><div class=c2 t="+o.getModifyTick()+"></div><a href=/post?re="+i+"&jmp=%2F>Re</a></div></div>");
					break;
				default:
					page.out("<div class=post><a href=/"+
						w+"><img src=/icons/"+
						w+" class=icon></a><div class=text>"+
						x+"<br>"+t+"</br><a href=/post?re="+
						i+"&jmp=%2F>Re</a></div></div>");
				}
				ret++;
			}
		}
		return ret;
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
	static public I store(String text,String plink,long type,long rate,
		I owner,PersistenceManager mgr,boolean end){
		I ret=new I(text,plink,type,rate,owner);
		mgr.makePersistent(ret);
		if(ret.i==0L){
			ret.i=ret._key.getId();
			ret.c=ret.m;
			ret.t=ret.m;
			if(end)
				mgr.makePersistent(ret);
		}
		return ret;
	}
	@SuppressWarnings("unchecked")
	static public void table(Object rs,Page page)throws IOException{
		if(!((List<I>)rs).isEmpty()){
			page.out("<table class=list>");
			for(I i:(List<I>)rs){
				I b=i.getBase();
				page.out("<tr><th width=40%><a href=/"+i.getPath()+">"+
					i.getTitle(true)+"</a><th><a href=/"+b+">"+b+
					"</a><th>"+i.getOwner()+"<td>"+i.getReplyString()+
					"<td class=c2 t="+i.getReplyTick()+">");
			}
			page.out("</table>");
		}
	}
	@SuppressWarnings("unchecked")
	static public void tableEx(Object rs,Page page)throws IOException{
		if(!((List<I>)rs).isEmpty()){
			page.out("<table class=list>");
			for(I o:(List<I>)rs){
				page.out("<tr><th width=40%><a href=/"+o.getPath()+">"+o.getTitle(true)+
					"</a><th><a href=/post?i="+o+"&jmp=>"+o.getType()+
					"</a><th>"+o.getOwner()+"<td class=c2 t="+
					o.getModifyTick()+"><td><a href=/admins?i="+o+
					">=</a>");
			}
			page.out("</table>");
		}
	}
	static public I timed(String val){
		if(val==null)
			return null;
		I i=new I(val);
		i.o=i.i;
		i.w=i.j;
		i.m=i.e==null?now():new Date(Long.parseLong(i.e)*1000);
		i.t=i.m;
		return i;
	}

	public I(long id,long site,String text,String plink,long classid,long rate,
		long ownerid,long ownersite){
		init(text,plink,classid,rate,ownerid,ownersite);
		this.i=id;
		this.j=site;
		this._key=KeyFactory.createKey(I.class.getSimpleName(),id+"."+site);
	}
	public I(String text,String plink,long classid,long rate,I owner){
		init(text,plink,classid,rate,owner.i,owner.j);
	}
	public I(long id,long site){
		this.i=id;
		this.j=site;
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
			this.e=t[2];
		this.m=now();
		this.t=now();
	}
	public boolean equals(I i){
		return this.i.equals(i.i)&&this.j.equals(i.j);
	}
	public long getAccessTick(){
	    return c.getTime()/1000;
	}
	public I getBase(){
	    return new I(b,s);
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
	public String getPath(){
		if(a==1 || a==2)
			return i+"."+j;
	    return b+"."+s+"/"+(p==null?i+"."+j:p);
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
	public I getRef(){
	    return new I(d,h);
	}
	public long getReplyCount(){
	    return u;
	}
	public String getReplyString(){
	    return u==0?"":u.toString();
	}
	public long getReplyTick(){
	    return y.getTime()/1000;
	}
	public long getSite(){
	    return j;
	}
	public String getTimed(){
		return i+"."+j+"."+t.getTime()/1000;
	}
	public String getTitle(boolean html){
		return x.equals("")?(html?"<i>(untitled)</i>":"(untitled)"):x;
	}
	public String getText(){
	    return x;
	}
	public long getType(){
	    return a;
	}
	public long getVersion(){
	    return v;
	}
	public long incReplyCounter(){
		return ++u;
	}
	public boolean isPicture(){
		if(e==null)
			return false;
		return e.equalsIgnoreCase("jpg");
	}
	public void setBase(I base,I owner){
		if(base==null || base.j==0)
			base=owner;
		if(base.j!=0){
		    this.b=base.i;
		    this.s=base.j;
		}
	}
	public void setExtra(String val){
	    this.z=val;
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
	public void setRef(I val){
		if(val.j!=0){
			log.warning("ref: "+val);
			this.d=val.i;
			this.h=val.j;
		}
		log.warning(this+"/"+val);
	}
	public void setReplyCount(long val){
	    u=val;
	}
	public void setReplyTime(Date val){
		this.y=val==null?now():val;
	}	
	public void setText(String val){
	    this.x=val;
	}
	public String toString(){
		return i+"."+j;
	}
}