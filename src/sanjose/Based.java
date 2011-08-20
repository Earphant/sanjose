package sanjose;

import java.io.IOException;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Based{
	private static final Logger log = Logger.getLogger(Based.class.getName());

	private void Index(I id,Page page,Session ssn)throws IOException{
		PersistenceManager m=Helper.getMgr();
		I d=I.query(id,m);
		if(d.getType()==1)
			Individual.out(d,page,m,ssn);
		else
			Organization.out(d,page,m,ssn);		
	}
	private void Object(I id,String base,HttpServletResponse rsp,Page page)
	    throws IOException{
		log.warning("Object");
		if(id.isPicture()){
			new Picture().Regular(id,rsp);
		}
		else{
			PersistenceManager m=Helper.getMgr();
			I d=I.query(id,m);
			page.title=d.getTitle(true);
			page.aside="<ul><li><a href=/post?i="+d+">Edit</a><li><a href=/post/mark?re="+d+">Mark</a></ul>";
			if(d.getType()==12)
				page.out("<a href=/originals/"+id+".jpg title=test><img src=/"+base+"/"+id+".jpg></a>");
			page.out("<form method=post action=/post?re="+d+">");
			page.out("<textarea name=text rows=5></textarea>");
			page.out("<input type=submit name=ok value=Reply></form>");
			page.end(null);
		}
	}

	public Based(HttpServletResponse rsp,HttpServletRequest req,String plink,
		Session ssn)throws IOException{
		log.warning("Based");
		String[]s=plink.split("/");
		Page p=new Page(rsp);
		if(s.length>2){
			String n=s[2];
			if(n.equalsIgnoreCase("profile")){
				new Profile().out(plink,p);
				return;
			}
			if(n.equalsIgnoreCase("dashboard")){
				new Dashboard().out(plink,p);
				return;
			}
			if(n.equalsIgnoreCase("contacts")){
				new Contacts().out(plink,p);
				return;
			}
			if(n.equalsIgnoreCase("tags")){
				new Tags().out(plink,p);
				return;
			}
			if(n.equalsIgnoreCase("steps")){
				new Steps().out(plink,p);
				return;
			}
			if(n.equalsIgnoreCase("weight")){
				new Weight().out(plink,p);
				return;
			}
			if(n.equalsIgnoreCase("heart-rate")){
				new HeartRate().out(plink,p);
				return;
			}
			if(n.equalsIgnoreCase("fat")){
				new Fat().out(plink,p);
				return;
			}
			Object(new I(n),s[1],rsp,p);
			return;
		}
		log.warning(plink);
		Index(new I(s[1]),p,ssn);
	}
}