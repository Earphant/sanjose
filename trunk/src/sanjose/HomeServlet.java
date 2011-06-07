package sanjose;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class HomeServlet extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse resp)
		throws IOException{
		String pl=req.getPathInfo();
		Page page=new Page(resp);
		if(pl.equals("/"))
			page.title="Home";
		else
			new Based(pl,page);
		page.End("<form method=post action=/post/><textarea rows=5></textarea><input type=submit name=ok></form>");
	}
}
