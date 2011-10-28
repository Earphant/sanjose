package sanjose;

import java.io.IOException;
import javax.jdo.PersistenceManager;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class PostServlet extends HttpServlet{
	//private static final Logger log=Logger.getLogger(PostServlet.class.getName());
	private void getMessage(HttpServletRequest req,HttpServletResponse rsp,
		Page page,PersistenceManager mgr)throws IOException{
		I i=new I(req.getParameter("i"),0);
		I b=new I(req.getParameter("b"),0);
		I r=new I(req.getParameter("re"),0);
		page.title="Post";
		page.aside="<ul><li><a href=/post>Message</a><li><a href=/post/documents>Document</a><li><a href=/post/picture>Picture</a><li><a href=/post/marks>Mark</a><li><a href=/post/events>Event</a><li><a href=/post/upload>Upload</a></ul><ul><li><a href=/post/books>Book</a><li><a href=/post/issues>Issue</a></ul><ul><li><a href=/post/weight>Weight</a><li><a href=/post/heart-rate>Heart Rate</a><li><a href=/post/step>Step</a><li><a href=/post/fat>Fat</a></ul>";
		page.out("<form method=post action=/post?i="+i+"&b="+b+"&re="+r+
			"><textarea name=text rows=10>");
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
		I b=new I(req.getParameter("b"),0);
		I i=new I(req.getParameter("i"),0);
		I re=new I(req.getParameter("re"),0);
		PersistenceManager m=Helper.getMgr();
		try{
			if(i.getSite()==0){
				i=I.store(v,null,0,(byte)0,sn.owner,m,false);
				i.setRef(re);
				i.setBase(b,re);
				if(re.getSite()!=0){
					re=I.query(re,m);
					re.incReplyCounter();
					re.setReplyTime(null);
					m.makePersistent(re);
				}
			}
			else{
				i=I.query(i,m);
				i.setText(v);
				i.setModifyTime(null);
			}
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
				if(n.equalsIgnoreCase("adindividual")||n.equalsIgnoreCase("adorganization")){
					new AdminsServlet().doGet(req,rsp);
					return;
				}
				if(n.equalsIgnoreCase("individual")){
					new Individual().doGet(req,rsp,p,m);
					return;
				}
				if(n.equalsIgnoreCase("organization")){
					new Organization().doGet(req,rsp,p,m);
					return;
				}
				if(n.equalsIgnoreCase("fat")){
					new Fat().doGet(req,rsp);
					return;
				}
				if(n.equalsIgnoreCase("heart-rate")){
					new HeartRate().doGet(req,rsp,p);
					return;
				}
				
				if(n.equalsIgnoreCase("steps")){
					new Steps().doGet(req,rsp,p);
					return;
				}
				if(n.equalsIgnoreCase("tags")){
					new Tags().doGet(req,rsp,p);
					return;
				}
				/*
				if(n.equalsIgnoreCase("upload")){
					new Upload().doGet(req,rsp,p);
					return;
				}
				*/
				if(n.equalsIgnoreCase("weight")){
					new Weight().doGet(req,rsp,p);
					return;
				}
				if(n.equalsIgnoreCase("documents")){
					new Document().doGet(req,rsp,p);
					return;
				}
			}
		}
		getMessage(req,rsp,p,m);
	}
	public void doPost(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		String v=req.getPathInfo();
		if(v!=null){
			String[]s=v.split("/");
			if(s.length>1){
				String n=s[1];
				if(n.equalsIgnoreCase("adindividual")||n.equalsIgnoreCase("adorganization")){
					new AdminsServlet().doPost(req,rsp);
					return;
				}
				if(n.equalsIgnoreCase("individual")){
					new Individual().doPost(req,rsp);
					return;
				}
				if(n.equalsIgnoreCase("organization")){
					new Organization().doPost(req,rsp);
					return;
				}
				if(n.equalsIgnoreCase("fat")){
					new Fat().doPost(req,rsp);
					return;
				}
				if(n.equalsIgnoreCase("heart-rate")){
					new HeartRate().doPost(req,rsp);
					return;
				}
				if(n.equalsIgnoreCase("steps")){
					new Steps().doPost(req,rsp);
					return;
				}
				if(n.equalsIgnoreCase("tags")){
					new Tags().doPost(req,rsp);
					return;
				}
				/*
				if(n.equalsIgnoreCase("upload")){
					new Upload().doPost(req,rsp);
					return;
				}
				*/
				if(n.equalsIgnoreCase("weight")){
					new Weight().doPost(req,rsp);
					return;
				}
				if(n.equalsIgnoreCase("documents")){
					new Document().doPost(req,rsp);
					return;
				}
			}
		}
		postMessage(req,rsp);
	}
}
