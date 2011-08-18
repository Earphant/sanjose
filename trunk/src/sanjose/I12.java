package sanjose;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;

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

	public I12(I i,Blob org){
		this.i=i.getId();
		this.j=i.getSite();
		this.org=org;
		this._key=this.i+"."+this.j;
	}
	public I12(Long i,Long j,Blob ico,boolean b){
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
	public String getExtension(){
		return ext;
	}
	public Blob getOriginal(){
	    return org;
	}
	public Blob getIcon(){
	    return ico;
	}
	public Blob getRegular(){
	    return reg;
	}
	public Blob getThumbnail(){
	    return thm;
	}
	public void setExtension(String val){
		ext=val;
	}
	public void setPicture(){
		ImagesService isv=ImagesServiceFactory.getImagesService();
		Image o=ImagesServiceFactory.makeImage(org.getBytes());
		try{
			ext=o.getFormat().toString();
			Transform rez=ImagesServiceFactory.makeResize(500,500);
			Image img=isv.applyTransform(rez,o);
			reg=new Blob(img.getImageData());
			rez=ImagesServiceFactory.makeResize(96,96);
			img=isv.applyTransform(rez,o);
			thm=new Blob(img.getImageData());
			rez=ImagesServiceFactory.makeResize(48,48);
			img=isv.applyTransform(rez,o);
			ico=new Blob(img.getImageData());
		}
		catch(IllegalArgumentException e){
			e.printStackTrace();
		}
	}
	public void setRegular(Blob val){
		this.reg=val;
	}
	public void setThumbnail(Blob val){
		this.thm=val;
	}
	public void setIcon(Blob val){
		this.ico=val;
	}
}