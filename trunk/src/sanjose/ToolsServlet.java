package sanjose;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class ToolsServlet extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse resp)
		throws IOException{
		Page page=new Page(resp);
	
		page.title="Tools";
		page.aside="<ul><li><a href=/post/>Post</a><li><a href=/system/settings>Settings</a><li><a href=/12.3/dashboard>Dashboard</a></ul>";
		page.Out("Tools");
		page.End(null);
	}
}
