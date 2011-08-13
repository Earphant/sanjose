package sanjose;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

public class Page{
	private Boolean full=false;
	private Boolean head=false;
	private HttpServletResponse response;

	public Page(HttpServletResponse resp){
		response=resp;
	}

	public void Begin()throws IOException{
		if(!head)
			Head(null);
		response.getWriter().println("<body class=c90><nav>"+system+nav+
			"</nav><header>"+header+"</header><aside>"+aside+
			"</aside><article><h1>"+title+"</h1>");
	}
	public void Body(String cont)throws IOException{
		if(!head)
			Head(null);
		if(cont==null)
			cont="<body class=c90><nav>"+system+nav+"</nav><header>"+header+
				"</header><aside>"+aside+"</aside><article><h1>"+title+
				"</h1>"+articale+"</article><footer>"+footer+
				"</footer></body><script src=/js/adxon.js></script><script src=/js/></script></html>";
		response.getWriter().println(cont);
	}
	public void end(String cont)throws IOException{
		if(!head)
			Begin();
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
			cont="<!doctype html><html><head><meta http-equiv=content-type content="+content_type+"><link rel=stylesheet type=text/css href=/css/><title>"+title+"</title></head>";
		response.getWriter().println(cont);
		head=true;
	}
	public void out(String cont)throws IOException{
		if(!head)
			Begin();
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
