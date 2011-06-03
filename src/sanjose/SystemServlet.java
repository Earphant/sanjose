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
				rsp.sendRedirect(userService.createLoginURL("/"));
				return;
			}
			page.title="Sign In";
			Cookie k=new Cookie("u",user.getNickname());
			rsp.addCookie(k);
		}
		else if(pl.equalsIgnoreCase("/signout")){
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
