package sanjose;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class IconsServlet extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		new Picture().Icon(req.getPathInfo(),rsp);
	}
}
