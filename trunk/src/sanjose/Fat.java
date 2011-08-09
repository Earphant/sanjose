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
					int year= cal.get(Calendar.YEAR);
					int month= cal.get(Calendar.MONTH)+1;
					int date= cal.get(Calendar.DAY_OF_MONTH);
					int hour= cal.get(Calendar.HOUR_OF_DAY);
					int min= cal.get(Calendar.MINUTE);
					int sec = cal.get(Calendar.SECOND);
					p.Out("Fat:<input type=text name=fat value="+f+">Water:<input type=text name=wat value="+w+"><br>Time:<input type=text name=year style=width:40px; value="+year+">年<input type=text name=month style=width:20px; value="+month+">月<input type=text name=date style=width:20px; value="+date+">日 <input type=text name=hour style=width:20px; value="+hour+">:<input type=text name=min style=width:20px; value="+min+">:<input type=text name=sec style=width:20px; value="+sec+">");
					
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
			int year= cal.get(Calendar.YEAR);
			int month= cal.get(Calendar.MONTH)+1;
			int date= cal.get(Calendar.DAY_OF_MONTH);
			int hour= cal.get(Calendar.HOUR_OF_DAY)+8;
			int min= cal.get(Calendar.MINUTE);
			int sec = cal.get(Calendar.SECOND);
			p.Out("Fat:<input type=text name=fat value=>Water:<input type=text name=wat value=><br>Time:<input type=text name=year style=width:40px; value="+year+">年<input type=text name=month style=width:20px; value="+month+">月<input type=text name=date style=width:20px; value="+date+">日 <input type=text name=hour style=width:20px; value="+hour+">:<input type=text name=min style=width:20px; value="+min+">:<input type=text name=sec style=width:20px; value="+sec+">");
		}
		p.End("<input type=submit name=ok></form>");
	}
	public void doPost(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
        PersistenceManager mgr=Helper.getMgr();
        
        Long fat=Long.parseLong(req.getParameter("fat"));	
        Long wat=Long.parseLong(req.getParameter("wat"));	
        
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
				I i=new I("","",135L,0L,1L,1L);
				mgr.makePersistent(i);
				if(i.geti()==0L)
					i.seti();
				i.seto(s.id);
				i.setw(s.site);
				mgr.makePersistent(i);
				I135 i135=new I135(i,fat,wat,t);			
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
		rsp.sendRedirect("/"+s.id+"."+s.site+"/fat");
	}
	public void Out(String plink,Page page) throws IOException{
		String[]s=plink.split("/");
		String base=s[1];
		page.title="Fat";
		page.aside="<ul><li><a href=/post/fat>Post</a></ul><ul><li><a href=/system/settings>Settings</a><li><a href=/"+base+"/profile>Profile</a><li><a href=/"+base+"/contacts>Contacts</a><li><a href=/"+base+"/tags>Tags</a></ul><ul><li><a href=/"+base+"/dashboard>Dashboard</a><li><a href=/"+base+"/activities>Activities</a><li><a href=/"+base+"/historical>Historical</a></ul><ul><li><a href=/"+base+"/weight>Weight</a><li><a href=/"+base+"/heartrate>Heart Rate</a><li><a href=/"+base+"/steps>Steps</a><li><a href=/"+base+"/fat>Fat</a></ul>";

		PersistenceManager mgrimg=Helper.getMgr();
		Query q=mgrimg.newQuery(I135.class);
		q.setOrdering("t asc");
		try{
			@SuppressWarnings("unchecked")
			List<I135> r=(List<I135>)q.execute();
			
			final long MAX=40;
			final long MIN=0;
			if(!r.isEmpty()){
				Long T =0L;
                Long W =0L;
				                           
                int n;               
                for(n=0;(n+1)<r.size();n++){
                	Long ti=r.get(n).gett().getTime();
                	Long tii=r.get(n+1).gett().getTime();
                	Long txi=(tii-ti)/3600000;
                	
                	Long vol=r.get(n).getfat();
                	Long volL=r.get(n+1).getfat();
					Long wid=(vol-MIN)*100/(MAX-MIN);
					page.Out("<div style="+"background-color:#000;height:"+txi+"px;width:"+wid+"%"+">&nbsp;</div>");
					T=tii;
					W=volL;	
                }

                Date t=new Date();
                Long tnow=t.getTime();
                Long txL=(tnow-T)/3600000;
                Long widL=(W-MIN)*100/(MAX-MIN);
                page.Out("<div style="+"background-color:#000;height:"+txL+"px;width:"+widL+"%"+">&nbsp;</div>");
                
			}
		}
		finally{
			q.closeAll();
		}
		
		PersistenceManager mgr=Helper.getMgr();
		Query q2=mgr.newQuery(I135.class);
		q2.setOrdering("t desc");
		try{
			@SuppressWarnings("unchecked")
			List<I135> r=(List<I135>)q2.execute();
			if(!r.isEmpty()){
				for(I135 i135:r){
					long t = i135.gett().getTime();
					SimpleDateFormat time=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
					page.Out(time.format(t)+"<br>"+base+":  Fat: "+i135.getfat()+" Water: "+i135.getwat()+" <a href=/post/fat?i="+i135.geti()+"."+i135.getj()+"."+i135.gett().getTime()+">修改</a><br>");
				}
			}
		}
		finally{
			q.closeAll();
		}
		page.End(null);
	}
	
}