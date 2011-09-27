package sanjose;

import java.io.IOException;
import java.util.Date;
import javax.jdo.PersistenceManager;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Document extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse rsp, Page page)
			throws IOException {
		page.title = "Document";
		page.aside = "<ul><li><a href=/post>Message</a><li><a href=/post/documents>Document</a><li><a href=/post/picture>Picture</a><li><a href=/post/marks>Mark</a><li><a href=/post/events>Event</a><li><a href=/post/upload>Upload</a></ul><ul><li><a href=/post/books>Book</a><li><a href=/post/issues>Issue</a></ul><ul><li><a href=/post/weight>Weight</a><li><a href=/post/heart-rate>Heart Rate</a><li><a href=/post/steps>Steps</a><li><a href=/post/fat>Fat</a></ul>";
		page.out("<form method=post action=/post/documents>");
		page.out("Title:<br><textarea name=text rows=1></textarea>");
		page.out("Abstract:<br><textarea name=abstract rows=5></textarea>");
		page.out("Text:<br><textarea name=html rows=20></textarea>");
		page.end("<input type=submit name=ok value=publish></form>");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse rsp)
			throws IOException {
		Session sn = new Session("/post/documents");
		String base = sn.owner.getId() + "." + sn.owner.getSite();
		String title = req.getParameter("text");
		String abs = req.getParameter("abstract");
		String text = req.getParameter("html");
		PersistenceManager mgr = Helper.getMgr();
		// i=new I(sn.owner.getId(),sn.owner.getSite()); n
		// I i=new I( , ,"",null,8,0,sn.owner.getId(),sn.owner.getSite());
		// I i=new I(sn.owner.getId()+"."+sn.owner.getSite());
		I i = I.store(title,null,8,(byte)0,sn.owner,mgr,false);
		i.setQuotation(abs);
		mgr.makePersistent(i);
		I8 i8 = new I8(i,text,1,new Date());
		mgr.makePersistent(i8);
		mgr.close();
		rsp.sendRedirect("/" + base + "/");
	}
}