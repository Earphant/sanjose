package sanjose;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class CommunityServlet extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		Page page=new Page(rsp);

		page.title="Community";
		page.Navi(null);
		page.Body(null);
		page.Aside(null);
		page.Footer(null);
	}
}
