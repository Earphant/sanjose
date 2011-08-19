package	sanjose;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Individual{
	public void doGet(HttpServletRequest req,HttpServletResponse rsp,Page page,
		PersistenceManager mgr)throws IOException{
		I i=new I(req.getParameter("i"));
		page.title="Organization";
		page.aside="<ul><li><a href=/post>Message</a><li><a href=/post/documents>Document</a><li><a href=/post/picture>Picture</a><li><a href=/post/marks>Mark</a><li><a href=/post/events>Event</a><li><a href=/post/upload>Upload</a></ul><ul><li><a href=/post/books>Book</a><li><a href=/post/issues>Issue</a></ul><ul><li><a href=/post/weight>Weight</a><li><a href=/post/heart-rate>Heart Rate</a><li><a href=/post/steps>Steps</a><li><a href=/post/fat>Fat</a></ul>";
		if(i.getSite()==0)
			page.out("<form method=post action=/post/individual><input type=text name=text>");
		else{
			page.out("<form method=post action=/post/individual?i="+i+">");
			i=I.query(i,mgr);
			page.out("<input type=text name=text value="+i.getText()+">");
		}
		page.end("<br><input type=submit name=ok></form>");
	}
	public void doPost(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		Session sn=new Session("/post/organization");
		String v=req.getParameter("text");
		I i=new I(req.getParameter("i")),o;
		PersistenceManager m=Helper.getMgr();
		try{
			if(i.getSite()==0)
				o=I.create(v,null,2,0,sn.owner,m,true);
			else{
				o=I.query(i,m);
				o.setText(v);
				o.setModifyTime(null);
			}
		}
		finally{
			m.close();
		}
		rsp.sendRedirect("/"+o);
	}
	//@SuppressWarnings("unchecked")
	public void out(I id,Page page,PersistenceManager mgr,Session ssn)
		throws IOException{
		page.title=id.getTitle(false);
		if(id.equals(ssn.owner))
			page.aside="<ul><li><a href=/post>Post</a><li><a href=/system/settings>Settings</a></ul>";
		else{
			Query q=mgr.newQuery(I21.class);
			I o=ssn.owner;
			q.setFilter("o=="+o.getId()+" && w=="+o.getSite()+" && i=="+
				id.getId()+" && j=="+id.getSite());
			try{
				@SuppressWarnings("unchecked")
				List<I21> r=(List<I21>)q.execute();
				page.aside=r.isEmpty()?"<ul><li><a href=/system/follow?i="+id+">Follow</a></ul>":
					"<ul><li><a href=/system/unfollow?i="+id+">Unfollow</a></ul>";
			}
			finally{
				q.closeAll();
			}
		}
		page.aside+="<ul><li><a href=/"+id+"/profile>Profile</a><li><a href=/"+
			id+"/contacts>Contacts</a><li><a href=/"+
			id+"/tags>Tags</a></ul><ul><li><a href=/"+
			id+"/dashboard>Dashboard</a><li><a href=/"+
			id+"/activities>Activities</a><li><a href=/"+
			id+"/historical>Historical</a></ul><ul><li><a href=/"+
			id+"/weight>Weight</a><li><a href=/"+
			id+"/heart-rate>Heart Rate</a><li><a href=/"+
			id+"/steps>Steps</a><li><a href=/"+id+"/fat>Fat</a></ul>";
		page.out(id+"/"+ssn.owner);
		page.out("<form method=post action=/post?re="+id+">");
		page.out("<textarea name=text rows=5></textarea>");
		page.out("<input type=submit name=ok value=Reply></form>");
		Query q=mgr.newQuery(I.class);
		q.setFilter("o==oParam && w==wParam ");	
		q.declareParameters("Long oParam,Long wParam");
        q.setOrdering("m desc");
		try{
            I.list(q.execute(id.getId(),id.getSite()),page);
		}
		finally{
			q.closeAll();
		}
		page.end(null);
	}
}