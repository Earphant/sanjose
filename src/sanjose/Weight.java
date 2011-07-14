package sanjose;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Weight{
	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		Page p=new Page(rsp);
		p.title="Weight";
		p.aside="<ul><li><a href=/post/>Message</a><li><a href=/post/documents>Document</a><li><a href=/post/picture>Picture</a><li><a href=/post/marks>Mark</a><li><a href=/post/events>Event</a><li><a href=/post/uploads>Upload</a></ul><ul><li><a href=/post/books>Book</a><li><a href=/post/issues>Issue</a></ul><ul><li><a href=/post/weight>Weight</a><li><a href=/post/heartrate>Heart Rate</a><li><a href=/post/steps>Steps</a><li><a href=/post/fat>Fat</a></ul>";
		p.End("<form method=post action=/post/weight><input type=text name=weight><input type=submit name=ok value=Ok></form>");
	}
	public void doPost(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		String vols=req.getParameter("weight");
		Long vol=Long.parseLong(vols);
	    PersistenceManager wgh=Helper.getMgr();
	    I138 i138=new I138(1L,3L,vol);
		try{
		    wgh.makePersistent(i138);
		}
		finally{
		    wgh.close();
		}
		rsp.sendRedirect("/12.3/weight");
	}
	public void Out(String plink,Page page) throws IOException{
		page.title="Weight";
		page.aside="<ul><li><a href=/post/weight>Post</a></ul><ul><li><a href=/system/settings>Settings</a><li><a href=/12.3/profile>Profile</a><li><a href=/12.3/contacts>Contacts</a><li><a href=/12.3/tags>Tags</a></ul><ul><li><a href=/12.3/dashboard>Dashboard</a><li><a href=/12.3/activities>Activities</a><li><a href=/12.3/historical>Historical</a></ul><ul><li><a href=/12.3/weight>Weight</a><li><a href=/12.3/heartrate>Heart Rate</a><li><a href=/12.3/steps>Steps</a><li><a href=/12.3/fat>Fat</a></ul>";
		
		PersistenceManager wgh=Helper.getMgr();
		Query q=wgh.newQuery(I138.class);
		q.setOrdering("t desc");
		try{
			@SuppressWarnings("unchecked")
			List<I138> r=(List<I138>)q.execute();
			if(!r.isEmpty()){
				for(I138 i138:r){
					page.Out(i138.getn()+"."+i138.geto()+": "+i138.getvol()+"<br>");
				}
			}
		}
		finally{
			q.closeAll();
		}
		page.End(null);
	}
	
}
