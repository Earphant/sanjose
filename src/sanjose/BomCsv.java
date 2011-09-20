package sanjose;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BomCsv{
	private static final Logger log=Logger.getLogger(BomCsv.class.getName());
	private I own;
	private PersistenceManager mgr;
	private String[] head;

	private boolean postLine(String line)throws ParseException{
		if(line==null)
			return false;
		String[]s=line.split(" ");
		long qty=0;
		String ref=null;
		String val=null;
		for(int i=s.length;i>0;){
			String a=head[--i];
			String v=s[i];
			if(a.equalsIgnoreCase("qty"))
				qty=Long.parseLong(v);
			else if(a.equalsIgnoreCase("ref"))
				ref=v;
			else if(a.equalsIgnoreCase("val"))
				val=v;
		}
		return true;
	}
	private boolean prepare(String line,I owner)throws IOException{
		if(line==null)
			return false;
		boolean qty=false;
		boolean val=false;
		head=line.split(",");
		for(int i=head.length;i>0;){
			String a=head[--i];
			if(a.equalsIgnoreCase("part reference"))
				head[i]="ref";
			if(a.equalsIgnoreCase("quantity")){
				head[i]="qty";
				qty=true;
			}
			if(a.equalsIgnoreCase("reference"))
				head[i]="ref";
			if(a.equalsIgnoreCase("value")){
				head[i]="val";
				val=true;
			}
			if(a.equalsIgnoreCase("qty"))
				qty=true;
			if(a.equalsIgnoreCase("val"))
				val=true;
		}
		if(qty && val){
			log.warning("Ok");
			mgr=Helper.getMgr();
			own=owner;
			return true;
		}
		return false;
	}
	private void unprepare(){
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
		if(s.length>2 && prepare(s[0],owner)){
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
		return ret;
	}
}