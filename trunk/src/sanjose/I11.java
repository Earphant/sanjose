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
	@Persistent
	private Long rnk;
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
	public Long getId(){
	    return i;
	}
	public Long getSite(){
	    return j;
	}
	public String getEmail(){
	    return eml;
	}
	public String getPassword(){
	    return pwd;
	}
	public Long getRank(){
	    return rnk;
	}
	public void setPassword(String val){
	    pwd=val;
	}
	public void setRank(long val){
	    rnk=val;
	}
}