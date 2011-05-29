package sanjose;

import com.google.appengine.api.users.*;

import java.io.IOException;
import javax.servlet.http.*;

import com.google.appengine.api.users.User;

@SuppressWarnings("serial")
public class HomeServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		Page page=new Page(resp);

		page.Body("<h1>Home</h1>");
		page.Out("<a href=\"/\">Home5</a><br>");
		if(user==null){
			page.Out("<a href=\"signin\">Sign in</a><br>");
		}
		else{
			page.Out("<a href=\"signout\">Sign out</a><br>");
		}
		page.Out("<a href=\"about.html\">About</a>");
	}
}
