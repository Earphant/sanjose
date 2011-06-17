package sanjose;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class PostServlet extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		Page p=new Page(rsp);
		p.title="Post";
		p.aside="<ul><li><a href=/post/>Message</a><li><a href=/post/documents>Document</a><li><a href=/post/picture>Picture</a><li><a href=/post/marks>Mark</a><li><a href=/post/events>Event</a><li><a href=/post/uploads>Upload</a></ul><ul><li><a href=/post/books>Book</a><li><a href=/post/parameters>Parameter</a><li><a href=/post/issues>Issue</a></ul>";
		p.Out(req.getPathInfo());
		p.Out("<br>");
		p.End("<form method=post action=/post/><textarea name=text rows=10></textarea><input type=submit name=ok></form>");
	}
	public void doPost(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		String s=req.getParameter("text");
		PersistenceManager mgr=Helper.getMgr();
		I i=new I(s,"",0);
		try{
			mgr.makePersistent(i);
		}
		finally{
			mgr.close();
		}
		rsp.sendRedirect("/");
	}
}
