package sanjose;

//import java.util.Date;
//import com.google.appengine.api.datastore.Text;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class I8{
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private Long _key;
	@Persistent
	private Long i;
	@Persistent
	private Long j;
	@Persistent
	private String x;

	public I8(String x){
		this.i=(long) 0;
		this.j=(long) 9;
		this.x=x;
	}
/*
 	public int getb(){
	    return b;
	}
	public String gete(){
	    return e.getValue();
	}
	*/
	public Long geti(){
	    return i==0?_key:i;
	}
	public Long getj(){
	    return j;
	}
	/*
	public int geto(){
	    return o;
	}
	public String getp(){
	    return p;
	}
	public int gets(){
	    return s;
	}
	public int getw(){
	    return w;
	}
	*/
	public String getx(){
	    return x;
	}
	public void setx(String x){
	    this.x=x;
	}
}