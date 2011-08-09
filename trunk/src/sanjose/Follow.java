package sanjose;

import java.io.IOException;
import java.util.Date;
import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Follow{
	public Follow() {
	}
	public Follow(HttpServletRequest req,HttpServletResponse rsp)
    	throws IOException{
		Session s=new Session("ow");
		Id d=new Id(req.getParameter("i"));
		PersistenceManager m=Helper.getMgr();   
		I21 i=new I21(d.i,d.j,s.id,s.site,new Date());
		try{
			m.makePersistent(i);
		}finally {
			m.close();
		}
		rsp.sendRedirect("/"+d.i+"."+d.j+"/");
	}
	public void Unfollow(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		Session s=new Session("ow");
		Id d=new Id(req.getParameter("i"));
		PersistenceManager m=Helper.getMgr();   
		I21 i=new I21(d.i,d.j,s.id,s.site,new Date());
		try{
			m.makePersistent(i);
			m.deletePersistent(i);
		}finally {
			m.close();
		}
		rsp.sendRedirect("/"+d.i+"."+d.j+"/");
	}
}
