package sanjose;

import java.io.IOException;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Steps extends DataText{
	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		Page p=new Page(rsp);
		I i=I.createTimed(req.getParameter("i"));
		p.title="Steps";
		p.aside="<ul><li><a href=/post>Message</a><li><a href=/post/documents>Document</a><li><a href=/post/picture>Picture</a><li><a href=/post/marks>Mark</a><li><a href=/post/events>Event</a><li><a href=/post/upload>Upload</a></ul><ul><li><a href=/post/books>Book</a><li><a href=/post/issues>Issue</a></ul><ul><li><a href=/post/weight>Weight</a><li><a href=/post/heartrate>Heart Rate</a><li><a href=/post/steps>Steps</a><li><a href=/post/fat>Fat</a></ul>";
		if(i==null){
			p.Out("<form method=post action=/post/steps>");
			p.Out("Value<br><input type=text name=v>");
		}
		else{
			p.Out("<form method=post action=/post/steps?i="+i.getTimed()+">");
			Query q=Helper.getMgr().newQuery(I139.class);
			q.setFilter("o==oParam && w==wParam && t==tParam");
			q.declareImports("import java.util.Date");
			q.declareParameters("Long oParam,Long wParam,Date tParam");
			try{
				@SuppressWarnings("unchecked")
				List<I139> r=(List<I139>)q.execute(i.getId(),i.getSite(),
					i.getCreateTime());
				if(!r.isEmpty())
					p.Out("Value<br><input type=text name=v value="+
						r.get(0).getVal()+">");
			}
			finally{
				q.closeAll();
			}
		}
		p.End("<br><input type=submit name=ok></form>");
	}
	public void doPost(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		Session sn=new Session("/post");
		I i=I.createTimed(req.getParameter("i"));
		long v=Long.parseLong(req.getParameter("v"));
		if(i==null){
			i=new I(sn.owner.getId(),sn.owner.getSite());
			i.setModifyTime(null);
		}
		PersistenceManager mgr=Helper.getMgr();
		try{
			I139 i139=new I139(i,i.getModifyTime(),v);
			mgr.makePersistent(i139);
			updatePost(i,139,getHtml(i,false,mgr),mgr);
		}
		finally{
			mgr.close();
		}
		rsp.sendRedirect("/"+i+"/steps");
	}
	public void Out(String plink,Page page)throws IOException{
		String[]s=plink.split("/");
		String b=s[1];
		page.title="Steps";
		page.aside="<ul><li><a href=/post/steps>Post</a></ul><ul><li><a href=/system/settings>Settings</a><li><a href=/"+b+"/profile>Profile</a><li><a href=/"+b+"/contacts>Contacts</a><li><a href=/"+b+"/tags>Tags</a></ul><ul><li><a href=/"+b+"/dashboard>Dashboard</a><li><a href=/"+b+"/activities>Activities</a><li><a href=/"+b+"/historical>Historical</a></ul><ul><li><a href=/"+b+"/weight>Weight</a><li><a href=/"+b+"/heartrate>Heart Rate</a><li><a href=/"+b+"/steps>Steps</a><li><a href=/"+b+"/fat>Fat</a></ul>";
		page.Out("<div class=grf2>");
		page.Out(getHtml(new I(b),true,Helper.getMgr()));
		page.Out("</div>");		
		page.End(null);
    }
}