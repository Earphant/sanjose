package	sanjose;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Individual{
	static public String menu(I id,Session ssn,PersistenceManager mgr){
		String ret=null;
		if(id.equals(ssn.owner))
			ret="<ul><li><a href=/post>Post</a><li><a href=/system/settings>Settings</a></ul>";
		else{
			Query q=mgr.newQuery(I21.class);
			I o=ssn.owner;
			q.setFilter("o=="+o.getId()+" && w=="+o.getSite()+" && i=="+
				id.getId()+" && j=="+id.getSite());
			try{
				@SuppressWarnings("unchecked")
				List<I21> r=(List<I21>)q.execute();
				ret=r.isEmpty()?"<ul><li><a href=/system/follow?i="+id+">Follow</a></ul>":
					"<ul><li><a href=/system/unfollow?i="+id+">Unfollow</a></ul>";
			}
			finally{
				q.closeAll();
			}
		}
		return ret;
	}
	//@SuppressWarnings("unchecked")
	static public void out(I id,Page page,PersistenceManager mgr,Session ssn)
		throws IOException{
		page.title=id.getTitle(false);
		page.aside=menu(id,ssn,mgr)+
			"<ul><li><a href=/"+id+"/profile>Profile</a><li><a href=/"+
			id+"/contacts>Contacts</a><li><a href=/"+
			id+"/tags>Tags</a></ul><ul><li><a href=/"+
			id+"/dashboard>Dashboard</a><li><a href=/"+
			id+"/activities>Activities</a><li><a href=/"+
			id+"/historical>Historical</a></ul><ul><li><a href=/"+
			id+"/weight>Weight</a><li><a href=/"+
			id+"/heart-rate>Heart Rate</a><li><a href=/"+
			id+"/steps>Steps</a><li><a href=/"+id+"/fat>Fat</a></ul>";
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
	public void doGet(HttpServletRequest req,HttpServletResponse rsp,Page page,
		PersistenceManager mgr)throws IOException{
		I i=new I(req.getParameter("i"),0);
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
		I i=new I(req.getParameter("i"),0);
		I o;
		PersistenceManager m=Helper.getMgr();
		try{
			if(i.getSite()==0)
				o=I.store(v,null,2,(byte)0,sn.owner,m,true);
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
}