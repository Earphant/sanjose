package sanjose;

import java.io.IOException;
import java.util.List;
import javax.jdo.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class HomeServlet extends HttpServlet{
	private void Signed(Page page,Session ssn)
		throws IOException{
		page.title="Home";
		page.Begin();
		page.Out(ssn.utext);
		page.Out("<br>");
		page.Out("<form method=post action=/post/><textarea name=text rows=5></textarea><input type=submit name=ok></form>");
		PersistenceManager mgr=Helper.getMgr();
		Query q=mgr.newQuery(I.class);
		q.setOrdering("m desc");
		try{
			@SuppressWarnings("unchecked")
			List<I> r=(List<I>)q.execute();
			if(!r.isEmpty()){
				for(I i:r){
					page.Out(i.getx()+" <a href=/post?i="+i.geti()+"."+i.getj()+">=</a><br>");
				}
			}
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
		Session s=new Session(true);
		if(n.equals("/")){
			if(s.utext==null)
				Unsigned(p,s);
			else
				Signed(p,s);
		}
		else
			new Based(n,p);
	}
}
