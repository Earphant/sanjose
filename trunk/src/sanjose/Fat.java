package sanjose;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Fat {
	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		Page p=new Page(rsp);
		p.title="Fat";
		p.aside="<ul><li><a href=/post/>Message</a><li><a href=/post/documents>Document</a><li><a href=/post/picture>Picture</a><li><a href=/post/marks>Mark</a><li><a href=/post/events>Event</a><li><a href=/post/uploads>Upload</a></ul><ul><li><a href=/post/books>Book</a><li><a href=/post/issues>Issue</a></ul><ul><li><a href=/post/weight>Weight</a><li><a href=/post/heartrate>Heart Rate</a><li><a href=/post/steps>Steps</a><li><a href=/post/fat>Fat</a></ul>";
		p.End("<form method=post action=/post/fat>Fat:<input type=text name=fat>Water:<input type=text name=wat><input type=submit name=ok value=Ok></form>");
	}
	public void doPost(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
        PersistenceManager mgr=Helper.getMgr();
        String vol1=req.getParameter("fat");
        String vol2=req.getParameter("wat");
        Long fat=Long.parseLong(vol1);	
        Long wat=Long.parseLong(vol2);	
		I135 i135=new I135(1L,9L,fat,wat);
		try{
			mgr.makePersistent(i135);
		}
		finally{
			mgr.close();
		}
		rsp.sendRedirect("/12.3/fat");
	}
	public void Out(String plink,Page page) throws IOException{
		page.title="Fat";
		page.aside="<ul><li><a href=/post/fat>Post</a></ul><ul><li><a href=/system/settings>Settings</a><li><a href=/12.3/profile>Profile</a><li><a href=/12.3/contacts>Contacts</a><li><a href=/12.3/tags>Tags</a></ul><ul><li><a href=/12.3/dashboard>Dashboard</a><li><a href=/12.3/activities>Activities</a><li><a href=/12.3/historical>Historical</a></ul><ul><li><a href=/12.3/weight>Weight</a><li><a href=/12.3/heartrate>Heart Rate</a><li><a href=/12.3/steps>Steps</a><li><a href=/12.3/fat>Fat</a></ul>";
		PersistenceManager mgr=Helper.getMgr();
		Query q=mgr.newQuery(I135.class);
		q.setOrdering("t desc");
		try{
			@SuppressWarnings("unchecked")
			List<I135> r=(List<I135>)q.execute();
			if(!r.isEmpty()){
				for(I135 i135:r){
					page.Out(i135.getn()+"."+i135.geto()+": "+"<br>"+"fat"+": "+i135.getfat()+"<br>"+"water"+": "+i135.getwat()+"<br>");
				}
			}
		}
		finally{
			q.closeAll();
		}
		page.End(null);
	}
	
}