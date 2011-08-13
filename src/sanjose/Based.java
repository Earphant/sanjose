package sanjose;

import java.io.IOException;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Based{
	private void Index(String plink,Page page,HttpServletRequest req)throws IOException{
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
	private void Object(String id,String base,HttpServletResponse rsp,Page page)
	    throws IOException{
		//Id b=new Id(base);
		Id d=new Id(id);
		if(d.IsPicture()){
			new Picture().Regular(d,rsp);
		}
		else{
			page.title=id;
			PersistenceManager mgr=Helper.getMgr();
			Query q=mgr.newQuery(I.class);
			q.setFilter("i==iParam && j==jParam");
			q.declareParameters("Long iParam,Long jParam");
			try{
				@SuppressWarnings("unchecked")
				List<I> r=(List<I>)q.execute(d.i,d.j);
				if(!r.isEmpty()){
					I i=r.get(0);
					if(i.getType()==12)
						page.out("<a href=/originals/"+id+".jpg title=test><img src=/"+base+"/"+id+".jpg></a>");
					page.out(i.getTitle());
				}
			}
			finally{
				q.closeAll();
			}
			page.end(null);
		}
	}

	public Based(String plink,HttpServletResponse rsp,HttpServletRequest req)throws IOException{
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
			Object(n,s[1],rsp,p);
			return;
		}
		Index(plink,p,req);
	}
}