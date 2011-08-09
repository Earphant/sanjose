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
				
				I ii138=new I("","",138L,0L,id,site);
				mgr.makePersistent(ii138);
				ii138.setId();
				I138 i138=new I138(id,site,Long.parseLong(s[2]),t);
				ii138.seti138(i138);
				mgr.makePersistent(ii138);
				
				I ii135=new I("","",135L,0L,id,site);
				mgr.makePersistent(ii135);
				ii135.setId();
				I135 i135=new I135(id,site,Long.parseLong(s[3]),Long.parseLong(s[4]),t);
				ii135.seti135(i135);
				mgr.makePersistent(ii135);
				
				I ii136=new I("","",136L,0L,id,site);
				mgr.makePersistent(ii136);
				ii136.setId();
				I136 i136=new I136(id,site,Long.parseLong(s[5]),t);
				ii136.seti136(i136);
				mgr.makePersistent(ii136);
				
				I ii139=new I("","",139L,0L,id,site);
				mgr.makePersistent(ii139);
				ii139.setId();
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