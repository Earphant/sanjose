package sanjose;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class HomeServlet extends HttpServlet{
	private static final Logger log = Logger.getLogger(HomeServlet.class.getName());

	private void followed(List<I21> list,Page page,PersistenceManager mgr)
		throws IOException{
		log.warning("followed");
		Query q=mgr.newQuery(I.class);
		q.setFilter("o==oParam && w==wParam ");	
		q.declareParameters("Long oParam,Long wParam");
		try{
			for(I21 o:list){
                I.list(q.execute(o.geti(),o.getj()),page);
			}
		}
        finally{
            q.closeAll();
        }
	}
	@SuppressWarnings("unchecked")
	private void signed(Page page,Session ssn)throws IOException{
		I owner=ssn.owner;
		page.title="Home";
		page.aside=("<div class=column1><img src=/thumbnails/"+owner+" class=owner></div><ul class=column2><ul><li><a href=/products/>Products</a><li><a href=/downloads/>Downloads</a><li><a href=/support/>Support</a><li><a href=/community/>Community</a></ul></ul>");
		page.out("<form method=post action=/post/><textarea name=text rows=3></textarea><div class=postsub><input type=submit name=ok></div></form>");
		PersistenceManager m=Helper.getMgr();
		Query q=m.newQuery(I21.class);
		q.setFilter("o==oParam && w==wParam");	
		q.declareParameters("Long oParam,Long wParam");
		try{
			List<I21> r=(List<I21>)q.execute(owner.getId(),owner.getSite());
			if(r.isEmpty())
				unfollowed(page,m);
			else
				followed(r,page,m);
		}
		finally{
			q.closeAll();
		}
		page.end(null);
		return;
    }		
	private void unfollowed(Page page,PersistenceManager mgr)
		throws IOException{
		log.warning("unfollowed");
		Query q=mgr.newQuery(I.class);
		q.setFilter("r==0");
        q.setOrdering("m desc");
		q.setRange(0,25);
		try{
            I.list(q.execute(),page);
		}
		finally{
			q.closeAll();
		}
	}
	private void unsigned(Page page,Session ssn)throws IOException{
		page.title="Home";
		page.begin();
		page.end(null);
	}

	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		String n=req.getPathInfo();
		Page p=new Page(rsp);
		Session s=new Session("/");
		if(n.equals("/")){
			if(s.email==null)
				unsigned(p,s);
			else
				signed(p,s);
		}
		else
			new Based(rsp,req,n,s);
	}	
}				  