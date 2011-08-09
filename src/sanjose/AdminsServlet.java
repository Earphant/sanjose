package sanjose;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class AdminsServlet extends HttpServlet{
	@SuppressWarnings("unchecked")
	private void Pictures(HttpServletRequest req,HttpServletResponse rsp) throws IOException{
		Page page=new Page(rsp);
		page.title="Pictures";
		page.aside="<ul><li><a href=/admins>Admins</a></ul><ul><li><a href=/admins/pictures>Pictures</a><li><a href=/admins/posts>Posts</a><li><a href=/admins/users>Users</a></ul>";

		Id id = new Id(req.getParameter("i"));
		if(id.i==0L){
			PersistenceManager mgr=Helper.getMgr();
			Query q11=mgr.newQuery(I11.class);
			q11.setOrdering("i desc");
			try{
				List<I11> r=(List<I11>)q11.execute();
				if(!r.isEmpty()){
					for(I11 i11:r){
						page.Out("<a href=/admins/pictures?i="+i11.geti()+"."+i11.getj()+">"+i11.geteml()+"</a><br>");
					}
				}
			}
			finally{
				q11.closeAll();
			}
			page.End(null);
		}
		else{
			page.Out("<form method=post action=/admins/pictures>");
			
			PersistenceManager mgr=Helper.getMgr();	
			Query q=mgr.newQuery(I.class);
			q.setFilter("o==oParam && w==wParam");
			q.declareParameters("Long oParam,Long wParam");
			q.setOrdering("i desc");
			try{
				List<I> r=(List<I>)q.execute(id.i,id.j);
				if(!r.isEmpty()){	
					for(I i:r){
						if(i.geta()==12L)
							page.Out("<a href=/post/uploads?i="+i.getId()+"."+i.getSite()+"><img src=/thumbnails/"+i.getId()+"."+i.getSite()+"></a><br>");

					}
				}
			}
			finally{
				q.closeAll();
				mgr.close();
			}
			page.End(null);
		}
	}
	@SuppressWarnings("unchecked")
	private void Posts(HttpServletRequest req,HttpServletResponse rsp) throws IOException{
		Page page=new Page(rsp);
		page.title="Posts";
		page.aside="<ul><li><a href=/admins>Admins</a></ul><ul><li><a href=/admins/pictures>Pictures</a><li><a href=/admins/posts>Posts</a><li><a href=/admins/users>Users</a></ul>";

		Id id = new Id(req.getParameter("i"));
		if(id.i==0L){
			PersistenceManager mgr=Helper.getMgr();
			Query q11=mgr.newQuery(I11.class);
			q11.setOrdering("i desc");
			try{
				List<I11> r=(List<I11>)q11.execute();
				if(!r.isEmpty()){
					for(I11 i11:r){
						page.Out("<a href=/admins/posts?i="+i11.geti()+"."+i11.getj()+">"+i11.geteml()+"</a><br>");
					}
				}
			}
			finally{
				q11.closeAll();
			}
			page.End(null);
		}
		else{
			page.Out("<form method=post action=/admins/posts>");
			
			PersistenceManager mgr=Helper.getMgr();	
			Query q=mgr.newQuery(I.class);
			q.setFilter("o==oParam && w==wParam");
			q.declareParameters("Long oParam,Long wParam");
			q.setOrdering("t desc");
			try{
				List<I> r=(List<I>)q.execute(id.i,id.j);
				if(!r.isEmpty()){	
					for(I i:r){
						if(i.geta()==12)
							page.Out("<a href=/post/upload?i="+i.getId()+"."+i.getSite()+"><img src=/thumbnails/"+i.getId()+"."+i.getSite()+"></a><br>");
                        if(i.geta()==135){
                        	long t = i.geti135().gettime().getTime();
        					SimpleDateFormat time=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                        	page.Out(time.format(t)+"  Fat:"+i.geti135().getfat()+"  Water:"+i.geti135().getwat());
                        	
                        }
                        if(i.geta()==136){
                        	long t = i.geti136().gettime().getTime();
        					SimpleDateFormat time=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                        	page.Out(time.format(t)+"  Heart Rate:"+i.geti136().getvol());
                        	
                        }
                        if(i.geta()==138){
                        	long t = i.geti138().gettime().getTime();
        					SimpleDateFormat time=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                        	page.Out(time.format(t)+"  Weight:"+i.geti138().getvol());
                        	
                        }
                        if(i.geta()==139){
                        	long t = i.geti139().gettime().getTime();
        					SimpleDateFormat time=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                        	page.Out(time.format(t)+"  Steps:"+i.geti139().getvol());
                        	
                        }	
					}
				}
			}
			finally{
				q.closeAll();
				mgr.close();
			}
			page.End(null);
		}
	}
	@SuppressWarnings("unchecked")
	private void Users(HttpServletRequest req,HttpServletResponse rsp) throws IOException{
		Page page=new Page(rsp);
		page.title="Users";
		page.aside="<ul><li><a href=/admins>Admins</a></ul><ul><li><a href=/admins/pictures>Pictures</a><li><a href=/admins/posts>Posts</a><li><a href=/admins/users>Users</a></ul>";

		Id id = new Id(req.getParameter("i"));
		if(id.i==0L){
			PersistenceManager mgr=Helper.getMgr();
			Query q11=mgr.newQuery(I11.class);
			q11.setOrdering("i desc");
			try{
				List<I11> r=(List<I11>)q11.execute();
				if(!r.isEmpty()){
					for(I11 i11:r){
						page.Out("<a href=/admins/users?i="+i11.geti()+"."+i11.getj()+">"+i11.geteml()+"</a><br>");
					}
				}
			}
			finally{
				q11.closeAll();
			}
			page.End(null);	
		}
		else{
			page.Out("<form method=post action=/admins/users>");
			PersistenceManager mgr=Helper.getMgr();	
			Query q12=mgr.newQuery(I12.class);
			q12.setFilter("i==iParam && j==jParam");
			q12.declareParameters("Long iParam,Long jParam");
			try{
				List<I12> r12=(List<I12>)q12.execute(id.i,id.j);
				if(!r12.isEmpty()){	
					page.Out("<a href=/post/upload?i="+id.i+"."+id.j+".admin><img src=/icons/"+id.i+"."+id.j+"></a><br>");
				}
				else
					page.Out("<a href=/post/upload?i="+id.i+"."+id.j+".admin><div style=background-color:#EEE;height:48px;width:48px>&nbsp;</div></a>");
			}
			finally{
				q12.closeAll();
			}
			
			Query q11=mgr.newQuery(I11.class);
			q11.setFilter("i==iParam && j==jParam");
			q11.declareParameters("Long iParam,Long jParam");
			try{
				List<I11> r11=(List<I11>)q11.execute(id.i,id.j);
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
				List<I> r=(List<I>)q.execute(id.i,id.j);
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
				List<I1> r1=(List<I1>)q1.execute(id.i,id.j);
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
							+"Birthday:<input type=text name=year style=width:40px; value="+year+">年<input type=text name=month style=width:20px; value="+month+">月<input type=text name=date style=width:20px; value="+date+">日<br>"
							+"Occupation:<input type=text name=ocp value="+ocp+"><br>"
							+"Postal Code:<input type=text name=zip style=width:40px; value="+zip+">Telephone Number:<input type=text name=tel style=width:110px; value="+tel+"><br>"
							+"Address:<textarea name=add rows=1>"+add+"</textarea><br>");
				}
			}
			finally{
				q1.closeAll();
				mgr.close();
			}
			page.Out("<input type=hidden name=i value="+id.i+"."+id.j+">");
			page.End("<input type=submit name=ok></form>");
		}
	}

    public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
	    String n=req.getPathInfo();
        if(n!=null){
    	    String[]s=n.split("/");
            if(s.length>1){
            	n=s[1];
                if(n.equalsIgnoreCase("pictures")){
                	Pictures(req,rsp);
                    return;              
                }               
                if(n.equalsIgnoreCase("posts")){
                    Posts(req,rsp);
                    return;
                }               
                if(n.equalsIgnoreCase("users")){
                    Users(req,rsp);
                    return;
                }                      
            }    
        }
        Page page=new Page(rsp);
        page.title="Admins";
        page.aside="<ul><li><a href=/admins>Admins</a></ul><ul><li><a href=/admins/pictures>Pictures</a><li><a href=/admins/posts>Posts</a><li><a href=/admins/users>Users</a></ul>";
        page.Out("<a href=/admins/posts>Posts</a><br>");
        page.Out("<a href=/admins/pictures>Pictures</a><br>");
        page.Out("<a href=/admins/users>Users</a><br>");
        page.End(null);
	}
    @SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req,HttpServletResponse rsp)
    throws IOException{
	Id id = new Id(req.getParameter("i"));
	if(id.i!=0L){
		PersistenceManager mgr=Helper.getMgr();
		
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
				List<I11> r11=(List<I11>)q11.execute(id.i,id.j);
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
				List<I> r=(List<I>)q.execute(id.i,id.j);
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
				List<I1> r1=(List<I1>)q1.execute(id.i,id.j);
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
	    rsp.sendRedirect("/admins/users");
	}
	}
}
