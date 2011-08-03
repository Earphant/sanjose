package sanjose;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class I12{
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private Key key;
	@Persistent
	private Long i;
	@Persistent
	private Long j;
	@Persistent
	private Blob img;


	public I12(I i,Blob img){
		this.i=i.geti();
		this.j=i.getj();
		this.img=img;
	}
	public Long geti(){
	    return i;
	}
	public Long getj(){
	    return j;
	}
	public Blob getimg(){
	    return img;
	}

}