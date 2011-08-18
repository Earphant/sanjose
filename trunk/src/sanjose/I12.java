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
	private Blob org;
	@Persistent
	private Blob reg;
	@Persistent
	private Blob thm;
	@Persistent
	private Blob ico;

	public I12(I i,Blob org){
		this.i=i.getId();
		this.j=i.getSite();
		this.org=org;
		this._key=this.i+"."+this.j;
	}
	public I12(Long i,Long j,Blob ico){
		this.i=i;
		this.j=j;
		this.ico=ico;
		this._key=this.i+"."+this.j;
	}
	public Long geti(){
	    return i;
	}
	public Long getj(){
	    return j;
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