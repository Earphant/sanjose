package sanjose;

import java.io.IOException;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class CommunityServlet extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		Page p=new Page(rsp);
		p.title="Community";
		p.aside="<ul><li><a href=/post/organization>Create a group</a></ul>";
		PersistenceManager mgr=Helper.getMgr();
		Query q=mgr.newQuery(I.class);
		q.setFilter("r==0");
		q.setOrdering("m desc");
		try{
			new RegList((List<I>)q.execute(),p);
		}
		finally{
			q.closeAll();
		}
		p.end(null);
	}
}
