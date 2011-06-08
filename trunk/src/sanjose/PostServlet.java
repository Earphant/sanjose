package sanjose;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class PostServlet extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		Page page=new Page(rsp);
	
		page.title="Post";
		page.aside="<ul><li><a href=/post/>Message</a><li><a href=/post/documents>Document</a><li><a href=/post/picture>Picture</a><li><a href=/post/marks>Mark</a><li><a href=/post/events>Event</a></ul><ul><li><a href=/post/parameters>Parameter</a><li><a href=/post/issues>Issue</a></ul>";
		page.Out(req.getPathInfo());
		page.Out("<br>");
		page.End("<form method=post action=/post/><textarea rows=10></textarea><input type=submit name=ok></form>");
	}
	public void doPost(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		rsp.sendRedirect("/");
	}
}
