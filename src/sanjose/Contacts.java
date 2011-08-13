package sanjose;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class Contacts {
	public void out(String plink,Page page) throws IOException{
		page.title="Contacts";
		Session s=new Session("ow");
		String[]current=plink.split("/");
		String currentbase=current[1];
		
		page.aside="<ul><li><a href=/post/>Post</a></ul><ul><li><a href=/system/settings>Settings</a><li><a href=/"+currentbase+"/profile>Profile</a><li><a href=/"+currentbase+"/contacts>Contacts</a><li><a href=/"+currentbase+"/tags>Tags</a></ul><ul><li><a href=/"+currentbase+"/dashboard>Dashboard</a><li><a href=/"+currentbase+"/activities>Activities</a><li><a href=/"+currentbase+"/historical>Historical</a></ul><ul><li><a href=/"+currentbase+"/weight>Weight</a></ul>";	
		
		PersistenceManager mgr=Helper.getMgr();
		Query q=mgr.newQuery(I11.class);		
		q.setOrdering("i desc");
		try{
			@SuppressWarnings("unchecked")
			List<I11> r=(List<I11>)q.execute();
			if(!r.isEmpty()){
				for(I11 i:r){
					String d=i.getId()+"."+i.getSite();
					String x=i.getEmail();
					String base=i.getId()+"."+i.getSite();
					
					page.out("<a href=/"+base+"/><img src=/icons/"+d+".jpg></a>");
					page.out("<a href=/"+base+"/>"+x+"</a><br>");
				}
			}
		}
		finally{
			q.closeAll();
		}
		
		
		page.out("<br>我关注的人：");
		
		
		
		
		
		
		PersistenceManager mgr21=Helper.getMgr();	
		Query q21=mgr21.newQuery(I21.class);		
		q21.setOrdering("i desc");
		q21.setFilter("o==oParam && w==wParam ");	
		q21.declareParameters("Long oParam,Long wParam");
     
		try{
			@SuppressWarnings("unchecked")
			List<I21> r=(List<I21>)q21.execute(s.id,s.site);
		
			if(!r.isEmpty()){
				for(I21 i21:r){
					String d=i21.geti()+"."+i21.getj();					

                    page.out("<a href=/"+d+"/><img src=/icons/"+d+".jpg></a>");

				}
			}
		}
		finally{
			q21.closeAll();
		}
		
		
		
		page.End(null);

		}

}

