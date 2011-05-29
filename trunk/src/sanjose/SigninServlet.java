package sanjose;

import com.google.appengine.api.users.*;
import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class SigninServlet extends HttpServlet {
	private Page page=null;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		if(user != null){
			page=new Page(resp);
			page.Body("Hello, " + user.getNickname());
		}
		else{
			resp.sendRedirect(userService.createLoginURL("/"));
		}
	}
}
