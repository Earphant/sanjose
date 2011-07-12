package sanjose;

import java.io.IOException;
import javax.servlet.http.*;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class SystemServlet extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		UserService ur=UserServiceFactory.getUserService();
		String pl=req.getPathInfo();
		Page page=new Page(rsp);

		if(pl.equals("/"))
			page.title="System";
		else if(pl.equalsIgnoreCase("/signin")){
			User us=ur.getCurrentUser();
			if(us==null){
				rsp.sendRedirect(ur.createLoginURL("/system/signin"));
				return;
			}
			page.title="Sign In";
			Cookie ck=new Cookie("us","12.3:10&12.3&User&"+us.getNickname());
			ck.setMaxAge(-1);
			ck.setPath("/");
			rsp.addCookie(ck);
			rsp.sendRedirect("/");
			return;
		}
		else if(pl.equalsIgnoreCase("/signout")){
			Cookie ck=new Cookie("us",null);
			ck.setMaxAge(0);
			ck.setPath("/");
			rsp.addCookie(ck);
			rsp.sendRedirect(ur.createLogoutURL("/"));
			return;
		}
		else if(pl.equals("/signup"))
			page.title="Sign Up";
		else
			page.title=pl;
		page.Head(null);
		page.Body(null);
	}
}
