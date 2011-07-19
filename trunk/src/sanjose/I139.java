package sanjose;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class I139 {
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private Key key ;
	
	@Persistent
	private Long n;
	
	@Persistent
	private Long o;
	
	@Persistent
	private Date t;
	
	@Persistent
	private Long vol;


	public I139(long n,long o,long vol){
		Date c=new Date();	
		this.n=0L;
		this.o=9L;
		this.t=c;
		this.vol=vol;
	}
	
	public long getn(){
		return n==0L?key.getId():n;
	}
	public long geto(){
	    return o;
	}
	public Date gett(){
		return t;
	}
	public long getvol(){
	    return vol;
	}
}



