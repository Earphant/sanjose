package sanjose;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

@PersistenceCapable
public class I1{
	@SuppressWarnings("unused")
	@PrimaryKey
	private String _key;
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
		this.i=i.getId();
		this.j=i.getSite();
		this._key=this.i+"."+this.j;
		this.fsn="";
		this.mdn="";
		this.lsn="";
		this.gnd="";
		this.t=null;
		this.ocp="";
		this.zip=0L;
		this.tel=0L;
		this.add="";
	}
	
	static public I1 query(I id,PersistenceManager mgr){
		I1 ret=null;
		Query q1=mgr.newQuery(I1.class);
		q1.setFilter("i==iParam && j==jParam");
		q1.declareParameters("Long iParam,Long jParam");
		try{
			@SuppressWarnings("unchecked")
			List<I1> r1=(List<I1>)q1.execute(id.getId(),id.getSite());
			if(!r1.isEmpty())
				ret=r1.get(0);
		}
		finally{
			q1.closeAll();
		}
		return ret;
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
