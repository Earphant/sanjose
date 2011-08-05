package sanjose;

//import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Blob;
//import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class I12{
	@SuppressWarnings("unused")
	@PrimaryKey
	private String key;
	@Persistent
	private Long i;
	@Persistent
	private Long j;
	@Persistent
	private String ext;
	@Persistent
	private Blob dat;

	public I12(I i,String ext,Blob dat){
		this.i=i.geti();
		this.j=i.getj();
		this.key=i+"."+j;
		this.ext=ext;
		this.dat=dat;
	}
	public Long geti(){
	    return i;
	}
	public Long getj(){
	    return j;
	}
	public String getext(){
	    return ext;
	}
	public Blob getdat(){
	    return dat;
	}

}