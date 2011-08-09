package sanjose;

import java.io.IOException;
//import java.util.Date;
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
		page.aside="<ul><li><a href=/post/weight>Post</a></ul><ul><li><a href=/system/settings>Settings</a><li><a href=/"+currentbase+"/profile>Profile</a><li><a href=/"+currentbase+"/contacts>Contacts</a><li><a href=/"+currentbase+"/tags>Tags</a></ul><ul><li><a href=/"+currentbase+"/dashboard>Dashboard</a><li><a href=/"+currentbase+"/activities>Activities</a><li><a href=/"+currentbase+"/historical>Historical</a></ul><ul><li><a href=/"+currentbase+"/weight>Weight</a><li><a href=/"+currentbase+"/heartrate>Heart Rate</a><li><a href=/"+currentbase+"/steps>Steps</a><li><a href=/"+currentbase+"/fat>Fat</a></ul>";		PersistenceManager mgr=Helper.getMgr();
		Query q=mgr.newQuery(I.class);		
		q.setOrdering("m desc");
		String ss=req.getPathInfo();
		ss=ss.substring(1);
		
		String[]s=ss.split("\\.");
		Long o=0l;Long w=0l;
       
		int n=s.length;
		if(n>0){
			o=Long.parseLong(s[0]);
			}
		 String[]sss=s[1].split("/");
		 w=Long.parseLong(sss[0]);
		 q.setFilter("w==wParam && o==oParam ");	
			q.declareImports("import java.util.Date");
			q.declareParameters("Long wParam,Long oParam");
			
       
		try{
			@SuppressWarnings("unchecked")
			List<I> r=(List<I>)q.execute(w,o);
			
		
			
			
			if(!r.isEmpty()){
				for(I i:r){
					
				  
					String d=i.geti()+"."+i.getj();
					String x=i.getx();
					String base=i.getb()+"."+i.gets();
				
					if(x==null || x.equals(""))
						x="<i>(Untitled)</i>";
					if(i.geta()==12){
					    page.Out("a"+o+"aa"+w+"aa");
						page.Out("<a href=/"+base+"/"+d+"><img src=/thumbnails/"+d+".jpg></a>");
					}
					else
						page.Out(x);
					page.Out(" <a href=/post?i="+d+">=</a><br>");
				}
			}
		}
		finally{
			q.closeAll();
		}
		page.End(null);
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
					if(i.geta()==12)
						page.Out("<a href=/originals/"+id+".jpg title=test><img src=/"+base+"/"+id+".jpg></a>");
					page.Out(i.getx());
				}
			}
			finally{
				q.closeAll();
			}
			page.End(null);
		}
	}

	public Based(String plink,HttpServletResponse rsp,HttpServletRequest req)throws IOException{
		String[]s=plink.split("/");
		Page p=new Page(rsp);
		if(s.length>2){
			String n=s[2];
			if(n.equalsIgnoreCase("profile")){
				new Profile().Out(plink,p);
				return;
			}
			if(n.equalsIgnoreCase("dashboard")){
				new Dashboard().Out(plink,p);
				return;
			}
			if(n.equalsIgnoreCase("contacts")){
				new Contacts().Out(plink,p);
				return;
			}
			if(n.equalsIgnoreCase("tags")){
				new Tags().Out(plink,p);
				return;
			}
			if(n.equalsIgnoreCase("steps")){
				new Steps().Out(plink,p);
				return;
			}
			if(n.equalsIgnoreCase("weight")){
				new Weight().Out(plink,p);
				return;
			}
			if(n.equalsIgnoreCase("heartrate")){
				new HeartRate().Out(plink,p);
				return;
			}
			if(n.equalsIgnoreCase("fat")){
				new Fat().Out(plink,p);
				return;
			}
			Object(n,s[1],rsp,p);
			return;
		}
		Index(plink,p,req);
	}
}