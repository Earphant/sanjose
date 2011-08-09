package sanjose;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;


public class Contacts {
	public void Out(String plink,Page page) throws IOException{
		page.title="Contacts";
		page.aside="<ul><li><a href=/post/>Post</a></ul><ul><li><a href=/system/settings>Settings</a><li><a href=/12.3/profile>Profile</a><li><a href=/12.3/contacts>Contacts</a><li><a href=/12.3/tags>Tags</a></ul><ul><li><a href=/12.3/dashboard>Dashboard</a><li><a href=/12.3/activities>Activities</a><li><a href=/12.3/historical>Historical</a></ul><ul><li><a href=/12.3/weight>Weight</a></ul>";	
		
		PersistenceManager mgr=Helper.getMgr();
		Query q=mgr.newQuery(I11.class);		
		q.setOrdering("i desc");
		try{
			@SuppressWarnings("unchecked")
			List<I11> r=(List<I11>)q.execute();
			if(!r.isEmpty()){
				for(I11 i:r){
					String d=i.geti()+"."+i.getj();
					String x=i.geteml();
					String base=i.geti()+"."+i.getj();
					
					page.Out("<a href=/"+base+"/><img src=/icons/"+d+".jpg></a>");
					page.Out("<a href=/"+base+"/>"+x+"</a>");
				}
			}
		}
		finally{
			q.closeAll();
		}
		page.End(null);

	}


}

