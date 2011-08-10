package sanjose;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.jdo.*;
import javax.servlet.http.*;



@SuppressWarnings("serial")
public class HomeServlet extends HttpServlet{
	@SuppressWarnings("unchecked")
	private void Signed(Page page,Session ssn)throws IOException{
		page.title="Home";
		page.Out("<form method=post action=/post/><textarea name=text rows=5></textarea><input type=submit name=ok></form>");
					
		Session	s=new Session("OW");
		Long o=s.id;
		Long w=s.site;
		String AA=null;
		PersistenceManager m=Helper.getMgr();
		Query q=m.newQuery(I21.class);		
		q.setOrdering("i desc");
		q.setFilter("o==oParam && w==wParam ");	
		q.declareParameters("Long oParam,Long wParam");
		try{
			List<I21> r=(List<I21>)q.execute(o,w);
			if(!r.isEmpty()){
				for(I21 i21:r){
					 AA=i21.geti()+"."+i21.getj();						                    
				}
			}
		}
		finally{
			q.closeAll();
		}
		
		if(AA==null){
			 PersistenceManager mgr1=Helper.getMgr();
             Query q1=mgr1.newQuery(I.class);            
             q1.setOrdering("m desc");
             try{
                 new RegList((List<I>)q1.execute(),page);
             }
             finally{
                 q1.closeAll();
             }
		}
		
		
		else{		
			
			List <Long[]> IJM=new ArrayList<Long[]>();	
			List <Long[]> ij=new ArrayList<Long[]>();
			
		    PersistenceManager m1=Helper.getMgr();
			Query q1=m1.newQuery(I.class);		
			q1.setOrdering("m desc");
			q1.setFilter("o==oParam && w==wParam ");	
			q1.declareParameters("Long oParam,Long wParam");			
			try{
				List<I> r=(List<I>)q1.execute(o,w);				
				if(!r.isEmpty()){
					I i=r.get(0);
					Long[] ii={0L,0L};			
	                ii[0]=i.getOwnerId();
					ii[1]=i.getOwnerSite();
					ij.add(ii);
				}
			}
			finally{
				q1.closeAll();
			}
	
			
			PersistenceManager m21=Helper.getMgr();
			Query q21=m21.newQuery(I21.class);		
			q21.setOrdering("t desc");
			q21.setFilter("o==oParam && w==wParam");	
			q21.declareParameters("Long oParam,Long wParam");	
			try{			
				List<I21> r=(List<I21>)q21.execute(o,w);					
				if(!r.isEmpty()){
					for(I21 i21:r){
						Long[] i={0L,0L};			
		                i[0]=i21.geti();
						i[1]=i21.getj();					
	                    ij.add(i);
					}
				}
			}
			finally{
				q21.closeAll();
			}			
				

			PersistenceManager m2=Helper.getMgr();
			Query q2=m2.newQuery(I.class);		
			q2.setOrdering("m desc");
			q2.setFilter("o==oParam && w==wParam ");	
			q2.declareParameters("Long oParam,Long wParam"); 
			try{			
                for(int k=0;k<ij.size();k++){
			       Long ownerid=ij.get(k)[0];
			       Long ownersite=ij.get(k)[1];
				   List<I> r=(List<I>)q2.execute(ownerid,ownersite);				  
				   if(!r.isEmpty()){
						for(I i:r){
							Long[] ii={0L,0L,0L};			
			                ii[0]=i.getOwnerId();
							ii[1]=i.getOwnerSite();
							ii[2]=(i.getm()).getTime();	
							IJM.add(ii);		                    
						}
					}			   
                }
			}
			finally{
				q2.closeAll();					
			}
			
			
			for(int i=0;i<IJM.size()-1;i++) {
				int index=i;
				Long temp0,temp1,temp2;
				for(int j=i+1;j<IJM.size();j++) 				   
				   if((IJM.get(j))[2]>IJM.get(index)[2])
					   index=j;
				   temp0=IJM.get(index)[0];
				   temp1=IJM.get(index)[1];
				   temp2=IJM.get(index)[2];
				   IJM.get(index)[0]=IJM.get(i)[0];
				   IJM.get(index)[1]=IJM.get(i)[1];
				   IJM.get(index)[2]=IJM.get(i)[2];
				   IJM.get(i)[0]=temp0;
				   IJM.get(i)[1]=temp1;
				   IJM.get(i)[2]=temp2;   				    				    
				
			}

			
			PersistenceManager m3=Helper.getMgr();
		    Query q3=m3.newQuery(I.class);		
			q3.setOrdering("m desc");
			q3.setFilter("o==oParam && w==wParam && c==cParam");	
			q3.declareParameters("Long oParam,Long wParam,Long cParam");
			try{			               
				for(int k=0;k<IJM.size();k++){
				    Long ownerid=IJM.get(k)[0];
				    Long ownersite=IJM.get(k)[1];
				    Long M1=IJM.get(k)[2];
				    Date M2=new Date(M1);
					new RegList((List<I>)q3.execute(ownerid,ownersite,M2),page);
                }
		    }
			finally{
			    q3.closeAll();					
			}
		}
	
		page.End(null);
    }
		

	private void Unsigned(Page page,Session ssn)
		throws IOException{
		page.title="Home";
		page.Begin();
		page.End(null);
	}

	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		String n=req.getPathInfo();
		Page p=new Page(rsp);
		Session s=new Session("");
		if(n.equals("/")){
			if(s.email==null)
				Unsigned(p,s);
			else
				Signed(p,s);
		}
		else
			new Based(n,rsp,req);
	}
		

}
