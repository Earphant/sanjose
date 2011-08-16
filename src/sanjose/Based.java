package sanjose;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Based{
	private static final Logger log = Logger.getLogger(Based.class.getName());

	private void Index(I id,Page page,Session ssn)throws IOException{
		PersistenceManager m=Helper.getMgr();
		I d=I.query(id,m);
		if(d.getType()==1)
			new Individual().out(d,page,m,ssn);
		else
			new Organization().out(d,page,m,ssn);		
	}
	private void Index(String plink,Page page,HttpServletRequest req)
		throws IOException{
		String[]current=plink.split("/");
		String currentbase=current[1];
	    page.title=plink;	
		Session sn=new Session("ow");
		long ci=sn.owner.getId();
		long cs=sn.owner.getSite();
		String p=req.getPathInfo();
		String[] s=p.split("/");
		Id ow=new Id(s[1]);
		PersistenceManager m=Helper.getMgr();
		if(ci==ow.i && cs==ow.j){
			page.aside="<ul><li><a href=/post>Post</a></ul><ul><li><a href=/system/settings>Settings</a><li><a href=/"+currentbase+"/profile>Profile</a><li><a href=/"+currentbase+"/contacts>Contacts</a><li><a href=/"+currentbase+"/tags>Tags</a></ul><ul><li><a href=/"+currentbase+"/dashboard>Dashboard</a><li><a href=/"+currentbase+"/activities>Activities</a><li><a href=/"+currentbase+"/historical>Historical</a></ul><ul><li><a href=/"+currentbase+"/weight>Weight</a><li><a href=/"+currentbase+"/heart-rate>Heart Rate</a><li><a href=/"+currentbase+"/steps>Steps</a><li><a href=/"+currentbase+"/fat>Fat</a></ul>";
		}
		else{
			Query q=m.newQuery(I21.class);
			q.setFilter("o=="+ci+" && w=="+cs+" && i=="+ow.i+" && j=="+ow.j);
			try{
				@SuppressWarnings("unchecked")
				List<I21> r=(List<I21>)q.execute();
				page.aside=r.isEmpty()?"<ul><li><a href=/system/follow?i="+ow.i+"."+ow.j+">Follow</a></ul><ul><li><a href=/"+currentbase+"/profile>Profile</a><li><a href=/"+currentbase+"/contacts>Contacts</a><li><a href=/"+currentbase+"/tags>Tags</a></ul><ul><li><a href=/"+currentbase+"/dashboard>Dashboard</a><li><a href=/"+currentbase+"/activities>Activities</a><li><a href=/"+currentbase+"/historical>Historical</a></ul><ul><li><a href=/"+currentbase+"/weight>Weight</a><li><a href=/"+currentbase+"/heart-rate>Heart Rate</a><li><a href=/"+currentbase+"/steps>Steps</a><li><a href=/"+currentbase+"/fat>Fat</a></ul>":
					"<ul><li><a href=/system/unfollow?i="+ow.i+"."+ow.j+">Unfollow</a></ul><ul><li><a href=/"+currentbase+"/profile>Profile</a><li><a href=/"+currentbase+"/contacts>Contacts</a><li><a href=/"+currentbase+"/tags>Tags</a></ul><ul><li><a href=/"+currentbase+"/dashboard>Dashboard</a><li><a href=/"+currentbase+"/activities>Activities</a><li><a href=/"+currentbase+"/historical>Historical</a></ul><ul><li><a href=/"+currentbase+"/weight>Weight</a><li><a href=/"+currentbase+"/heart-rate>Heart Rate</a><li><a href=/"+currentbase+"/steps>Steps</a><li><a href=/"+currentbase+"/fat>Fat</a></ul>";
			}
			finally{
				q.closeAll();
			}
		}
		//PersistenceManager mgr1=Helper.getMgr();	
		Query q=m.newQuery(I.class);		
		q.setOrdering("m desc");
		q.setFilter("o==oParam && w==wParam ");	
		q.declareImports("import java.util.Date");
		q.declareParameters("Long oParam,Long wParam");
     
		try{
			@SuppressWarnings("unchecked")
			List<I> r=(List<I>)q.execute(ow.i,ow.j);
		
			if(!r.isEmpty()){
				for(I i:r){
					String d=i.getId()+"."+i.getSite();
					String t=i.getTitle();
					String base=i.getOwnerId()+"."+i.getOwnerSite();
				
					if(t==null || t.equals(""))
						t="<i>(Untitled)</i>";
					if(i.getType()==12){
					 
						page.out("<a href=/"+base+"/"+d+"><img src=/thumbnails/"+d+".jpg></a>");
					}
					else
						page.out(t);
					page.out(" <a href=/post?i="+d+">=</a><br>");
				}
			}
		}
		finally{
			q.closeAll();
		}
		page.end(null);
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
			page.title=d.getTitle();
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