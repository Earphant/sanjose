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
		p.aside="<ul><li><a href=/post>Message</a><li><a href=/post/documents>Document</a><li><a href=/post/picture>Picture</a><li><a href=/post/marks>Mark</a><li><a href=/post/events>Event</a><li><a href=/post/upload>Upload</a></ul><ul><li><a href=/post/books>Book</a><li><a href=/post/issues>Issue</a></ul><ul><li><a href=/post/weight>Weight</a><li><a href=/post/heartrate>Heart Rate</a><li><a href=/post/steps>Steps</a><li><a href=/post/fat>Fat</a></ul>";
		p.Out("<form method=post action=/post/steps>");
		
		if(timed.t!=null){
			PersistenceManager mgr=Helper.getMgr();
			Query q=mgr.newQuery(I139.class);
			q.setFilter("o==oParam && w==wParam && t==tParam");
			q.declareImports("import java.util.Date");
			q.declareParameters("Long oParam,Long wParam,Date tParam");
			try{
				@SuppressWarnings("unchecked")
				List<I139> r=(List<I139>)q.execute(timed.o,timed.w,timed.t);
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
					p.Out("Value:<input type=text name=steps value="+v+"><br>Date:<input type=text name=year style=width:40px; value="+year+">-<input type=text name=month style=width:20px; value="+month+">-<input type=text name=date style=width:20px; value="+date+"> Time:<input type=text name=hour style=width:20px; value="+hour+">:<input type=text name=min style=width:20px; value="+min+">:<input type=text name=sec style=width:20px; value="+sec+">");
				}
			}
			finally{
				q.closeAll();
			}
			p.Out("<input type=hidden name=i value="+timed.o+"."+timed.w+"."+timed.t.getTime()+">");
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
			p.Out("Value:<input type=text name=steps value=><br>Date:<input type=text name=year style=width:40px; value="+year+">-<input type=text name=month style=width:20px; value="+month+">-<input type=text name=date style=width:20px; value="+date+"> Time:<input type=text name=hour style=width:20px; value="+hour+">:<input type=text name=min style=width:20px; value="+min+">:<input type=text name=sec style=width:20px; value="+sec+">");
		}
		p.End("<input type=submit name=ok></form>");
	}
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		PersistenceManager mgr=Helper.getMgr();
		
		Long vol=Long.parseLong(req.getParameter("steps"));
		int year = Integer.parseInt(req.getParameter("year"));
		int month = Integer.parseInt(req.getParameter("month"))-1;
		int date = Integer.parseInt(req.getParameter("date"));
		int hour = Integer.parseInt(req.getParameter("hour"));
		int min = Integer.parseInt(req.getParameter("min"));
		int sec = Integer.parseInt(req.getParameter("sec"));
		Calendar calendar = Calendar.getInstance();
		calendar.set(year,month,date,hour,min,sec);
		Date t = calendar.getTime();
		Date now=new Date();
		Session s=new Session("/post");
		Timed timed=new Timed(req.getParameter("i"));
		if(timed.t==null){
			Query qi=mgr.newQuery(I.class);
			qi.setFilter("o==oParam && w==wParam && a==aParam");
			qi.declareParameters("Long oParam,Long wParam,Long aParam");
			try{
				List<I> r=(List<I>)qi.execute(s.id,s.site,139);
				if(r.isEmpty()){
					I i= new I(s.name,"",139L,0L,s.id,s.site);
					i.setModifyTime(now);
					mgr.makePersistent(i);
				}
				else{
					I i=r.get(0);
					i.setModifyTime(now);
					mgr.makePersistent(i);
				}
				I139 i139=new I139(s.id,s.site,vol,t);
				mgr.makePersistent(i139);
			}
			finally{
				qi.closeAll();
				mgr.close();
			}
		}
		else{
			Query q=mgr.newQuery(I139.class);
			q.setFilter("o==oParam && w==wParam && t==tParam");
			q.declareImports("import java.util.Date");
			q.declareParameters("Long oParam,Long wParam,Date tParam");
			try{
				List<I139> r=(List<I139>)q.execute(timed.o,timed.w,timed.t);
				if(!r.isEmpty()){
					I139 i139=r.get(0);
					i139.setVol(vol);
					mgr.makePersistent(i139);
				}
			}
			finally{
				q.closeAll();
			}
			Query qi=mgr.newQuery(I.class);
			qi.setFilter("o==oParam && w==wParam && a==aParam");
			qi.declareParameters("Long oParam,Long wParam,Long aParam");
			try{
				List<I> r=(List<I>)qi.execute(timed.o,timed.w,139);
				if(!r.isEmpty()){
					I i=r.get(0);
					i.setModifyTime(now);
					mgr.makePersistent(i);
				}
			}
			finally{
				qi.closeAll();
				mgr.close();
			}
		}
		rsp.sendRedirect("/"+s.id+"."+s.site+"/steps");
	}
	@SuppressWarnings("unchecked")
	public void Out(String plink,Page page) throws IOException{
		String[]s=plink.split("/");
		String base=s[1];
		page.title="Steps";
		page.aside="<ul><li><a href=/post/steps>Post</a></ul><ul><li><a href=/system/settings>Settings</a><li><a href=/"+base+"/profile>Profile</a><li><a href=/"+base+"/contacts>Contacts</a><li><a href=/"+base+"/tags>Tags</a></ul><ul><li><a href=/"+base+"/dashboard>Dashboard</a><li><a href=/"+base+"/activities>Activities</a><li><a href=/"+base+"/historical>Historical</a></ul><ul><li><a href=/"+base+"/weight>Weight</a><li><a href=/"+base+"/heartrate>Heart Rate</a><li><a href=/"+base+"/steps>Steps</a><li><a href=/"+base+"/fat>Fat</a></ul>";
		Long id=Long.parseLong(base.split("\\.")[0]);
		Long site=Long.parseLong(base.split("\\.")[1]);
		
		PersistenceManager mgr=Helper.getMgr();
		Query q1=mgr.newQuery(I139.class);
		q1.setFilter("o==oParam && w==wParam");
		q1.declareParameters("Long oParam,Long wParam");
		q1.setOrdering("t desc");
		try{
			List<I139> r=(List<I139>)q1.execute(id,site);
			String abc="steps";
			page.Out("<div class=graf>");
			page.Out(new Graph().Daily(r,abc));
			page.Out("</div>");
		}
		finally{
			q1.closeAll();
		}
		Query q2=mgr.newQuery(I139.class);
		q2.setFilter("o==oParam && w==wParam");
		q2.declareParameters("Long oParam,Long wParam");
		q2.setOrdering("t desc");
		try{
			List<I139> r=(List<I139>)q2.execute(id,site);
			if(!r.isEmpty()){			
				for(I139 i139:r){
					long t = i139.gettime().getTime();
					SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					page.Out(time.format(t)+"<br>"+base+": "+i139.getvol()+" <a href=/post/steps?i="+i139.getOwnerId()+"."+i139.getOwnerSite()+"."+i139.gettime().getTime()+">ÐÞ¸Ä</a><br>");
				}
			}
		}
		finally{
			q2.closeAll();
			mgr.close();
		}
		page.End(null);
    }
}