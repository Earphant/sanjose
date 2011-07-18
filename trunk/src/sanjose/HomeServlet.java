package sanjose;

import java.io.IOException;
import java.util.List;
import javax.jdo.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class HomeServlet extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse resp)
		throws IOException{
		String pl=req.getPathInfo();
		Page p=new Page(resp);
		if(pl.equals("/")){
			p.title="Home";
			p.Begin();
			p.Out("<form method=post action=/post/><textarea name=text rows=5></textarea><input type=submit name=ok></form>");
			PersistenceManager mgr=Helper.getMgr();
			Query q=mgr.newQuery(I.class);
			q.setOrdering("m desc");
			try{
				@SuppressWarnings("unchecked")
				List<I> r=(List<I>)q.execute();
				if(!r.isEmpty()){
					for(I i:r){
						p.Out(i.getx()+" <a href=/post?i="+i.geti()+"."+i.getj()+">=</a><br>");
					}
				}
			}
			finally{
				q.closeAll();
			}
			p.End(null);
		}
		else new Based(pl,p);
	}
}
