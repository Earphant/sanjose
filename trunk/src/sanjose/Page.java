package sanjose;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

public class Page{
	private Boolean full=false;
	private Boolean head=false;
	private HttpServletResponse response;
	private PrintWriter writer;

	public Page(HttpServletResponse resp)throws IOException{
		response=resp;
		writer=resp.getWriter();
	}

	public void begin()throws IOException{
		if(!head)
			Head(null);
		writer.print("<body class=c90><nav>"+system+nav+"</nav><header>"+
			header+"</header>");
		if(aside==null)
			writer.print("<article class=full><h1>"+title+"</h1>");
		else
			writer.print("<aside>"+aside+"</aside><article><h1>"+title+"</h1>");
	}
	public void end(String cont)throws IOException{
		if(!head)
			begin();
		if(cont!=null)
			response.getWriter().println(cont);
		if(!full)
			response.getWriter().println("</article>");
		response.getWriter().println("<footer>"+footer+
			"</footer></body><script src=/js/adxon.js></script><script src=/js/></script></html>");
	}
	public void Full()throws IOException{
		if(!head)
			Head(null);
		response.getWriter().println("<body class=c90><nav>"+system+nav+
			"</nav><header>"+header+"</header><h1>"+title+"</h1>");
		full=true;
	}
	public void Head(String cont)throws IOException{
		response.setContentType(content_type);
		if(cont==null)
			cont="<!doctype html><html><head><meta http-equiv=content-type content="+content_type+"><link rel=stylesheet type=text/css href=/css/><title>"+title+"</title></head><script>document.createElement('article');document.createElement('aside');document.createElement('footer');document.createElement('header');document.createElement('nav');</script>";
		response.getWriter().println(cont);
		head=true;
	}
	public void out(String cont)throws IOException{
		if(!head)
			begin();
		response.getWriter().println(cont);
	}
	public String articale="";
	public String aside="<ul><li><a href=/products/>Products</a><li><a href=/downloads/>Downloads</a><li><a href=/support/>Support</a><li><a href=/community/>Community</a></ul>";
	public String content_type="text/html;charset=UTF-8";
	public String footer="<ul><li><a href=/about/>About</a><li><a href=/about/contact.html>Contact</a><li><a href=/about/privacy.html>Privacy</a><li><a href=/about/term.html>Term</a><li><a href=/forum/>Forum</a></ul>";
	public String header="";
	public String nav="<ul><li><a href=/>Home</a><li><a href=/products/>Products</a><li><a href=/downloads/>Downloads</a><li><a href=/support/>Support</a><li><a href=/community/>Community</a></ul>";
	public String system="<ul class=c99><li><a href=/tools/>Tools</a><li><a href=/system/>System</a></ul>";
	public String title="";
}
