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

public class Fat {
	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		Page p=new Page(rsp);
		Timed timed=new Timed(req.getParameter("i"));
		p.title="Fat";
		p.aside="<ul><li><a href=/post>Message</a><li><a href=/post/documents>Document</a><li><a href=/post/picture>Picture</a><li><a href=/post/marks>Mark</a><li><a href=/post/events>Event</a><li><a href=/post/uploads>Upload</a></ul><ul><li><a href=/post/books>Book</a><li><a href=/post/issues>Issue</a></ul><ul><li><a href=/post/weight>Weight</a><li><a href=/post/heartrate>Heart Rate</a><li><a href=/post/steps>Steps</a><li><a href=/post/fat>Fat</a></ul>";
		p.Out("<form method=post action=/post/fat>");
		if(timed.t!=null){
			PersistenceManager mgr=Helper.getMgr();
			Query q=mgr.newQuery(I135.class);
			q.setFilter("n==nParam && o==oParam && t==tParam");
			q.declareImports("import java.util.Date");
			q.declareParameters("Long nParam,Long oParam,Date tParam");
			try{
				@SuppressWarnings("unchecked")
				List<I135> r=(List<I135>)q.execute(timed.n,timed.o,timed.t);
				if(!r.isEmpty()){
					I135 i135=r.get(0);
					Long f=i135.getfat();
					Long w=i135.getwat();
					Date t=i135.gett();
					Calendar cal = Calendar.getInstance();
					cal.setTime(t);
					Long year=(long) cal.get(Calendar.YEAR);
					Long month=(long) cal.get(Calendar.MONTH);
					Long date=(long) cal.get(Calendar.DAY_OF_MONTH);
					Long hour=(long) cal.get(Calendar.HOUR_OF_DAY);
					Long min=(long) cal.get(Calendar.MINUTE);
					p.Out("Fat:<input type=text name=fat value="+f+">Water:<input type=text name=wat value="+w+"><br>Time:<input type=text name=year style=width:40px; value="+year+">年<input type=text name=month style=width:20px; value="+month+">月<input type=text name=date style=width:20px; value="+date+">日 <input type=text name=hour style=width:20px; value="+hour+">：<input type=text name=min style=width:20px; value="+min+">");
					
				}
			}
			finally{
				q.closeAll();
			}
			p.Out("<input type=hidden name=i value="+timed.n+"."+timed.o+"."+timed.t.getTime()+">");
		}
		else{
			Date now=new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(now);
			Long year=(long) cal.get(Calendar.YEAR);
			Long month=(long) cal.get(Calendar.MONTH)+1;
			Long date=(long) cal.get(Calendar.DAY_OF_MONTH);
			Long hour=(long) cal.get(Calendar.HOUR_OF_DAY)+8;
			Long min=(long) cal.get(Calendar.MINUTE);
			p.Out("Fat:<input type=text name=fat value=>Water:<input type=text name=wat value=><br>Time:<input type=text name=year style=width:40px; value="+year+">年<input type=text name=month style=width:20px; value="+month+">月<input type=text name=date style=width:20px; value="+date+">日 <input type=text name=hour style=width:20px; value="+hour+">：<input type=text name=min style=width:20px; value="+min+">");
		}
		p.End("<input type=submit name=ok></form>");
	}
	public void doPost(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
        PersistenceManager mgr=Helper.getMgr();
        
        Long fat=Long.parseLong(req.getParameter("fat"));	
        Long wat=Long.parseLong(req.getParameter("wat"));	
        
        int year = (int)Long.parseLong(req.getParameter("year"));
        int month = (int)Long.parseLong(req.getParameter("month"));
        int date = (int)Long.parseLong(req.getParameter("date"));
        int hour = (int)Long.parseLong(req.getParameter("hour"));
        int min = (int)Long.parseLong(req.getParameter("min"));
        int sec = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month,date,hour,min,sec);
        Date t = calendar.getTime();
        
        Timed timed=new Timed(req.getParameter("i"));
        if(timed.t==null){
        	I135 i135=new I135(1L,9L,fat,wat,t);
			try{
				mgr.makePersistent(i135);
			}
			finally{
				mgr.close();
			}
		}
		else{
			Query q=mgr.newQuery(I135.class);
			q.setFilter("n==nParam && o==oParam && t==tParam");
			q.declareImports("import java.util.Date");
			q.declareParameters("Long iParam,Long jParam,Date tParam");
			try{
				@SuppressWarnings("unchecked")
				List<I135> r=(List<I135>)q.execute(timed.n,timed.o,timed.t);
				if(!r.isEmpty()){
					I135 i135=r.get(0);
					i135.setfat(fat);
					i135.setwat(wat);
					i135.sett(t);
				}
			}
			finally{
				q.closeAll();
				mgr.close();
			}
		}
		rsp.sendRedirect("/12.3/fat");
	}
	public void Out(String plink,Page page) throws IOException{
		page.title="Fat";
		page.aside="<ul><li><a href=/post/fat>Post</a></ul><ul><li><a href=/system/settings>Settings</a><li><a href=/12.3/profile>Profile</a><li><a href=/12.3/contacts>Contacts</a><li><a href=/12.3/tags>Tags</a></ul><ul><li><a href=/12.3/dashboard>Dashboard</a><li><a href=/12.3/activities>Activities</a><li><a href=/12.3/historical>Historical</a></ul><ul><li><a href=/12.3/weight>Weight</a><li><a href=/12.3/heartrate>Heart Rate</a><li><a href=/12.3/steps>Steps</a><li><a href=/12.3/fat>Fat</a></ul>";
		PersistenceManager mgr=Helper.getMgr();
		Query q=mgr.newQuery(I135.class);
		q.setOrdering("t desc");
		try{
			@SuppressWarnings("unchecked")
			List<I135> r=(List<I135>)q.execute();
			if(!r.isEmpty()){
				for(I135 i135:r){
					long t = i135.gett().getTime();
					SimpleDateFormat time=new SimpleDateFormat("yyyy年MM月dd日 hh:mm");
					page.Out(time.format(t)+"<br>"+i135.getn()+"."+i135.geto()+":  Fat: "+i135.getfat()+" Water: "+i135.getwat()+" <a href=/post/fat?i="+i135.getn()+"."+i135.geto()+"."+i135.gett().getTime()+">修改</a><br>");
				}
			}
		}
		finally{
			q.closeAll();
		}
		page.End(null);
	}
	
}