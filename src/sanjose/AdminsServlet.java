package sanjose;

import java.io.IOException;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class AdminsServlet extends HttpServlet{
	private void form(I id,Page page)throws IOException{
		PersistenceManager m=Helper.getMgr();
		I o=I.query(id,m);
		switch((int)o.getType()){
		case 1:
			individualForm(o,page,m);
			break;
		case 2:
			organizationForm(o,page,m);
			break;
		case 12:
			pictureForm(o,page,m);
			break;
		default:
			page.title="Form";
			page.out("<form method=post action=/post?i="+o+"&jmp=/admins?i="+o+">");
			page.out("Text<br><input name=text value="+o.getText()+"><br>");
			page.out("Base<br><input name=b value="+o.getBase()+"><br>");
			page.out("Owner<br><input name=o value="+o.getOwner()+"><br>");
			page.out("Ref<br><input name=re value="+o.getRef()+"><br>");
			page.out("Create<br><input name=t value="+o.getCreateTick()+"><br>");
			page.out("Modify<br><input name=m value="+o.getModifyTick()+"><br>");
			page.out("Rate<br><input name=r value="+o.getRate()+"><br>");
			page.out("<br><input type=submit name=ok value=Ok>");
			page.out("</form>");
		}
		page.end(null);
	}
	private void list(String title,String type,Page page)throws IOException{
		page.title=title;
		PersistenceManager m=Helper.getMgr();
		Query q=m.newQuery(I.class);
		if(type!=null)
			q.setFilter("a=="+type);
		q.setOrdering("m desc");
		q.setRange(0,100);
		try{
			I.tableEx(q.execute(),page);
		}
		finally{
			q.closeAll();
			m.close();
		}
		page.end(null);
	}
	private void individualForm(I id,Page page,PersistenceManager mgr)
		throws IOException{
		page.out("<a href=/post/upload?i="+id+"><img src=/icons/"+id+" class=icon></a><br>");
		page.title="Individual";
		page.out("<form method=post action=/post/adindividual?i="+id+">");
		Individual.individualGet(id,page,mgr);
		page.out("<input type=hidden name=i value="+id+">");
		page.out("<br><input type=submit name=ok></form>");
	}
	private void organizationForm(I id,Page page,PersistenceManager mgr)
		throws IOException{
		page.out("<a href=/post/upload?i="+id.getId()+"."+id.getSite()+"><img src=/icons/"+id.getId()+"."+id.getSite()+" class=icon></a><br>");
		page.title="Groups";
		page.out("<form method=post action=/post/adorganization?i="+id+">");
		Organization.organizationGet(id, page, mgr);
		page.out("<input type=hidden name=i value="+id.getId()+"."+id.getSite()+">");
		page.out("<input type=submit name=ok value=Ok></form>");
	}
	private void pictureForm(I id,Page page,PersistenceManager mgr)
		throws IOException{
		Query q=mgr.newQuery(I.class);
		q.setFilter("a==aParam");
		q.declareParameters("Long aParam");
		q.setOrdering("m desc");
		try{
			@SuppressWarnings("unchecked")
			List<I> r=(List<I>)q.execute(12);
			if(!r.isEmpty()){	
				for(I i:r){
					page.out("<a href=/"+i.getOwner()+"/"+i.getId()+"."+i.getSite()+"><img src=/thumbnails/"+i.getId()+"."+i.getSite()+"></a><br>");
				}
			}
		}
		finally{
			q.closeAll();
			mgr.close();
		}			
	}
    public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
        Page p=new Page(rsp);
        p.nav="<ul><li><a href=/>Home</a><li><a href=/admins?a=2>Groups</a><li><a href=/admins?a=12>Pictures</a><li><a href=/admins?a=>Posts</a><li><a href=/admins?a=1>Users</a></ul>";
        p.aside=null;
		String a=req.getParameter("a");
		I i=new I(req.getParameter("i"),0);
		
		if(a==null){
			if(i.getSite()==0){
		        p.title="Admins";
		        p.out("<a href=/admins?a=2>Groups</a><br>");
		        p.out("<a href=/admins?a=12>Pictures</a><br>");
		        p.out("<a href=/admins?a=>Posts</a><br>");
		        p.out("<a href=/admins?a=1>Users</a><br>");
		        p.end(null);
			}
			else
				form(i,p);
		}
		else{
			if(a.equals(""))
                list("Posts",null,p);
			if(a.equals("12"))
                list("Pictures",a,p);
			if(a.equals("1"))
                list("Users",a,p);
			if(a.equals("2"))
                list("Groups",a,p);
		}
	}
	public void doPost(HttpServletRequest req,HttpServletResponse rsp)
	    throws IOException{
		PersistenceManager mgr=Helper.getMgr();
		I i = new I(req.getParameter("i"),0);
		I o = I.query(i, mgr);
		if(i.getId()!=0L){
			if(o.getType()==1){
				Individual.individualPost(req, o, mgr);
				rsp.sendRedirect("/admins?a=1");
			}
			if(o.getType()==2){
				Organization.organizationPost(req, o, mgr);
				rsp.sendRedirect("/admins?a=2");
			}
		}
	}
}