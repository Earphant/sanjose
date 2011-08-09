package sanjose;

import com.google.appengine.api.datastore.Blob;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class I12{
	@SuppressWarnings("unused")
	@PrimaryKey
	private String _key;
	@Persistent
	private Long i;
	@Persistent
	private Long j;
	@Persistent
	private String ext;
	@Persistent
	private Blob org;
	@Persistent
	private Blob reg;
	@Persistent
	private Blob thm;
	@Persistent
	private Blob ico;

	public I12(I i,String ext,Blob org){
		this.i=i.geti();
		this.j=i.getj();
		this.ext=ext;
		this.org=org;
		this._key=this.i+"."+this.j;
	}
	public I12(Long i,Long j,String ext,Blob ico){
		this.i=i;
		this.j=j;
		this.ext=ext;
		this.ico=ico;
		this._key=this.i+"."+this.j;
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
	public Blob getorg(){
	    return org;
	}
	public Blob getico(){
	    return ico;
	}
	public Blob getreg(){
	    return reg;
	}
	public Blob getthm(){
	    return thm;
	}
	public void setreg(Blob reg){
		this.reg=reg;
	}
	public void setthm(Blob thm){
		this.thm=thm;
	}
	public void setico(Blob ico){
		this.ico=ico;
	}
}