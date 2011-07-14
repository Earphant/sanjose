package sanjose;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;


@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class I138{
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private Long n;
	
	@Persistent
	private Long o;
	
	@Persistent
	private Date t;
	
	@Persistent
	private Long vol;
	
	public I138(Long n,Long o,Long vol){
		Date c=new Date();
		this.n=0L;
		this.o=3L;
		this.vol=vol;
		this.t=c;
    }
	
	public Long getn(){
	    return n==0L?key.getId():n;
	}
	
	public Long geto(){
		return o;
	}
	    
	public Date gett(){
		return t;
	}
	
	public Long getvol(){
		return vol;
	}
}