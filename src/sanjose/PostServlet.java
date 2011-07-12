package sanjose;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class PostServlet extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		String[]s=req.getPathInfo().split("/");
		String n="*";
		if(s.length>1){
			n=s[1];
			if(n.equalsIgnoreCase("tags")){
				new Tags().doGet(req,rsp);
				return;
			}
			if(n.equalsIgnoreCase("steps")){
				new Steps().doGet(req,rsp);
				return;
			}
			if(n.equalsIgnoreCase("heartrate")){
				new HeartRate().doGet(req,rsp);
				return;
			}
			if(n.equalsIgnoreCase("weight")){
				new Weight().doGet(req,rsp);
				return;
			}
		}
		Page p=new Page(rsp);
		p.title="Post";
		p.aside="<ul><li><a href=/post/>Message</a><li><a href=/post/documents>Document</a><li><a href=/post/picture>Picture</a><li><a href=/post/marks>Mark</a><li><a href=/post/events>Event</a><li><a href=/post/uploads>Upload</a></ul><ul><li><a href=/post/books>Book</a><li><a href=/post/issues>Issue</a></ul><ul><li><a href=/post/weight>Weight</a><li><a href=/post/heartrate>Heart Rate</a><li><a href=/post/steps>Steps</a></ul>";
		p.Out(n);
		p.Out("<br>");
		p.Out(req.getPathInfo());
		p.Out("<br>");
		p.End("<form method=post action=/post/><textarea name=text rows=10></textarea><input type=submit name=ok></form>");
	}
	public void doPost(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		String[]s=req.getPathInfo().split("/");
		String n="*";
		if(s.length>1){
			n=s[1];
			if(n.equalsIgnoreCase("tags")){
				new Tags().doPost(req,rsp);
				return;
			}
			if(n.equalsIgnoreCase("steps")){
				new Steps().doPost(req,rsp);
				return;
			}
			if(n.equalsIgnoreCase("heartrate")){
				new HeartRate().doPost(req,rsp);
				return;
			}
			if(n.equalsIgnoreCase("weight")){
				new Weight().doPost(req,rsp);
				return;
			}
		}
		String v=req.getParameter("text");
		PersistenceManager mgr=Helper.getMgr();
		I i=new I(v,"",0);
		try{
			mgr.makePersistent(i);
		}
		finally{
			mgr.close();
		}
		rsp.sendRedirect("/");
	}
}
