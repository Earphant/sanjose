package sanjose;

import java.io.IOException;
import javax.servlet.http.*;
//import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class SystemServlet extends HttpServlet{
	private UserService usv=UserServiceFactory.getUserService();
	private Page page;

	private void Settings(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{	
		page.title="Settings";
		page.End(null);
	}
	private void Signin(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		Session s=new Session("");
		if(s.site==0L){
			rsp.sendRedirect(usv.createLoginURL("/system/signin"));
			return;
		}
		page.title="Sign In";
		rsp.addCookie(s.cookie);
		rsp.sendRedirect("/");
	}
	private void Signout(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		Session ss=new Session(null);
		rsp.addCookie(ss.cookie);
		rsp.sendRedirect(usv.createLogoutURL("/"));
	}
	private void Signup(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{	
		page.title="Sign Up";
	}

	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		page=new Page(rsp);
		String p=req.getPathInfo();

		if(p.equals("/"))
			page.title="System";
		else if(p.equalsIgnoreCase("/settings")){
			Settings(req,rsp);
			return;
		}
		else if(p.equalsIgnoreCase("/signin")){
			Signin(req,rsp);
			return;
		}
		else if(p.equalsIgnoreCase("/signout")){
			Signout(req,rsp);
			return;
		}
		else if(p.equals("/signup")){
			Signup(req,rsp);
			return;
		}
		else
			page.title=p;
		page.Head(null);
		page.Body(null);
	}
}
