package sanjose;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Steps {
	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		Page p=new Page(rsp);
		p.title="Steps";
		p.aside="<ul><li><a href=/post/heartrate>Post</a></ul><ul><li><a href=/system/settings>Settings</a><li><a href=/12.3/profile>Profile</a><li><a href=/12.3/contacts>Contacts</a><li><a href=/12.3/tags>Tags</a></ul><ul><li><a href=/12.3/dashboard>Dashboard</a><li><a href=/12.3/activities>Activities</a><li><a href=/12.3/historical>Historical</a></ul><ul><li><a href=/12.3/weight>Weight</a><li><a href=/12.3/heartrate>Heart Rate</a><li><a href=/12.3/steps>Steps</a><li><a href=/12.3/fat>Fat</a></ul>";
		PersistenceManager ste=Helper.getMgr();
		p.Out("<form method=post action=/post/steps><input type=text name=steps><input type=submit name=ok value=Ok></form>");
		
		Query q=ste.newQuery(I139.class);
		q.setOrdering("t desc");
		try{
			@SuppressWarnings("unchecked")
			List<I139> r=(List<I139>)q.execute();
			if(!r.isEmpty()){
				for(I139 i139:r){
					p.Out(i139.getn()+"."+i139.geto()+">"+i139.getvol()+"<br>"+"<input type=submit name=ok value=Ok>"+"<br>");
				}
			}
		}
		finally{
			q.closeAll();
		}
		p.End(null);
	}
	


			

	
	public void doPost(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		 PersistenceManager ste=Helper.getMgr();
	        String ss=req.getParameter("steps");
	        Long vol=Long.parseLong(ss);	
			I139 i139=new I139(1L,3L,vol);
			try{
			    ste.makePersistent(i139);
			}
			finally{
				ste.close();
			}
		rsp.sendRedirect("/12.3/steps");
	}
	public void Out(String plink,Page page) throws IOException{
		
		page.title="Steps";
		page.aside="<ul><li><a href=/post/heartrate>Post</a></ul><ul><li><a href=/system/settings>Settings</a><li><a href=/12.3/profile>Profile</a><li><a href=/12.3/contacts>Contacts</a><li><a href=/12.3/tags>Tags</a></ul><ul><li><a href=/12.3/dashboard>Dashboard</a><li><a href=/12.3/activities>Activities</a><li><a href=/12.3/historical>Historical</a></ul><ul><li><a href=/12.3/weight>Weight</a><li><a href=/12.3/heartrate>Heart Rate</a><li><a href=/12.3/steps>Steps</a><li><a href=/12.3/fat>Fat</a></ul>";
		PersistenceManager ste=Helper.getMgr();
		page.Out("<form method=post action=/post/steps><input type=text name=steps><input type=submit name=ok value=Ok></form>");
		
		Query q=ste.newQuery(I139.class);
		q.setOrdering("t desc");
		try{
			@SuppressWarnings("unchecked")
			List<I139> r=(List<I139>)q.execute();
			if(!r.isEmpty()){
				for(I139 i139:r){
					page.Out(i139.getn()+"."+i139.geto()+">"+i139.getvol()+"<br>");
				}
			}
		}
		finally{
			q.closeAll();
		}
		page.End(null);
	}
	
}

 
