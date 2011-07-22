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
		Timed timed=new Timed(req.getParameter("i"));
		p.title="Steps";
		p.aside="<ul><li><a href=/post/steps>Post</a></ul><ul><li><a href=/system/settings>Settings</a><li><a href=/12.3/profile>Profile</a><li><a href=/12.3/contacts>Contacts</a><li><a href=/12.3/tags>Tags</a></ul><ul><li><a href=/12.3/dashboard>Dashboard</a><li><a href=/12.3/activities>Activities</a><li><a href=/12.3/historical>Historical</a></ul><ul><li><a href=/12.3/weight>Weight</a><li><a href=/12.3/heartrate>Heart Rate</a><li><a href=/12.3/steps>Steps</a><li><a href=/12.3/fat>Fat</a></ul>";
	
		p.Out("<form method=post action=/post/steps>");
		
		if(timed.t!=null){
			PersistenceManager mgr=Helper.getMgr();
			Query q=mgr.newQuery(I139.class);
			q.setFilter("n==nParam && o==oParam && t==tParam");
			q.declareImports("import java.util.Date");
			q.declareParameters("Long nParam,Long oParam,Date tParam");
			try{
				@SuppressWarnings("unchecked")
				List<I139> r=(List<I139>)q.execute(timed.n,timed.o,timed.t);
				if(!r.isEmpty()){
					I139 i139=r.get(0);
					Long v=i139.getvol();
					p.Out("<textarea name=steps rows=5>"+v+"</textarea>");
				}
			}
			finally{
				q.closeAll();
			}
			p.Out("<input type=hidden name=i value="+timed.n+"."+timed.o+"."+timed.t.getTime()/1000+">");
		}
		else p.Out("<textarea name=steps rows=5></textarea>");
		p.End("<input type=submit name=ok></form>");
		
	}
	
	


			

	
	public void doPost(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		 PersistenceManager mgr=Helper.getMgr(); 
		  String ss=req.getParameter("steps");
	        Long vol=Long.parseLong(ss);
	        Timed timed=new Timed(req.getParameter("i"));
			if(timed.t==null){
				I139 i139=new I139(1L,9L,vol);
				try{
					mgr.makePersistent(i139);
				}
				finally{
					mgr.close();
				}
			}
			else{
				Query q=mgr.newQuery(I139.class);
				q.setFilter("n==nParam && o==oParam && t==tParam");
				q.declareImports("import java.util.Date");
				q.declareParameters("Long iParam,Long jParam,Date tParam");
				try{
					@SuppressWarnings("unchecked")
					List<I139> r=(List<I139>)q.execute(timed.n,timed.o,timed.t);
					if(!r.isEmpty()){
						I139 i139=r.get(0);
						i139.setvol(vol);
					}
				}
				finally{
					q.closeAll();
					mgr.close();
				}
			}
			rsp.sendRedirect("/12.3/steps");
		}
	public void Out(String plink,Page page) throws IOException{
		
		page.title="Steps";
		page.aside="<ul><li><a href=/post/steps>Post</a></ul><ul><li><a href=/system/settings>Settings</a><li><a href=/12.3/profile>Profile</a><li><a href=/12.3/contacts>Contacts</a><li><a href=/12.3/tags>Tags</a></ul><ul><li><a href=/12.3/dashboard>Dashboard</a><li><a href=/12.3/activities>Activities</a><li><a href=/12.3/historical>Historical</a></ul><ul><li><a href=/12.3/weight>Weight</a><li><a href=/12.3/heartrate>Heart Rate</a><li><a href=/12.3/steps>Steps</a><li><a href=/12.3/fat>Fat</a></ul>";
		PersistenceManager mgr=Helper.getMgr();
		Query q=mgr.newQuery(I139.class);
		q.setOrdering("t desc");
		try{
			@SuppressWarnings("unchecked")
			List<I139> r=(List<I139>)q.execute();
			if(!r.isEmpty()){
				for(I139 i139:r){
					page.Out(+i139.getn()+"."+i139.geto()+": "+i139.getvol()+" <a href=/post/steps?i="+i139.getn()+"."+i139.geto()+"."+i139.gett().getTime()/1000+">ÐÞ¸Ä</a><br>");
				}
			}
		}
		finally{
			q.closeAll();
		}
		page.End(null);
	}
	
}
