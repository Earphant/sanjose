package	sanjose;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Organization{
	//@SuppressWarnings("unchecked")
	static public void out(I id,Page page,PersistenceManager mgr,Session ssn)
		throws IOException{
		page.title=id.getTitle(false);
		Query q=mgr.newQuery(I21.class);
		I o=ssn.owner;
		q.setFilter("o=="+o.getId()+" && w=="+o.getSite()+" && i=="+
			id.getId()+" && j=="+id.getSite());
		try{
			@SuppressWarnings("unchecked")
			List<I21> r=(List<I21>)q.execute();
			page.aside=r.isEmpty()?"<ul><li><a href=/system/follow?i="+id+">Follow</a><li><a href=/post?b="+id+">Post</a></ul>":
				"<ul><li><a href=/system/unfollow?i="+id+">Unfollow</a><li><a href=/post/organization?i="+id+">Setting</a></ul>";
		}
		finally{
			q.closeAll();
		}
		q=mgr.newQuery(I.class);
		q.setFilter("b==oParam && s==wParam ");	
		q.declareParameters("Long oParam,Long wParam");
        q.setOrdering("m desc");
		try{
            I.table(q.execute(id.getId(),id.getSite()),page);
//            I.table(q.execute(),page);
		}
		finally{
			q.closeAll();
		}
		page.end(null);
	}

	public void doGet(HttpServletRequest req,HttpServletResponse rsp,Page page,
		PersistenceManager mgr)throws IOException{
		I i=new I(req.getParameter("i"));
		page.title="Organization";
		page.aside="<ul><li><a href=/post>Message</a><li><a href=/post/documents>Document</a><li><a href=/post/picture>Picture</a><li><a href=/post/marks>Mark</a><li><a href=/post/events>Event</a><li><a href=/post/upload>Upload</a></ul><ul><li><a href=/post/books>Book</a><li><a href=/post/issues>Issue</a></ul><ul><li><a href=/post/weight>Weight</a><li><a href=/post/heart-rate>Heart Rate</a><li><a href=/post/steps>Steps</a><li><a href=/post/fat>Fat</a></ul>";
		if(i.getSite()==0)
			page.out("<form method=post action=/post/organization><input type=text name=text>");
		else{
			i=I.query(i,mgr);
			page.out("<a href=/post/upload?i="+i.getId()+"."+i.getSite()+"><img src=/icons/"+i.getId()+"."+i.getSite()+" class=icon></a><br>");
			page.out("<form method=post action=/post/organization?i="+i+">");
			page.out("Group Name<br><input type=text name=text value="+i.getText()+"><br>");
			page.out("Quotation<br><textarea name=q rows=10>"+i.getQuotation()+"</textarea><br>");
		}
		page.out("<input type=hidden name=i value="+i.getId()+"."+i.getSite()+">");
		page.end("<input type=submit name=ok></form>");
	}
	public void doPost(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		Session sn=new Session("/post/organization");
		String v=req.getParameter("text");
		I i=new I(req.getParameter("i")),o;
		PersistenceManager m=Helper.getMgr();
		try{
			if(i.getSite()==0){
				o=I.store(v,null,2,0,sn.owner,m,true);
				
			}
			else{
				String q=req.getParameter("q");
				o=I.query(i,m);
				o.setText(v);
				o.setQuotation(q);
				o.setModifyTime(null);
			}
		}
		finally{
			m.close();
		}
		rsp.sendRedirect("/"+o);
	}
}