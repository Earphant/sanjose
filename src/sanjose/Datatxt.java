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
		SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String v;
		r.readLine();
		r.readLine();
		while((v=r.readLine())!=null){
			String s[]=v.split(" ");
			Date t;
			PersistenceManager mgr=Helper.getMgr();
			try {
				t=fmt.parse(v);
				Long w=Long.parseLong(s[2]);
				Long f=Long.parseLong(s[3]);
				Long a=Long.parseLong(s[4]);
				Long e=Long.parseLong(s[5]);
				Long p=Long.parseLong(s[6]);
				I138 i138=new I138(id,site,w,t);
				I135 i135=new I135(id,site,f,a,t);
				I136 i136=new I136(id,site,e,t);
				I139 i139=new I139(id,site,p,t);
				mgr.makePersistent(i138);
				mgr.makePersistent(i135);
				mgr.makePersistent(i136);
				mgr.makePersistent(i139);
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
		rsp.sendRedirect("/12.3");
	}
}