package sanjose;

import java.io.IOException;
import java.util.List;
import javax.jdo.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class HomeServlet extends HttpServlet{
	@SuppressWarnings("unchecked")
	private void Signed(Page page,Session ssn)throws IOException{
		page.title="Home";
		page.Out("<form method=post action=/post/><textarea name=text rows=5></textarea><input type=submit name=ok></form>");
		
		PersistenceManager m=Helper.getMgr();
		Query q=m.newQuery(I.class);		
		q.setOrdering("m desc");
		try{
			new RegList((List<I>)q.execute(),page);
		}
		finally{
			q.closeAll();
		}
		page.End(null);
	}
	private void Unsigned(Page page,Session ssn)
		throws IOException{
		page.title="Home";
		page.Begin();
		page.End(null);
	}

	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		String n=req.getPathInfo();
		Page p=new Page(rsp);
		Session s=new Session("");
		if(n.equals("/")){
			if(s.email==null)
				Unsigned(p,s);
			else
				Signed(p,s);
		}
		else
			new Based(n,rsp,req);
	}
}
