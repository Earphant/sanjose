package sanjose;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
//import com.google.appengine.api.datastore.Key;
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class I139 {
	@PrimaryKey
	@SuppressWarnings("unused")
	private String key;
	
	@Persistent
	private Long n;
	
	@Persistent
	private Long o;
	
	@Persistent
	private Date t;
	
	@Persistent
	private Long vol;
	
	public I139(Long n,Long o,Long vol){
		Long t=new Date().getTime()/1000;
		this.key=n+"."+o+"."+t;
		this.n=n;
		this.o=o;
		this.t=new Date(t*1000);
		this.vol=vol;
    }
	
	public Long getn(){
	    return n;
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
	public void setvol(long vol){
		this.vol=vol;
	}
}

