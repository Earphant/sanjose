package sanjose;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.xml.crypto.Data;

public class Steps {
	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		Page p=new Page(rsp);
		Timed timed=new Timed(req.getParameter("i"));
		p.title="Steps";
		p.aside="<ul><li><a href=/post>Message</a><li><a href=/post/documents>Document</a><li><a href=/post/picture>Picture</a><li><a href=/post/marks>Mark</a><li><a href=/post/events>Event</a><li><a href=/post/uploads>Upload</a></ul><ul><li><a href=/post/books>Book</a><li><a href=/post/issues>Issue</a></ul><ul><li><a href=/post/weight>Weight</a><li><a href=/post/heartrate>Heart Rate</a><li><a href=/post/steps>Steps</a><li><a href=/post/fat>Fat</a></ul>";
		p.Out("<form method=post action=/post/steps>");
		
		if(timed.t!=null){
			PersistenceManager mgr=Helper.getMgr();
			Query q=mgr.newQuery(I139.class);
			q.setFilter("i==iParam && j==jParam && t==tParam");	
			q.declareImports("import java.util.Date");
			q.declareParameters("Long iParam,Long jParam,Date tParam");
			
			
			try{
				@SuppressWarnings("unchecked")
				List<I139> r=(List<I139>)q.execute(timed.n,timed.o,timed.t);
				if(!r.isEmpty()){
					I139 i139=r.get(0);
					Long v=i139.getvol();
					Date t=i139.gettime();
					Calendar cal = Calendar.getInstance();
					cal.setTime(t);
					int year= cal.get(Calendar.YEAR);
					int month= cal.get(Calendar.MONTH)+1;
					int date= cal.get(Calendar.DAY_OF_MONTH);
					int hour= cal.get(Calendar.HOUR_OF_DAY);
					int min= cal.get(Calendar.MINUTE);
					int sec = cal.get(Calendar.SECOND);
					p.Out("Value:<input type=text name=steps value="+v+"><br>Time:<input type=text name=year style=width:40px; value="+year+">年<input type=text name=month style=width:20px; value="+month+">月<input type=text name=date style=width:20px; value="+date+">日 <input type=text name=hour style=width:20px; value="+hour+">:<input type=text name=min style=width:20px; value="+min+">:<input type=text name=sec style=width:20px; value="+sec+">");
				}
			}
			finally{
				q.closeAll();
			}
			p.Out("<input type=hidden name=i value="+timed.n+"."+timed.o+"."+timed.t.getTime()+">");
		}
		else {
			Date now=new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(now);
			int year= cal.get(Calendar.YEAR);
			int month= cal.get(Calendar.MONTH)+1;
			int date= cal.get(Calendar.DAY_OF_MONTH);
			int hour= cal.get(Calendar.HOUR_OF_DAY)+8;
			int min= cal.get(Calendar.MINUTE);
			int sec = cal.get(Calendar.SECOND);
			p.Out("Value:<input type=text name=steps value=><br>Time:<input type=text name=year style=width:40px; value="+year+">年<input type=text name=month style=width:20px; value="+month+">月<input type=text name=date style=width:20px; value="+date+">日 <input type=text name=hour style=width:20px; value="+hour+">:<input type=text name=min style=width:20px; value="+min+">:<input type=text name=sec style=width:20px; value="+sec+">");
		
		}
		p.End("<input type=submit name=ok></form>");
		
	}
	public void doPost(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		PersistenceManager mgr=Helper.getMgr(); 
		String ss=req.getParameter("steps");
		Long vol=Long.parseLong(ss);
		int year = Integer.parseInt(req.getParameter("year"));
		int month = Integer.parseInt(req.getParameter("month"))-1;
		int date = Integer.parseInt(req.getParameter("date"));
		int hour = Integer.parseInt(req.getParameter("hour"));
		int min = Integer.parseInt(req.getParameter("min"));
		int sec = Integer.parseInt(req.getParameter("sec"));

		Calendar calendar = Calendar.getInstance();
		calendar.set(year,month,date,hour,min,sec);
		Date t = calendar.getTime();
		Session s=new Session("/post");
		Timed timed=new Timed(req.getParameter("i"));
		if(timed.t==null){
			try{
				I i=new I("","",139L,0L,1L,1L);
				mgr.makePersistent(i);
				if(i.geti()==0L)
					i.seti();
				i.seto(s.id);
				i.setw(s.site);
				mgr.makePersistent(i);
				I139 i139=new I139(i,vol,t);			
				mgr.makePersistent(i139);
			}
			finally{
				mgr.close();
			}
		}
		else{
			Query q=mgr.newQuery(I139.class);
			q.setFilter("w==nParam && o==oParam && t==tParam");
			q.declareImports("import java.util.Date");
			q.declareParameters("Long iParam,Long jParam,Date tParam");
			try{
				@SuppressWarnings("unchecked")
				List<I139> r=(List<I139>)q.execute(timed.n,timed.o,timed.t);
				if(!r.isEmpty()){
					I139 i139=r.get(0);
					i139.setvol(vol);
				}
			}
			finally{
				q.closeAll();
				mgr.close();
			}
		}
		rsp.sendRedirect("/"+s.id+"."+s.site+"/steps");
	}
	public void Out(String plink,Page page) throws IOException{
		String[]s=plink.split("/");
		String base=s[1];
		page.title="Steps";
		page.aside="<ul><li><a href=/post/step>Post</a></ul><ul><li><a href=/system/settings>Settings</a><li><a href=/"+base+"/profile>Profile</a><li><a href=/"+base+"/contacts>Contacts</a><li><a href=/"+base+"/tags>Tags</a></ul><ul><li><a href=/"+base+"/dashboard>Dashboard</a><li><a href=/"+base+"/activities>Activities</a><li><a href=/"+base+"/historical>Historical</a></ul><ul><li><a href=/"+base+"/weight>Weight</a><li><a href=/"+base+"/heartrate>Heart Rate</a><li><a href=/"+base+"/steps>Steps</a><li><a href=/"+base+"/fat>Fat</a></ul>";
		
		PersistenceManager mgr=Helper.getMgr();
		Query q1=mgr.newQuery(I139.class);
		try{
			@SuppressWarnings("unchecked")
			List<I138> r=(List<I138>)q1.execute();
			String as="steps";
			page.Out("<div class=graf>");
			page.Out(new Graph().Daily(r,as));
			page.Out("</div>");
		}
		finally{
			q1.closeAll();
		}
	 


		
		
		
		
		Query q=mgr.newQuery(I139.class);
		q.setOrdering("t desc");
		try{
			@SuppressWarnings("unchecked")
			List<I139> r=(List<I139>)q.execute();
			if(!r.isEmpty()){
				for(I139 i139:r){
					long t = i139.gettime().getTime();
					SimpleDateFormat time=new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
					page.Out(time.format(t)+"<br>"+base+": "+i139.getvol()+" <a href=/post/steps?i="+i139.geti()+"."+i139.getj()+"."+i139.gettime().getTime()+">修改</a><br>");
				}
			}
		}
		finally{
			q.closeAll();
		}
		page.End(null);
	
               }
           }