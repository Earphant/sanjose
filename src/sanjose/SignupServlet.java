package sanjose;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class SignupServlet extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse resp)
		throws IOException{
		Page page=new Page(resp);
	
		page.title="Sign Up";
		page.Nav(null);
		page.Body(null);
		page.Aside(null);
		page.Footer(null);
	}
}
