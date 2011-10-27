package sanjose;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
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
	public void individualForm(I id,Page page,PersistenceManager mgr)
		throws IOException{
		page.out("<a href=/post/upload?i="+id+"><img src=/icons/"+id+" class=icon></a><br>");
		page.out("<form method=post action=/post/adminindividual?i="+id+">");
		Individual.individualGet(id,page,mgr);
	}
	@SuppressWarnings("unchecked")
	private void organizationForm(I id,Page page,PersistenceManager mgr)
		throws IOException{
		page.out("<a href=/post/upload?i="+id.getId()+"."+id.getSite()+"><img src=/icons/"+id.getId()+"."+id.getSite()+" class=icon></a><br>");
		String nkn=id.getText()==null?"nick+name":id.getText();

		Query q1=mgr.newQuery(I1.class);
		q1.setFilter("i==iParam && j==jParam");
		q1.declareParameters("Long iParam,Long jParam");
		try{
			List<I1> r1=(List<I1>)q1.execute(id.getId(),id.getSite());
			I1 i1=r1.get(0);
			String fsn=i1.getfsn()==""||i1.getfsn()==null?"first+name":i1.getfsn();
			String mdn=i1.getmdn()==""||i1.getmdn()==null?"middle+name":i1.getmdn();
			String lsn=i1.getlsn()==""||i1.getlsn()==null?"last+name":i1.getlsn();
			String gnf=i1.getgnd()=="female"?"checked":"";
			String gnm=i1.getgnd()=="male"?"checked":"";
			String yir="";
			String mth="";
			String dat="";
			String ocp=i1.getocp();
			String zip=i1.getzip()==0L?"":i1.getzip().toString();
			String tel=i1.gettel()==0L?"":i1.gettel().toString();
			String add=i1.getadd();
			if(i1.gett()!=null){
				Date t=i1.gett();
				Calendar cal = Calendar.getInstance();
				cal.setTime(t);
				yir= Integer.toString(cal.get(Calendar.YEAR));
				mth= Integer.toString(cal.get(Calendar.MONTH)+1);
				dat= Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
			}
			page.title="Individual";
			page.out("<form method=post action=/post/individual?i="+id.getId()+"."+id.getSite()+">");
			page.out("Nick Name<br><input type=text name=text value="+nkn+"><br>");
			page.out("First Name<br><input type=text name=fsn value="+fsn+"><br>");
			page.out("Middle Name<br><input type=text name=mdn value="+mdn+"><br>");
			page.out("Last Name<br><input type=text name=lsn value="+lsn+"><br>");
			page.out("Gender<br><input type=radio name=gnd value=female "+gnf+">Female<input type=radio name=gnd value=male "+gnm+">Male<br>");
			page.out("Birthday<br><input type=text name=yir value="+yir+">-<input type=text name=mth value="+mth+">-<input type=text name=dat value="+dat+"><br>");
			page.out("Occupation<br><input type=text name=ocp value="+ocp+"><br>");
			page.out("Postal Code<br><input type=text name=zip value="+zip+"><br>");
			page.out("Telephone Number<br><input type=text name=tel value="+tel+"><br>");
			page.out("Address<br><textarea name=add rows=1>"+add+"</textarea>");
		}
		finally{
			q1.closeAll();
			mgr.close();
		}
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
		page.end(null);			
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
		if(i.getId()!=0L){
			Individual.individualPost(req,i,mgr);
		}
		rsp.sendRedirect("/admins?a=1");
	}
}
