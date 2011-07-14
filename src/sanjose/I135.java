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
public class I135{
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
	private Long fat;
	@Persistent
	private Long wat;


	public I135(long n,long o,long fat,long wat){
		Date c=new Date();	
		this.n=1L;
		this.o=9L;
		this.t=c;
		this.fat=fat;
		this.wat=wat;
	}
	
	public long getn(){
	    return n;
	}
	public long geto(){
	    return o;
	}
	public long getfat(){
	    return fat;
	}
	public long getwat(){
	    return wat;
	}
}
