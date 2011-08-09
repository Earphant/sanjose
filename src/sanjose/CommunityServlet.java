package sanjose;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class CommunityServlet extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		Page page=new Page(rsp);
		page.title="Community";
		PersistenceManager mgr=Helper.getMgr();
		Query q=mgr.newQuery(I.class);
		q.setFilter("r==0");
		q.setOrdering("m desc");
		try{
			@SuppressWarnings("unchecked")
			List<I> r=(List<I>)q.execute();
			if(!r.isEmpty()){
				for(I i:r){
					String o=i.geto()+"."+i.getw();
					page.Out("<a href=/"+o+"><img class=icon src=/icons/"+o+"></a>");
					page.Out(i.getx()+" <a href=/post?i="+i.geti()+"."+i.getj()+">=</a><br>");
				}
			}
		}
		finally{
			q.closeAll();
		}
		page.End(null);
	}
}
