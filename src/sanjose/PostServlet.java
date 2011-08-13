package sanjose;

import java.io.IOException;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class PostServlet extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		String n=req.getPathInfo();
		if(n!=null){
			String[]s=n.split("/");
			if(s.length>1){
				n=s[1];
				if(n.equalsIgnoreCase("tags")){
					new Tags().doGet(req,rsp);
					return;
				}
				if(n.equalsIgnoreCase("step")){
					new Steps().doGet(req,rsp);
					return;
				}
				if(n.equalsIgnoreCase("heart-rate")){
					new HeartRate().doGet(req,rsp);
					return;
				}
				if(n.equalsIgnoreCase("weight")){
					new Weight().doGet(req,rsp);
					return;
				}
				if(n.equalsIgnoreCase("fat")){
					new Fat().doGet(req,rsp);
					return;
				}
				if(n.equalsIgnoreCase("upload")){
					new Upload().doGet(req,rsp);
					return;
				}

			}
		}
		Page p=new Page(rsp);
		Id id=new Id(req.getParameter("i"));
		p.title="Post";
		p.aside="<ul><li><a href=/post>Message</a><li><a href=/post/documents>Document</a><li><a href=/post/picture>Picture</a><li><a href=/post/marks>Mark</a><li><a href=/post/events>Event</a><li><a href=/post/upload>Upload</a></ul><ul><li><a href=/post/books>Book</a><li><a href=/post/issues>Issue</a></ul><ul><li><a href=/post/weight>Weight</a><li><a href=/post/heart-rate>Heart Rate</a><li><a href=/post/step>Step</a><li><a href=/post/fat>Fat</a></ul>";
		p.out("<form method=post action=/post?i="+id.i+"."+id.j+"><textarea name=text rows=10>");
		if(id.i!=0){
			PersistenceManager mgr=Helper.getMgr();
			Query q=mgr.newQuery(I.class);
			q.setFilter("i==iParam && j==jParam");
			q.declareParameters("Long iParam,Long jParam");
			try{
				@SuppressWarnings("unchecked")
				List<I> r=(List<I>)q.execute(id.i,id.j);
				if(!r.isEmpty()){
					I i=r.get(0);
					p.out(i.getTitle());
				}
			}
			finally{
				q.closeAll();
			}
		}
		p.out("</textarea>");
		p.End("<input type=submit name=ok></form>");
	}
	public void doPost(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		String v=req.getPathInfo();
		if(v!=null){
			String[]s=v.split("/");
			if(s.length>1){
				String n=s[1];
				if(n.equalsIgnoreCase("tags")){
					new Tags().doPost(req,rsp);
					return;
				}
				if(n.equalsIgnoreCase("step")){
					new Steps().doPost(req,rsp);
					return;
				}
				if(n.equalsIgnoreCase("heart-rate")){
					new HeartRate().doPost(req,rsp);
					return;
				}
				if(n.equalsIgnoreCase("weight")){
					new Weight().doPost(req,rsp);
					return;
				}
				if(n.equalsIgnoreCase("fat")){
					new Fat().doPost(req,rsp);
					return;
				}
				if(n.equalsIgnoreCase("upload")){
					new Upload().doPost(req,rsp);
					return;
				}   
			}
		}
		v=req.getParameter("text");
		Session sn=new Session("/post");
		Id id=new Id(req.getParameter("i"));
		PersistenceManager m=Helper.getMgr();
		if(id.i==0){
			I i=new I(v,null,0L,0L,sn.id,sn.site);
			try{
				m.makePersistent(i);
				i.setId(m);
			}
			finally{
				m.close();
			}
		}
		else{
			Query q=m.newQuery(I.class);
			q.setFilter("i==iParam && j==jParam");
			q.declareParameters("Long iParam,Long jParam");
			try{
				@SuppressWarnings("unchecked")
				List<I> r=(List<I>)q.execute(id.i,id.j);
				if(!r.isEmpty()){
					I i=r.get(0);
					i.setText(v);
				}
			}
			finally{
				q.closeAll();
				m.close();
			}
		}
		rsp.sendRedirect("/");
	}
}
