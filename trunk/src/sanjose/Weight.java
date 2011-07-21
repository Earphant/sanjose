package sanjose;

import java.io.IOException;
//import java.text.ParseException;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Weight {
	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		Page p=new Page(rsp);
		Timed timed=new Timed(req.getParameter("i"));
		p.title="Weight";
		p.aside="<ul><li><a href=/post>Message</a><li><a href=/post/documents>Document</a><li><a href=/post/picture>Picture</a><li><a href=/post/marks>Mark</a><li><a href=/post/events>Event</a><li><a href=/post/uploads>Upload</a></ul><ul><li><a href=/post/books>Book</a><li><a href=/post/issues>Issue</a></ul><ul><li><a href=/post/weight>Weight</a><li><a href=/post/weight>Heart weight</a><li><a href=/post/steps>Steps</a><li><a href=/post/fat>Fat</a></ul>";
		p.Out("<form method=post action=/post/weight>");
		if(timed.t!=null){
			PersistenceManager mgr=Helper.getMgr();
			Query q=mgr.newQuery(I138.class);
			q.setFilter("n==nParam && o==oParam && t==tParam");
			q.declareImports("import java.util.Date");
			q.declareParameters("Long nParam,Long oParam,Date tParam");
			try{
				@SuppressWarnings("unchecked")
				List<I138> r=(List<I138>)q.execute(timed.n,timed.o,timed.t);
				if(!r.isEmpty()){
					I138 i138=r.get(0);
					Long v=i138.getvol();
					p.Out("<textarea name=weight rows=5>"+v+"</textarea>");
				}
			}
			finally{
				q.closeAll();
			}
			p.Out("<input type=hidden name=i value="+timed.n+"."+timed.o+"."+timed.t.getTime()/1000+">");
		}
		else p.Out("<textarea name=weight rows=5></textarea>");
		p.End("<input type=submit name=ok></form>");
	}
	public void doPost(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
        PersistenceManager mgr=Helper.getMgr(); 
        String vols=req.getParameter("weight");
        Long vol=Long.parseLong(vols);
        Timed timed=new Timed(req.getParameter("i"));
		if(timed.t==null){
			I138 i=new I138(1L,9L,vol);
			try{
				mgr.makePersistent(i);
			}
			finally{
				mgr.close();
			}
		}
		else{
			Query q=mgr.newQuery(I138.class);
			q.setFilter("n==nParam && o==oParam && t==tParam");
			q.declareImports("import java.util.Date");
			q.declareParameters("Long iParam,Long jParam,Date tParam");
			try{
				@SuppressWarnings("unchecked")
				List<I138> r=(List<I138>)q.execute(timed.n,timed.o,timed.t);
				if(!r.isEmpty()){
					I138 i138=r.get(0);
					i138.setvol(vol);
				}
			}
			finally{
				q.closeAll();
				mgr.close();
			}
		}
		rsp.sendRedirect("/12.3/weight");
	}
	public void Out(String plink,Page page) throws IOException{
		page.title="Weight";
		page.aside="<ul><li><a href=/post/weight>Post</a></ul><ul><li><a href=/system/settings>Settings</a><li><a href=/12.3/profile>Profile</a><li><a href=/12.3/contacts>Contacts</a><li><a href=/12.3/tags>Tags</a></ul><ul><li><a href=/12.3/dashboard>Dashboard</a><li><a href=/12.3/activities>Activities</a><li><a href=/12.3/historical>Historical</a></ul><ul><li><a href=/12.3/weight>Weight</a><li><a href=/12.3/weight>Heart weight</a><li><a href=/12.3/steps>Steps</a><li><a href=/12.3/fat>Fat</a></ul>";
		PersistenceManager mgr=Helper.getMgr();
		Query q=mgr.newQuery(I138.class);
		q.setOrdering("t desc");
		try{
			@SuppressWarnings("unchecked")
			List<I138> r=(List<I138>)q.execute();
			if(!r.isEmpty()){
				for(I138 i138:r){
					page.Out(+i138.getn()+"."+i138.geto()+": "+i138.getvol()+" <a href=/post/weight?i="+i138.getn()+"."+i138.geto()+"."+i138.gett().getTime()/1000+">ÐÞ¸Ä</a><br>");
				}
			}
		}
		finally{
			q.closeAll();
		}
		page.End(null);
	}
	
}

 