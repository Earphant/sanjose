package sanjose;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

public class Page {
	private HttpServletResponse response;
	public Page(HttpServletResponse resp){
		response=resp;
	}
	public void Body(String contents) throws IOException{
		response.setContentType("text/html");
		response.getWriter().println(contents);
	}
	public void Head(String title){
		
	}
	public void Out(String contents) throws IOException{
		response.getWriter().println(contents);
	}
}
