package sanjose;

import java.io.IOException;
import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HeartRate extends DataText{
	public void doGet(HttpServletRequest req,HttpServletResponse rsp,Page page)
		throws IOException{
		I i=I.timed(req.getParameter("i"),0);
		page.title="Heart Rate";
		page.aside="<ul><li><a href=/post>Message</a><li><a href=/post/documents>Document</a><li><a href=/post/picture>Picture</a><li><a href=/post/marks>Mark</a><li><a href=/post/events>Event</a><li><a href=/post/upload>Upload</a></ul><ul><li><a href=/post/books>Book</a><li><a href=/post/issues>Issue</a></ul><ul><li><a href=/post/weight>Weight</a><li><a href=/post/heart-rate>Heart Rate</a><li><a href=/post/steps>Steps</a><li><a href=/post/fat>Fat</a></ul>";		if(i==null){
			page.out("<form method=post action=/post/heart-rate>");
			page.out("Value<br><input type=text name=v>");
		}
		else{
			page.out("<form method=post action=/post/heart-rate?i="+i.getTimed()+">");
			page.out("Value<br><input type=text name=v value="+
				getSingleVal(i,I136.class)+">");
		}
		page.end("<br><input type=submit name=ok></form>");
	}
	public void doPost(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		Session sn=new Session("/");
		I i=I.timed(req.getParameter("i"),0);
		long v=Long.parseLong(req.getParameter("v"));
		if(i==null){
			i=new I(sn.owner.getId(),sn.owner.getSite());
			i.setModifyTime(null);
		}
		PersistenceManager mgr=Helper.getMgr();
		try{
			I136 I136=new I136(i,i.getModifyTime(),v);
			mgr.makePersistent(I136);
			updatePost(i,136,getHtml(i,I136.class,null,28,mgr),"Heart Rate",
				"heart-rate",mgr);
		}
		finally{
			mgr.close();
		}
		rsp.sendRedirect("/"+i+"/heart-rate");
	}
	public void out(String plink,Page page) throws IOException{
		String[]s=plink.split("/");
		I w=new I(s[1],0);
		page.title="Heart Rate";
		page.aside="<ul><li><a href=/post/heart-rate>Post</a></ul><ul><li><a href=/system/settings>Settings</a><li><a href=/"+w+"/profile>Profile</a><li><a href=/"+w+"/contacts>Contacts</a><li><a href=/"+w+"/tags>Tags</a></ul><ul><li><a href=/"+w+"/dashboard>Dashboard</a><li><a href=/"+w+"/activities>Activities</a><li><a href=/"+w+"/historical>Historical</a></ul><ul><li><a href=/"+w+"/weight>Weight</a><li><a href=/"+w+"/heart-rate>Heart Rate</a><li><a href=/"+w+"/steps>Steps</a><li><a href=/"+w+"/fat>Fat</a></ul>";		page.out("<div class=grf2>");
		page.out(getHtml(w,I136.class,"/post/heart-rate?i="+w+".",28,
			Helper.getMgr()));
		page.out("</div>");
		page.out("<div class=grf2>");
		page.out(getHtml(w,I136.class,"/post/heart-rate?i="+w+".",88,
			Helper.getMgr()));
		page.out("</div>");
		page.end(null);
	}
}