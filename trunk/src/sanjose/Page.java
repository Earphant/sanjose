package sanjose;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

public class Page{
	private HttpServletResponse response;

	public Page(HttpServletResponse resp){
		response=resp;
	}

	public void Aside(String cont)throws IOException{
		if(cont==null)
			cont="<ul><li><a href=\"/products/\">Products</a><li><a href=\"/downloads/\">Downloads</a><li><a href=\"/support/\">Support</a><li><a href=\"/community/\">Community</a></ul>";
		response.getWriter().println(cont);
	}
	public void Body(String cont)throws IOException{
		response.setContentType(content_type);
		if(cont==null)
			cont="<h1>"+title+"</h1>";
		response.getWriter().println(cont);
	}
	public void Footer(String cont)throws IOException{
		if(cont==null)
			cont="<ul><li><a href=\"/about/\">About</a><li><a href=\"/about/contact.html\">Contact</a><li><a href=\"/forum/\">Forum</a><br></ul>";
		response.getWriter().println(cont);
	}
	public void Head(String title){
		
	}
	public void Navi(String cont)throws IOException{
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		if(cont==null){
			cont="<ul><li><a href=\"/\">Home</a><li><a href=\"/products/\">Products</a><li><a href=\"/downloads/\">Downloads</a><li><a href=\"/support/\">Support</a><li><a href=\"/community/\">Community</a></ul><ul id=\"sign\">";
			if(user==null)
				cont+="<li><a href=\"/tools/signin\">Sign in</a><li><a href=\"/tools/signup\">Sign up</a>";
			else
				cont+="<li><a href=\"/mail/\">Inbox</a><li><a href=\"/tools/signout\">Sign out</a>";
			cont+="</ul>";
		}
		response.getWriter().println(cont);
	}
	public void Out(String cont)throws IOException{
		response.getWriter().println(cont);
	}
	public String title="";
	public String content_type="text/html";
}
