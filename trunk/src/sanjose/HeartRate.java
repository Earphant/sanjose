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
		p.aside="<ul><li><a href=/post>Message</a><li><a href=/post/documents>Document</a><li><a href=/post/picture>Picture</a><li><a href=/post/marks>Mark</a><li><a href=/post/events>Event</a><li><a href=/post/uploads>Upload</a></ul><ul><li><a href=/post/books>Book</a><li><a href=/post/issues>Issue</a></ul><ul><li><a href=/post/weight>Weight</a><li><a href=/post/heartrate>Heart Rate</a><li><a href=/post/steps>Steps</a><li><a href=/post/fat>Fat</a></ul>";
		p.Out("<form method=post action=/post/heartrate>");
		if(timed.t!=null){
			PersistenceManager mgr=Helper.getMgr();
			Query q=mgr.newQuery(I136.class);
			q.setFilter("n==nParam && o==oParam && t==tParam");
			q.declareImports("import java.util.Date");
			q.declareParameters("Long nParam,Long oParam,Date tParam");
			try{
				@SuppressWarnings("unchecked")
				List<I136> r=(List<I136>)q.execute(timed.n,timed.o,timed.t);
				if(!r.isEmpty()){
					I136 i136=r.get(0);
					Long v=i136.getvol();
					Date t=i136.gett();
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
			I136 i=new I136(s.id,s.site,vol,t);
			try{
				mgr.makePersistent(i);
			}
			finally{
				mgr.close();
			}
		}
		else{
			Query q=mgr.newQuery(I136.class);
			q.setFilter("n==nParam && o==oParam && t==tParam");
			q.declareImports("import java.util.Date");
			q.declareParameters("Long iParam,Long jParam,Date tParam");
			try{
				@SuppressWarnings("unchecked")
				List<I136> r=(List<I136>)q.execute(timed.n,timed.o,timed.t);		
				if(!r.isEmpty()){
					I136 i136=r.get(0);
					i136.setvol(vol);
					i136.sett(t);
				}
			}
			finally{
				q.closeAll();
				mgr.close();
			}
		}
		rsp.sendRedirect("/12.3/heartrate");
	}
	public void Out(String plink,Page page) throws IOException{
		page.title="Heart Rate";
		page.aside="<ul><li><a href=/post/heartrate>Post</a></ul><ul><li><a href=/system/settings>Settings</a><li><a href=/12.3/profile>Profile</a><li><a href=/12.3/contacts>Contacts</a><li><a href=/12.3/tags>Tags</a></ul><ul><li><a href=/12.3/dashboard>Dashboard</a><li><a href=/12.3/activities>Activities</a><li><a href=/12.3/historical>Historical</a></ul><ul><li><a href=/12.3/weight>Weight</a><li><a href=/12.3/heartrate>Heart Rate</a><li><a href=/12.3/steps>Steps</a><li><a href=/12.3/fat>Fat</a></ul>";
		

		long tx1=100l;int day1=0;int day2=0;int a=0;int f=0;
		long tx2=86400000l;
		long tx8=3l;
		long tx4=0l;
		long tx5=0l;
	    long re=1l;
		long sos=0l;
	    Date t1=new Date();
	    long tt1=t1.getTime();
		int ii2=0;
		long dd=0;
		
	    tt1=tt1+tx2/tx8;
		long[] atr={0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l};
				PersistenceManager mgr=Helper.getMgr();
		
		Query q1=mgr.newQuery(I136.class);
		q1.setOrdering("t desc");
		
		try{
			@SuppressWarnings("unchecked")
			List<I136> r=(List<I136>)q1.execute();
			if(!r.isEmpty()){
				 tx4=0l;
				 tx5=0l;re=1l;
				for(I136 i:r){
					f++;
					long tx3= i.getvol();
					long tt2 = i.gett().getTime();
					long tt3=tt1-tt2;
					if(tt3<(tx1*tx2))
					{
                       day1=(int) (tt3/tx2);   
                       if(day1==day2)
                          {  
                    	   re=re+1l;
                    	   if(sos==0l){re=1l;sos=1l;}
                           tx5=atr[day2]*(re-2l) +tx4; 
                           
                           atr[day2]=tx5/re;
                          } 
                       else 
                         {  
                    	  
                    	    atr[day2]=(tx5+tx4)/re;
                    	    re=1l;
                    	    tx5=0l;
                    	  
                          }
                       tx4= tx3;
					   day2=day1;
						}
					atr[day2]=(tx5+tx4)/re;
				
				}
					       
			}
		}
		finally{
			q1.closeAll();
		}	
						
	   for(int ii1=0;ii1<80;ii1++){
		
		  if(atr[ii1]!=0){
		 	  
			  if(a==0)
		    {
			
			  a=1;
			  ii2=ii1;
			  for(int ii3=0;ii3<ii1;ii3++)
			  { atr[ii3]=atr[ii1];
		   }
		   }
		
			else
		{
		  
		  if((ii1-ii2)!=0&&(ii1-ii2)!=1)
		{  
			  dd=atr[ii2]-atr[ii1];
			  long sdd=dd/(ii1-ii2);
			  for(int ii3=1;ii3<ii1-ii2;ii3++)
			  {
				  atr[ii2+ii3]=atr[ii2+ii3-1]-sdd;
		   }
			  ii2=ii1;
		}
		  else
		  { ii2=ii1;}
		  
		} 
		}	
		
		}	
		   
		int[] abc={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

	long max=0l;	long min=atr[0]; long d=0l;
	long aaa=200l;  int bb=30; int aa1=0;
	

	  for(int ii=0;ii<30;ii++){
		
		if(max<atr[ii]&&aa1==0){
			max=atr[ii];
		} 
		if(min>atr[ii]&&aa1==0&&atr[ii]!=0){
			min=atr[ii];
        }
		if(atr[ii]==0&&aa1==0){ 
		  bb=ii; aa1=1;
	     }
		d=max-min;
	    aaa=10l;
	    max=max/aaa;
	    aaa=200l;
	 }
	
	   int s11=550+(30-bb)*17;
	  
	   for(int ii=0;ii<bb&&d!=0;ii++){
	     atr[ii]=(atr[ii]-min+max)*aaa/d;
	     abc[ii]=(int)atr[ii];	
	 }
		
		
			for(int aa=0;aa<bb;aa++)
			
		{  	
			
			if((aa%3)==0)
			{page.Out("<div style=position:absolute;bottom:100px;background-color:#0ff;right:"+s11+"px;height:"+abc[aa]+"px;width:17px>&nbsp;</div>");}
			else
			{ if((aa%3)==1)
			    {	page.Out("<div style=position:absolute;bottom:100px;background-color:#ff0;right:"+s11+"px;height:"+abc[aa]+"px;width:17px>&nbsp;</div>");}
			   else
			{  page.Out("<div style=position:absolute;bottom:100px;background-color:#f0f;right:"+s11+"px;height:"+abc[aa]+"px;width:17px>&nbsp;</div>");}
			             
			
			
			}
			s11=s11+15;
		    
		    
		}
	

	
	page.Out("<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>");
		page.Out("<br><br>asd");
		
			
		PersistenceManager mgrdata=Helper.getMgr();
		Query q2=mgrdata.newQuery(I136.class);
		q2.setOrdering("t desc");
		try{
			@SuppressWarnings("unchecked")
			List<I136> r=(List<I136>)q2.execute();
			
			
			if(!r.isEmpty()){			
				
				
				for(I136 i136:r){

					long t = i136.gett().getTime();
					SimpleDateFormat time=new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");					
					page.Out(time.format(t)+"<br>"+i136.getn()+"."+i136.geto()+": "+i136.getvol()+" <a href=/post/heartrate?i="+i136.getn()+"."+i136.geto()+"."+i136.gett().getTime()+">修改</a><br>");				
				}
			}
		}
		finally{
			q2.closeAll();
		}
		page.End(null);
	}
	}
	

		
	
		
		
		
	   
		
		