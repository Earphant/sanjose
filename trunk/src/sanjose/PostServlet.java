package sanjose;

import java.io.IOException;
//import java.util.logging.Logger;
import javax.jdo.PersistenceManager;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class PostServlet extends HttpServlet{
	//private static final Logger log=Logger.getLogger(PostServlet.class.getName());
	private void getMessage(HttpServletRequest req,HttpServletResponse rsp,
		Page page,PersistenceManager mgr)throws IOException{
		I i=new I(req.getParameter("i"));
		page.title="Post";
		page.aside="<ul><li><a href=/post>Message</a><li><a href=/post/documents>Document</a><li><a href=/post/picture>Picture</a><li><a href=/post/marks>Mark</a><li><a href=/post/events>Event</a><li><a href=/post/upload>Upload</a></ul><ul><li><a href=/post/books>Book</a><li><a href=/post/issues>Issue</a></ul><ul><li><a href=/post/weight>Weight</a><li><a href=/post/heart-rate>Heart Rate</a><li><a href=/post/step>Step</a><li><a href=/post/fat>Fat</a></ul>";
		page.out("<form method=post action=/post?i="+i+"><textarea name=text rows=10>");
		if(i.getSite()!=0){
			i=I.query(i,mgr);
			page.out(i.getText());
		}
		page.out("</textarea>");
		page.end("<input type=submit name=ok></form>");
	}
	private void postMessage(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		Session sn=new Session("/post");
		String v=req.getParameter("text");
		I i=new I(req.getParameter("i"));
		PersistenceManager m=Helper.getMgr();
		try{
			if(i.getSite()==0)
				I.create(v,null,0,0,sn.owner,m,true);
			else{
				i=I.query(i,m);
				i.setText(v);
				i.setModifyTime(null);
				m.makePersistent(i);
			}
		}
		finally{
			m.close();
		}
		rsp.sendRedirect("/");
	}
	private void postReply(I re,HttpServletRequest req,
		HttpServletResponse rsp)throws IOException{
		Session sn=new Session("/post");
		String v=req.getParameter("text");
		PersistenceManager m=Helper.getMgr();
		try{
			I i=I.create(v,null,0,0,sn.owner,m,false);
			i.setRef(re);
			m.makePersistent(i);
		}
		finally{
			m.close();
		}
		rsp.sendRedirect("/");
	}
	
	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		Page p=new Page(rsp);
		PersistenceManager m=Helper.getMgr();
		String n=req.getPathInfo();
		if(n!=null){
			String[]s=n.split("/");
			if(s.length>1){
				n=s[1];
				if(n.equalsIgnoreCase("fat")){
					new Fat().doGet(req,rsp);
					return;
				}
				if(n.equalsIgnoreCase("heart-rate")){
					new HeartRate().doGet(req,rsp,p);
					return;
				}
				if(n.equalsIgnoreCase("organization")){
					new Organization().doGet(req,rsp,p,m);
					return;
				}
				if(n.equalsIgnoreCase("step")){
					new Steps().doGet(req,rsp,p);
					return;
				}
				if(n.equalsIgnoreCase("tags")){
					new Tags().doGet(req,rsp,p);
					return;
				}
				if(n.equalsIgnoreCase("upload")){
					new Upload().doGet(req,rsp,p);
					return;
				}
				if(n.equalsIgnoreCase("weight")){
					new Weight().doGet(req,rsp,p);
					return;
				}
			}
		}
		getMessage(req,rsp,p,m);
	}
	public void doPost(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		I i=new I(req.getParameter("re"));
		if(i.getSite()!=0){
			postReply(i,req,rsp);
			return;
		}
		String v=req.getPathInfo();
		if(v!=null){
			String[]s=v.split("/");
			if(s.length>1){
				String n=s[1];
				if(n.equalsIgnoreCase("fat")){
					new Fat().doPost(req,rsp);
					return;
				}
				if(n.equalsIgnoreCase("heart-rate")){
					new HeartRate().doPost(req,rsp);
					return;
				}
				if(n.equalsIgnoreCase("organization")){
					new Organization().doPost(req,rsp);
					return;
				}
				if(n.equalsIgnoreCase("step")){
					new Steps().doPost(req,rsp);
					return;
				}
				if(n.equalsIgnoreCase("tags")){
					new Tags().doPost(req,rsp);
					return;
				}
				if(n.equalsIgnoreCase("upload")){
					new Upload().doPost(req,rsp);
					return;
				}   
				if(n.equalsIgnoreCase("weight")){
					new Weight().doPost(req,rsp);
					return;
				}
			}
		}
		postMessage(req,rsp);
	}
}
