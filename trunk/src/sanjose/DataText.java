package sanjose;

import java.io.IOException;
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
	private String[] head;

	private boolean checkType(String line){
		if(line==null)
			return false;
		return line.trim().equalsIgnoreCase("<!doctype palo-alto>");
	}
	private boolean postLine(String line)throws ParseException{
		if(line==null)
			return false;
		try{
			Date time;
			long v135=0x80000000;
			long v145=0x80000000;
			long v136=0x80000000;
			long v138=0x80000000;
			long v139=0x80000000;
			line=line.trim();
			time=fmt.parse(line);
			String[]s=line.split(" ");
			for(int i=s.length;i>0;){
				String a=head[--i];
				long v;
				try{
					v=Long.parseLong(s[i]);
					if(a.equalsIgnoreCase("fat"))
						v135=v;
					else if(a.equalsIgnoreCase("heart-rate"))
						v136=v;
					else if(a.equalsIgnoreCase("water"))
						v145=v;
					else if(a.equalsIgnoreCase("weight"))
						v138=v;
					else if(a.equalsIgnoreCase("step"))
						v139=v;
				}
				catch(NumberFormatException e){
				}
			}
			if(v135!=0x80000000 && v145!=0x80000000)
				mgr.makePersistent(new I135(own,time,v135,v145));
			if(v136!=0x80000000)
				mgr.makePersistent(new I136(own,time,v136));
			if(v138!=0x80000000)
				mgr.makePersistent(new I138(own,time,v138));
			if(v139!=0x80000000)
				mgr.makePersistent(new I139(own,time,v139));
		}
		catch(ParseException e){
			e.printStackTrace();
		}
		return true;
	}
	private boolean prepare(String line,I owner)throws IOException{
		log.warning("prep");
		if(line==null)
			return false;
		head=line.split("\\s");
		mgr=Helper.getMgr();
		own=owner;
		return true;
	}
	private void unprepare(){
		log.warning("unprep");
		for(String i:head){
			log.warning(i);
			if(i.equalsIgnoreCase("heart-rate"))
				updatePost(own,136,getHtml(own,I136.class,null,28,mgr),"Heart Rate","heart-rate",mgr);
			else if(i.equalsIgnoreCase("weight"))
				updatePost(own,138,getHtml(own,I138.class,null,28,mgr),"Weight","weight",mgr);
			else if(i.equalsIgnoreCase("step"))
				updatePost(own,139,getHtml(own,I139.class,null,28,mgr),"Steps","steps",mgr);
		}
		mgr.close();
	}

	@SuppressWarnings("rawtypes")
	protected String getHtml(I owner,Class cls,String post,long length,
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
			ret=new Graph().html(r,post==null?null:post,
				new Date().getTime()/1000,length,86400);
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
	protected void updatePost(I owner,long type,String html,String text,
		String plink,PersistenceManager mgr){
		Query q=mgr.newQuery(I.class);
		q.setFilter("o==oParam && w==wParam && a==aParam");
		q.declareParameters("Long oParam,Long wParam,Long aParam");
		try{
			@SuppressWarnings("unchecked")
			List<I> r=(List<I>)q.execute(owner.getId(),owner.getSite(),type);
			I i;
			if(r.isEmpty()){
				i=I.store(text,plink,type,0,owner,mgr,false);
			}
			else{
				i=r.get(0);
				i.setText(text);
				i.setPlink(plink);
				i.setModifyTime(new Date());
			}
			i.setQuotation(html);
			mgr.makePersistent(i);
		}
		finally{
			q.closeAll();
		}
	}

	public boolean doPost(HttpServletRequest req,HttpServletResponse rsp,
		byte[] bytes,I owner)throws IOException{
		boolean ret=false;
		String v=new String(bytes);
		String[]s=v.split("\n");
		if(s.length>2 && checkType(s[0])){
			if(prepare(s[1],owner)){
				try{
					for(String i:s)
						postLine(i);
					rsp.sendRedirect("/"+owner+"/");
					ret=true;
				}
				catch(ParseException e){
					e.printStackTrace();
				}
				finally{
					unprepare();
				}
			}
		}
		return ret;
	}
}