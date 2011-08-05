package sanjose;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletResponse;

public class Based{
	private void Index(String plink,Page page)throws IOException{
		page.title=plink;
		page.aside="<ul><li><a href=/post>Post</a></ul><ul><li><a href=/system/settings>Settings</a><li><a href=/12.3/profile>Profile</a><li><a href=/12.3/contacts>Contacts</a><li><a href=/12.3/tags>Tags</a></ul><ul><li><a href=/12.3/dashboard>Dashboard</a><li><a href=/12.3/activities>Activities</a><li><a href=/12.3/historical>Historical</a></ul><ul><li><li><a href=/12.3/weight>Weight</a><li><a href=/12.3/heartrate>Heart Rate</a><li><a href=/12.3/steps>Steps</a><li><a href=/12.3/fat>Fat</a></ul>";
		PersistenceManager mgr=Helper.getMgr();
		Query q=mgr.newQuery(I.class);
		//q.setFilter("a==0");
		q.setOrdering("m desc");
		try{
			@SuppressWarnings("unchecked")
			List<I> r=(List<I>)q.execute();
			if(!r.isEmpty()){
				for(I i:r){
					String d=i.geti()+"."+i.getj();
					String x=i.getx();
					if(x==null || x.equals(""))
						x="<i>(Untitled)</i>";
					if(i.geta()==12)
						page.Out("<a href=/1.1/"+d+">"+x+"</a>");
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
	private void Object(String id,String base,HttpServletResponse rsp,
		Page page)throws IOException{
		//Id b=new Id(base);
		Id d=new Id(id);
		PersistenceManager mgr=Helper.getMgr();
		if(d.IsPicture()){
			Query q=mgr.newQuery(I12.class);
			q.setFilter("i==iParam && j==jParam");
			q.declareParameters("Long iParam,Long jParam");
			try{
				@SuppressWarnings("unchecked")
				List<I12> r=(List<I12>)q.execute(d.i,d.j);
				if(!r.isEmpty()){
					I12 i=r.get(0);
					rsp.getOutputStream().write(i.getdat().getBytes());
				}
			}
			finally{
				q.closeAll();
			}
		}
		else{
			page.title=id;
			Query q=mgr.newQuery(I.class);
			q.setFilter("i==iParam && j==jParam");
			q.declareParameters("Long iParam,Long jParam");
			try{
				@SuppressWarnings("unchecked")
				List<I> r=(List<I>)q.execute(d.i,d.j);
				if(!r.isEmpty()){
					I i=r.get(0);
					if(i.geta()==12)
						page.Out("<img src=/"+base+"/"+id+".jpg>");
					page.Out(i.getx());
				}
			}
			finally{
				q.closeAll();
			}
			page.End(null);
		}
	}

	public Based(String plink,HttpServletResponse rsp)throws IOException{
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
		Index(plink,p);
	}
}