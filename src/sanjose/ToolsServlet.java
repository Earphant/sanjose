package sanjose;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class ToolsServlet extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		String n=req.getPathInfo();
		if(n!=null){
			String[]s=n.split("/");
			if(s.length>1){
				n=s[1];
				if(n.equalsIgnoreCase("debug")){
					new Debug().doGet(req,rsp);
					return;
				}		
			}
		}
		Page page=new Page(rsp);
		page.title="Tools";
		page.aside="<ul><li><a href=/post>Post</a><li><a href=/system/settings>Settings</a><li><a href=/12.3/dashboard>Dashboard</a></ul>";
		page.Out("<a href=/tools/debug>Debug</a><br>");
		page.Out("<a href=/admins>Admins</a><br>");
		page.End(null);
	}
}
