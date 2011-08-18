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
			individualForm(id,page,m);
			break;
		case 2:
			organizationForm(id,page,m);
			break;
		case 12:
			pictureForm(id,page,m);
			break;
		default:
			page.title="Form";
		}
		page.end(null);
	}
	@SuppressWarnings("unchecked")
	private void individualForm(I id,Page page,PersistenceManager mgr)
		throws IOException{

		page.out("<a href=/post/upload?i="+id.getId()+"."+id.getSite()+"><img src=/icons/"+id.getId()+"."+id.getSite()+" class=icon></a><br>");
		String nkn=id.getText()==null?"nick+name":id.getText();

		Query q1=mgr.newQuery(I1.class);
		q1.setFilter("i==iParam && j==jParam");
		q1.declareParameters("Long iParam,Long jParam");
		try{
			List<I1> r1=(List<I1>)q1.execute(id.getId(),id.getSite());
			I1 i1=r1.get(0);
			String fsn=i1.getfsn()==""?"first+name":i1.getfsn();
			String mdn=i1.getmdn()==""?"middle+name":i1.getmdn();
			String lsn=i1.getlsn()==""?"last+name":i1.getlsn();
			String gnf=i1.getgnd()=="female"?"":"checked";
			String gnm=i1.getgnd()=="male"?"":"checked";
			String yir="";
			String mth="";
			String dat="";
			String ocp=i1.getocp()==""?"":i1.getocp();
			String zip=i1.getzip()==0L?"":i1.getzip().toString();
			String tel=i1.gettel()==0L?"":i1.gettel().toString();
			String add=i1.getadd()==""?"":i1.getadd();
			if(i1.gett()!=null){
				Date t=i1.gett();
				Calendar cal = Calendar.getInstance();
				cal.setTime(t);
				yir= Integer.toString(cal.get(Calendar.YEAR));
				mth= Integer.toString(cal.get(Calendar.MONTH)+1);
				dat= Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
			}
			page.title="Individual";
			page.out("<form method=post action=/post/individual?i="+id+">");
			page.out("Nick Name<br><input type=text name=nkn value="+nkn+"><br>");
			page.out("First Name<br><input type=text name=fsn value="+fsn+"><br>");
			page.out("Middle Name<br><input type=text name=mdn value="+mdn+"><br>");
			page.out("Last Name<br><input type=text name=lsn value="+lsn+"><br>");
			page.out("Gender&nbsp;&nbsp;<input type=radio name=gnd value=female "+gnf+">Female<input type=radio name=gnd value=male "+gnm+">Male<br>");
			page.out("Birthday<br><input type=text name=yir value="+yir+"><input type=text name=mth value="+mth+"><input type=text name=dat value="+dat+"><br>");
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
		page.end("<input type=submit name=ok value=Ok></form>");
	}
	@SuppressWarnings("unchecked")
	private void list(String title,String type,Page page)throws IOException{
		page.title=title;
		PersistenceManager m=Helper.getMgr();
		Query q=m.newQuery(I.class);
		if(type!=null)
			q.setFilter("a=="+type);
		q.setOrdering("m desc");
		try{
			List<I> r=(List<I>)q.execute();
			if(!r.isEmpty()){
				page.out("<table class=list>");
				for(I i:r)
					page.out("<tr><th width=40%><a href=/"+i.getPath()+">"+i.getTitle(true)+
						"</a><th><a href=/post?i="+i+"&jmp=>"+i.getType()+
						"</a><th>"+i.getOwner()+"<td class=c2 t="+
						i.getModifyTick()+"><td><a href=/admins?i="+i+
						">=</a>");
				page.out("</table>");
			}
		}
		finally{
			q.closeAll();
			m.close();
		}
		page.end(null);
	}
	private void organizationForm(I id,Page page,PersistenceManager mgr)
		throws IOException{
		String nkn="nick+name";
		String fsn="first+name";
		String mdn="middle+name";
		String lsn="last+name";
		String gnf="";
		String gnm="";
		String yir="";
		String mth="";
		String dat="";
		String ocp="";
		String zip="";
		String tel="";
		String add="";
		page.title="Picture";
		page.out("<form method=post action=/post/individual?i="+id+">");
		page.out("Nick Name<br><input type=text name=fsn value="+nkn+"><br>");
		page.out("First Name<br><input type=text name=fsn value="+fsn+"><br>");
		page.out("Middle Name<br><input type=text name=mdn value="+mdn+"><br>");
		page.out("Last Name<br><input type=text name=lsn value="+lsn+"><br>");
		page.out("Gender&nbsp;&nbsp;<input type=radio name=gnd value=female"+gnf+">Female<input type=radio name=gnd value=male"+gnm+">Male<br>");
		page.out("Birthday<br><input type=text name=year value="+yir+"><input type=text name=month value="+mth+"><input type=text name=date value="+dat+"><br>");
		page.out("Occupation<br><input type=text name=ocp value="+ocp+"><br>");
		page.out("Postal Code<br><input type=text name=zip value="+zip+"><br>");
		page.out("Telephone Number<br><input type=text name=tel value="+tel+"><br>");
		page.out("Address<br><textarea name=add rows=1>"+add+"</textarea>");
		page.out("<input type=submit name=ok value=Ok>");
		page.out("</form>");
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
        p.aside="<ul><li><a href=/admins>Admins</a></ul><ul><li><a href=/admins?a=12>Pictures</a><li><a href=/admins?a=>Posts</a><li><a href=/admins?a=1>Users</a></ul>";
		String a=req.getParameter("a");
		I i=new I(req.getParameter("i"));
		if(a==null){
			if(i.getSite()==0){
		        p.title="Admins";
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
				form(i,p);
			if(a.equals("1"))
                list("Users",a,p);
		}
	}
    @SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req,HttpServletResponse rsp)
	    throws IOException{
		I i = new I(req.getParameter("i"));
		if(i.getId()!=0L){
			PersistenceManager mgr=Helper.getMgr();
			String pwd1 = req.getParameter("pwd1");
			String pwd2 = req.getParameter("pwd2");
			   String pwd="";
			   if(pwd1==pwd2 && pwd1!="")
				   pwd= pwd1;
			Query q11=mgr.newQuery(I11.class);
	        q11.setFilter("i==iParam && j==jParam");
			q11.declareParameters("Long iParam,Long jParam");
			   try{
					List<I11> r11=(List<I11>)q11.execute(i.getId(),i.getSite());
					if(!r11.isEmpty()){
						I11 i11=r11.get(0);
						if(pwd!=null){
							i11.setPassword(pwd);
							mgr.makePersistent(i11);
						}
					}
				}
				finally{
					q11.closeAll();
				}
			I ii=I.query(i,mgr);
			ii.setText(req.getParameter("nkn"));
			
			String fsn = req.getParameter("fsn");
			String mdn = req.getParameter("mdn");
			String lsn = req.getParameter("lsn");
			String gnd = req.getParameter("gnd");
			String yir = req.getParameter("yir");
	        String mth = req.getParameter("mth");
	        String dat = req.getParameter("dat");
	           Calendar calendar = Calendar.getInstance();
	           Date t=null;
	        if(yir!="" && mth!="" && dat!=""){
	        	calendar.set(Integer.parseInt(yir),Integer.parseInt(mth),Integer.parseInt(dat));
	        	t = calendar.getTime();
	        }
	        String ocp = req.getParameter("ocp");
	        Long zip=req.getParameter("zip")==""?0L:Long.parseLong(req.getParameter("zip"));
	        Long tel=req.getParameter("tel")==""?0L:Long.parseLong(req.getParameter("tel"));
	        String add = req.getParameter("add");
	        Query q1=mgr.newQuery(I1.class);
	        q1.setFilter("i==iParam && j==jParam");
			q1.declareParameters("Long iParam,Long jParam");
			   try{
					List<I1> r1=(List<I1>)q1.execute(i.getId(),i.getSite());
					if(!r1.isEmpty()){
						I1 i1=r1.get(0);
						i1.setfsn(fsn);
						i1.setfsn(mdn);
						i1.setlsn(lsn);
						i1.setgnd(gnd);
						i1.sett(t);
						i1.setocp(ocp);
						i1.setzip(zip);
						i1.settel(tel);
						i1.setadd(add);
						mgr.makePersistent(i1);
					}
				}
				finally{
					q1.closeAll();
					mgr.close();
				}   
		    rsp.sendRedirect("/admins?a=1");
		}
	}
}
