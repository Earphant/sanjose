package sanjose;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HeartRate {
	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		Page p=new Page(rsp);
		p.title="Heart Rate";
		p.aside="<ul><li><a href=/post/>Message</a><li><a href=/post/documents>Document</a><li><a href=/post/picture>Picture</a><li><a href=/post/marks>Mark</a><li><a href=/post/events>Event</a><li><a href=/post/uploads>Upload</a></ul><ul><li><a href=/post/books>Book</a><li><a href=/post/issues>Issue</a></ul><ul><li><a href=/post/weight>Weight</a><li><a href=/post/heartrate>Heart Rate</a><li><a href=/post/steps>Steps</a></ul>";
		p.End("<form method=post action=/post/heartrate><input type=text name=rate><input type=submit name=ok value=Ok></form>");
	}
	public void doPost(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
        PersistenceManager mgr=Helper.getMgr();
        String vols=req.getParameter("rate");
        Long vol=Long.parseLong(vols);	
		I136 i=new I136(1,9,vol);
		try{
			mgr.makePersistent(i);
		}
		finally{
			mgr.close();
		}
		rsp.sendRedirect("/12.3/heartrate");
	}
	public void Out(String plink,Page page) throws IOException{
		page.title="Heart Rate";
		page.aside="<ul><li><a href=/post/heartrate>Post</a></ul><ul><li><a href=/system/settings>Settings</a><li><a href=/12.3/profile>Profile</a><li><a href=/12.3/contacts>Contacts</a><li><a href=/12.3/tags>Tags</a></ul><ul><li><a href=/12.3/dashboard>Dashboard</a><li><a href=/12.3/activities>Activities</a><li><a href=/12.3/historical>Historical</a></ul><ul><li><a href=/12.3/weight>Weight</a><li><a href=/12.3/heartrate>Heart Rate</a><li><a href=/12.3/steps>Steps</a></ul>";
		PersistenceManager mgr=Helper.getMgr();
		Query q=mgr.newQuery(I136.class);
		q.setOrdering("t desc");
		try{
			@SuppressWarnings("unchecked")
			List<I136> r=(List<I136>)q.execute();
			if(!r.isEmpty()){
				for(I136 i136:r){
					page.Out(i136.getn()+"."+i136.geto()+": "+i136.getvol()+"<br>");
				}
			}
		}
		finally{
			q.closeAll();
		}
	}
	
}

 