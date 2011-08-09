package sanjose;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class I11{
	@Persistent
	private Long i;
	@Persistent
	private Long j;
	@PrimaryKey
	private String eml;
	@Persistent
	private String pwd;

	public I11(I i,String eml){
		this.i=i.getId();
		this.j=i.getSite();
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