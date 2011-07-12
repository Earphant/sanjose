package sanjose;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Weight{
	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		Page p=new Page(rsp);
		p.title="Weight";
		p.aside="<ul><li><a href=/post/>Message</a><li><a href=/post/documents>Document</a><li><a href=/post/picture>Picture</a><li><a href=/post/marks>Mark</a><li><a href=/post/events>Event</a><li><a href=/post/uploads>Upload</a></ul><ul><li><a href=/post/books>Book</a><li><a href=/post/issues>Issue</a></ul><ul><li><a href=/post/weight>Weight</a><li><a href=/post/heartrate>Heart Rate</a><li><a href=/post/steps>Steps</a></ul>";
		p.End("<form method=post action=/post/weight><textarea name=text rows=10></textarea><input type=submit name=ok></form>");
	}
	public void doPost(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		rsp.sendRedirect("/12.3/weight");
	}
	public void Out(String plink,Page page) throws IOException{
		page.title="Weight";
		page.aside="<ul><li><a href=/post/weight>Post</a></ul><ul><li><a href=/system/settings>Settings</a><li><a href=/12.3/profile>Profile</a><li><a href=/12.3/contacts>Contacts</a><li><a href=/12.3/tags>Tags</a></ul><ul><li><a href=/12.3/dashboard>Dashboard</a><li><a href=/12.3/activities>Activities</a><li><a href=/12.3/historical>Historical</a></ul><ul><li><a href=/12.3/weight>Weight</a><li><a href=/12.3/heartrate>Heart Rate</a><li><a href=/12.3/steps>Steps</a></ul>";
		int i;
		for(i=0;i<10;i++)
			page.Out("<h2>Based</h2>Blah blah.");
	}
}
