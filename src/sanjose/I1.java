package sanjose;

import java.util.Date;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class I1{
	@SuppressWarnings("unused")
	@PrimaryKey
	private String key;
	@Persistent
	private Long i;
	@Persistent
	private Long j;
	@SuppressWarnings("unused")
	@Persistent
	private Long v;
	@Persistent
	private String fsn;
	@Persistent
	private String mdn;
	@Persistent
	private String lsn;
	@Persistent
	private String gnd;
	@Persistent
	private Date t;
	@Persistent
	private String ocp;	
	@Persistent
	private Long zip;
	@Persistent
	private Long tel;
	@Persistent
	private String add;

	public I1(I i){
		this.key=i+"."+j;
		this.i=i.geti();
		this.j=i.getj();
	}
	public I1(I i,String fsn,String mdn,String lsn,String gnd,Date t,String ocp,Long zip,Long tel,String add){
		this.i=i.geti();
		this.j=i.getj();
		this.fsn=fsn;
		this.mdn=mdn;
		this.lsn=lsn;
		this.gnd=gnd;
		this.t=t;
		this.ocp=ocp;
		this.zip=zip;
		this.tel=tel;
		this.add=add;
	}
	
	public Long geti(){
	    return i;
	}
	public Long getj(){
	    return j;
	}
    public String getfsn(){
    	return fsn;
    }
    public String getlsn(){
    	return lsn;
    }
    public String getmdn(){
    	return mdn;
    }
    public String getgnd(){
    	return gnd;
    }
    public Date gett(){
    	return t;
    }
    public String getocp(){
    	return ocp;
    }
    public String getadd(){
    	return add;
    }
    public Long getzip(){
    	return zip;
    }
    public Long gettel(){
    	return tel;
    }
    public void setfsn(String fsn){
	    this.fsn=fsn;
    }
    public void setmdn(String mdn){
	    this.mdn=mdn;
    }
    public void setlsn(String lsn){
	    this.lsn=lsn;
    }
    public void setgnd(String gnd){
	    this.gnd=gnd;
    }
    public void sett(Date t){
	    this.t=t;
    }
    public void setocp(String ocp){
	    this.ocp=ocp;
    }
    public void setzip(Long zip){
	    this.zip=zip;
    }
    public void settel(Long tel){
	    this.tel=tel;
    }
    public void setadd(String add){
	    this.add=add;
    }
}
