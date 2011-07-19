package sanjose;

import java.io.IOException;
import javax.servlet.http.*;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class SystemServlet extends HttpServlet{
	private UserService usv=UserServiceFactory.getUserService();
	private Page page;

	private void Signin(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{	
		User us=usv.getCurrentUser();
		if(us==null){
			rsp.sendRedirect(usv.createLoginURL("/system/signin"));
			return;
		}
		page.title="Sign In";
		Cookie ck=new Cookie("us","12.3:10&12.3&User&"+us.getNickname());
		ck.setMaxAge(-1);
		ck.setPath("/");
		rsp.addCookie(ck);
		rsp.sendRedirect("/");
	}
	private void Signout(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{	
		Cookie ck=new Cookie("us",null);
		ck.setMaxAge(0);
		ck.setPath("/");
		rsp.addCookie(ck);
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