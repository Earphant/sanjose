package sanjose;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DataText{
	@SuppressWarnings("unchecked")
	public boolean doPost(HttpServletRequest req,HttpServletResponse rsp,
		InputStream stream,Long id,Long site)throws IOException{
		BufferedReader r=new BufferedReader(new InputStreamReader(stream));
		SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String v;
		if(!r.readLine().equalsIgnoreCase("<!doctype palo-alto>"))
			return false;
		r.readLine();
		while((v=r.readLine())!=null){
			String s[]=v.split(" ");
			Session current=new Session("");
			Date t;
			Date now=new Date();
			PersistenceManager mgr=Helper.getMgr();
			Query qi=mgr.newQuery(I.class);
			qi.setFilter("o==oParam && w==wParam && a==aParam");
			qi.declareParameters("Long oParam,Long wParam,Long aParam");
			try {
				t=fmt.parse(v);

				List<I> r138=(List<I>)qi.execute(id,site,138);
				if(r138.isEmpty()){
					I i138= new I(current.name,"",138L,0L,id,site);
					i138.setModifyTime(now);
					mgr.makePersistent(i138);
				}
				else{
					I i=r138.get(0);
					i.setModifyTime(now);
					mgr.makePersistent(i);
				}
				I138 i138=new I138(id,site,Long.parseLong(s[2]),t);
				mgr.makePersistent(i138);

				List<I> r135=(List<I>)qi.execute(id,site,135);
				if(r135.isEmpty()){
					I i135= new I(current.name,"",135L,0L,id,site);
					i135.setModifyTime(now);
					mgr.makePersistent(i135);
				}
				else{
					I i=r135.get(0);
					i.setModifyTime(now);
					mgr.makePersistent(i);
				}
				I135 i135=new I135(id,site,Long.parseLong(s[3]),Long.parseLong(s[4]),t);
				mgr.makePersistent(i135);

				List<I> r136=(List<I>)qi.execute(id,site,136);
				if(r136.isEmpty()){
					I i136= new I(current.name,"",136L,0L,id,site);
					i136.setModifyTime(now);
					mgr.makePersistent(i136);
				}
				else{
					I i=r136.get(0);
					i.setModifyTime(now);
					mgr.makePersistent(i);
				}
				I136 i136=new I136(id,site,Long.parseLong(s[5]),t);
				mgr.makePersistent(i136);

				List<I> r139=(List<I>)qi.execute(id,site,139);
				if(r136.isEmpty()){
					I i139= new I(current.name,"",139L,0L,id,site);
					i139.setModifyTime(now);
					mgr.makePersistent(i139);
				}
				else{
					I i=r139.get(0);
					i.setModifyTime(now);
					mgr.makePersistent(i);
				}
				I139 i139=new I139(id,site,Long.parseLong(s[6]),t);
				mgr.makePersistent(i139);
			} 
			catch(ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				mgr.close();
			}
		}
		//I139.updatePost(i,139,html(i,false,mgr),mgr);
		r.close();
		rsp.sendRedirect("/"+id+"."+site+"/");
		return true;
	}
	public void updatePost(Timed id,long type,String html,
		PersistenceManager mgr){
		Query q=mgr.newQuery(I.class);
		q.setFilter("o==oParam && w==wParam && a==aParam");
		q.declareParameters("Long oParam,Long wParam,Long aParam");
		try{
			@SuppressWarnings("unchecked")
			List<I> r=(List<I>)q.execute(id.o,id.w,139);
			I i;
			if(r.isEmpty()){
				i=new I(html,null,type,0,id.o,id.w);
				mgr.makePersistent(i);
				i.setId(mgr);
			}
			else{
				i=r.get(0);
				i.setQuotation(html);
				i.setModifyTime(new Date());
				mgr.makePersistent(i);
			}
		}
		finally{
			q.closeAll();
		}
	}
}