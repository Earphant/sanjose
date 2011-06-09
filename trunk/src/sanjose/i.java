package sanjose;

import java.util.Date;

import com.google.appengine.api.datastore.Text;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class i{
	@Persistent
	private int i;
	@Persistent
	private int j;
	@Persistent
	private int b;
	@Persistent
	private int s;
	@Persistent
	private int o;
	@Persistent
	private int w;
	@SuppressWarnings("unused")
	@Persistent
    private Date m;
	@SuppressWarnings("unused")
	@Persistent
    private Date t;
	@Persistent
	private String p;
	@PrimaryKey
	private String x;
	@Persistent
	private Text e;

	public i(String p,String x,int i){
//		String s[]=v.split(",");
//		double r=Double.parseDouble(s[7]);

//		this.b	=p.substring(0,2).equals("sz")?"she":"sha";
//		this.chg=r==0?0.0:(Double.parseDouble(s[4])-r)/r;
		this.i=i;
		this.p	=p;
//		this.val=new Text(v);
		this.x	=x;
//		this.y	=0;
	}
	public int getb(){
	    return b;
	}
	public String gete(){
	    return e.getValue();
	}
	public int geti(){
	    return i;
	}
	public int getj(){
	    return j;
	}
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
	public String gettxt(){
	    return x;
	}
	public void seti(int g){
		i=g;
	}
	public void settxt(String s){
	    x=s;
	}
	public void sete(String v){
		e=new Text(v);
	}
}