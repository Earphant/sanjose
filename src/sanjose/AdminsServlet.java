package sanjose;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class AdminsServlet extends HttpServlet{
	private void Users(HttpServletRequest req,HttpServletResponse rsp) throws IOException{
		Page page=new Page(rsp);
		page.title="Users";
		page.aside="<ul><li><a href=/post>Post</a><li><a href=/system/settings>Settings</a><li><a href=/12.3/dashboard>Dashboard</a></ul>";
		PersistenceManager mgr=Helper.getMgr();
		Query q=mgr.newQuery(I.class);
		q.setOrdering("t desc");
		try{
			@SuppressWarnings("unchecked")
			List<I> r=(List<I>)q.execute();
			if(!r.isEmpty()){
				for(I i:r){
					page.Out(i.getx()+"<br>");
				}
			}
		}
		finally{
			q.closeAll();
		}
		page.End(null);	
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
