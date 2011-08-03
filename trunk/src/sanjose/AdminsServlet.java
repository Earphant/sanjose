package sanjose;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class AdminsServlet extends HttpServlet{
	private void Users(HttpServletRequest req,HttpServletResponse rsp){
		
	}
	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		String n=req.getPathInfo();
		if(n!=null){
			String[]s=n.split("/");
			if(s.length>1){
				n=s[1];
				if(n.equalsIgnoreCase("users")){
					Users(req,rsp);
					return;
				}		
			}
		}
		Page page=new Page(rsp);
		page.title="Admins";
		page.aside="<ul><li><a href=/post>Post</a><li><a href=/system/settings>Settings</a><li><a href=/12.3/dashboard>Dashboard</a></ul>";
		page.Out("<a href=/admins/users>Users</a><br>");
		page.End(null);
	}
}
