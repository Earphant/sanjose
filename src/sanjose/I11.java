package sanjose;

//import java.util.Date;
//import com.google.appengine.api.datastore.Text;
//import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class I11{
	@Persistent
	private Long i;
	@Persistent
	private Long j;
	@PrimaryKey
	private String eml;
	@Persistent
	private String pwd;

	public I11(Long i,Long j,String eml){
		this.i=(long) 0;
		this.j=(long) 9;
		this.eml=eml;
		this.pwd="";
	}
	public Long geti(){
	    return i;
	}
	public Long getj(){
	    return j;
	}
	public String geteml(){
	    return eml;
	}
	public String getpwd(){
	    return pwd;
	}
	public void seteml(String x){
	    eml=x;
	}
	public void setpwd(String x){
	    pwd=x;
	}
}