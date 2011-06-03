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
		UserService userService = UserServiceFactory.getUserService();
		String pl=req.getPathInfo();
		Page page=new Page(rsp);

		if(pl.equals("/")||pl==null)
			page.title="System";
		else if(pl.equalsIgnoreCase("/signin")){
			User user = userService.getCurrentUser();
			if(user==null){
				rsp.sendRedirect(userService.createLoginURL("/system/signin"));
				return;
			}
			page.title="Sign In";
			Cookie ck=new Cookie("us","12.3:10&&User&"+user.getNickname());
			ck.setMaxAge(-1);
			ck.setPath("/");
			rsp.addCookie(ck);
			rsp.sendRedirect("/");
		}
		else if(pl.equalsIgnoreCase("/signout")){
			Cookie ck=new Cookie("us",null);
			ck.setMaxAge(0);
			ck.setPath("/");
			rsp.addCookie(ck);
			rsp.sendRedirect(userService.createLogoutURL("/"));
			return;
		}
		else if(pl.equals("/signup"))
			page.title="Sign Up";
		else
			page.title=pl;
		page.Body(null);
	}
}
