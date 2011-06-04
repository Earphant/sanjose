package sanjose;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class HomeServlet extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse resp)
		throws IOException{
		String pl=req.getPathInfo();
		Page page=new Page(resp);

		if(pl==null||pl.equals("/"))
			page.title="Home";
		else
			page.title=pl;
		page.Head(null);
		page.Body(null);
	}
}
