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

public class HeartRate {
	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		Page p=new Page(rsp);
		Timed timed=new Timed(req.getParameter("i"));
		p.title="Heart Rate";
		p.aside="<ul><li><a href=/post>Message</a><li><a href=/post/documents>Document</a><li><a href=/post/picture>Picture</a><li><a href=/post/marks>Mark</a><li><a href=/post/events>Event</a><li><a href=/post/upload>Upload</a></ul><ul><li><a href=/post/books>Book</a><li><a href=/post/issues>Issue</a></ul><ul><li><a href=/post/weight>Weight</a><li><a href=/post/heartrate>Heart Rate</a><li><a href=/post/steps>Steps</a><li><a href=/post/fat>Fat</a></ul>";
		p.Out("<form method=post action=/post/heartrate>");
		if(timed.t!=null){
			PersistenceManager mgr=Helper.getMgr();
			Query q=mgr.newQuery(I136.class);
			q.setFilter("i==iParam && j==jParam && t==tParam");
			q.declareImports("import java.util.Date");
			q.declareParameters("Long iParam,Long jParam,Date tParam");
			try{
				@SuppressWarnings("unchecked")
				List<I136> r=(List<I136>)q.execute(timed.i,timed.j,timed.t);
				if(!r.isEmpty()){
					I136 i136=r.get(0);
					Long v=i136.getvol();
					Date t=i136.gettime();
					Calendar cal = Calendar.getInstance();
					cal.setTime(t);
					int year= cal.get(Calendar.YEAR);
					int month= cal.get(Calendar.MONTH)+1;
					int date= cal.get(Calendar.DAY_OF_MONTH);
					int hour= cal.get(Calendar.HOUR_OF_DAY);
					int min= cal.get(Calendar.MINUTE);
					int sec = cal.get(Calendar.SECOND);
					p.Out("Value:<input type=text name=rate value="+v+"><br>Time:<input type=text name=year style=width:40px; value="+year+">年<input type=text name=month style=width:20px; value="+month+">月<input type=text name=date style=width:20px; value="+date+">日 <input type=text name=hour style=width:20px; value="+hour+">:<input type=text name=min style=width:20px; value="+min+">:<input type=text name=sec style=width:20px; value="+sec+">");
				    
				}
			}
			finally{
				q.closeAll();
			}
			p.Out("<input type=hidden name=i value="+timed.i+"."+timed.j+"."+timed.t.getTime()+">");
			
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
			p.Out("Value:<input type=text name=rate value=><br>Time:<input type=text name=year style=width:40px; value="+year+">年<input type=text name=month style=width:20px; value="+month+">月<input type=text name=date style=width:20px; value="+date+">日 <input type=text name=hour style=width:20px; value="+hour+">:<input type=text name=min style=width:20px; value="+min+">:<input type=text name=sec style=width:20px; value="+sec+">");
		 }   
		p.End("<input type=submit name=ok></form>");
	}
	public void doPost(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
        PersistenceManager mgr=Helper.getMgr(); 
        String vols=req.getParameter("rate");
        Long vol=Long.parseLong(vols);
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
				I i=new I("","",136L,0L,s.id,s.site);
				mgr.makePersistent(i);
				i.setId();
				I136 i136=new I136(i,vol,t);
				i.seti136(i136);
				mgr.makePersistent(i);
			}
			finally{
				mgr.close();
			}
		}
		else{
			Query q=mgr.newQuery(I136.class);
			q.setFilter("w==wParam && o==oParam && t==tParam");
			q.declareImports("import java.util.Date");
			q.declareParameters("Long iParam,Long jParam,Date tParam");
			try{
				@SuppressWarnings("unchecked")
				List<I136> r=(List<I136>)q.execute(timed.i,timed.j,timed.t);		
				if(!r.isEmpty()){
					I136 i136=r.get(0);
					i136.setvol(vol);
					i136.gettime();
				}
			}
			finally{
				q.closeAll();
				mgr.close();
			}
		}
		rsp.sendRedirect("/"+s.id+"."+s.site+"/heartrate");
	}
	public void Out(String plink,Page page) throws IOException{
		String[]s=plink.split("/");
		String base=s[1];
		page.title="Heart Rate";
		page.aside="<ul><li><a href=/post/heartrate>Post</a></ul><ul><li><a href=/system/settings>Settings</a><li><a href=/"+base+"/profile>Profile</a><li><a href=/"+base+"/contacts>Contacts</a><li><a href=/"+base+"/tags>Tags</a></ul><ul><li><a href=/"+base+"/dashboard>Dashboard</a><li><a href=/"+base+"/activities>Activities</a><li><a href=/"+base+"/historical>Historical</a></ul><ul><li><a href=/"+base+"/weight>Weight</a><li><a href=/"+base+"/heartrate>Heart Rate</a><li><a href=/"+base+"/steps>Steps</a><li><a href=/"+base+"/fat>Fat</a></ul>";
		Long id=Long.parseLong(base.split("\\.")[0]);
		Long site=Long.parseLong(base.split("\\.")[1]);
		
		PersistenceManager mgr=Helper.getMgr();
		Query q1=mgr.newQuery(I136.class);
		q1.setOrdering("t desc");
		try{
			@SuppressWarnings("unchecked")
			List<I136> r=(List<I136>)q1.execute();
			page.Out("<div class=graf>");
			String rate="heartrate";
			page.Out(new Graph().Daily(r,rate));
			page.Out("</div>");
		}
		finally{
			q1.closeAll();
		}	

		Query q2=mgr.newQuery(I.class);
		q2.setFilter("o==oParam && w==wParam");
		q2.declareParameters("Long oParam,Long wParam");
		q2.setOrdering("t desc");
		try{
			@SuppressWarnings("unchecked")
			List<I> r=(List<I>)q2.execute(id,site);
			if(!r.isEmpty()){			
				for(I i:r){
					if(i.geta()==136){
						long t = i.geti136().gettime().getTime();
						SimpleDateFormat time=new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");					
						page.Out(time.format(t)+"<br>"+base+": "+i.geti136().getvol()+" <a href=/post/heartrate?i="+i.geti136().geti()+"."+i.geti136().getj()+"."+i.geti136().gettime().getTime()+">修改</a><br>");				
					}
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