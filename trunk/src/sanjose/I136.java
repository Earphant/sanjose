package sanjose;

//import java.util.Date;
//import com.google.appengine.api.datastore.Text;
import java.util.Date;


import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class I136{
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private Long key ;
	@Persistent
	private Long n;
	@Persistent
	private Long o;
	@Persistent
	@SuppressWarnings("unused")
	private Date t;
	@Persistent
	private Long vol;


	public I136(long n,long o,long vol){
		Date c=new Date();	
		this.n=1L;
		this.o=9L;
		this.t=c;
		this.vol=vol;
	}
	
	public long getn(){
	    return n;
	}
	public long geto(){
	    return o;
	}
	public long getvol(){
	    return vol;
	}
}
