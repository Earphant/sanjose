package sanjose;

import java.io.IOException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class CommunityServlet extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		Page p=new Page(rsp);
		p.title="Community";
		p.aside="<ul><li><a href=/post/organization>Create a group</a></ul>";
		PersistenceManager mgr=Helper.getMgr();
		Query q=mgr.newQuery(I.class);
		q.setFilter("r==0 && d==1 && h==1");
		q.setOrdering("m desc");
		q.setRange(0,50);
		try{
			I.table(q.execute(),p);
		}
		finally{
			q.closeAll();
		}
		p.end(null);
	}
}
