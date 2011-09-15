package sanjose;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class Contacts {
	public void out(String plink,Page page) throws IOException{
		Session s=new Session("ow");
		String[]current=plink.split("/");
		String currentbase=current[1];		
		PersistenceManager mgr=Helper.getMgr();
		Long I=Long.parseLong(currentbase.split("\\.")[0]);
		Long J=Long.parseLong(currentbase.split("\\.")[1]);
		Long a = null;
		Long O = null;
		Long W = null;
		Query q1=mgr.newQuery(I.class);
		q1.setFilter("i==iParam && j==jParam ");	
		q1.declareParameters("Long iParam,Long jParam");  
		try{
			@SuppressWarnings("unchecked")
			List<I> r=(List<I>)q1.execute(I,J);
			if(!r.isEmpty()){
				a=r.get(0).getType();	
			    O=r.get(0).getOwner().getId();
			    W=r.get(0).getOwner().getSite();
			}
		}
		finally{
			q1.closeAll();
		}		
		
		if(a==1){
			page.title="Contacts";
			page.aside="<ul><li><a href=/post/>Post</a></ul><ul><li><a href=/system/settings>Settings</a><li><a href=/"+currentbase+"/profile>Profile</a><li><a href=/"+currentbase+"/contacts>Contacts</a><li><a href=/"+currentbase+"/tags>Tags</a></ul><ul><li><a href=/"+currentbase+"/dashboard>Dashboard</a><li><a href=/"+currentbase+"/activities>Activities</a><li><a href=/"+currentbase+"/historical>Historical</a></ul><ul><li><a href=/"+currentbase+"/weight>Weight</a></ul>";			
			page.out("<br>The people tha I followed :");	
			List <Long[]> IJ1=new ArrayList<Long[]>();
			Query q21=mgr.newQuery(I21.class);		
			q21.setOrdering("i desc");
			q21.setFilter("o==oParam && w==wParam ");	
			q21.declareParameters("Long oParam,Long wParam");    
			try{
				@SuppressWarnings("unchecked")
				List<I21> r=(List<I21>)q21.execute(s.owner.getId(),s.owner.getSite());		
				if(!r.isEmpty()){
					for(I21 i21:r){
						String d=i21.geti()+"."+i21.getj();						
						Long A=i21.geta();
						if(A==1){
							Long[] ij={i21.geti(),i21.getj()};
							IJ1.add(ij);
	                        page.out("<a href=/"+d+"/><img src=/icons/"+d+".jpg></a>");
						}
					}
				}
			}
			finally{
				q21.closeAll();
			}
			
			page.out("<br>The people that you may be interested in :");
			Query q11=mgr.newQuery(I11.class);		
			q11.setOrdering("i desc");
			try{
				@SuppressWarnings("unchecked")
				List<I11> r=(List<I11>)q11.execute();
				if(!r.isEmpty()){
					for(I11 i:r){
						String d=i.getId()+"."+i.getSite();
						String x=i.getEmail();
						String base=i.getId()+"."+i.getSite();
						String owner=s.owner.getId()+"."+s.owner.getSite();
						int k;
						for(k=0;k<IJ1.size();k++){
						   if(d.equals(owner) || (IJ1.get(k)[0]==i.getId() && IJ1.get(k)[1]==i.getSite()))break;
						}
						if(k==IJ1.size())
						   page.out("<div class=real style=left:"+33+"%;background-color:#fff;><a href=/"+base+"/><img src=/icons/"+d+".jpg></a><div><a href=/"+base+"/>"+x+"</a></div><div><a href=/system/follow?i="+d+">Follow</a></div></div>");				                    
					}
				}				
			}
			finally{
				q11.closeAll();
			}		
			
			page.out("<br><br><br>The groups that I joined :");	
			List <Long[]> IJ2=new ArrayList<Long[]>();
			Query q_21=mgr.newQuery(I21.class);		
			q_21.setOrdering("i desc");
			q_21.setFilter("o==oParam && w==wParam ");	
			q_21.declareParameters("Long oParam,Long wParam");     
			try{
				@SuppressWarnings("unchecked")
				List<I21> r=(List<I21>)q_21.execute(s.owner.getId(),s.owner.getSite());		
				if(!r.isEmpty()){
					for(I21 i21:r){
						String d=i21.geti()+"."+i21.getj();						
						Long A=i21.geta();
						if(A==2){
						   Long[] ij={i21.geti(),i21.getj()};
						   IJ2.add(ij);
	                       page.out("<a href=/"+d+"/><img src=/icons/"+d+".jpg></a>");
						}
					}
				}
			}
			finally{
				q_21.closeAll();
			}
			
			page.out("<br>Groups that you may be interested in :");
			Query qi=mgr.newQuery(I.class);
			qi.setFilter("a=="+2+"");
			try{
				@SuppressWarnings("unchecked")
				List<I> r=(List<I>)qi.execute();
				if(!r.isEmpty())
					for(I i:r){						
						String d=i.getId()+"."+i.getSite();
						String x=i.getText();
						String base=i.getId()+"."+i.getSite();
						int k;
						for(k=0;k<IJ2.size();k++){				                
			                 if(IJ2.get(k)[0]==i.getId() && IJ2.get(k)[1]==i.getSite())break;
			            }	
					    if(k==IJ2.size())
						   page.out("<div class=real style=left:"+33+"%;background-color:#fff;><a href=/"+base+"/><img src=/icons/"+d+".jpg></a><div><a href=/"+base+"/>"+x+"</a></div><div><a href=/system/follow?i="+d+">Follow</a></div></div>");
					}
			}
			finally{
				qi.closeAll();
			}			
		}
		
		else{
			page.title="Members";
			page.aside="<ul><li><a href=/post/>Post</a></ul><ul><li><a href=/system/settings>Settings</a><li><a href=/"+currentbase+"/profile>Profile</a><li><a href=/"+currentbase+"/contacts>Contacts</a><li><a href=/"+currentbase+"/tags>Tags</a></ul><ul><li><a href=/"+currentbase+"/dashboard>Dashboard</a><li><a href=/"+currentbase+"/activities>Activities</a><li><a href=/"+currentbase+"/historical>Historical</a></ul>";			
			page.out("<br>Create Person :");
			Query q11=mgr.newQuery(I11.class);		
			q11.setOrdering("i desc");
			q11.setFilter("i==iParam && j==jParam ");	
			q11.declareParameters("Long iParam,Long jParam");    
			try{
				@SuppressWarnings("unchecked")
				List<I11> r=(List<I11>)q11.execute(O,W);			
				if(!r.isEmpty()){
					for(I11 i11:r){
						String x=i11.getEmail();
						String d=i11.getId()+"."+i11.getSite();					
	                    page.out("<div class=post><a href=/"+d+"/><img src=/icons/"+d+".jpg></a><div><a href=/"+d+"/>"+x+"</a></div></div>");
					}
				}
			}
			finally{
				q11.closeAll();
			}
			
			page.out("<br>Group Members :");
			Query q21=mgr.newQuery(I21.class);		
			q21.setOrdering("i desc");
			q21.setFilter("i==iParam && j==wjaram ");	
			q21.declareParameters("Long iParam,Long jParam");    
			try{
				@SuppressWarnings("unchecked")
				List<I21> r=(List<I21>)q21.execute(I,J);			
				if(!r.isEmpty()){
					for(I21 i21:r){
						String d=i21.geto()+"."+i21.getw();					
	                    page.out("<a href=/"+d+"/><img src=/icons/"+d+".jpg></a>");
					}
				}
			}
			finally{
				q21.closeAll();
			}
		}
						
		page.end(null);
        mgr.close();		
	}      
}


