package sanjose;

import java.io.IOException;
import java.util.Date;

import javax.jdo.PersistenceManager;
import javax.servlet.http.*;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class SystemServlet extends HttpServlet{
	private UserService usv=UserServiceFactory.getUserService();
	private Page page;
	private String jmp;

	private void follow(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		Session s=new Session("ow");
		I d=new I(req.getParameter("i"),1);
		PersistenceManager m=Helper.getMgr();
		I21 i=new I21(d,s.owner,new Date());
		try{
			m.makePersistent(i);
		}finally {
			m.close();
		}
		rsp.sendRedirect("/"+d+"/");
	}
	private void settings(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{	
		page.title="Settings";
		I owner=new Session("/tools/settings").owner;
		PersistenceManager mgr=Helper.getMgr();	
		page.out("<a href=/post/upload?i="+owner+"><img src=/icons/"+owner+" class=icon></a><br>");
		page.out("<form method=post action=/post/individual?i="+owner+">");
		Individual.individualGet(owner,page,mgr);
	}
	private void signin(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		Session s=new Session("");
		if(s.owner==null){
			rsp.sendRedirect(usv.createLoginURL(jmp==null?"/system/signin":
				"/system/signin?jmp="+jmp));
			return;
		}
		page.title="Sign In";
		rsp.addCookie(s.cookie);
		rsp.sendRedirect(jmp==null?"/":jmp);
	}
	private void signout(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		Session ss=new Session(null);
		rsp.addCookie(ss.cookie);
		rsp.sendRedirect(usv.createLogoutURL(jmp==null?"/":jmp));
	}
	private void signup(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{	
		page.title="Sign Up";
	}
	private void unfollow(HttpServletRequest req,HttpServletResponse rsp)
	throws IOException{
	Session s=new Session("ow");
	I d=new I(req.getParameter("i"),1);
	PersistenceManager m=Helper.getMgr();   
	I21 i=new I21(d,s.owner,new Date());
	try{
		m.makePersistent(i);
		m.deletePersistent(i);
	}finally {
		m.close();
	}
	rsp.sendRedirect("/"+d+"/");
}
	private void join(HttpServletRequest req,HttpServletResponse rsp)
	throws IOException{
		Session s=new Session("ow");
		I d=new I(req.getParameter("i"),2);
		PersistenceManager m=Helper.getMgr();   
		I21 i=new I21(d,s.owner,new Date());
		try{
			m.makePersistent(i);
		}finally {
			m.close();
		}
		rsp.sendRedirect("/"+d+"/");
}
	private void quit(HttpServletRequest req,HttpServletResponse rsp)
	throws IOException{
	Session s=new Session("ow");
	I d=new I(req.getParameter("i"),2);
	PersistenceManager m=Helper.getMgr();   
	I21 i=new I21(d,s.owner,new Date());
	try{
		m.makePersistent(i);
		m.deletePersistent(i);
	}finally {
		m.close();
	}
	rsp.sendRedirect("/"+d+"/");
}

	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		page=new Page(rsp);
		String p=req.getPathInfo();
		jmp=req.getParameter("jmp");

		if(p.equals("/"))
			page.title="System";
		else if(p.equalsIgnoreCase("/follow")){
			follow(req,rsp);
			return;
		}
		else if(p.equalsIgnoreCase("/settings")){
			settings(req,rsp);
			return;
		}
		else if(p.equalsIgnoreCase("/signin")){
			signin(req,rsp);
			return;
		}
		else if(p.equalsIgnoreCase("/signout")){
			signout(req,rsp);
			return;
		}
		else if(p.equals("/signup")){
			signup(req,rsp);
			return;
		}
		else if(p.equalsIgnoreCase("/unfollow")){
			unfollow(req,rsp);
			return;
		}
		else if(p.equalsIgnoreCase("/join")){
			join(req,rsp);
			return;
		}
		else if(p.equalsIgnoreCase("/quit")){
			quit(req,rsp);
			return;
		}
		else
			page.title=p;
		page.end(null);
	}
	public void doPost(HttpServletRequest req,HttpServletResponse rsp)
	throws IOException{
		PersistenceManager mgr=Helper.getMgr();	
		I i=new Session("").owner;
		Individual.individualPost(req,i,mgr);
		rsp.sendRedirect("/"+i+"/");
	}
}