package sanjose;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class ThumbnailsServlet extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		new Picture().Thumbnail(req.getPathInfo(),rsp);
	}
}
