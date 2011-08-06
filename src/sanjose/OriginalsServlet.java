package sanjose;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class OriginalsServlet extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		new Picture().Original(req.getPathInfo(),rsp);
	}
}
