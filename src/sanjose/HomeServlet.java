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
		page.out("<form method=post action=/post/><textarea name=text rows=5></textarea><input type=submit name=ok></form>");
					
		Session	s=new Session("OW");
		List <Long[]> IJM=new ArrayList<Long[]>();	
		List <Long[]> IJ=new ArrayList<Long[]>();
		Long o=s.owner.getId();
		Long w=s.owner.getSite();
		String AA=null;
		Long[] ii={o,w};			
		IJ.add(ii);
		
		PersistenceManager m=Helper.getMgr();
		Query q=m.newQuery(I21.class);		
		q.setFilter("o==oParam && w==wParam ");	
		q.declareParameters("Long oParam,Long wParam");
		try{
			List<I21> r=(List<I21>)q.execute(o,w);
			if(!r.isEmpty()){
				for(I21 i21:r){
					 AA=i21.geti()+"."+i21.getj();	
					 Long[] i={i21.geti(),i21.getj()};							
	                 IJ.add(i);
				}
			}
		}
		finally{
			q.closeAll();
		}
		
		if(AA==null){
             Query q1=m.newQuery(I.class);            
             q1.setOrdering("m desc");
             try{
                 new RegList((List<I>)q1.execute(),page);
             }
             finally{
                 q1.closeAll();
             }
		}		
		
		else{						
			Query q2=m.newQuery(I.class);		
			q2.setFilter("o==oParam && w==wParam ");	
			q2.declareParameters("Long oParam,Long wParam"); 
			try{			
                for(int k=0;k<IJ.size();k++){
			       Long ownerid=IJ.get(k)[0];
			       Long ownersite=IJ.get(k)[1];
				   List<I> r=(List<I>)q2.execute(ownerid,ownersite);				  
				   if(!r.isEmpty()){
						for(I i:r){
							Long[] ijm={i.getOwnerId(),i.getOwnerSite(),(i.getModifyTime()).getTime()};				
							IJM.add(ijm);		                    
						}
					}			   
                }
			}
			finally{
				q2.closeAll();					
			}		
			
			for(int i=0;i<IJM.size()-1;i++) {
				int index=i;
				Long[] temp;
				for(int j=i+1;j<IJM.size();j++) 				   
				   if((IJM.get(j))[2]>IJM.get(index)[2])
					   index=j;
				   temp=IJM.get(index);
				   IJM.get(index)[0]=IJM.get(i)[0];
				   IJM.get(index)[1]=IJM.get(i)[1];
				   IJM.get(index)[2]=IJM.get(i)[2];
				   IJM.get(i)[0]=temp[0];
				   IJM.get(i)[1]=temp[1];
				   IJM.get(i)[2]=temp[2];   				    				    				
			}
			
		    Query q3=m.newQuery(I.class);		
			q3.setFilter("o==oParam && w==wParam && m==mParam");	
			q3.declareImports("import java.util.Date");
			q3.declareParameters("Long oParam,Long wParam,Date mParam");
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
	    m.close();
		page.end(null);
    }		

	private void Unsigned(Page page,Session ssn)
		throws IOException{
		page.title="Home";
		page.Begin();
		page.end(null);
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
