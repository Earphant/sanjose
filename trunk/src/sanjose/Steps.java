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
			q.setFilter("n==nParam && o==oParam && t==tParam");	
			q.declareImports("import java.util.Date");
			q.declareParameters("Long nParam,Long oParam,Date tParam");
			try{
				@SuppressWarnings("unchecked")
				List<I139> r=(List<I139>)q.execute(timed.n,timed.o,timed.t);
				if(!r.isEmpty()){
					I139 i139=r.get(0);
					Long v=i139.getvol();
					Date t=i139.gett();
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
				I139 i139=new I139(s.id,s.site,vol,t);
				try{
					mgr.makePersistent(i139);
				}
				finally{
					mgr.close();
				}
			}
			else{
				Query q=mgr.newQuery(I139.class);
				q.setFilter("n==nParam && o==oParam && t==tParam");
				q.declareImports("import java.util.Date");
				q.declareParameters("Long iParam,Long jParam,Date tParam");
				try{
					@SuppressWarnings("unchecked")
					List<I139> r=(List<I139>)q.execute(timed.n,timed.o,timed.t);
					if(!r.isEmpty()){
						I139 i139=r.get(0);
						i139.setvol(vol);
						i139.sett(t);
					}
				}
				finally{
					q.closeAll();
					mgr.close();
				}
			}
			rsp.sendRedirect("/12.3/steps");
		}
	public void Out(String plink,Page page) throws IOException{
		
		page.title="Steps";
		
		page.aside="<ul><li><a href=/post/steps>Post</a></ul><ul><li><a href=/system/settings>Settings</a><li><a href=/12.3/profile>Profile</a><li><a href=/12.3/contacts>Contacts</a><li><a href=/12.3/tags>Tags</a></ul><ul><li><a href=/12.3/dashboard>Dashboard</a><li><a href=/12.3/activities>Activities</a><li><a href=/12.3/historical>Historical</a></ul><ul><li><a href=/12.3/weight>Weight</a><li><a href=/12.3/heartrate>Heart Rate</a><li><a href=/12.3/steps>Steps</a><li><a href=/12.3/fat>Fat</a></ul>";
		
		page.Out("<div style=background-color:#000;height:1px;width:20px>&nbsp;</div>");
		page.Out("youjww");
		
		
		PersistenceManager mgr=Helper.getMgr();
		Query q1=mgr.newQuery(I139.class);
		
		
		
		
		long dateb=0l;
		q1.setOrdering("t asc");
	
	    long datea=0l;
		try{
			@SuppressWarnings("unchecked")
			List<I139> r=(List<I139>)q1.execute();
		   int iag=0;
		   long t1=0l;
		   long d=0l;
			if(!r.isEmpty())
			{
				
				for(I139 i:r)
					
				{	
					if(iag==0)
					{ 
		     		t1=i.gett().getTime();iag=1;
			        dateb=i.getvol();}
				   	long t = i.gett().getTime();
				    long ls=1000000l;
				    d=t-t1;
				    t1=t;
				    d=d/ls;
				    int s =(int) d;
				    String ss = String.valueOf(s);
					   
				    long dateab=i.getvol();
				  
				    
				    
				    page.Out("<div style=background-color:#F00;height:"+s+"px;width:"+dateb+"px>&nbsp;</div>");
				    dateb=dateab;
					
				}
				Date isf=new Date();
				long nowe=isf.getTime();
				d=nowe-t1;
				long ls=1000000l;
				  d=d/ls;
				  int s =(int) d;
				 page.Out("<div style=background-color:#F00;height:"+s+"px;width:"+dateb+"px>&nbsp;</div>");
				  
				
				
				
				
			}
		}
		finally{
			q1.closeAll();
		}
		
		
		
		
		
	
		
		
		
		page.Out("<div style=background-color:#000;height:1px;width:20px>&nbsp;</div>");
		
		
		
		Query q=mgr.newQuery(I139.class);
		q.setOrdering("t desc");
		try{
			@SuppressWarnings("unchecked")
			List<I139> r=(List<I139>)q.execute();
			if(!r.isEmpty()){
				for(I139 i139:r){
					long t = i139.gett().getTime();
					SimpleDateFormat time=new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
					page.Out(time.format(t)+"<br>"+i139.getn()+"."+i139.geto()+": "+i139.getvol()+" <a href=/post/steps?i="+i139.getn()+"."+i139.geto()+"."+i139.gett().getTime()+">修改</a><br>");
				}
			}
		}
		finally{
			q.closeAll();
		}
		page.End(null);
	}
	
}
