package sanjose;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Datatxt{
	public void doPost(HttpServletRequest req,HttpServletResponse rsp,
		InputStream stream,Long id,Long site)throws IOException{
		BufferedReader r=new BufferedReader(new InputStreamReader(stream));
		SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String v;
		r.readLine();
		r.readLine();
		while((v=r.readLine())!=null){
			String s[]=v.split(" ");
			Date t;
			PersistenceManager mgr=Helper.getMgr();
			try {
				t=fmt.parse(v);
				
				I ii138=new I("","",138L,0L,1L,1L);
				mgr.makePersistent(ii138);
				if(ii138.geti()==0L)
					ii138.seti();
				ii138.seto(id);
				ii138.setw(site);	
				I138 i138=new I138(id,site,Long.parseLong(s[2]),t);
				ii138.seti138(i138);
				mgr.makePersistent(ii138);
				
				I ii135=new I("","",135L,0L,1L,1L);
				mgr.makePersistent(ii135);
				if(ii135.geti()==0L)
					ii135.seti();
				ii135.seto(id);
				ii135.setw(site);
				I135 i135=new I135(id,site,Long.parseLong(s[3]),Long.parseLong(s[4]),t);
				ii135.seti135(i135);
				mgr.makePersistent(ii135);
				
				I ii136=new I("","",136L,0L,1L,1L);
				mgr.makePersistent(ii136);
				if(ii136.geti()==0L)
					ii136.seti();
				ii136.seto(id);
				ii136.setw(site);
				I136 i136=new I136(id,site,Long.parseLong(s[5]),t);
				ii136.seti136(i136);
				mgr.makePersistent(ii136);
				
				I ii139=new I("","",139L,0L,1L,1L);
				mgr.makePersistent(ii139);
				if(ii139.geti()==0L)
					ii139.seti();
				ii139.seto(id);
				ii139.setw(site);
				I139 i139=new I139(id,site,Long.parseLong(s[6]),t);
				ii139.seti139(i139);
				mgr.makePersistent(ii139);
			} 
			catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				mgr.close();
			}
		}
		r.close();
		rsp.sendRedirect("/"+id+"."+site+"/");
	}
}