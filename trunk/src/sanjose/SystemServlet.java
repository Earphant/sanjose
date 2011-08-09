package sanjose;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;
//import com.google.appengine.api.users.User;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class SystemServlet extends HttpServlet{
	private UserService usv=UserServiceFactory.getUserService();
	private Page page;

	@SuppressWarnings("unchecked")
	private void Settings(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{	
		page.title="Settings";
		
		PersistenceManager mgr=Helper.getMgr();	
		
		Session s=new Session("");
		
		Query q12=mgr.newQuery(I12.class);
		q12.setFilter("i==iParam && j==jParam");
		q12.declareParameters("Long iParam,Long jParam");
		try{
			List<I12> r12=(List<I12>)q12.execute(s.id,s.site);
			if(!r12.isEmpty()){	
				page.Out("<a href=/post/uploads?i="+s.id+"."+s.site+".admin><img src=/icons/"+s.id+"."+s.site+"></a><br>");
			}
			else
				page.Out("<a href=/post/uploads?i="+s.id+"."+s.site+".admin><div style=background-color:#EEE;height:48px;width:48px>&nbsp;</div></a>");
		}
		finally{
			q12.closeAll();
		}
		Query q11=mgr.newQuery(I11.class);
		q11.setFilter("i==iParam && j==jParam");
		q11.declareParameters("Long iParam,Long jParam");
		try{
			List<I11> r11=(List<I11>)q11.execute(s.id,s.site);
			if(!r11.isEmpty()){	
				I11 i11=r11.get(0);
				String eml=i11.geteml();
				page.Out("Account:<input type=text name=eml value="+eml+"><br>"
						+"Password:<input type=password name=pwd1 value=><input type=text name=pwd2 value=><br>");		
			}
		}
		finally{
			q11.closeAll();
		}
		Query q=mgr.newQuery(I.class);
		q.setFilter("i==iParam && j==jParam");
		q.declareParameters("Long iParam,Long jParam");
		try{
			List<I> r=(List<I>)q.execute(s.id,s.site);
			if(!r.isEmpty()){
				I i=r.get(0);
				String x=i.getx();
				page.Out("<br>Nick Name:<input type=text name=x value="+x+"><br>");
			}
		}
		finally{
			q.closeAll();
		}
		Query q1=mgr.newQuery(I1.class);
		q1.setFilter("i==iParam && j==jParam");
		q1.declareParameters("Long iParam,Long jParam");
		try{
			List<I1> r1=(List<I1>)q1.execute(s.id,s.site);
			if(!r1.isEmpty()){
				I1 i1=r1.get(0);
				String fsn="";
				if(i1.getfsn()!=null)
					fsn=i1.getfsn();
				String mdn="";
				if(i1.getmdn()!=null)
					mdn=i1.getmdn();
				String lsn="";
				if(i1.getlsn()!=null)
					lsn=i1.getlsn();
				String gnd="";
				if(i1.getgnd()!=null)
					gnd=i1.getgnd();
				String gnd1="";
				String gnd2="";
					if(gnd.equals("female"))
						gnd1="checked";
					if(gnd.equals("male"))
						gnd2="checked";
				int year=0;
				int month=0;
				int date=0;
				if(i1.gett()!=null){
					Date t=i1.gett();
					Calendar cal = Calendar.getInstance();
					cal.setTime(t);
					year= cal.get(Calendar.YEAR);
					month= cal.get(Calendar.MONTH)+1;
					date= cal.get(Calendar.DAY_OF_MONTH);
				}
				String ocp="";
				if(i1.getocp()!=null)
					ocp=i1.getocp();
				Long tel=0L;
				if(i1.gettel()!=null)
					tel=i1.gettel();
				Long zip=0L;
				if(i1.getzip()!=null)
					zip=i1.getzip();
				String add="";
				if(i1.getadd()!=null)
					add=i1.getadd();
				page.Out("First Name:<input type=text name=fsn style=width:60px; value="+fsn+">Middle Name:<input type=text name=mdn style=width:60px; value="+mdn+">Last Name:<input type=text name=lsn style=width:60px; value="+lsn+"><br>"
						+"Gender:<input type=radio name=gnd value=female "+gnd1+">Female<input type=radio name=gnd value=male "+gnd2+">Male<br>"
						+"Birthday:<input type=text name=year style=width:40px; value="+year+">��<input type=text name=month style=width:20px; value="+month+">��<input type=text name=date style=width:20px; value="+date+">��<br>"
						+"Occupation:<input type=text name=ocp value="+ocp+"><br>"
						+"Postal Code:<input type=text name=zip style=width:40px; value="+zip+">Telephone Number:<input type=text name=tel style=width:110px; value="+tel+"><br>"
						+"Address:<textarea name=add rows=1>"+add+"</textarea><br>");
			}
		}
		finally{
			q1.closeAll();
			mgr.close();
		}
		page.Out("<input type=hidden name=i value="+s.id+"."+s.site+">");
		page.End("<input type=submit name=ok></form>");
	}
	private void Signin(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		Session s=new Session("");
		if(s.site==0L){
			rsp.sendRedirect(usv.createLoginURL("/system/signin"));
			return;
		}
		page.title="Sign In";
		rsp.addCookie(s.cookie);
		rsp.sendRedirect("/");
	}
	private void Signout(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		Session ss=new Session(null);
		rsp.addCookie(ss.cookie);
		rsp.sendRedirect(usv.createLogoutURL("/"));
	}
	private void Signup(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{	
		page.title="Sign Up";
	}

	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		page=new Page(rsp);
		String p=req.getPathInfo();

		if(p.equals("/"))
			page.title="System";
		else if(p.equalsIgnoreCase("/settings")){
			Settings(req,rsp);
			return;
		}
		else if(p.equalsIgnoreCase("/signin")){
			Signin(req,rsp);
			return;
		}
		else if(p.equalsIgnoreCase("/signout")){
			Signout(req,rsp);
			return;
		}
		else if(p.equals("/signup")){
			Signup(req,rsp);
			return;
		}
		else
			page.title=p;
		page.Head(null);
		page.Body(null);
	}
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req,HttpServletResponse rsp)
	throws IOException{
		User user=UserServiceFactory.getUserService().getCurrentUser();
		PersistenceManager mgr=Helper.getMgr();	
		Long currenti=0L;
		Long currentj=0L;
		if(user!=null){
			String email=user.getEmail();
			Query q11=mgr.newQuery(I11.class);
	        q11.setFilter("eml==emlParam");
			q11.declareParameters("String emlParam");
			   try{
					List<I11> r11=(List<I11>)q11.execute(email);
					if(!r11.isEmpty()){
						I11 i11=r11.get(0);
						currenti=i11.geti();
						currentj=i11.getj();
					}
			   }
			   finally{
				   q11.closeAll();
			   }
		}
		
		String eml = req.getParameter("eml");
		String pwd1 = req.getParameter("pwd1");
		String pwd2 = req.getParameter("pwd2");
		   String pwd=null;
		   if(pwd1==pwd2 && pwd1!=null)
			   pwd= pwd1;
		Query q11=mgr.newQuery(I11.class);
        q11.setFilter("i==iParam && j==jParam");
		q11.declareParameters("Long iParam,Long jParam");
		   try{
				List<I11> r11=(List<I11>)q11.execute(currenti,currentj);
				if(!r11.isEmpty()){
					I11 i11=r11.get(0);
					i11.seteml(eml);
					if(pwd!=null)
						i11.setpwd(pwd);
				}
			}
			finally{
				q11.closeAll();
			}
		
		String x = req.getParameter("x");
		Query q=mgr.newQuery(I.class);
        q.setFilter("i==iParam && j==jParam");
		q.declareParameters("Long iParam,Long jParam");
		   try{
				List<I> r=(List<I>)q.execute(currenti,currentj);
				if(!r.isEmpty()){
					I i=r.get(0);
					i.setx(x);
				}
			}
			finally{
				q.closeAll();
			}
		
		String fsn = req.getParameter("fsn");
		String mdn = req.getParameter("mdn");
		String lsn = req.getParameter("lsn");
		String gnd = req.getParameter("gnd");
		   int year = Integer.parseInt(req.getParameter("year"));
           int month = Integer.parseInt(req.getParameter("month"));
           int date = Integer.parseInt(req.getParameter("date"));
           Calendar calendar = Calendar.getInstance();
           Date t=null;
           if(year!=0L || month!=0L || date!=0L){
        	   calendar.set(year,month-1,date);
        	   t = calendar.getTime();
           }
        String ocp = req.getParameter("ocp");
        Long zip=Long.parseLong(req.getParameter("zip"));
        Long tel=Long.parseLong(req.getParameter("tel"));
        String add = req.getParameter("add");
        Query q1=mgr.newQuery(I1.class);
        q1.setFilter("i==iParam && j==jParam");
		q1.declareParameters("Long iParam,Long jParam");
		   try{
				List<I1> r1=(List<I1>)q1.execute(currenti,currentj);
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
				}
			}
			finally{
				q1.closeAll();
				mgr.close();
			}
		rsp.sendRedirect("/"+currenti+"."+currentj+"/");
	}
}