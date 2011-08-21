package sanjose;

import java.io.IOException;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Steps extends DataText{
	private static final Logger log = Logger.getLogger(Steps.class.getName());

	public void doGet(HttpServletRequest req,HttpServletResponse rsp,Page page)
		throws IOException{
		I i=I.timed(req.getParameter("i"));
		page.title="Steps";
		page.aside="<ul><li><a href=/post>Message</a><li><a href=/post/documents>Document</a><li><a href=/post/picture>Picture</a><li><a href=/post/marks>Mark</a><li><a href=/post/events>Event</a><li><a href=/post/upload>Upload</a></ul><ul><li><a href=/post/books>Book</a><li><a href=/post/issues>Issue</a></ul><ul><li><a href=/post/weight>Weight</a><li><a href=/post/heart-rate>Heart Rate</a><li><a href=/post/steps>Steps</a><li><a href=/post/fat>Fat</a></ul>";
		if(i==null){
			log.warning("null");
			page.out("<form method=post action=/post/step>");
			page.out("Value<br><input type=text name=v>");
		}
		else{
			log.warning("value");
			page.out(req.getParameter("i")+"/"+i.getTimed());
			page.out("<form method=post action=/post/step?i="+i.getTimed()+">");
			page.out("Value<br><input type=text name=v value="+
				getSingleVal(i,I139.class)+">");
		}
		page.end("<br><input type=submit name=ok></form>");
	}
	public void doPost(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		Session sn=new Session("/post");
		I i=I.timed(req.getParameter("i"));
		long v=Long.parseLong(req.getParameter("v"));
		if(i==null){
			i=new I(sn.owner.getId(),sn.owner.getSite());
			i.setModifyTime(null);
		}
		PersistenceManager mgr=Helper.getMgr();
		try{
			I139 i139=new I139(i,i.getModifyTime(),v);
			mgr.makePersistent(i139);
			updatePost(i,139,getHtml(i,I139.class,null,28,mgr),"Steps","steps",
				mgr);
		}
		finally{
			mgr.close();
		}
		rsp.sendRedirect("/"+i+"/steps");
	}
	public void out(String plink,Page page)throws IOException{
		String[]s=plink.split("/");
		String b=s[1];
		page.title="Steps";
		page.aside="<ul><li><a href=/post/step>Post</a></ul><ul><li><a href=/system/settings>Settings</a><li><a href=/"+b+"/profile>Profile</a><li><a href=/"+b+"/contacts>Contacts</a><li><a href=/"+b+"/tags>Tags</a></ul><ul><li><a href=/"+b+"/dashboard>Dashboard</a><li><a href=/"+b+"/activities>Activities</a><li><a href=/"+b+"/historical>Historical</a></ul><ul><li><a href=/"+b+"/weight>Weight</a><li><a href=/"+b+"/heart-rate>Heart Rate</a><li><a href=/"+b+"/steps>Steps</a><li><a href=/"+b+"/fat>Fat</a></ul>";
		page.out("<div class=grf2>");
		page.out(getHtml(new I(b),I139.class,"/post/step?i="+b+".",28,
			Helper.getMgr()));
		page.out("</div>");
		page.end(null);
    }
}