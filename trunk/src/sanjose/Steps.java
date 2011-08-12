package sanjose;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Steps extends DataText{
	private String html(Timed i,boolean post,PersistenceManager mgr){
		String ret=null;
		Query q=mgr.newQuery(I139.class);
		q.setFilter("o==oParam && w==wParam");
		q.declareParameters("Long oParam,Long wParam");
		q.setOrdering("t");
		try{
			@SuppressWarnings("unchecked")
			List<I139> r=(List<I139>)q.execute(i.o,i.w);
			ret=new Graph().html(r,post?"/post/steps?i="+i+".":null,0,0,
				86400);
		}
		finally{
			q.closeAll();
		}
		return ret;
	}

	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		Page p=new Page(rsp);
		String i=req.getParameter("i");
		Timed timed=new Timed(i);
		p.title="Steps";
		p.aside="<ul><li><a href=/post>Message</a><li><a href=/post/documents>Document</a><li><a href=/post/picture>Picture</a><li><a href=/post/marks>Mark</a><li><a href=/post/events>Event</a><li><a href=/post/upload>Upload</a></ul><ul><li><a href=/post/books>Book</a><li><a href=/post/issues>Issue</a></ul><ul><li><a href=/post/weight>Weight</a><li><a href=/post/heartrate>Heart Rate</a><li><a href=/post/steps>Steps</a><li><a href=/post/fat>Fat</a></ul>";
		p.Out("<form method=post action=/post/steps?i="+i+">");
		
		if(timed.t==null)
			p.Out("Value<br><input type=text name=v>");
		else{
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
		p.End("<br><input type=submit name=ok></form>");
	}
	public void doPost(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		Timed i=new Timed(req.getParameter("i"));
		Long v=Long.parseLong(req.getParameter("v"));
		if(i.t==null)
			i.t=new Date();
		PersistenceManager mgr=Helper.getMgr();
		Session s=new Session("/post");
		try{
			I139 i139=new I139(s.id,s.site,v,i.t);
			mgr.makePersistent(i139);
			//updatePost(i,139,html(i,false,mgr),mgr);
		}
		finally{
			mgr.close();
		}
		rsp.sendRedirect("/"+s.id+"."+s.site+"/steps");
	}
	public void Out(String plink,Page page)throws IOException{
		String[]s=plink.split("/");
		String b=s[1];
		page.title="Steps";
		page.aside="<ul><li><a href=/post/steps>Post</a></ul><ul><li><a href=/system/settings>Settings</a><li><a href=/"+b+"/profile>Profile</a><li><a href=/"+b+"/contacts>Contacts</a><li><a href=/"+b+"/tags>Tags</a></ul><ul><li><a href=/"+b+"/dashboard>Dashboard</a><li><a href=/"+b+"/activities>Activities</a><li><a href=/"+b+"/historical>Historical</a></ul><ul><li><a href=/"+b+"/weight>Weight</a><li><a href=/"+b+"/heartrate>Heart Rate</a><li><a href=/"+b+"/steps>Steps</a><li><a href=/"+b+"/fat>Fat</a></ul>";
		page.Out("<div class=grf2>");
		page.Out(html(new Timed(b),true,Helper.getMgr()));
		page.Out("</div>");		
		page.End(null);
    }
}