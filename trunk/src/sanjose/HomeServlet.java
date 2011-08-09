package sanjose;

import java.io.IOException;
import java.util.List;
import javax.jdo.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class HomeServlet extends HttpServlet{
	private void Signed(Page page,Session ssn)throws IOException{
		page.title="Home";
		page.Out("<form method=post action=/post/><textarea name=text rows=5></textarea><input type=submit name=ok></form>");
		
		PersistenceManager m=Helper.getMgr();
		Query q=m.newQuery(I.class);		
		q.setOrdering("m desc");
		try{
			@SuppressWarnings("unchecked")
			List<I> r=(List<I>)q.execute();
			if(!r.isEmpty()){
				for(I o:r){
					String i=o.geti()+"."+o.getj();
					String x=o.getx();
					String w=o.geto()+"."+o.getw();
					if(x==null || x.equals(""))
						x="<i>(Untitled)</i>";
					switch((int)o.geta()){
					case 12:
						page.Out("<a href=/"+w+"/><img src=/icons/"+w+">:  <a href=/"+w+"/"+i+"><img src=/thumbnails/"+i+"></a><br>");
						break;
					default:
						page.Out("<a href=/"+w+"/><img src=/icons/"+w+"></a>: "+x+"<br>");
					}
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
