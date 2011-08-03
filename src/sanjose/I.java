package sanjose;

import java.util.Date;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Text;

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
	private Long n;
	@Persistent
	private Long o;
	@SuppressWarnings("unused")
	@Persistent
	private Long r;
	@Persistent
	private Long s;
	@SuppressWarnings("unused")
	@Persistent
    private Date c;
	@SuppressWarnings("unused")
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
	private Text e;

	public I(Long i,Long j,String x,String p,Long a,Long r,Long n,Long o){
		Date c=new Date();
		this.key=KeyFactory.createKey(I.class.getSimpleName(),i+"."+j);
		this.i=i;
		this.j=j;
		this.a=a;
		this.b=1L;
		this.s=1L;
		this.n=n;
		this.o=o;
		this.c=c;
		this.m=c;
		this.r=r;
		this.t=c;
		this.p=p;
		this.x=x;
		this.e=new Text("");
	}
	public I(String x,String p,Long a,Long r,Long n,Long o){
		Date c=new Date();
		this.i=0L;
		this.j=9L;
		this.a=a;
		this.b=1L;
		this.c=c;
		this.m=c;
		this.n=n;
		this.o=o;
		this.p=p;
		this.r=r;
		this.s=1L;
		this.t=c;
		this.x=x;
		this.e=new Text("");
	}
	public Long geta(){
	    return a;
	}
	public Long getb(){
	    return b;
	}
	public String gete(){
	    return e.getValue();
	}
	public Long geti(){
	    return i;
	}
	public Long getj(){
	    return j;
	}
	public Long getn(){
	    return n;
	}
	public Long geto(){
	    return o;
	}
	public String getp(){
	    return p;
	}
	public Long gets(){
	    return s;
	}
	public String getx(){
	    return x;
	}
	public void seti(){
		if(this.i==0L)
			this.i=key.getId();
	}
	public void setx(String x){
	    this.x=x;
	}
	public void sete(String e){
		this.e=new Text(e);
	}
}