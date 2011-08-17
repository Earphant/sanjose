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
		Session sn=new Session("/");;	
		List <Long[]> IJM=new ArrayList<Long[]>();	
		List <Long[]> IJ=new ArrayList<Long[]>();
		Long o=ssn.owner.getId();
		Long w=ssn.owner.getSite();
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
							if(i.getRefId()==1&&i.getRefSite()==1){
							Long[] owmij={i.getOwnerId(),i.getOwnerSite(),(i.getModifyTime()).getTime(),i.getId(),i.getSite()};				
							IJM.add(owmij);		                    
						}}
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
				   IJM.get(index)[3]=IJM.get(i)[3];
				   IJM.get(index)[4]=IJM.get(i)[4];
				   IJM.get(i)[0]=temp[0];
				   IJM.get(i)[1]=temp[1];
				   IJM.get(i)[2]=temp[2];
				   IJM.get(i)[3]=temp[3];
				   IJM.get(i)[4]=temp[4];
				   
			}
				  
				   
				   
				  
			for(int k=0;k<IJM.size();k++){
		  
			 Query q4=m.newQuery(I.class);		
				q4.setFilter(" m==mParam&&i==iParam&&j==jParam");	
				q4.declareImports("import java.util.Date");
				q4.declareParameters("Date mParam,Long iParam,Long jParam");
				try{			               
					    Long i=IJM.get(k)[3];
					    Long j=IJM.get(k)[4];
					    Long ownerid=IJM.get(k)[0];
					    Long ownersite=IJM.get(k)[1];
					    Long M1=IJM.get(k)[2];
					    Date M2=new Date(M1);
						new RegList((List<I>)q4.execute(M2,i,j),page);
	                }
			    
				finally{
				    q4.closeAll();					
				}
				  Query q3=m.newQuery(I.class);		
					q3.setFilter("d==dParam && h==hParam ");	
					q3.declareImports("import java.util.Date");
					q3.declareParameters("Long dParam,Long hParam");
					try{			               
						  
						    Long d=IJM.get(k)[3];
						    Long h=IJM.get(k)[4];
						    
							   List<I> r=(List<I>)q3.execute(d,h);			  
							   if(!r.isEmpty()){
									for(I i:r){
										
										page.out("»Ø¸´£º");
										page.out(i.getText()+"****");
		                 }}}
					finally{
					    q3.closeAll();					
					}
		}}
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
		Session s=new Session("/");
		if(n.equals("/")){
			if(s.email==null)
				Unsigned(p,s);
			else
				Signed(p,s);
		}
		else
			new Based(rsp,req,n,s);
	}	
}
 
				  