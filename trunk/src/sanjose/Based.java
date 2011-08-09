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
		page.aside="<ul><li><a href=/post>Post</a></ul><ul><li><a href=/system/settings>Settings</a><li><a href=/"+currentbase+"/profile>Profile</a><li><a href=/"+currentbase+"/contacts>Contacts</a><li><a href=/"+currentbase+"/tags>Tags</a></ul><ul><li><a href=/"+currentbase+"/dashboard>Dashboard</a><li><a href=/"+currentbase+"/activities>Activities</a><li><a href=/"+currentbase+"/historical>Historical</a></ul><ul><li><a href=/"+currentbase+"/weight>Weight</a><li><a href=/"+currentbase+"/heartrate>Heart Rate</a><li><a href=/"+currentbase+"/steps>Steps</a><li><a href=/"+currentbase+"/fat>Fat</a></ul>";		PersistenceManager mgr=Helper.getMgr();
						
		Session currentowner=new Session("ow");
		Long ci=currentowner.id;
		Long cs=currentowner.site;
		String ss=req.getPathInfo();
		String[] s2=ss.split("/");
		String[] s=s2[1].split("\\.");
		Long o=0L;Long w=0L;
        int n=s.length;
		if(n>1){
			o=Long.parseLong(s[0]);
			w=Long.parseLong(s[1]);
		}

		if(ci!=o){	
			
			PersistenceManager mgr21=Helper.getMgr();	
			Query q21=mgr21.newQuery(I21.class);		
			q21.setOrdering("i desc");
			q21.setFilter("o==oParam && w==wParam && i==iParam");	
			q21.declareParameters("Long oParam,Long wParam,Long iParam");
	     
			try{
				@SuppressWarnings("unchecked")
				List<I21> r=(List<I21>)q21.execute(ci,cs,o);
			
				if(!r.isEmpty()){
					page.Out("已关注<br>");
				}
				else{
					page.Out("<form method=post action=/post/friends><input type=hidden name=i value="+o+"."+w+"><input type=submit name=ok value=关注此人></form>");		    
				}
			}
			finally{
				q21.closeAll();
			}
			    
			
			


			
		}
				
		PersistenceManager mgr1=Helper.getMgr();	
		Query q=mgr1.newQuery(I.class);		
		q.setOrdering("m desc");
		q.setFilter("o==oParam && w==wParam ");	
		q.declareImports("import java.util.Date");
		q.declareParameters("Long oParam,Long wParam");
     
		try{
			@SuppressWarnings("unchecked")
			List<I> r=(List<I>)q.execute(o,w);
		
			if(!r.isEmpty()){
				for(I i:r){
					String d=i.geti()+"."+i.getj();
					String x=i.getx();
					String base=i.geto()+"."+i.getw();
				
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