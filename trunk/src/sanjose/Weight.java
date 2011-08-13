package sanjose;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Weight extends DataText{
	public void doGet(HttpServletRequest req,HttpServletResponse rsp,Page page)
		throws IOException{
		I i=I.timed(req.getParameter("i"));
		page.title="Weight";
		page.aside="<ul><li><a href=/post>Message</a><li><a href=/post/documents>Document</a><li><a href=/post/picture>Picture</a><li><a href=/post/marks>Mark</a><li><a href=/post/events>Event</a><li><a href=/post/upload>Upload</a></ul><ul><li><a href=/post/books>Book</a><li><a href=/post/issues>Issue</a></ul><ul><li><a href=/post/weight>Weight</a><li><a href=/post/heart-rate>Heart Rate</a><li><a href=/post/steps>Steps</a><li><a href=/post/fat>Fat</a></ul>";
		if(i==null){
			page.out("<form method=post action=/post/weight>");
			page.out("Value<br><input type=text name=v>");
		}
		else{
			page.out("<form method=post action=/post/weight?i="+i.getTimed()+">");
			page.out("Value<br><input type=text name=v value="+
				getSingleVal(i,I138.class)+">");
		}
		page.end("<br><input type=submit name=ok></form>");
	}
	public void doPost(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		Session sn=new Session("/post");
		I i=I.timed(req.getParameter("i"));
		long v=Long.parseLong(req.getParameter("v"));
		if(i==null){
			i=new I(sn.owner.getId(),sn.owner.getSite());
			i.setModifyTime(null);
		}
		PersistenceManager mgr=Helper.getMgr();
		try{
			I138 i138=new I138(i,i.getModifyTime(),v);
			mgr.makePersistent(i138);
			updatePost(i,138,getHtml(i,I138.class,null,mgr),"Weight","weight",
				mgr);
		}
		finally{
			mgr.close();
		}
		rsp.sendRedirect("/"+i+"/weight");
	}
	/*
	public void doGet0(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		Page p=new Page(rsp);
		Timed timed=new Timed(req.getParameter("i"));
		p.title="Weight";
		p.aside="<ul><li><a href=/post>Message</a><li><a href=/post/documents>Document</a><li><a href=/post/picture>Picture</a><li><a href=/post/marks>Mark</a><li><a href=/post/events>Event</a><li><a href=/post/upload>Upload</a></ul><ul><li><a href=/post/books>Book</a><li><a href=/post/issues>Issue</a></ul><ul><li><a href=/post/weight>Weight</a><li><a href=/post/heart-rate>Heart Rate</a><li><a href=/post/step>Step</a><li><a href=/post/fat>Fat</a></ul>";
		p.out("<form method=post action=/post/weight>");
		if(timed.t!=null){
			PersistenceManager mgr=Helper.getMgr();
			Query q=mgr.newQuery(I138.class);
			q.setFilter("o==oParam && w==wParam && t==tParam");
			q.declareImports("import java.util.Date");
			q.declareParameters("Long oParam,Long wParam,Date tParam");
			try{
				@SuppressWarnings("unchecked")
				List<I138> r=(List<I138>)q.execute(timed.o,timed.w,timed.t);
				if(!r.isEmpty()){
					I138 i138=r.get(0);
					Long v=i138.getVal();
					Date t=i138.getTime();
					Calendar cal = Calendar.getInstance();
					cal.setTime(t);
					int year= cal.get(Calendar.YEAR);
					int month= cal.get(Calendar.MONTH)+1;
					int date= cal.get(Calendar.DAY_OF_MONTH);
					int hour= cal.get(Calendar.HOUR_OF_DAY);
					int min= cal.get(Calendar.MINUTE);
					int sec = cal.get(Calendar.SECOND);
					p.out("Value:<input type=text name=weight value="+v+"><br>Date:<input type=text name=year style=width:40px; value="+year+">-<input type=text name=month style=width:20px; value="+month+">-<input type=text name=date style=width:20px; value="+date+"> Time:<input type=text name=hour style=width:20px; value="+hour+">:<input type=text name=min style=width:20px; value="+min+">:<input type=text name=sec style=width:20px; value="+sec+">");
				}
			}
			finally{
				q.closeAll();
			}
			p.out("<input type=hidden name=i value="+timed.o+"."+timed.w+"."+timed.t.getTime()+">");
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
			p.out("Value:<input type=text name=weight value=><br>Date:<input type=text name=year style=width:40px; value="+year+">-<input type=text name=month style=width:20px; value="+month+">-<input type=text name=date style=width:20px; value="+date+"> Time:<input type=text name=hour style=width:20px; value="+hour+">:<input type=text name=min style=width:20px; value="+min+">:<input type=text name=sec style=width:20px; value="+sec+">");
		}
		p.End("<input type=submit name=ok></form>");
	}
	public void doPost0(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
        PersistenceManager mgr=Helper.getMgr(); 
        
        Long vol=Long.parseLong(req.getParameter("weight"));
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
				@SuppressWarnings("unchecked")
				List<I> r=(List<I>)qi.execute(s.id,s.site,138);
				if(r.isEmpty()){
					I i= new I(s.name,"",138L,0L,s.id,s.site);
					i.setModifyTime(now);
					mgr.makePersistent(i);
				}
				else{
					I i=r.get(0);
					i.setModifyTime(now);
					mgr.makePersistent(i);
				}
				I138 i138=new I138(s.id,s.site,vol,t);
				mgr.makePersistent(i138);
			}
			finally{
				qi.closeAll();
				mgr.close();
			}
		}
		else{
			Query q=mgr.newQuery(I138.class);
			q.setFilter("o==oParam && w==wParam && t==tParam");
			q.declareImports("import java.util.Date");
			q.declareParameters("Long oParam,Long wParam,Date tParam");
			try{
				@SuppressWarnings("unchecked")
				List<I138> r=(List<I138>)q.execute(timed.o,timed.w,timed.t);
				if(!r.isEmpty()){
					I138 i138=r.get(0);
					i138.setVal(vol);
					mgr.makePersistent(i138);
				}
			}
			finally{
				q.closeAll();
			}
			Query qi=mgr.newQuery(I.class);
			qi.setFilter("o==oParam && w==wParam && a==aParam");
			qi.declareParameters("Long oParam,Long wParam,Long aParam");
			try{
				@SuppressWarnings("unchecked")
				List<I> r=(List<I>)qi.execute(timed.o,timed.w,138);
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
		rsp.sendRedirect("/"+s.id+"."+s.site+"/weight");
	}
	*/
	@SuppressWarnings("unchecked")
	public void out(String plink,Page page)throws IOException{
		String[]s=plink.split("/");
		String b=s[1];
		page.title="Weight";
		page.aside="<ul><li><a href=/post/weight>Post</a></ul><ul><li><a href=/system/settings>Settings</a><li><a href=/"+b+"/profile>Profile</a><li><a href=/"+b+"/contacts>Contacts</a><li><a href=/"+b+"/tags>Tags</a></ul><ul><li><a href=/"+b+"/dashboard>Dashboard</a><li><a href=/"+b+"/activities>Activities</a><li><a href=/"+b+"/historical>Historical</a></ul><ul><li><a href=/"+b+"/weight>Weight</a><li><a href=/"+b+"/heart-rate>Heart Rate</a><li><a href=/"+b+"/steps>Steps</a><li><a href=/"+b+"/fat>Fat</a></ul>";
		Long id=Long.parseLong(b.split("\\.")[0]);
		Long site=Long.parseLong(b.split("\\.")[1]);
		
		PersistenceManager mgr=Helper.getMgr();
		Query q1=mgr.newQuery(I138.class);
		q1.setFilter("o==oParam && w==wParam");
		q1.declareParameters("Long oParam,Long wParam");
		q1.setOrdering("t");
		try{
			List<I138> r=(List<I138>)q1.execute(id,site);
			page.out("<div class=grf2>");
			String abc="weight";
			page.out(new Graph().Daily(r,abc));
			page.out("</div>");

			page.out("<div class=grf2>");
			page.out(getHtml(new I(b),I138.class,"/post/weight?i="+b+".",
				Helper.getMgr()));
			page.out("</div>");
		}
		finally{
			q1.closeAll();
		}
		Query q2=mgr.newQuery(I138.class);
		q2.setFilter("o==oParam && w==wParam");
		q2.declareParameters("Long oParam,Long wParam");
		q2.setOrdering("t desc");
		try{
			List<I138> r=(List<I138>)q2.execute(id,site);
			if(!r.isEmpty()){			
				for(I138 i138:r){
					long t = i138.getTime().getTime();
					SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					page.out(time.format(t)+"<br>"+b+": "+i138.getVal()+" <a href=/post/weight?i="+i138.getOwnerId()+"."+i138.getOwnerSite()+"."+i138.getTime().getTime()+">ÐÞ¸Ä</a><br>");					
				}
			}
		}
		finally{
			q2.closeAll();
			mgr.close();
		}
		page.end(null);
	}
}