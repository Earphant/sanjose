package sanjose;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DataText{
	private static final Logger log=Logger.getLogger(Upload.class.getName());
	private SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private I own;
	private PersistenceManager mgr;
	private boolean checkType(String line){
		if(line==null)
			return false;
		return line.trim().equalsIgnoreCase("<!doctype palo-alto>");
	}
	private boolean postLine(String line)throws ParseException{
		if(line==null)
			return false;
		Date t=fmt.parse(line);
		String[]s=line.split(" ");
		mgr.makePersistent(new I135(own,t,Long.parseLong(s[3]),Long.parseLong(s[4])));
		mgr.makePersistent(new I136(own,t,Long.parseLong(s[5])));
		mgr.makePersistent(new I138(own,t,Long.parseLong(s[2])));
		mgr.makePersistent(new I139(own,t,Long.parseLong(s[6])));
		return true;
	}
	private boolean prepare(String line,I owner)throws IOException{
		log.warning("prep");
		if(line==null)
			return false;
		mgr=Helper.getMgr();
		own=owner;
		return true;
	}
	private void unprepare(){
		log.warning("unprep");
		updatePost(own,136,getHtml(own,I136.class,null,mgr),mgr);
		updatePost(own,138,getHtml(own,I138.class,null,mgr),mgr);
		updatePost(own,139,getHtml(own,I139.class,null,mgr),mgr);
		mgr.close();
	}

	@SuppressWarnings("rawtypes")
	protected String getHtml(I owner,Class cls,String post,
		PersistenceManager mgr){
		String ret=null;
		Query q=mgr.newQuery(cls);
		q.setFilter("o==oParam && w==wParam");
		q.declareParameters("Long oParam,Long wParam");
		q.setOrdering("t");
		try{
			@SuppressWarnings("unchecked")
			List<Single> r=(List<Single>)q.execute(owner.getId(),
				owner.getSite());
			ret=new Graph().html(r,post==null?null:post,0,0,86400);
		}
		finally{
			q.closeAll();
		}
		return ret;
	}
	@SuppressWarnings("rawtypes")
	protected long getSingleVal(I i,Class cls){
		long ret=0;
		Query q=Helper.getMgr().newQuery(cls);
		q.setFilter("o==oParam && w==wParam && t==tParam");
		q.declareImports("import java.util.Date");
		q.declareParameters("Long oParam,Long wParam,Date tParam");
		try{
			@SuppressWarnings("unchecked")
			List<Single> r=(List<Single>)q.execute(i.getId(),i.getSite(),
				i.getCreateTime());
			if(!r.isEmpty())
				ret=r.get(0).getVal();
		}
		finally{
			q.closeAll();
		}
		return ret;
	}
	protected void updatePost(I owner,long type,String html,
		PersistenceManager mgr){
		Query q=mgr.newQuery(I.class);
		q.setFilter("o==oParam && w==wParam && a==aParam");
		q.declareParameters("Long oParam,Long wParam,Long aParam");
		try{
			@SuppressWarnings("unchecked")
			List<I> r=(List<I>)q.execute(owner.getId(),owner.getSite(),type);
			I i;
			if(r.isEmpty()){
				i=new I("",null,type,0,owner);
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

	public boolean doPost(HttpServletRequest req,HttpServletResponse rsp,
		InputStream stream,I owner)throws IOException{
		BufferedReader rd=new BufferedReader(new InputStreamReader(stream));
		if(checkType(rd.readLine())){
			if(prepare(rd.readLine(),owner)){
				try {
					while(postLine(rd.readLine()));
				} 
				catch(ParseException e){
					e.printStackTrace();
				}
				finally{
					unprepare();
				}
			}
		}
		rd.close();
		rsp.sendRedirect("/"+owner+"/");
		return false;
	}
}