package sanjose;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class ToolsServlet extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		String n=req.getPathInfo();
		Session ssn=new Session("");
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
		page.aside="<div class=column1></div><ul class=column2><ul><li><a href=/post>Post</a><li><a href=/system/settings>Settings</a><li><a href=/12.3/dashboard>Dashboard</a></ul></ul>";
		if(ssn.owner==null){
			page.out("<a href=/tools/debug>Debug</a><br>");
			page.out("<a href=/system/signin?jmp=%2Ftools>Sign in</a><br>");
			page.out("<a href=/system/signup?jmp=%2Ftools>Sign up</a><br>");
		}
		else{
			page.out("<a href=/admins>Admins</a><br>");
			page.out("<a href=/backend>Backend</a><br>");
			page.out("<a href=/tools/debug>Debug</a><br>");
			page.out("<a href=/system/signout?jmp=%2Ftools>Sign out</a><br>");
			page.out("<a href=/post/upload>Upload</a><br>");
		}
		page.end(null);
	}
}
