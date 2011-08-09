package sanjose;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class I11{
	@SuppressWarnings("unused")
	@PrimaryKey
	private String _key;
	@Persistent
	private Long i;
	@Persistent
	private Long j;
	@PrimaryKey
	private String eml;
	@Persistent
	private String pwd;

	public I11(I i,String eml){
		this.i=i.geti();
		this.j=i.getj();
		this.eml=eml;
		this.pwd="";
		this._key=this.i+"."+this.j;
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