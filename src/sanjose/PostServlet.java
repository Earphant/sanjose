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
		Page p=new Page(rsp);
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
					new Organization().doGet(req,rsp,p);
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
					p.out(i.getText());
				}
			}
			finally{
				q.closeAll();
			}
		}
		p.out("</textarea>");
		p.end("<input type=submit name=ok></form>");
	}
	public void doPost(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
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
		Session sn=new Session("/post");
		v=req.getParameter("text");
		I i=new I(req.getParameter("i"));
		PersistenceManager m=Helper.getMgr();
		try{
			if(i.getSite()==0)
				new I(v,null,0,0,sn.owner,m);
			else{
				I o=I.query(i,m);
				o.setText(v);
				o.setModifyTime(null);
			}
		}
		finally{
			m.close();
		}
		rsp.sendRedirect("/");
	}
}
