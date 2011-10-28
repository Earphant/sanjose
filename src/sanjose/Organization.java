package	sanjose;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Organization{
	static public String menu(I id,Session ssn,PersistenceManager mgr){
		String ret=null;
		Long I=id.getId();
		Long J=id.getSite();
		Long O = null;
		Long W = null;
		Query q=mgr.newQuery(I.class);		
		q.setFilter("i==iParam && j==jParam ");	
		q.declareParameters("Long iParam,Long jParam");  
		try{
			@SuppressWarnings("unchecked")
			List<I> r=(List<I>)q.execute(I,J);
			if(!r.isEmpty()){
				Long a=r.get(0).getType();	
			    if(a==2){
			    	O=r.get(0).getOwner().getId();
			    	W=r.get(0).getOwner().getSite();
			    }			    
			}
		}
		finally{
			q.closeAll();
		}
		if(O==ssn.owner.getId() && W==ssn.owner.getSite())
			ret="<ul><li><a href=/post>Post</a><li><a href=/system/settings?i="+id+">Settings</a></ul><ul><li><a href=/"+id+"/contacts>members</a></ul>";
		else{
			Query q21=mgr.newQuery(I21.class);
			I o=ssn.owner;
			q21.setFilter("o=="+o.getId()+" && w=="+o.getSite()+" && i=="+
				id.getId()+" && j=="+id.getSite());
			try{
				@SuppressWarnings("unchecked")
				List<I21> r=(List<I21>)q21.execute();
				ret=r.isEmpty()?"<ul><li><a href=/system/join?i="+id+">Join</a><li><a href=/post?b="+id+">Post</a></ul><ul><li><a href=/"+id+"/contacts>members</a></ul>":
					"<ul><li><a href=/system/quit?i="+id+">Quit</a><li><a href=/post?b="+id+">Post</a></ul><ul><li><a href=/"+id+"/contacts>members</a></ul>";
			}
			finally{
				q21.closeAll();
			}
		}
		return ret;
	}
	//@SuppressWarnings("unchecked")
	static public void out(I id,Page page,PersistenceManager mgr,Session ssn)
		throws IOException{
		page.title=id.getTitle(false);
		page.aside=menu(id,ssn,mgr);		
		Query q=mgr.newQuery(I.class);
		q.setFilter("b==oParam && s==wParam ");	
		q.declareParameters("Long oParam,Long wParam");
        q.setOrdering("m desc");
		try{
            I.table(q.execute(id.getId(),id.getSite()),page);
//            I.table(q.execute(),page);
		}
		finally{
			q.closeAll();
		}
		page.end(null);
	}
	public void doGet(HttpServletRequest req,HttpServletResponse rsp,Page page,
		PersistenceManager mgr)throws IOException{
		I i=new I(req.getParameter("i"),0);
		page.title="Organization";
		page.aside="<ul><li><a href=/post>Message</a><li><a href=/post/documents>Document</a><li><a href=/post/picture>Picture</a><li><a href=/post/marks>Mark</a><li><a href=/post/events>Event</a><li><a href=/post/upload>Upload</a></ul><ul><li><a href=/post/books>Book</a><li><a href=/post/issues>Issue</a></ul><ul><li><a href=/post/weight>Weight</a><li><a href=/post/heart-rate>Heart Rate</a><li><a href=/post/steps>Steps</a><li><a href=/post/fat>Fat</a></ul>";
		if(i.getSite()==0)
			page.out("<form method=post action=/post/organization><input type=text name=text>");
		else{
			i=I.query(i,mgr);
			page.out("<a href=/post/upload?i="+i.getId()+"."+i.getSite()+"><img src=/icons/"+i.getId()+"."+i.getSite()+" class=icon></a><br>");
			page.out("<form method=post action=/post/organization?i="+i+">");
			organizationGet(i,page,mgr);
		}
		page.out("<input type=hidden name=i value="+i.getId()+"."+i.getSite()+">");
		page.end("<input type=submit name=ok></form>");
	}
	public void doPost(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		Session sn=new Session("/post/organization");
		I i=new I(req.getParameter("i"),0);
		I o;
		PersistenceManager m=Helper.getMgr();
		try{
			if(i.getSite()==0){
				String v=req.getParameter("text");
				o=I.store(v,null,2,(byte)0,sn.owner,m,true);
				I21 i21=new I21(o,o.getOwner(),new Date());
			    m.makePersistent(i21);
			}
			else{
				o = I.query(i,m);
				organizationPost(req,o,m);
			}
		}
		finally{
			m.close();
		}
		rsp.sendRedirect("/"+o);
	}
	public static void organizationGet(I i,Page page,PersistenceManager mgr)
	throws IOException{
		page.out("Group Name<br><input type=text name=text value="+i.getText()+"><br>");
		page.out("Quotation<br><textarea name=q rows=10>"+i.getQuotation()+"</textarea><br>");
	}
	public static void organizationPost(HttpServletRequest req,I i,PersistenceManager mgr)
	throws IOException{
		String v=req.getParameter("text");
		String q=req.getParameter("q");
		i.setText(v);
		i.setQuotation(q);
		i.setModifyTime(null);
		mgr.close();
	}
}