package sanjose;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Steps extends DataText{
	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		Page p=new Page(rsp);
		String i=req.getParameter("i");
		Timed timed=new Timed(i);
		p.title="Steps";
		p.aside="<ul><li><a href=/post>Message</a><li><a href=/post/documents>Document</a><li><a href=/post/picture>Picture</a><li><a href=/post/marks>Mark</a><li><a href=/post/events>Event</a><li><a href=/post/upload>Upload</a></ul><ul><li><a href=/post/books>Book</a><li><a href=/post/issues>Issue</a></ul><ul><li><a href=/post/weight>Weight</a><li><a href=/post/heartrate>Heart Rate</a><li><a href=/post/steps>Steps</a><li><a href=/post/fat>Fat</a></ul>";
		p.Out("<form method=post action=/post/steps?i="+i+">");
		
		if(timed.t!=null){
			Query q=Helper.getMgr().newQuery(I139.class);
			q.setFilter("o==oParam && w==wParam && t==tParam");
			q.declareImports("import java.util.Date");
			q.declareParameters("Long oParam,Long wParam,Date tParam");
			try{
				@SuppressWarnings("unchecked")
				List<I139> r=(List<I139>)q.execute(timed.o,timed.w,timed.t);
				if(!r.isEmpty()){
					I139 i139=r.get(0);
					long v=i139.getVal();
					p.Out("Value<br><input type=text name=v value="+v+">");
				}
			}
			finally{
				q.closeAll();
			}
		}
		else
			p.Out("Value<br><input type=text name=v>");
		p.End("<br><input type=submit name=ok></form>");
	}
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		PersistenceManager mgr=Helper.getMgr();
		Long val=Long.parseLong(req.getParameter("v"));
		Date now=new Date();
		Session s=new Session("/post");
		Timed timed=new Timed(req.getParameter("i"));
		if(timed.t==null){
			try{
				I139 i139=new I139(s.id,s.site,val,now);
				mgr.makePersistent(i139);
			}
			finally{
				mgr.close();
			}
		}
		else{
			Query q=mgr.newQuery(I139.class);
			q.setFilter("o==oParam && w==wParam && t==tParam");
			q.declareImports("import java.util.Date");
			q.declareParameters("Long oParam,Long wParam,Date tParam");
			try{
				List<I139> r=(List<I139>)q.execute(timed.o,timed.w,timed.t);
				if(!r.isEmpty()){
					I139 i139=r.get(0);
					i139.setVal(val);
					mgr.makePersistent(i139);
				}
			}
			finally{
				q.closeAll();
			}
		}
		updatePost(timed,139,mgr);
		rsp.sendRedirect("/"+s.id+"."+s.site+"/steps");
	}
	@SuppressWarnings("unchecked")
	public void Out(String plink,Page page) throws IOException{
		String[]s=plink.split("/");
		String base=s[1];
		page.title="Steps";
		page.aside="<ul><li><a href=/post/steps>Post</a></ul><ul><li><a href=/system/settings>Settings</a><li><a href=/"+base+"/profile>Profile</a><li><a href=/"+base+"/contacts>Contacts</a><li><a href=/"+base+"/tags>Tags</a></ul><ul><li><a href=/"+base+"/dashboard>Dashboard</a><li><a href=/"+base+"/activities>Activities</a><li><a href=/"+base+"/historical>Historical</a></ul><ul><li><a href=/"+base+"/weight>Weight</a><li><a href=/"+base+"/heartrate>Heart Rate</a><li><a href=/"+base+"/steps>Steps</a><li><a href=/"+base+"/fat>Fat</a></ul>";
		I d=new I(s[1]);
		
		PersistenceManager mgr=Helper.getMgr();
		Query q1=mgr.newQuery(I139.class);
		q1.setFilter("o==oParam && w==wParam");
		q1.declareParameters("Long oParam,Long wParam");
		q1.setOrdering("t");
		try{
			List<I139> r=(List<I139>)q1.execute(d.getId(),d.getSite());
			String abc="steps";
			page.Out("<div class=graf>");
			page.Out(new Graph().Daily(r,abc));
			page.Out("</div>");
			
			page.Out("<div class=graf>");
			page.Out(new Graph().html(r,"/post/steps?i="+d+".",0,0,86400));
			page.Out("</div>");
		}
		finally{
			q1.closeAll();
		}
		page.End(null);
    }
}