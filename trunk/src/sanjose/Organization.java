package	sanjose;

import java.io.IOException;
import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Organization{
	public void doGet(HttpServletRequest req,HttpServletResponse rsp,Page page,
		PersistenceManager mgr)throws IOException{
		I i=new I(req.getParameter("i"));
		page.title="Organization";
		page.aside="<ul><li><a href=/post>Message</a><li><a href=/post/documents>Document</a><li><a href=/post/picture>Picture</a><li><a href=/post/marks>Mark</a><li><a href=/post/events>Event</a><li><a href=/post/upload>Upload</a></ul><ul><li><a href=/post/books>Book</a><li><a href=/post/issues>Issue</a></ul><ul><li><a href=/post/weight>Weight</a><li><a href=/post/heart-rate>Heart Rate</a><li><a href=/post/steps>Steps</a><li><a href=/post/fat>Fat</a></ul>";
		if(i.getSite()==0)
			page.out("<form method=post action=/post/organization><input type=text name=text>");
		else{
			page.out("<form method=post action=/post/organization?i="+i+">");
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
				o=I.create(v,null,2,0,sn.owner,m);
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
	public void out(String plink,Page page)throws IOException{
		page.end(null);
	}
}