package sanjose;

import java.io.*;
import java.util.*;
import javax.cache.*;
import javax.jdo.*;

public final class Helper{
	private static final PersistenceManagerFactory pi=
		JDOHelper.getPersistenceManagerFactory("transactions-optional");

	public Cache				mem=null;
	public PersistenceManager	mgr=null;

	public static PersistenceManager getMgr(){
	    return pi.getPersistenceManager();
	}
	//@SuppressWarnings("unchecked")
	public static Cache get_mem(){
		//try{
			//Map p=new HashMap();
	        //p.put(GCacheFactory.EXPIRATION_DELTA,57600);
			//return CacheManager.getInstance().getCacheFactory().createCache(p);
		//}catch(CacheException e){
			//e.printStackTrace();
		//}
		return null;
	}

//	@SuppressWarnings("unchecked")
	public I get_item(String pnk){
		I i;
		//mem_open();
		String v=(String)mem.get(pnk);
		if(v==null){
			mgr_open();
			i=(I)mgr.getObjectById(I.class,pnk);
//			mem.put(pnk,i.getval()+","+i.gettxt());
			return i;
		}
		//String s[];
		//s=v.split(",");
//		i=new I(pnk,s[8],0);
//		i.setval(v);
		return null;
	}
//	@SuppressWarnings("unchecked")
	public String get_val(I i,Date t,int c)throws IOException{
		String	r="";
		/*
		String	p=i.getpnk();
		String	r=i.getval();
		mgr_open();
		List<v80>s=(List<v80>)mgr.newQuery("SELECT FROM "+v80.class.getName()+
			" WHERE p=='"+p+"' ORDER BY t DESC RANGE 0,7").execute();
        if(s.iterator().hasNext()){
			Calendar a=new GregorianCalendar();
			Calendar b=Calendar.getInstance();
			v80 	 o=s.get(0);
			a.setTime(o.gettme());
			if(b.getTimeInMillis()-a.getTimeInMillis()>36000000)
				fetch_val(p,o);
			for(v80 k:s)r+=";"+k.getval();
        }
        else r=fetch_val(p,null);
        */
		return r;
	}
	public void mgr_close(){
		if(mgr!=null)mgr.close();
	}
	public void mgr_open(){
		if(mgr==null)mgr=getMgr();
	}
	/*
	@SuppressWarnings("unchecked")
	public List<i80>list_item(String bas){
		mgr_open();
		if(!bas.equals(""))bas=" WHERE b=='"+bas+"'";
		return (List<i80>)mgr.newQuery("SELECT FROM "+i80.class.getName()+bas+
			" ORDER BY chg DESC RANGE 0,50").execute();
	}
	public void mem_open(){
		if(mem==null)mem=get_mem();
	}
	public boolean net_item(i80 i){
		return false;
	}
	public void out(String text)throws IOException{
		rsp.getWriter().print(text);
	}
	*/
}