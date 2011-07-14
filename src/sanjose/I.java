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
	private Long s;
	@Persistent
	private Long o;
	@Persistent
	private Long w;
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

	public I(String x,String p,long a,long i,long j){
		Date c=new Date();
		this.key=KeyFactory.createKey(I.class.getSimpleName(),i+"."+j);
		this.i=i;
		this.j=j;
		this.a=a;
		this.b=1L;
		this.s=1L;
		this.o=1L;
		this.w=1L;
		this.c=c;
		this.m=c;
		this.t=c;
		this.p=p;
		this.x=x;
		this.e=new Text("");
	}
	public I(String x,String p,long a){
		Date c=new Date();
		this.i=null;
		this.i=0L;
		this.j=9L;
		this.a=a;
		this.b=1L;
		this.s=1L;
		this.o=1L;
		this.w=1L;
		this.c=c;
		this.m=c;
		this.t=c;
		this.p=p;
		this.x=x;
		this.e=new Text("");
	}
	public long geta(){
	    return a;
	}
	public long getb(){
	    return b;
	}
	public String gete(){
	    return e.getValue();
	}
	public long geti(){
	    return i==0L?key.getId():i;
	}
	public long getj(){
	    return j;
	}
	public long geto(){
	    return o;
	}
	public String getp(){
	    return p;
	}
	public long gets(){
	    return s;
	}
	public long getw(){
	    return w;
	}
	public String getx(){
	    return x;
	}
	public void setx(String x){
	    this.x=x;
	}
	public void sete(String e){
		this.e=new Text(e);
	}
}