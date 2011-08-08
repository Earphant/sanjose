package sanjose;

import java.io.IOException;
import java.util.Date;
import java.util.List;



public class Graph{
	@SuppressWarnings("unchecked")
	public String Daily(Object list,String type)throws IOException{
	    long tx1=100l;int day1=0;int day2=0;String s="";
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
		int a=0;
	    tt1=tt1+tx2/tx8;
		long[] atr={0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l};
		long[] atrd={0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l};
		long[] atrt={0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l};
		
				if(((List<Single>)list).isEmpty())
					return "";
				 tx4=0l;
				 tx5=0l;re=1l;
				
					for(Single i:(List<Single>)list){	
					long tx3= i.getvol();
					long tt2 = i.gettime().getTime();
					long tt3=tt1-tt2;
				
					if(tt3<(tx1*tx2))
					{  
					   
                       day1=(int) (tt3/tx2);   
                       atrt[day1]=tt2;
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
					   atrd[day1]=1;
						}
					atr[day2]=(tx5+tx4)/re;
				
				
				}
						       
		
		
	
				
	   for(int ii1=0;ii1<80;ii1++){
		
		  if(atr[ii1]!=0){
		 	  
			  if(a==0){
		    
			
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
		  else{
		   ii2=ii1;}
		  
		} 
		}	
		
		}	
		   

	long max=0l;	long min=atr[0]; 
	  int bb=30; int aa1=0;
	
	
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
	}
	for(int ii=0;ii<8;ii++){
		s=atr[ii]+"a"+s;
	}
	max-=min;
	if(max==0)
		return "";
	long k=80000/max;
	
	min-=10000/k;
	int n=10;
	for(int aaa1=bb-1;aaa1>-1;aaa1--){
		if(atrd[aaa1]==0)
		s+="<div   style=left:"+n+"px;height:"+(atr[aaa1]-min)*k/1000+"%;background-color:#f0f></div>";
		else
		s+="<div   style=left:"+n+"px;height:"+(atr[aaa1]-min)*k/1000+"%;background-color:#ff0><a href=/post/"+type+"?i=1.9."+atrt[aaa1]+" title="+atr[aaa1]+">¸Ä</a></div>";
		n+=16;
	}
	return s;
}
}
		
		
		
		
		
		
